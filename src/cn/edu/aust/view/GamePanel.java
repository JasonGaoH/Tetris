package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.PlayMusic;
import cn.edu.aust.config.ConfigFactory;
import cn.edu.aust.config.DialogConfig;
import cn.edu.aust.config.GameConfig;
import cn.edu.aust.controller.PlayerController;
import cn.edu.aust.dto.GameDto;

public class GamePanel extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}
	/**
	 * 该变量用于判断开始按钮是否可用，默认状态下为true
	 * */
	private boolean isStopStart=true;
	/**
	 * 存储窗口对象的数组声明
	 * */
	private List<Dialog> dialogs = null;
	/**
	 * 引入数据处理类的引用
	 * */
	private GameDto dto;
	/**
	 * 声明开始按钮
	 * */
	private JButton startbutton;
	/**
	 * 声明暂停按钮
	 * */
	private JButton stopbutton;
	/**
	 * 获取暂停图片标签
	 * */
	private ImageIcon ICON_STOP=new ImageIcon("Graphics/String/pause_test.png");
	/**
	 * 获取开始按钮图片
	 * */
	private ImageIcon ICON_START=new ImageIcon("Graphics/String/start.png");
	/**
	 * 获取按钮的标签
	 * */
	private static final Image IMG_START=
			new ImageIcon("Graphics/String/start.png").getImage();
	
	/**
	 * 获取图片宽度
	 * */
	private final int img_x=IMG_START.getWidth(null);
	/**
	 * 获取图片的高度
	 * */
	private final int img_y=IMG_START.getHeight(null);
	/**
	 * 按钮与边框距离
	 * */
	private static final int PADDING=40;
	/**
	 * 该类为游戏主面板
	 */
	public GamePanel(GameDto dto) {
		this.dto=dto;
		
		initButton();
		initDialog();
	}
	private void initDialog() {
		//获得游戏配置
		GameConfig cfg=ConfigFactory.getGameConfig();
		//获得游戏中每个框体的配置
		List<DialogConfig> dialogsConfig=cfg.getDialogsConfig();
		dialogs=new ArrayList<Dialog>(dialogsConfig.size());
		for(DialogConfig dialogConfig :dialogsConfig){
			try {
				//获得类对象
				Class<?> cls = Class.forName(dialogConfig.getClassName());
				//获得构造函数
				Constructor<?> ctr=cls.getConstructor(int.class,int.class,int.class,int.class);
				//调用构造函数创建对象
				Dialog dia=(Dialog) ctr.newInstance(
						dialogConfig.getX(),dialogConfig.getY(),
						dialogConfig.getW(),dialogConfig.getH()
				       );
				
				dialogs.add(dia);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//初始化每个窗口时需将dto设置进去
//		dialogs = new Dialog[] {
//				// 绘制窗口
//				// 硬编码，这是不好的编程习惯，TODO 学XML之后再处理
//				new DialogBackground(0, 0, 0, 0),
//				new DialogDataBase(40, 32, 334, 281),
//				new DialogDisk(40, 333, 334, 281),
//				new DialogGame(419, 32, 326, 582),
//				new DialogButton(788, 32, 334, 124),
//				new DialogNext(788, 188, 176, 148),
//				new DialogLevel(964, 188, 158, 148),
//				new DialogPoint(788, 368, 334, 160),
//				new DialogAbout(788,560,334,54)
//
//		};
		for(int i=0;i<dialogs.size();i++){
			dialogs.get(i).setDto(dto);
		}
	}
	
	private void initButton(){
		//用于初始化开始按钮
		this.setLayout(null);
		 startbutton=new JButton(ICON_START);
		startbutton.setBounds(788+PADDING, 32+PADDING, img_x, img_y);
		startbutton.addActionListener(new StartAction());
		this.add(startbutton);
		
		//用于初始化暂停按钮
		stopbutton =new JButton(ICON_STOP);
		stopbutton.setBounds(788+232, 32+PADDING, img_x, img_y);
		 //默认情况下设置暂停为不可用状态
		stopbutton.setEnabled(false);
		stopbutton.addActionListener(new StopAction());
		this.add(stopbutton);
	}

	public void setController(PlayerController playController) {
		this.addKeyListener(playController);
	}

	protected void paintComponent(Graphics g) {
		for (int i = 0; i < dialogs.size(); dialogs.get(i++).paint(g));
		//获得游戏面板的焦点
		this.requestFocus();
	}
		
		/**
		 * StartAction用于监听开始按钮事件的响应
		 * */
	private class StartAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isStopStart){
				dto.getBlock().setGameInit(true);
				//将block类中的线程判断条件设为true，使线程能够启动
				dto.getBlock().setJudgeStop(true);
				//开启线程
				dto.getBlock().statrMainThread();
				//在按下开始按钮之后设置其为不可用状态
			    startbutton.setEnabled(false);
			    //将暂停按钮由不可用设置为可用
			    stopbutton.setEnabled(true);
			    //再将暂停按钮设置为不可用之后，将isStopStart置false，用它作为暂停按钮按下之后是否有效果
			    isStopStart=false;
			    PlayMusic.playMusic();
			    
			}
		}
	}
	/**
	 * StopAction用于监听开始按钮事件的响应
	 * */
	private class StopAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isStopStart){
				//调用block中的方法改变线程的启动条件
				dto.getBlock().setJudgeStop(false);
				//再将开始按钮设置为可用
				startbutton.setEnabled(true);
				//在将开始按钮设置为可用之后，再次将isStopStart初始化为true
				//在暂停按钮按下之后需将其设置为不可用
				stopbutton.setEnabled(false);
				isStopStart=true;
				PlayMusic.stopMusic();
			}
		}
		
	}
	
}
