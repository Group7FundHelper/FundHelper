package org.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.util.FileUtil;
import org.util.UpdateFundJz;

public class FundDiffUI extends javax.swing.JPanel {
	public FundDiffUI() {
		initComponents();
	}

	private void initComponents() {
		jLabel = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jButton2 = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();

		jLabel.setPreferredSize(new Dimension(30,30));  
		
		jLabel3.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jTextField1.setFont(new java.awt.Font("微软雅黑", 0, 12));
		jTextField2.setFont(new java.awt.Font("微软雅黑", 0, 12));
		jTextField1.setPreferredSize(new Dimension(200,30));  
		jTextField2.setPreferredSize(new Dimension(200,30));  
		
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane3.setPreferredSize(new Dimension(650,510));  
		jScrollPane3.setViewportView(jTextArea1);

		jLabel3.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jLabel3.setText("请输入两个基金: ");

		jButton2.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jButton2.setText("对比");
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diffFund();
			}
		});
		this.add(jLabel3, java.awt.BorderLayout.NORTH);
		this.add(jTextField1, java.awt.BorderLayout.CENTER);
		this.add(jTextField2, java.awt.BorderLayout.CENTER);
		this.add(jLabel, java.awt.BorderLayout.NORTH);
		this.add(jButton2, java.awt.BorderLayout.EAST);
		this.add(jScrollPane3, java.awt.BorderLayout.SOUTH);
	}
	
	private void diffFund(){
		if (jTextField1.getText().equals("") || jTextField2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请正确输入！");
			return;
		} else {
                                
			try {
				UpdateFundJz.updatejz(jTextField1.getText());
				UpdateFundJz.updatejz(jTextField2.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
                                        
			String fundNum[] = { jTextField1.getText(),
					jTextField2.getText() };
			JFrame newFrame = new LineChart().createLineChart(fundNum);
			newFrame.pack();
			newFrame.setLocationRelativeTo(null);
			newFrame.setVisible(true);
			String str1 = FileUtil.getFundInfo(jTextField1.getText());
			String str2 = FileUtil.getFundInfo(jTextField2.getText());
			jTextArea1.append(str1);
			jTextArea1.append(str2);
			jTextArea1.append("\n");
		}
	}
	
	private javax.swing.JLabel jLabel;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
}