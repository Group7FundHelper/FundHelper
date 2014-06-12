package org.ui;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.util.FileUtil;

public class AllFundUI extends javax.swing.JPanel {
	public AllFundUI() {
		initComponents();
	}

	private void initComponents() {
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jTable1.setFont(new java.awt.Font("微软雅黑", 0, 12));
		String[] tableHeads = new String[] { "序号","基金编号", "基金名称", "基金类型" };
		final DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		dtm.setColumnIdentifiers(tableHeads);
		jTable1.getTableHeader().setFont(new java.awt.Font("微软雅黑", 1, 12));
		Vector<Vector<String>> data = FileUtil.getFund();
		for (int i = 0; i < data.size(); i++) {
			dtm.addRow(data.get(i));
		}
		jScrollPane1.setViewportView(jTable1);
		jScrollPane1.setPreferredSize(new Dimension(650,550));  
		this.add(jScrollPane1, java.awt.BorderLayout.CENTER);

	}

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
}