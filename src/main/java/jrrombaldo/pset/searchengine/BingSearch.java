package jrrombaldo.pset.searchengine;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class BingSearch extends AbstractSearch {

	public BingSearch(String targetDomain) {
		super(targetDomain);

		this._searchEngineURL = "http://br.bing.com/search?first={1}&q={0}";
		this._dork_include = "site:";
		this._dork_exclude = "-site:";
		this._regex = "://[\\d\\w_-]+\\." + targetDomain;

	}

	@Override
	void handleCaptcha(URL url, int httpStatusCode, String content)
			throws MalformedURLException, IOException, URISyntaxException, Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not implemented at this time!");
	}

	public static void main(String[] args) {
		try {

			String domain = "uol.com.br";

			BingSearch bs = new BingSearch(domain);

			// gs.setProxy("localhost", 8989);

			int q = 1;
			for (String s : bs.listSubdomains()) {
				if (q == 1) {
					System.out.println("\nResults:");
				}
				System.out.println(q + ": " + s + domain);
				q++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
