package jrrombaldo.websearch;

import java.io.BufferedReader;
import java.io.IOException;
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
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

	// performance improvements
	protected int _dork_max = 30;
	protected int _page_max_empty = 2;
	protected int _page_max_num = 3;
	protected int _pasge_size = 10;

	// proxy usage
	protected boolean _useProxy;
	protected String _proxy;
	protected int _proxyPort;

	// scan setup
	protected String _targetDomain;
	protected Set<String> _subDomainsFounded;

	public AbstractSearch(String targetDomain) {
		this._targetDomain = targetDomain;
		this._subDomainsFounded = new HashSet<String>();
	}

	abstract void handleCaptcha(URL url, int httpStatusCode, int httpLength, String content) throws MalformedURLException, IOException, URISyntaxException, Exception;

	protected String makeURL(final int page) throws Exception {
		final StringBuilder sb = new StringBuilder();
		sb.append(this._dork_include);
		sb.append(this._targetDomain);

		int count = 0;
		for (final String subDomain : this._subDomainsFounded) {
			sb.append("+");
			sb.append(this._dork_exclude);
			sb.append(subDomain);
			count++;
			if (count >= this._dork_max) {
				break;
			}
		}
		return MessageFormat.format(this._searchEngineURL, sb.toString(), page);
	}

	protected HttpURLConnection getHTTPConnection(URL url) throws MalformedURLException, Exception, IOException {
		URLConnection urlConnection;
		
		
		
		if (this._useProxy) {
			final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this._proxy, this._proxyPort));

			urlConnection = url.openConnection(proxy);
		} else {
			urlConnection = url.openConnection();
		}
		urlConnection.addRequestProperty("User-agent", this._userAgent);
//		urlConnection.addRequestProperty("Cookie", this._captchaExempltionCookie);
		
		HttpURLConnection conn =  ((HttpURLConnection) urlConnection);
		List<String>cookies = conn.getHeaderFields().get("Set-Cookie");
		if (cookies != null)
			this._captchaExempltionCookie = conn.getHeaderFields().get("Set-Cookie").get(0);

		return conn;
	}

	protected String parsetHTTPContent(final int pageNumber) throws Exception {
		confidInavlidSSL();
		final URL url = new URL(makeURL(pageNumber));
		final HttpURLConnection httpConnection = getHTTPConnection(url);

		// httpConnection.setInstanceFollowRedirects(false);

		int httpStatusCode = httpConnection.getResponseCode();
		int httpLength = httpConnection.getContentLength();

		String line;

		StringBuilder sb = new StringBuilder();
		BufferedReader br;
		try {
			if (httpStatusCode > 400) {
				br = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			}

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			// most likely the search engine is asking for capatcha
			if (httpStatusCode == 503) {
				handleCaptcha(url, httpStatusCode, httpLength, sb.toString());
			}

		} catch (final IOException e) {
			// System.out.println("minor error, still processing... " +
			// e.getLocalizedMessage());
			e.printStackTrace();
		}

		System.out.println(new StringBuilder().append("\t -> ").append(" ContentLength: ").append(httpLength)
				.append("\tStatusCode: ").append(httpStatusCode).append("\t-\t").append(url));

		return sb.toString();
	}

	protected int extractDomains(final int pageNumber) throws Exception {
		final String content = parsetHTTPContent(pageNumber);
		// System.out.println(new StringBuilder().append("regex
		// ->").append(this.regex).toString());

		final Pattern pattern = Pattern.compile(this._regex);
		final Matcher matcher = pattern.matcher(content);

		final int sizeBefore = this._subDomainsFounded.size();

		while (matcher.find()) {
			String fnd = matcher.group();

			// clean up subdomain founded
			fnd = fnd.replaceFirst("://", "").replace(this._targetDomain, "");

			if (fnd.startsWith("3A")) {
				fnd = fnd.replaceFirst("3A", "");
			}
			if (fnd.startsWith("3a")) {
				fnd = fnd.replaceFirst("3a", "");
			}
			this._subDomainsFounded.add(fnd);
		}

		final int founds = (this._subDomainsFounded.size() - sizeBefore);
		System.out.println("\t found : " + founds);
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
				for (int pg = 0; pg < this._page_max_num && emptyPage < this._page_max_empty; pg++) {
					total = extractDomains(pg * this._pasge_size);
					// if found, reset counter
					if (total == 0) {
						emptyPage++;
					} else {
						emptyPage = 0;
					}
				}
			}

		} while (total > 0);

		return this._subDomainsFounded;
	}

	protected void setProxy(String proxy, int port) {
		this._useProxy = true;
		this._proxy = proxy;
		this._proxyPort = port;
	}

	protected void confidInavlidSSL() throws NoSuchAlgorithmException, KeyManagementException {
		final SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0], new TrustManager[] { new X509TrustManager() {
			public void checkClientTrusted(final X509Certificate[] arg0, final String arg1)
					throws CertificateException {
			}

			public void checkServerTrusted(final X509Certificate[] arg0, final String arg1)
					throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		}

		}, new SecureRandom());

		SSLContext.setDefault(ctx);
	}

}
