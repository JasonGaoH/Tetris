package cn.edu.aust.view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cn.edu.aust.dto.GameDto;

/**
 * 该类是用来封装画边框的一个类
 * @author GAOH
 *
 */
public abstract class Dialog {
	protected GameDto dto;
	
	private static final int PADDING=32;
	/**
	 * 将边框宽度定义为常量
	 * */
	private static final int  SIZE =3;
	/**
	 *  将图片对象设置为常量
	 *  Image img=Toolkit.getDefaultToolkit("图片路径").getImage(str);
	 * 注意：如果用该方法来获取图片时，需要不停的重绘才能在屏幕上显示出图片
	 */
	private static final Image IMG=new ImageIcon("Graphics/Window/Window_test.png").getImage();
	/**
	 * 获取图片宽度
	 * 	 * */
	private static final int IMG_W=IMG.getWidth(null);
	/**
	 * 获取图片高度
	 * */
	private static final int IMG_H=IMG.getHeight(null);
	/**
	 * 获取RECT图片
	 * */
	private static final Image IMG_RECT=
			new ImageIcon("Graphics/window/rect.png").getImage();
	
	/**
	 * RECT的宽度 
	 * */
	private final  int RECT_W=IMG_RECT.getWidth(null);
	/**
	 * RECT的高度
	 * */
	private final  int RECT_H=IMG_RECT.getHeight(null);
	/**
	 * 所画边框的横坐标
	 * */
	protected int x;
	/**
	 * 所画边框的纵坐标
	 * */
	protected int y;
	/**
	 * 定义所画边框的宽度
	 * */
	protected int w;
	/**
	 * 定义所画边框的高度
	 * */
	protected int h;
	
	
	public Dialog(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	
	public void createWindow(Graphics g){
		        // 左上
				g.drawImage(IMG, x, y, x + SIZE, y + SIZE, 0, 0, SIZE, SIZE, null);
				//中上
				g.drawImage(IMG, x+SIZE, y, (w+x)-SIZE, y+SIZE,SIZE, 0, IMG_W-SIZE,SIZE, null);
				//右上
				g.drawImage(IMG, w+x-SIZE, y, w+x, y+SIZE, IMG_W-SIZE, 0, IMG_W, SIZE, null);
				//中左
				g.drawImage(IMG, x, y+SIZE, x+SIZE, h+y-SIZE, 0, SIZE, SIZE, IMG_H-SIZE, null);
				//中中
				g.drawImage(IMG, x+SIZE, y+SIZE, w+x-SIZE, y+h-SIZE, SIZE, SIZE, IMG_W-SIZE, IMG_H-SIZE, null);
				//中右
				g.drawImage(IMG,  w+x-SIZE, y+SIZE, w+x, h+y-SIZE,IMG_W-SIZE, SIZE, IMG_W, IMG_H-SIZE, null);
				//下左
				g.drawImage(IMG,  x, h+y-SIZE, x+SIZE, h+y, 0, IMG_H-SIZE, SIZE, IMG_H, null);
				//下中
				g.drawImage(IMG, x+SIZE, y+h-SIZE, x+w-SIZE, y+h, SIZE, IMG_H-SIZE, IMG_W-SIZE, IMG_H, null);
				//下右
				g.drawImage(IMG, x+w-SIZE, y+h-SIZE, x+w, y+h, IMG_W-SIZE, IMG_H-SIZE, IMG_W, IMG_H, null);
				
	}
	
	/**
	 * 在父类中抽象出该方法类
	 * 用于绘制值槽
	 * @param x 表示值槽的左上角横坐标
	 * @param y 表示值槽的左上角纵坐标
	 * @param w 表示值槽的宽度
	 * @param h 表示值槽的高度
	 * @param title 表示值槽的所加文字
	 * @param g 画笔
	 * @param value 表示值槽每一次升级的基本值
	 * @param maxvalue 表示值槽升级的上限
	 * @param strnum 表示值槽中所绘制的数字
	 */
	protected void drawRect(int x,int y,int w,int h,String title,Graphics g,int value,int maxvalue,int num){
		
		//绘制背景
		g.setColor(Color.white);
		g.fillRect(x, y, w, h);
		g.setColor(Color.black);
		g.fillRect(x+1, y+1, w-2, h-2);
		//绘制值槽
		double p=(double)(value%maxvalue)/(double)maxvalue;
		int width=(int) (p*(w-2));//TODO
		//width为每次所加的彩条宽度
		int subIdx=(int) (p*RECT_W);
		//subIdx是在RECT这张图片上截取的位置坐标表示
		g.drawImage(IMG_RECT, x+1, y+1, this.x+PADDING+ width-16, y+1+h-2, //TODO
				0, 0, subIdx, RECT_H,null);//subIdx, 0, subIdx+1, RECT_H,null);
		g.setColor(Color.WHITE);
		//g.setFont(DEF_FONT);
		//TODO setFont会导致屏幕刷新变慢
		g.drawString(title, x+1+16, y+1+16);
		if(num==0){
			return;
		}
		String strnum=String.valueOf(num);
		//绘制分数    字符串的绘制纵坐标是以字符串地步计算的
		g.drawString(strnum, x+200, y+1+16);
	}
	public void setDto(GameDto dto){
		this.dto=dto;
	}
	public abstract void paint(Graphics g);
}
