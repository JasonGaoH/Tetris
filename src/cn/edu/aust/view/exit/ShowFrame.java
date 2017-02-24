package cn.edu.aust.view.exit;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ShowFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public ShowFrame(ShowPanel showPanel){
		this.add(showPanel);
		this.setResizable(false);
		this.setSize(showPanel.getWidth(), showPanel.getHeight()+30);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		this.setLocation((w - this.getWidth()) / 2,
				(h - this.getHeight()) / 2 - 32);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
}
