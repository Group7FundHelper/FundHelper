package org.ui;

public class MainFrame extends javax.swing.JFrame {

	public MainFrame() {
		super("帮我选基金");
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new AllFundUI();
		jPanel2 = new FundSearchUI();
		jPanel3 = new FundDiffUI();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
		jTabbedPane1.setFont(new java.awt.Font("微软雅黑", 1, 14));
		jTabbedPane1.addTab("所有基金", jPanel1);
		jTabbedPane1.addTab("排名查询", jPanel2);
		jTabbedPane1.addTab("净值对比", jPanel3);

		this.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		pack();
	}

	private AllFundUI jPanel1;
	private FundSearchUI jPanel2;
	private FundDiffUI jPanel3;
	private javax.swing.JTabbedPane jTabbedPane1;

}
