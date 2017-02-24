package cn.edu.aust.dto;

import java.util.Collections;
import java.util.List;

import cn.edu.aust.entities.GameBlock;

/**
 * 该类是连接业务逻辑类和游戏面板的
 * 即游戏中需要在画面上表现的数据均封装在该Dto类里面
 * @author GAOH
 *
 */
public class GameDto {
	public static final int MAP_W=10;
	public static final int MAP_H=18;
	private List<Player> dbRecord;
	private List<Player> diskRecord;
	private GameBlock block;
	private boolean[][] gameMap=null;
	private int next;
	private int nowLevel;
	private int nowPoint;
	private int nowDeleteLine;
	/**
	 * 构造方法
	 * */
	public GameDto(){
		//初始化GameDto
		init();
	}
	
	public void init(){
		//初始化游戏面板数组，该数组用于控制面板中方块的绘制
		gameMap=new boolean[MAP_W][MAP_H];
		
		nowPoint=2000;
		nowLevel=1;
	}
	public GameBlock getBlock() {
		return block;
	}
	public void setBlock(GameBlock block) {
		this.block = block;
	}
	
	public boolean[][] getGameMap() {
		return gameMap;
	}
	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
	
	public List<Player> getDbRecord() {
		return dbRecord;
	}
	public void setDbRecord(List<Player> dbRecord) {
		
		this.dbRecord = dbRecord;
	}
	public List<Player> getDiskRecord() {
		return diskRecord;
	}
	public void setDiskRecord(List<Player> diskRecord) {
		Collections.sort(diskRecord);
		this.diskRecord = diskRecord;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getNowLevel() {
		return nowLevel;
	}
	public void setNowLevel(int nowLevel) {
		this.nowLevel = nowLevel;
	}
	public int getNowPoint() {
		return nowPoint;
	}
	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}
	public int getNowDeleteLine() {
		return nowDeleteLine;
	}
	public void setNowDeleteLine(int nowDeleteLine) {
		this.nowDeleteLine = nowDeleteLine;
	}
}
