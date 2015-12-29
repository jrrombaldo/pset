/*******************************************************************************
 * Copyright (c) 2015 jrrombaldo.
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
    public int _dork_max = 100;
    public int _page_max_empty = 25;
    public int _page_max_num = 9999;
    public int _page_size = 10;

    // proxy usage
    public boolean _useProxy = false;
    public String _proxy = "localhost";
    public int _proxyPort = 8080;

}
