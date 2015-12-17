package jrrombaldo.websearch;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class GoogleSearch extends AbstractSearch {

	public GoogleSearch(String targetDomain) {
		super(targetDomain);

		this._searchEngineURL = "https://www.google.com.br/search?start={1}&q={0}";
		this._dork_include = "site:";
		this._dork_exclude = "-inurl:";
		this._regex = "://[\\d\\w_-]+\\." + targetDomain;
	}

	@Override
	void handleCaptcha(URL url, int httpStatusCode, int httpLength, String content) throws Exception {
		// System.out.println(httpLength);
		// System.out.println(httpStatusCode);
		// System.out.println(content);

		String id = "";
		Matcher matcher = Pattern.compile("/sorry/image\\?id\\=\\d+\\&").matcher(content);
		if (matcher.find()) {
			id = matcher.group().replace("/sorry/image?id=", "").replace("&", "");
			System.out.println("\t handling captcha  please type the image opened at Broswer...");

			HttpURLConnection conn4Cookie;
			conn4Cookie = this.getHTTPConnection(new URL("http://ipv4.google.com/sorry/image?id=" + id + "&hl=en"));
			
//			Image iamge =   (Image)conn4Cookie.getContent();
			
			
			InputStream is = new BufferedInputStream(conn4Cookie.getInputStream());
			BufferedImage image = ImageIO.read(is);
		    
		    //BufferedImage bimage = new BufferedImage(image.getWidth(image), image.getHeight(image), BufferedImage.TYPE_INT_RGB);

		    ImageIO.write(image, "jpg",new File("/Users/junior/Desktop/test.jpg"));

			
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn4Cookie.getInputStream()));
//			File f = new File("/Users/junior/Desktop/test.jpg");
//			FileWriter fr = new FileWriter(f);
//			BufferedWriter bw  = new BufferedWriter(fr);
//			String line;
//			while ((line = br.readLine()) != null) {
//				bw.write(line);
//			}
//			bw.flush();
//			bw.close();

			//Desktop.getDesktop().browse(new URL("http://ipv4.google.com/sorry/image?id=" + id + "&hl=en").toURI());

			Scanner scan = new Scanner(System.in);
			String captcha = scan.nextLine();
			// scan.close();

			StringBuilder sbUrl = new StringBuilder();

			sbUrl.append("https://ipv4.google.com/sorry/CaptchaRedirect?").append("continue=")
					.append(URLEncoder.encode(url.toString(), "UTF-8")).append("&id=").append(id).append("&captcha=")
					.append(captcha).append("&submit=Submit");

			HttpURLConnection httpConnection = this.getHTTPConnection(new URL(sbUrl.toString()));
			System.out.println(httpConnection.getResponseCode());

		}

		// action=CaptchaRedirect
		// method=get
		// continue=url
		// id=id
		// captcha=image value
		// submit="Submit

	}

	public static void main(final String[] args) {
		try {
			CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL ) );
			String domain = "uol.com.br";

			final GoogleSearch gs = new GoogleSearch(domain);

			gs.setProxy("localhost", 8989);

			int q = 1;
			for (final String s : gs.listSubdomains()) {
				if (q == 1) {
					System.out.println("\nResults:");
				}
				System.out.println(q + ": " + s + domain);
				q++;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
