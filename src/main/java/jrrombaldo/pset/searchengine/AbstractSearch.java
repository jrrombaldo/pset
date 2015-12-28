package jrrombaldo.pset.searchengine;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import jrrombaldo.pset.conf.ScanConfig;

public abstract class AbstractSearch {

    // have to force this user agent header to for force the search engine
    // answer non JavaScript "obfuscated" content
    protected String _userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)";

    // search engine specifics
    protected String _searchEngineURL;
    protected String _regex;
    protected String _dork_include;
    protected String _dork_exclude;
    protected String _captchaExempltionCookie;

    // scan setup
    protected String _targetDomain;
    protected Set<String> _subDomainsFound;

    public AbstractSearch(String targetDomain) {
        this._targetDomain = targetDomain;
        this._subDomainsFound = new HashSet<String>();

        // ensure that cookies will be persisted
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }

    abstract void handleCaptcha(URL url, int httpStatusCode, String content)
            throws MalformedURLException, IOException, URISyntaxException, Exception;

    protected String makeURL(int page) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(this._dork_include);
        sb.append(this._targetDomain);

        int count = 0;
        for (String subDomain : this._subDomainsFound) {
            sb.append("+");
            sb.append(this._dork_exclude);
            sb.append(subDomain);
            count++;
            if (count >= ScanConfig.get()._dork_max) {
                break;
            }
        }
        return MessageFormat.format(this._searchEngineURL, sb.toString(), page);
    }

    protected HttpURLConnection getHTTPConnection(URL url) throws MalformedURLException, Exception, IOException {
        URLConnection urlConnection;

        // ignoring SSL server certificate
        confidInavlidSSL();

        if (ScanConfig.get()._useProxy) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ScanConfig.get()._proxy, ScanConfig.get()._proxyPort));

            urlConnection = url.openConnection(proxy);
        } else {
            urlConnection = url.openConnection();
        }
        urlConnection.addRequestProperty("User-agent", this._userAgent);

        HttpURLConnection httpConnection = ((HttpURLConnection) urlConnection);
        // httpConnection.setInstanceFollowRedirects(false);

//        int httpStatusCode = httpConnection.getResponseCode();
//        int httpLength = httpConnection.getContentLength();
//        System.out.println(MessageFormat.format("Requesting - [{0}] - {1}", httpStatusCode, url));

        return httpConnection;
    }

    protected String parsetHTTPContent(int pageNumber) throws Exception {
        URL url = new URL(makeURL(pageNumber));
        HttpURLConnection httpConnection = getHTTPConnection(url);

        int httpStatusCode = httpConnection.getResponseCode();

        String line;

        StringBuilder sb = new StringBuilder();
        BufferedReader br;
        try {
            if (httpStatusCode > 400 && httpStatusCode != 404 ) {
                br = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            }

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            // most likely the search engine is asking for capatcha
            if (httpStatusCode == 503) {
                handleCaptcha(url, httpStatusCode, sb.toString());
            }

        } catch (IOException e) {
            // System.out.println("minor error, still processing... " +
            // e.getLocalizedMessage());
            e.printStackTrace();
        }

        return sb.toString();
    }

    protected int extractDomains(int pageNumber) throws Exception {
        String content = parsetHTTPContent(pageNumber);

        Pattern pattern = Pattern.compile(this._regex);
        Matcher matcher = pattern.matcher(content);

        int sizeBefore = this._subDomainsFound.size();

        while (matcher.find()) {
            String fnd = matcher.group();

            // clean up subdomain found
            fnd = fnd.replaceFirst("://", "").replace(this._targetDomain, "");

            if (fnd.startsWith("3A")) {
                fnd = fnd.replaceFirst("3A", "");
            }
            if (fnd.startsWith("3a")) {
                fnd = fnd.replaceFirst("3a", "");
            }
            this._subDomainsFound.add(fnd+this._targetDomain);
        }

        int founds = (this._subDomainsFound.size() - sizeBefore);
        System.out.println(MessageFormat.format("found:[{0}]", founds));
        return founds;
    }

    public Set<String> listSubdomains() throws Exception {
        int total;
        do {
            total = extractDomains(0);
            /*
			 * if not found any new subdomain on current page, browse next pages
			 * until reach maximum of empty pages set.
             */
            if (total == 0) {
                int emptyPage = 0;
                for (int pg = 0; pg < ScanConfig.get()._page_max_num && emptyPage < ScanConfig.get()._page_max_empty; pg++) {
                    total = extractDomains(pg * ScanConfig.get()._page_size);
                    // if found, reset counter
                    if (total == 0) {
                        emptyPage++;
                    } else {
                        emptyPage = 0;
                    }
                }
            }

        } while (total > 0);

        return this._subDomainsFound;
    }

    // used to download captcha images
    protected void downloadImage(String strURL, String imagePath) throws MalformedURLException, IOException, Exception {
        HttpURLConnection imageUrlConnection;
        imageUrlConnection = this.getHTTPConnection(new URL(strURL));

        InputStream is = new BufferedInputStream(imageUrlConnection.getInputStream());
        BufferedImage image = ImageIO.read(is);

        ImageIO.write(image, "jpg", new File(imagePath));
    }

    protected void setProxy(String proxy, int port) {
        ScanConfig.get()._useProxy = true;
        ScanConfig.get()._proxy = proxy;
        ScanConfig.get()._proxyPort = port;
    }

    protected void confidInavlidSSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }

        }, new SecureRandom());

        SSLContext.setDefault(ctx);
    }

}
