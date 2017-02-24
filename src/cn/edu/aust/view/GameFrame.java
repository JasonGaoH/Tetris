package cn.edu.aust.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import cn.edu.aust.domain.Data;
import cn.edu.aust.dto.GameDto;
import cn.edu.aust.view.exit.ShowFrame;
import cn.edu.aust.view.exit.ShowPanel;

/**
 * @author GAOH
 * 
 */
public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameFrame(GamePanel panle, final Data dataA, final Data dataB, final GameDto dto) {
		this.setSize(1168, 680);
		this.setTitle("Java俄罗斯方块");
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		this.setLocation((w - this.getWidth()) / 2,
				(h - this.getHeight()) / 2 - 32);
		this.setContentPane(panle);
		// 注意此处若用this.add(panel);会抛出空指针异常
		//this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ShowPanel showPanel=new ShowPanel(dataA,dataB,dto);
				ShowFrame frame=new ShowFrame(showPanel);
				frame.setVisible(true);
			}

		});
		this.setVisible(true);

	}
}
