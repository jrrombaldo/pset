package jrrombaldo.set.conf;

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
    public int _page_max_num = 100;
    public int _page_size = 30;

    // proxy usage
    public boolean _useProxy = false;
    public String _proxy = "localhost";
    public int _proxyPort = 8080;

}
