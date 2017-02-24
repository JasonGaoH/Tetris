package cn.edu.aust.view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import cn.edu.aust.domain.JdbcUtils;
import cn.edu.aust.dto.Player;

public class DialogDataBase extends Dialog {
	
	private static final Image IMG_DB=new ImageIcon("Graphics/String/db.png").getImage();
	private static final int PADDING=16;
	private final int IMG_H=IMG_DB.getHeight(null);
	private  final int exp_x=this.w-2*PADDING;
	private static final int LEVEL_UP=100;
	
	public DialogDataBase(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		//TODO
		g.drawImage(IMG_DB, this.x+PADDING, this.y+PADDING, null);
		if(JdbcUtils.isConnected()){
		List<Player> pla=this.dto.getDbRecord();
		int num=0;
		for(int i=0;i<pla.size();i++){
			num=pla.get(i).getPoint();
			this.drawRect(this.x+PADDING, this.y+PADDING+(IMG_H+10)*(i+1), exp_x, 28, pla.get(i).getName(), g, num, LEVEL_UP,num);
			}
		}
		else{
			for(int i=0;i<5;i++){
				this.drawRect(this.x+PADDING, this.y+PADDING+(IMG_H+10)*(i+1), exp_x, 28,"No   Data", g, 0, LEVEL_UP,0);
			}
		}
	}

}
