package cn.edu.aust.domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.aust.dto.Player;

public class DataDisk implements Data {

	@Override
	public List<Player> loadData() {
		List<Player> players=new ArrayList<Player>();
		ObjectInputStream ois=null;
		try {
			 ois=new ObjectInputStream(new FileInputStream("disk/diskdata.dat"));
			 players=(List<Player>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return players;
	}

	@Override
	public   void saveData(String name,int point) {
		List<Player> players=new ArrayList<Player>();
		ObjectInputStream ois=null;
		ObjectOutputStream oos=null;
		try {
			 ois=new ObjectInputStream(new FileInputStream("disk/diskdata.dat"));
			 players=(List<Player>) ois.readObject();
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		 players.add(new Player(name,point));
		 try{
			 oos=new ObjectOutputStream(new FileOutputStream("disk/diskdata.dat"));
			 oos.writeObject(players);
			 System.out.println("保存成功！");
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		/*ObjectOutputStream oos=null;
		List<Player> players=new ArrayList<Player>();
		players.add(new Player("凌风",5890));
		players.add(new Player("天涯",4670));
		players.add(new Player("逗比",3560));
		players.add(new Player("傻叉",3240));
		players.add(new Player("浣尘",2520));
		
		try {
			oos=new ObjectOutputStream(new FileOutputStream("disk/diskdata.dat"));
			oos.writeObject(players);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	 }
	
	
}
