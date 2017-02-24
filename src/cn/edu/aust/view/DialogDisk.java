package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import cn.edu.aust.dto.Player;

public class DialogDisk extends Dialog {
	/**
	 * 本地记录的标题
	 * */
	private static final Image IMG_DISK=new ImageIcon("Graphics/String/disk.png").getImage();
	/**
	 * 边距常量
	 * */
	private static final int PADDING=16;
	/**
	 * 获取值槽填充矩形的高度
	 * */
	private final int IMG_H=IMG_DISK.getHeight(null);
	/**
	 * 值槽的宽度
	 * */
	private  final int exp_x=this.w-2*PADDING;
	/**
	 * 值槽的高度
	 * */
	private final int RECT_H=28;
	/**
	 * 值槽的最大上限LEVEL_UP
	 * */
	private final int LEVEL_UP=100;
	
	public DialogDisk(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		g.drawImage(IMG_DISK, this.x+PADDING, this.y+PADDING, null);
		List<Player> pla=this.dto.getDiskRecord();
		if(pla.size() == 0) {
			return;
		}
		int num=0;
		for(int i=0;i<5;i++){
			num=pla.get(i).getPoint();
			int nowPoint=pla.get(i).getPoint();
			//TODO
			this.drawRect(this.x+PADDING, this.y+PADDING+(IMG_H+10)*(i+1), exp_x, RECT_H,pla.get(i).getName(), g, nowPoint, LEVEL_UP,num);
		}
	}

}
