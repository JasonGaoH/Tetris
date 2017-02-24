package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DialogBackground extends Dialog {
	private static Image IMG_WINDOW_1=new ImageIcon("Graphics/background/1.jpg").getImage();
	private static Image IMG_WINDOW_2=new ImageIcon("Graphics/background/2.jpg").getImage();
	private static Image IMG_WINDOW_3=new ImageIcon("Graphics/background/3.jpg").getImage();
	private static Image IMG_WINDOW_4=new ImageIcon("Graphics/background/4.jpg").getImage();
	private static Image IMG_WINDOW_5=new ImageIcon("Graphics/background/5.jpg").getImage();
	private static Image IMG_WINDOW_6=new ImageIcon("Graphics/background/6.jpg").getImage();
	private static Image IMG_WINDOW_7=new ImageIcon("Graphics/background/7.jpg").getImage();

	public DialogBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		switch(this.dto.getNowLevel()%7){
		case 1:
			g.drawImage(IMG_WINDOW_2, 0, 0,  null);
			break;
		case 2:
			g.drawImage(IMG_WINDOW_7, 0, 0,  null);
			break;
		case 3:
			g.drawImage(IMG_WINDOW_3, 0, 0,  null);
			break;
		case 4:
			g.drawImage(IMG_WINDOW_4, 0, 0,  null);
			break;
		case 5:
			g.drawImage(IMG_WINDOW_5, 0, 0,  null);
			break;
		case 6:
			g.drawImage(IMG_WINDOW_6, 0, 0,  null);
			break;
		case 0:
			g.drawImage(IMG_WINDOW_1, 0, 0,  null);
			break;
			
		}
		

	}

}
