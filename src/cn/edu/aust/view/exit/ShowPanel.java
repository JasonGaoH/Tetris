package cn.edu.aust.view.exit;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.aust.domain.Data;
import cn.edu.aust.dto.GameDto;

public class ShowPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int PADDING=32;
	private static final Image IMG_NUM=
			new ImageIcon("Graphics/String/num_.png").getImage();
	private static final Image IMG_SHOW=
			new ImageIcon("Graphics/game/showpanel.png").getImage();
	private static final Image IMG_ID=
			new ImageIcon("Graphics/game/id.png").getImage();
	private static final Image IMG_SCORE=
			new ImageIcon("Graphics/string/score.png").getImage();
	private static final Image IMG_SUGGEST=
			new ImageIcon("Graphics/string/suggest.png").getImage();
	private ImageIcon ICON_SAVE=new ImageIcon("Graphics/String/save.png");
	
	
	private ImageIcon ICON_EXIT=new ImageIcon("Graphics/String/exit.png");
	

	/**
	 * 数字图片的高度
	 * */
	private  final int IMG_H=36;
	/**
	 * 数字图片的宽度
	 * */
	private  final int IMG_W=26;
	/**
	 * 大边框距离
	 * */
	private  final int DIS=145;
	/**
	 * 经验值槽的宽度
	 * */
	private JTextField jtextField=null;
	private JButton saveButton=null;
	private JButton exitButton=null;
	private GameDto dto;
	private Data dataA;
	private Data dataB;
	
	public ShowPanel(final Data dataA, final Data dataB, final GameDto dto){
		this.dto=dto;
		this.dataA=dataA;
		this.dataB=dataB;
		this.setLayout(null);
		this.setSize(288, 300);
		initPanel();
		buttonEvent();
		
	}

	private void buttonEvent() {
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jtextField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "ID不能为空！");
				}else{
					dataA.saveData(jtextField.getText(),dto.getNowPoint());
					dataB.saveData(jtextField.getText(),dto.getNowPoint());
					System.exit(0);
				}
			}
			
		});
		
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void initPanel() {
		jtextField=new JTextField();
		jtextField.setBounds(128, 128, 96, 32);
		this.add(jtextField);
		saveButton=new JButton(ICON_SAVE);
		saveButton.setBounds(48, 200, 64, 32);
		this.add(saveButton);
		exitButton=new JButton(ICON_EXIT);
		exitButton.setBounds(156, 200, 64, 32);
		this.add(exitButton);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(IMG_SHOW, 0, 0, 288, 300, 0, 0, 288, 222, null);
		g.drawImage(IMG_ID, PADDING, PADDING, null);
		g.drawImage(IMG_SCORE, 100, 64, null);
		g.drawImage(IMG_SUGGEST, 32, 128, null);
		drawNumber(g);
	}
	
	private void drawNumber(Graphics g){
		String str=String.valueOf(this.dto.getNowPoint());
		for(int i=0;i<str.length();i++){
			int temp=Integer.parseInt(str.charAt(i)+"");
			g.drawImage(IMG_NUM, 
					PADDING+DIS+IMG_W*i, 
					28+PADDING, 
					PADDING+DIS+IMG_W+IMG_W*i, 
					28+PADDING+IMG_H, 
					temp*IMG_W, 0, temp*IMG_W+IMG_W, IMG_H, null);
		}
	}
	
	
}
