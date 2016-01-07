/*******************************************************************************
 * Copyright (c) 2016 Jr.Rombaldo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jr.Rombaldo
 *******************************************************************************/
package jrrombaldo.pset.searchengine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GoogleSearch extends AbstractSearch {

	String _captcha_img_url;
	String _captcha_regex;
	String _captcha_check_url;

	public GoogleSearch(String targetDomain) {
		super(targetDomain);

		this._searchEngineURL = "https://www.google.com.br/search?start={1}&q={0}";
		this._dork_include = "site:";
		this._dork_exclude = "-site:";
		this._regex = "://[\\d\\w_-]+\\." + targetDomain;

		this._captcha_img_url = "http://ipv4.google.com/sorry/image?id={0}&hl=en";
		this._captcha_regex = "/sorry/image\\?id\\=\\d+\\&";
		this._captcha_check_url = "https://ipv4.google.com/sorry/CaptchaRedirect?continue={0}&id={1}&captcha={2}&submit=Submit";

	}

	@Override
	void handleCaptcha(URL url, int httpStatusCode, String content) throws Exception {
		String imagePath = "/Users/junior/Desktop/test.jpg";

		String id = "";
		Matcher matcher = Pattern.compile(this._captcha_regex).matcher(content);
		if (matcher.find()) {
			id = matcher.group().replace("/sorry/image?id=", "").replace("&", "");

			this.downloadImage(MessageFormat.format(this._captcha_img_url, id), imagePath);

			String captcha = askUserForCaptcha(imagePath);

			String captchaURL = MessageFormat.format(this._captcha_check_url,
					URLEncoder.encode(url.toString(), "UTF-8"), id, captcha);

			// submitting captcha validation and closing connection
			HttpURLConnection conn = this.getHTTPConnection(new URL(captchaURL));
			conn.getResponseCode();
			conn.disconnect();
		}
	}

	String askUserForCaptcha(String imagePath) throws IOException {
		// System.out.println("\t #\n\t ### captcha required, please type the
		// value of [" + imagePath + "]\n\t #");
		// @SuppressWarnings("resource") // will be a GUI filed
		// Scanner scan = new Scanner(System.in);
		// return scan.nextLine();

		BufferedImage captchaBuff = ImageIO.read(new File(imagePath));
		JLabel captahImage = new JLabel(new ImageIcon(captchaBuff));

		String captchaValue = JOptionPane.showInputDialog(null, captahImage, "Captcha required",
				JOptionPane.PLAIN_MESSAGE);
		System.out.println(captchaValue);

		return captchaValue;

	}

	public static void main(String[] args) {
		try {

			String domain = "uol.com.br";

			GoogleSearch gs = new GoogleSearch(domain);

			gs.setProxy("localhost", 8989);
			int q = 1;
			for (String s : gs.listSubdomains()) {
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
