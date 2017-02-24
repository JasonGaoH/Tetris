package cn.edu.aust.domain;

import java.util.List;

import cn.edu.aust.dto.Player;

/**
 * 该接口提供对数据的持久层访问
 * @author GAOH
 *
 */
public interface Data {
	public List<Player>  loadData() ;
	
	public void saveData(String name, int i);
}
