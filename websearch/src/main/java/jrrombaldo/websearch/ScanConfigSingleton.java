package jrrombaldo.websearch;

public class ScanConfigSingleton {

	private static ScanConfigSingleton instance = null;

	protected ScanConfigSingleton() {
		// defeat instantiation.
	}

	public static ScanConfigSingleton get() {
		if (instance == null) {
			instance = new ScanConfigSingleton();
		}
		return instance;
	}
	
	
	// performance improvements
		public String dork_max = "30";
		public String page_max_empty = "2";
		public String page_max_num = "3";
		public String page_size = "10";

		// proxy usage
		public boolean _useProxy = false;
		public String _proxy = "localhost";
		public int _proxyPort = 8080;
	
	
	

}
