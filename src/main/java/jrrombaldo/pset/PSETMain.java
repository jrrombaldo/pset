package jrrombaldo.pset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import jrrombaldo.pset.gui.WebSearchMainForm;
import jrrombaldo.pset.searchengine.BingSearch;
import jrrombaldo.pset.searchengine.GoogleSearch;

public class PSETMain {

	public static void main(String[] args) {

		Options options = prepareOptions();
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(options, args);
			
			String domain = line.getOptionValue("d");

			//print help
			if (line.hasOption("h")) {
				printHelp(options);
				return;
			}
			
			//start gui e do nothing else
			if (! line.hasOption("c")) {
				startGuiVersion(domain);
				return;
			}
			
			
			if (!line.hasOption("g") && !line.hasOption("b")){
				System.out.println("No search engine selected, at least one should be present");
				printHelp(options);
				return;
			}
			
			if (line.hasOption("p")){
				String proxy = line.getOptionValue("p");
				System.out.println(proxy);
			}
			
			
			Set<String> results = new HashSet<>();
		
			if (line.hasOption("g")){
				results.addAll(new GoogleSearch(domain).listSubdomains());
			}
			
			if (line.hasOption("b")){
				results.addAll(new BingSearch(domain).listSubdomains());
			}
			
			
			 List<String> sortedResult = new ArrayList<String>(results);
             Collections.sort(sortedResult);
             int q =1;
             for (String subDomain : sortedResult) {
            	 if (q == 1) {
                     System.out.println("\nResults:");
                 }
                 System.out.println(q + ": "+subDomain);
                 q++;
             }
			
		
		} catch (ParseException exp) {
			System.out.println(exp.getLocalizedMessage());
			printHelp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Options prepareOptions() {
		Options options = new Options();

		Option help = new Option("h", "help", false, "Show usage help");
		options.addOption(help);

		Option cli = new Option("c", "cli", false, "Use CLI mode, otherwise will start GUI mode");
		options.addOption(cli);

		// OptionGroup searchEngine = new OptionGroup();
		Option google = new Option("g", "google", false, "Use google search engine");
		Option bing = new Option("b", "bing", false, "Use bing search engine");
		// searchEngine.addOption(google).addOption(bing);
		// searchEngine.setRequired(true);
		// options.addOptionGroup(searchEngine);
		options.addOption(google).addOption(bing);
		
		
		Option domain = new Option("d", "domain", true, "Target domain");
		domain.setRequired(true);
		options.addOption(domain);

		Option proxy = new Option("p", "proxy", true, "Use HTTP proxy format: host:port");
		options.addOption(proxy);

		return options;
	}

	private static void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("java -jar pset.jar [at least on search engine] target.com", options);
	}

	private static void startGuiVersion(String domain) {

		/* Set the Nimbus look and feel */
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {

			ex.printStackTrace();
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			new WebSearchMainForm(domain).setVisible(true);
		});
	}

}
