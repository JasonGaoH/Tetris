package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 该类用于将下一个即将产生的方块绘制在游戏面板上
 * @author GAOH
 *
 */
public class DialogNext extends Dialog {
	private static Image[] IMG_NEXT;
	/**
	 * 定义数组来存储图片对象
	 * 使用静态厨师块来初始化数组对象，注意这种方法
	 * */
	static {
		IMG_NEXT=new Image[7];
		for(int i=0;i<IMG_NEXT.length;i++){
			IMG_NEXT[i]=new ImageIcon("Graphics/game/"+i+".png").getImage();
		}
	}
		
	public DialogNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		if(this.dto.getBlock().getGameInit()){
			drawOfCenter(g);
		}
		
	}
	
	/**
	 * 该方法实现正中绘图
	 * */
	private void drawOfCenter(Graphics g){
		int img_w=IMG_NEXT[this.dto.getNext()].getWidth(null);
		int img_h=IMG_NEXT[this.dto.getNext()].getHeight(null);
		int now_x=(this.w-img_w)/2;
		int now_y=(this.h-img_h)/2;
		g.drawImage(IMG_NEXT[this.dto.getNext()], this.x+now_x,this.y+ now_y, null);
	}
}
