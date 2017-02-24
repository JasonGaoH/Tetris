package util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;


public class PlayMusic {

	private static Player player=null; 
	private static URL url=null;
	
	static {
		try {
			url=new URL("file:./music/test.mp3");
			player = Manager.createPlayer(url);
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NoPlayerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void playMusic(){
		player.start();
	}
	
	public static  void stopMusic(){
		player.stop();
	}
	
	public static void closeMusic(){
		player.close();
	}
}
