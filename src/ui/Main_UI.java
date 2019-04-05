package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.UserBiz;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main_UI extends JFrame {

	private JPanel contentPane;
    UserBiz ubz=new UserBiz();
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Main_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel();
		label.setForeground(Color.RED);
		label.setText("登陆日期:"+Login_UI.comboBox.getSelectedItem().toString());
		//System.out.println(Login_UI.comboBox.getSelectedItem().toString());
		label.setBounds(30, 30, 196, 18);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("\u91CD\u65B0\u767B\u9646");
		lblNewLabel.addMouseListener(new MouseAdapter() {//点击重新登陆
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				
				new Login_UI();
			}
		});
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(493, 370, 71, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("关闭");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			    
			}
		});
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setBounds(578, 370, 49, 27);
		contentPane.add(lblNewLabel_1);
		this.setTitle(ubz.Get_Currenttime());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
