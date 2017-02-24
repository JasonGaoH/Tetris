package cn.edu.aust.dto;

import java.io.Serializable;


public class Player implements Comparable<Player>, Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	public Player(String name, int point) {
		super();
		this.name = name;
		this.point = point;
	}
	private int point;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public int compareTo(Player pla) {
		return pla.point-this.point;
	}
	
	
}
