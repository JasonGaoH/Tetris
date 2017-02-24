package cn.edu.aust.controller;


import util.PlayMusic;
import cn.edu.aust.domain.Data;
import cn.edu.aust.domain.DataDataBase;
import cn.edu.aust.domain.DataDisk;
import cn.edu.aust.dto.GameDto;
import cn.edu.aust.entities.GameBlock;
import cn.edu.aust.service.GameService;
import cn.edu.aust.view.GameFrame;
import cn.edu.aust.view.GamePanel;


/**
 * GameController为游戏的控制中心
 * @author GAOH
 *
 */
public class GameController  {
	
	private GameDto dto;
	private GameService service;
	private GameBlock block;
	private GamePanel panel;
	private PlayerController playController;
	private Data dataA;
	private Data dataB;
	
	
	public GameController() {
		      super();
		      // 创建数据Dto对象
				 dto = new GameDto();
				  dataA=new DataDisk();
				  dataB=new DataDataBase();
				//创建游戏面板对象，并连接dto对象
				 panel = new GamePanel(dto);
				//创建业务逻辑对象，该对象用于对游戏中的数据进行处理，连接Dto对象
				 service = new GameService(dto,dataA,dataB);
				//创建玩家控制器，并连接业务逻辑对象和游戏面板对象
				 playController = new PlayerController(this);
				//将玩家控制器安装到panel上
				panel.setController(playController);
				//创建方块对象
				 block = new GameBlock(service,panel);
				//将方块对象传给dto
				dto.setBlock(block);
				
				//创建主窗体对象
				new GameFrame(panel,dataA,dataB,dto);
				//创建播放音乐的对象
				new PlayMusic();
	}

	/**
	 * 暂停状态下需要判断线程启动的条件，暂停状态下不允许移动方块
	 * */
	public void keyUp() {
		if(this.block.isJudgeStop()){
			this.service.keyUp();
		   this.panel.repaint();
		}
	}

	public void keyDown() {
		if(this.block.isJudgeStop()){
		this.service.keyDown();
		this.panel.repaint();
		}
	}

	public void keyLeft() {
		if(this.block.isJudgeStop()){
		this.service.keyLeft();
		this.panel.repaint();
		}
	}

	public void keyRight() {
		if(this.block.isJudgeStop()){
		this.service.keyRight();
		this.panel.repaint();
		}
	}

}
