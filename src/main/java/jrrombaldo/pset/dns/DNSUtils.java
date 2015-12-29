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
package jrrombaldo.pset.dns;
//package jrrombaldo.pset.dns;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import org.xbill.DNS.ARecord;
//import org.xbill.DNS.DClass;
//import org.xbill.DNS.ExtendedResolver;
//import org.xbill.DNS.Flags;
//import org.xbill.DNS.Lookup;
//import org.xbill.DNS.Message;
//import org.xbill.DNS.Name;
//import org.xbill.DNS.Record;
//import org.xbill.DNS.Resolver;
//import org.xbill.DNS.ReverseMap;
//import org.xbill.DNS.Section;
//import org.xbill.DNS.SimpleResolver;
//import org.xbill.DNS.Type;
//import org.xbill.DNS.WireParseException;
//import org.xbill.DNS.ZoneTransferException;
//import org.xbill.DNS.ZoneTransferIn;
//import org.xbill.DNS.ZoneTransferIn.ZoneTransferHandler;
//
//public class DNSUtils {
//
//	protected String[] servers;
//	protected Resolver resolver;
//
//	public DNSUtils() throws UnknownHostException {
//		resolver = new SimpleResolver();
//	}
//
//	public void setDNSServer(String[] dnsServers) throws UnknownHostException {
//		this.servers = dnsServers;
//		resolver = new ExtendedResolver(this.servers);
//	}
//
//	public String reverseDns(String hostIp) throws Exception {
//
//		Name name = ReverseMap.fromAddress(hostIp);
//		int type = Type.PTR;
//		int dclass = DClass.IN;
//		Record rec = Record.newRecord(name, type, dclass);
//		Message query = Message.newQuery(rec);
//		Message response = resolver.send(query);
//
//		Record[] answers = response.getSectionArray(1);
//		if (answers.length == 0)
//			return "";
//
//		return answers[0].rdataToString();
//	}
//
//	public Set<String> resolve(String strDomain) throws Exception {
//		Set<String> ipList = new HashSet<String>();
//
//		Record[] recordsv = lookup(strDomain, Type.A);
//		for (int i = 0; i < recordsv.length; i++) {
//			ARecord arec = (ARecord) recordsv[i];
//			ipList.add(arec.getAddress().getHostAddress());
//
//		}
//		return ipList;
//
//	}
//
//	public Record[] lookup(String domain, int type) throws Exception {
//		Lookup lookup = new Lookup(domain, type);
//		lookup.setResolver(resolver);
//		return lookup.run();
//	}
//
//	public static void main(String[] args) {
//		try {
//			String domain = "zonetransfer.me";
//
//			DNSUtils dns = new DNSUtils();
//			System.out.println(dns.resolve(domain));
//			
//			 for (Record rec: dns.lookup(domain, Type.NS)){
//			 String ns = rec.getAdditionalName().toString();
//			 System.out.println("requesting: "+ns);
//			
//			 dns.setDNSServer(new String[]{ns});
//			 for (Record rec2 : dns.lookup(domain, Type.ANY)){
//			 System.out.println(rec);
//			 }
//			 }
//
////			Name name = ReverseMap.fromAddress("217.147.180.162");
////
////			Resolver resolver = new ExtendedResolver(new String[] { "nsztm2.digi.ninja" });
////			int type = Type.AXFR;
////			int dclass = DClass.IN;
////			Record rec = Record.newRecord(name, type, dclass);
////			Message query = Message.newQuery(rec);
////			Message response = resolver.send(query);
////			for (Record rec1 : response.getSectionArray(1)) {
////				System.out.println(rec1);
////			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// public Message
//	// sendAXFR(Message query) throws IOException {
//	// Name qname = query.getQuestion().getName();
//	// ZoneTransferIn xfrin = ZoneTransferIn.newAXFR(qname, address, tsig);
//	// xfrin.setTimeout((int)(10000 / 1000));
//	// xfrin.setLocalAddress(new So);
//	// try {
//	// xfrin.run();
//	// }
//	// catch (ZoneTransferException e) {
//	// throw new WireParseException(e.getMessage());
//	// }
//	// List records = xfrin.getAXFR();
//	// Message response = new Message(query.getHeader().getID());
//	// response.getHeader().setFlag(Flags.AA);
//	// response.getHeader().setFlag(Flags.QR);
//	// response.addRecord(query.getQuestion(), Section.QUESTION);
//	// Iterator it = records.iterator();
//	// while (it.hasNext())
//	// response.addRecord((Record)it.next(), Section.ANSWER);
//	// return response;
//	// }
//
//}
