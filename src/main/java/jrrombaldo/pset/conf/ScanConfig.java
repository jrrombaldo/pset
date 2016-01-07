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
package jrrombaldo.pset.conf;

public class ScanConfig {

	private static ScanConfig instance = null;

	protected ScanConfig() {
		// defeat instantiation.
	}

	public static ScanConfig get() {
		if (instance == null) {
			instance = new ScanConfig();
		}
		return instance;
	}

	// performance improvements
	public int dork_max = 100;
	public int page_max_empty = 25;
	public int page_max_num = 9999;
	public int page_size = 10;

	// proxy usage
	public boolean useProxy = false;
	public String proxy = "localhost";
	public int proxyPort = 8080;

	// have to force this user agent header to for force the search engine
	// answer non JavaScript "obfuscated" content
	public String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)";

}
