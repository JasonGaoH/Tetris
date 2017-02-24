package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DialogAbout extends Dialog {
	//TODO …–Œ¥≥ı ºªØ
	private static Image IMG_AUTHOR=new ImageIcon("Graphics/string/au.png").getImage();
	public DialogAbout(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);	
		g.drawImage(IMG_AUTHOR, this.x+5, this.y+5, null);
	}

}
