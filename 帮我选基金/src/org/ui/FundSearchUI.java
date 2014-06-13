package org.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.util.FileUtil;
import org.util.UpdateFundJz;

public class FundSearchUI extends javax.swing.JPanel {
	public FundSearchUI() {
		initComponents();
	}

	private void initComponents() {
		jLabel = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel2 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox();
		jLabel3 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox();
		jButton1 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();

		jLabel.setPreferredSize(new Dimension(30,30));  
		
		jLabel1.setText("排名类型 ： ");
		jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 12));

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"开放型","货币型","理财型"}));
		jComboBox1.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jLabel2.setText("    排名规则 ： ");
		jLabel2.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"近1月增长率","近3月增长率","近6月增长率", "近1年增长率", "近3年增长率"}));
		jComboBox2.setFont(new java.awt.Font("微软雅黑", 1, 12));
		
		jLabel3.setText("    前几名  ：  ");
		jLabel3.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"前10", "前20", "前30", "前40", "前50"}));
		jComboBox3.setFont(new java.awt.Font("微软雅黑", 1, 12));
		
		String[] tableHeads = new String[] {"排名", "基金编号", "基金名称", "基金涨幅" };
		final DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
		dtm.setColumnIdentifiers(tableHeads);
		jTable2.getTableHeader().setFont(new java.awt.Font("微软雅黑", 1, 12));
		jButton1.setText("查询");
		jButton1.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				for (int index = jTable2.getModel().getRowCount() - 1; index >= 0; index--) {
					dtm.removeRow(index);
				}
				Vector<Vector<String>> data = FileUtil
						.getFundOfRank(FileUtil.getFileOfTime((String)FileUtil.getFileOfType((String)jComboBox1.getSelectedItem()), (String)jComboBox2.getSelectedItem()),(String)jComboBox3.getSelectedItem());
				for (int i = 0; i < data.size(); i++) {
					dtm.addRow(data.get(i));
				}
			}
		});

		jTable2.setFont(new java.awt.Font("微软雅黑", 0, 12));
		jTable2.getTableHeader().setReorderingAllowed(false);
		jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showLineChart();
			}
		};
		jTable2.addMouseListener(mouseListener);
		jScrollPane2.setViewportView(jTable2);
		
		jScrollPane2.setPreferredSize(new Dimension(650,510));  
		this.add(jLabel1, java.awt.BorderLayout.NORTH);
		this.add(jComboBox1, java.awt.BorderLayout.NORTH);
		this.add(jLabel2, java.awt.BorderLayout.NORTH);
		this.add(jComboBox2, java.awt.BorderLayout.NORTH);
		this.add(jLabel3, java.awt.BorderLayout.NORTH);
		this.add(jComboBox3, java.awt.BorderLayout.NORTH);
		this.add(jLabel, java.awt.BorderLayout.NORTH);
		this.add(jButton1, java.awt.BorderLayout.EAST);
		this.add(jScrollPane2, java.awt.BorderLayout.CENTER);
	}
	
	private void showLineChart(){
		int row = jTable2.getSelectedRow();
		int column = 1;
		String str = jTable2.getValueAt(row, column).toString();
                        
		try {
			UpdateFundJz.updatejz(str);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
                             
		String fundNum[] = { str };
		JFrame newFrame = new LineChart().createLineChart(fundNum);
		newFrame.pack();
		newFrame.setLocationRelativeTo(null);
		newFrame.setVisible(true);
	}
	
	private javax.swing.JLabel jLabel;
	private javax.swing.JButton jButton1;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JComboBox jComboBox3;
}