package cn.edu.aust.view;

import java.awt.Graphics;



public class DialogButton extends Dialog {
	public DialogButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
	}
	
}
