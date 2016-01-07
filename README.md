###PSET
PSET stands for Passive Subdomain Enumeration Tool, which goal is: given a domain PSET will enumerate all subdomains passively by probing search engines such as Goole.com and Bing.com. PSET will never directly probe any DNS and/or host on target domain, making enumeration scans 100% stealth and undetectable.

PSET stands out among other enumeration tools because of the capacity to automate queries on Google and Bing. In past years several similar tools stop working because search engine getting more and more strict with searching automation tools. PSET is able to bypass these restrictions by manipulating requests and removing complex JavaScript on responses.  

[Download latest version](https://github.com/jrrombaldo/pset/blob/master/pset-0.1.jar)

####Running
Basically PSET can run using a java GUI or from CLI. Keep in mind that even on CLI mode it's opens an popup with captcha images requested for some search engine when  run PSET multiple times, so in case you are on CLI only environment you wont't be able clear captchas. 

1. To run GUI mode it's just execute: `java -jar pset-0.1.jar `
2. To run CLI mode execute the same jar passing -c param `java -jar pset-0.1.jar -c`. 

Here is an example of possible parameters:  
`java -jar pset-0.1.jar -h`  
`usage: java -jar pset.jar [at least on search engine] target.com`  
` -b,--bing           Use bing search engine`  
` -c,--cli            Use CLI mode, otherwise will start GUI mode`  
` -d,--domain <arg>   Target domain`  
` -g,--google         Use google search engine`  
` -h,--help           Show usage help`  
` -p,--proxy <arg>    Use HTTP proxy format: host:port`  



####Download and compiling

In order to download and compile PSET you must have [apache maven](https://maven.apache.org/download.cgi), [git](https://git-scm.com/downloads) and [java development kit 7](www.oracle.com/technetwork/java/javase/downloads/) installed, as they are required to build/compile the source code into jar file.

1- Download the latest version from github:   
 `git clone https://github.com/jrrombaldo/pset`

2- Compile and build the jar:  
 `cd pset && mvn clean install`


It's done, the final jar will at `pset/pset-0.1.jar`, follow the steps to execute it.

