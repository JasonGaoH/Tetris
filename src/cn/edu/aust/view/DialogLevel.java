package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DialogLevel extends Dialog {
	private static final Image IMG_LEVEL=new ImageIcon("Graphics/String/level.png").getImage();
	private static final int PADDING=16;
	private static final Image IMG_NUM=new ImageIcon("Graphics/String/num_.png").getImage();
	private static final int DIS=64;
	private static final int IMG_H=36;
	private static final int IMG_W=26;
	public DialogLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		//绘制等级logo
		g.drawImage(IMG_LEVEL, this.x+PADDING, this.y+PADDING, null);
		//绘制等级数字
		drawNumber(g);
//		g.drawImage(IMG_NUM, this.x+DIS, this.y+DIS, 
//				this.x+DIS+IMG_W, this.y+DIS+IMG_H,
//				IMG_W*this.dto.getNowLevel(), 0, IMG_W+IMG_W*this.dto.getNowLevel(), IMG_H, null);
	}
	private void drawNumber(Graphics g){
		String str=String.valueOf(this.dto.getNowLevel());
		for(int i=0;i<str.length();i++){
			int temp=Integer.parseInt(str.charAt(i)+"");
			g.drawImage(IMG_NUM, 
					this.x+PADDING+DIS+IMG_W*i, 
					this.y+PADDING+DIS, 
					this.x+PADDING+DIS+IMG_W+IMG_W*i, 
					this.y+PADDING+IMG_H+DIS, 
					temp*IMG_W, 0, temp*IMG_W+IMG_W, IMG_H, null);
		}
	}
}
