package cn.edu.aust.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerController extends KeyAdapter {
	private GameController controller;
	public PlayerController(GameController controller) {
		super();
		this.controller = controller;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//对键盘事件进行监听
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			this.controller.keyUp();
			break;
		case KeyEvent.VK_DOWN:
			this.controller.keyDown();
				break;
		case KeyEvent.VK_LEFT:
			this.controller.keyLeft();
				break;
		case KeyEvent.VK_RIGHT:
			this.controller.keyRight();
				break;
		}
	}
}
