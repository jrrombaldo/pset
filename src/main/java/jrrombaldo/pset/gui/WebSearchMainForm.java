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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrrombaldo.pset.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;

import jrrombaldo.pset.searchengine.BingSearch;
import jrrombaldo.pset.searchengine.GoogleSearch;

/**
 *
 * @author junior
 */
public class WebSearchMainForm extends javax.swing.JFrame {
	private static final long serialVersionUID = -7342303842309410172L;

	private static String _domain;

	/**
	 * Creates new form NewMDIApplication
	 */
	public WebSearchMainForm(String domain) {
		_domain = domain;
		initComponents();

		targetTxt.setText(domain);

		importMessageConsole(consoleTxt);
		System.out.println("Started ...");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jDialog1 = new javax.swing.JDialog();
		jDialog2 = new javax.swing.JDialog();
		jDialog3 = new javax.swing.JDialog();
		jDialog4 = new javax.swing.JDialog();
		MainPanel = new javax.swing.JDesktopPane();
		jScrollPane3 = new javax.swing.JScrollPane();
		resultList = new javax.swing.JTree();
		jScrollPane4 = new javax.swing.JScrollPane();
		consoleTxt = new javax.swing.JTextArea();
		jLabel2 = new javax.swing.JLabel();
		targetTxt = new javax.swing.JTextField();
		googleChckBox = new javax.swing.JCheckBox();
		bingChckBox = new javax.swing.JCheckBox();
		goBtn = new javax.swing.JButton();
		totalLbl = new javax.swing.JLabel();

		javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
		jDialog1.getContentPane().setLayout(jDialog1Layout);
		jDialog1Layout.setHorizontalGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));
		jDialog1Layout.setVerticalGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE));

		javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
		jDialog2.getContentPane().setLayout(jDialog2Layout);
		jDialog2Layout.setHorizontalGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));
		jDialog2Layout.setVerticalGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE));

		javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
		jDialog3.getContentPane().setLayout(jDialog3Layout);
		jDialog3Layout.setHorizontalGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));
		jDialog3Layout.setVerticalGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE));

		javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
		jDialog4.getContentPane().setLayout(jDialog4Layout);
		jDialog4Layout.setHorizontalGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));
		jDialog4Layout.setVerticalGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("PSET - Passive Subdomain Enumeration Tool");
		setLocation(new java.awt.Point(100, 100));
		setResizable(false);

		resultList.setBackground(new java.awt.Color(204, 204, 204));
		javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("...");
		resultList.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
		jScrollPane3.setViewportView(resultList);

		consoleTxt.setBackground(new java.awt.Color(204, 204, 204));
		consoleTxt.setColumns(20);
		consoleTxt.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
		consoleTxt.setLineWrap(true);
		consoleTxt.setRows(5);
		jScrollPane4.setViewportView(consoleTxt);

		jLabel2.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		jLabel2.setForeground(new java.awt.Color(235, 170, 34));
		jLabel2.setText("Target domain");

		targetTxt.setBackground(new java.awt.Color(204, 204, 204));

		googleChckBox.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		googleChckBox.setForeground(new java.awt.Color(235, 170, 34));
		googleChckBox.setSelected(true);
		googleChckBox.setText("Google (passive)");
		googleChckBox.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				googleChckBoxActionPerformed(evt);
			}
		});

		bingChckBox.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		bingChckBox.setForeground(new java.awt.Color(235, 170, 34));
		bingChckBox.setSelected(true);
		bingChckBox.setText("Bing (passive)");

		goBtn.setBackground(new java.awt.Color(235, 170, 34));
		goBtn.setText("Go get them...");
		goBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				goBtnActionPerformed(evt);
			}
		});

		totalLbl.setForeground(new java.awt.Color(235, 170, 34));

		MainPanel.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
		MainPanel.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
		MainPanel.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
		MainPanel.setLayer(targetTxt, javax.swing.JLayeredPane.DEFAULT_LAYER);
		MainPanel.setLayer(googleChckBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
		MainPanel.setLayer(bingChckBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
		MainPanel.setLayer(goBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
		MainPanel.setLayer(totalLbl, javax.swing.JLayeredPane.DEFAULT_LAYER);

		javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
		MainPanel.setLayout(MainPanelLayout);
		MainPanelLayout.setHorizontalGroup(MainPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(MainPanelLayout.createSequentialGroup().addGroup(MainPanelLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(MainPanelLayout.createSequentialGroup().addGap(29, 29, 29)
								.addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(bingChckBox, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(MainPanelLayout.createSequentialGroup()
												.addGroup(MainPanelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE,
																100, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(googleChckBox, javax.swing.GroupLayout.PREFERRED_SIZE,
														223, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 223,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(targetTxt,
																javax.swing.GroupLayout.PREFERRED_SIZE, 223,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(0, 36, Short.MAX_VALUE))))
						.addGroup(MainPanelLayout.createSequentialGroup().addGap(55, 55, 55).addComponent(goBtn,
								javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 279,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(totalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(34, Short.MAX_VALUE)));
		MainPanelLayout.setVerticalGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
						.addGap(20, 20, 20)
						.addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(totalLbl, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(3, 3,
								3)
						.addGroup(
								MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												MainPanelLayout.createSequentialGroup()
														.addComponent(targetTxt, javax.swing.GroupLayout.PREFERRED_SIZE,
																30, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(9, 9, 9).addComponent(bingChckBox)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(googleChckBox).addGap(42, 42, 42)
										.addComponent(goBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE).addGap(50, 50, 50)
										.addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 221,
												javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jScrollPane3))
						.addGap(29, 29, 29)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(MainPanel));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void goBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_goBtnActionPerformed

		new DoSearchInBackgorund().execute();
	}// GEN-LAST:event_goBtnActionPerformed

	private void googleChckBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_googleChckBoxActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_googleChckBoxActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
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
			// java.util.logging.Logger.getLogger(WebSearchMainForm.class.getName()).log(java.util.logging.Level.SEVERE,
			// null, ex);
			ex.printStackTrace();
		}
		// </editor-fold>
		// </editor-fold>

		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		// JDK 8 required
		// java.awt.EventQueue.invokeLater(() -> {
		// new WebSearchMainForm("").setVisible(true);
		// });

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new WebSearchMainForm(_domain).setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JDesktopPane MainPanel;
	private javax.swing.JCheckBox bingChckBox;
	private javax.swing.JTextArea consoleTxt;
	private javax.swing.JButton goBtn;
	private javax.swing.JCheckBox googleChckBox;
	private javax.swing.JDialog jDialog1;
	private javax.swing.JDialog jDialog2;
	private javax.swing.JDialog jDialog3;
	private javax.swing.JDialog jDialog4;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JTree resultList;
	private javax.swing.JTextField targetTxt;
	private javax.swing.JLabel totalLbl;
	// End of variables declaration//GEN-END:variables

	private void importMessageConsole(JTextArea console) {
		MessageConsole mc = new MessageConsole(console);
		mc.redirectOut();
		mc.redirectErr(Color.RED, null);
		mc.setMessageLines(99999);
	}

	class DoSearchInBackgorund extends SwingWorker<String, String> {

		@Override
		protected String doInBackground() throws Exception {

			final Set<String> results = new HashSet<>();

			goBtn.setEnabled(false);

			try {
				String domain = targetTxt.getText();

				DefaultMutableTreeNode rootDomain = new DefaultMutableTreeNode(domain);
				resultList.setModel(new javax.swing.tree.DefaultTreeModel(rootDomain));

				consoleTxt.setText("");
				totalLbl.setText("");

				if (googleChckBox.isSelected()) {
					GoogleSearch google = new GoogleSearch(domain);
					try {
						Set<String> googleFindings = google.listSubdomains();
						System.out.println("google found " + googleFindings.size());
						System.out.println("");
						results.addAll(googleFindings);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (bingChckBox.isSelected()) {
					BingSearch bing = new BingSearch(domain);
					try {
						Set<String> bingFindings = bing.listSubdomains();
						System.out.println("bing found " + bingFindings.size());
						System.out.println("");
						results.addAll(bingFindings);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

				List<String> sortedResult = new ArrayList<String>(results);
				Collections.sort(sortedResult);

				for (String subDomain : sortedResult) {
					rootDomain.add(new DefaultMutableTreeNode(subDomain));

				}
				resultList.expandRow(0);

				totalLbl.setText("Total: " + results.size());

				// resultList.setModel(new AbstractListModel<String>() {
				// private static final long serialVersionUID =
				// -69543809995934521L;
				// String[] values = results.toArray(new
				// String[results.size()]);
				//
				// @Override
				// public int getSize() {
				// return values.length;
				// }
				//
				// @Override
				// public String getElementAt(int index) {
				// return values[index] + targetTxt.getText();
				// }
				// });
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				goBtn.setEnabled(true);
			}
			return "Done.";
		}

		@Override
		protected void done() {
			System.out.println("Done!");
		}
	}

}