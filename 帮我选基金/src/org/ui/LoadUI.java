package org.ui;

import java.awt.*;
import java.io.IOException;
import java.net.*;

import javax.swing.*;

import org.util.FileUtil;
import org.util.UpdateFundJz;

public class LoadUI extends JWindow implements Runnable {
	Thread splashThread;
	JProgressBar progress;

	public LoadUI() {
		Container container = getContentPane();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		URL url = getClass().getResource("login.jpg");
		if (url != null) {
			container.add(new JLabel(new ImageIcon(url)), BorderLayout.CENTER);
		}
		progress = new JProgressBar(1, 100);
		progress.setStringPainted(true);
		// progress.setString("加载程序中,请稍候......"); // 设置显示文字
		progress.setBackground(Color.white);
		Color c = new Color(123, 123, 123);
		progress.setForeground(c);
		container.add(progress, BorderLayout.SOUTH);

		Dimension screen = getToolkit().getScreenSize();
		pack();
		setLocation((screen.width - getSize().width) / 2,
				(screen.height - getSize().height) / 2);
	}

	public void start() {
		this.toFront();
		splashThread = new Thread(this);
		splashThread.start();
	}

	public void run() {
		setVisible(true);
		try {
			for (int i = 0; i < 100; i++) {
				Thread.sleep(50);
				progress.setValue(progress.getValue() + 1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dispose();
		showFrame();
	}

	static void showFrame() {
		MainFrame frame = new MainFrame();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		LoadUI splash = new LoadUI();
		splash.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
