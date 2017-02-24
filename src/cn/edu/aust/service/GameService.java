package cn.edu.aust.service;

import java.awt.Point;
import java.util.Random;

import cn.edu.aust.domain.Data;
import cn.edu.aust.dto.GameDto;

/**
 * 该类是整个程序的数据处理中心
 * 
 * @author GAOH
 * 
 */
public class GameService {
	/**
	 *  传入该接口的引用，用于将从数据库读到的数据加载进来
	 * */
	private Data dataA;
	/**
	 *  传入该接口的引用，用于将从硬盘读到的数据加载进来
	 * */
	private Data dataB;

	private boolean isGameOver=true;
	
	
	public boolean isGameOver() {
		return isGameOver;
	}

	private GameDto dto;
	/**
	 * 当消行数每增加5就让等级加1
	 * 用于计数
	 * */
	private int i=1;
	/**
	 * 定义该布尔型变量用于判断是否消行，
	 * 由于直接定义方法为布尔类型时会出现多行同时出现时不能消去所有行
	 * 是由于return会直接终止外层循环
	 * */
	private boolean isDelete=true;
	
	public GameService(GameDto dto, Data dataA, Data dataB)  {
		super();
		this.dto = dto;
		this.dataA=dataA;
		this.dataB=dataB;
		this.dto.setDiskRecord(dataA.loadData());
		this.dto.setDbRecord(dataB.loadData());
		
	}

	public void keyUp() {
		this.dto.getBlock().rotate(this.dto.getGameMap());
	}

	public void keyDown() {
		if (this.dto.getBlock().move(0, 1, this.dto.getGameMap())) {
			return;
		}
		/**
		 * 将有方块的地方传给地图对象数组
		 * */
		 boolean [][] map = this.dto.getGameMap();
		Point[] block = this.dto.getBlock().getPoints();
		for (int i = 0; i < block.length; i++) {
			map[block[i].x][block[i].y] = true;
		}
		// 产生一个新的图形
		/**
		 * 将下一个状态的方块传给面板
		 * 再重新生成一个新的方块
		 * 先初始化，再随机生成下一个方块
		 * */
		//if() 判断游戏是否结束
		for(int x=0;x<GameDto.MAP_W;x++){
			if(map[x][0]){
				chageGameOver();
				return ;
			}
		}
		this.dto.getBlock().init(this.dto.getNext());
		int temp=new Random().nextInt(100000)%7;
		this.dto.setNext(temp);
		
		
		/**
		 * 消行功能
		 * */
		deleteFullLine(map);
		if(!isDelete){
			int nowDeteleNum=this.dto.getNowDeleteLine()+1;
			this.dto.setNowDeleteLine(nowDeteleNum);
			int nowPoint=this.dto.getNowPoint()+10;
			this.dto.setNowPoint(nowPoint);
			isDelete=true;
		}
		/**
		 * 显示等级和分数
		 * */
		if(this.dto.getNowDeleteLine()>=5*i){
			int nowLevel=this.dto.getNowLevel()+1;
			this.dto.setNowLevel(nowLevel);
			++i;
		}
	}

	private void chageGameOver() {
		this.isGameOver=false;
	}

	private void deleteFullLine(boolean [][] map) {
		/**
		 * 注意消行操作逻辑比较复杂，注意注意
		 * */
		for(int y=0;y<GameDto.MAP_H;y++){
			boolean temp=true;
			for(int x=0;x<GameDto.MAP_W;x++){
				if(!map[x][y]){
					temp=false;
				}
			}
			//该if判断必须在内存循环结束之后，之前放在内存循环里，导致错误一直没找出来
			if(temp){
				//判断能否消行，如果能就消去
				deteleLine(y,map);
				isDelete=false;
				//return true; //error 若在此处加return时可能会导致单出现多行时无法消除多行，因为return表示直接结束该方法
			}
			
		}
	}

	private void deteleLine(int lineNum, boolean[][] map) {
		/**
		 * 消行操作
		 * */
		for(int y=lineNum;y>0;y--){
			for(int x=0;x<GameDto.MAP_W;x++){
				map[x][y]=map[x][y-1];
			}
			
		}
		
	}

	public void keyLeft() {
		this.dto.getBlock().move(-1, 0, this.dto.getGameMap());
	}

	public void keyRight() {
		this.dto.getBlock().move(1, 0, this.dto.getGameMap());
	}
}
