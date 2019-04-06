package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import bll.UserBiz;
import model.Users;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class Main_UI extends JFrame {

	private JPanel contentPane;
    public UserBiz ubz=new UserBiz();
    public JTree tree = new JTree();
    public static Users myuser=new Users();
    public ArrayList<String> arr_list=new ArrayList<String>();
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
		label.setBounds(417, 35, 196, 18);
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
		lblNewLabel.setBounds(444, 155, 71, 27);
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
		
		JButton button = new JButton("确实");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new QXGL_UI();
			}
		});
		button.setBounds(427, 78, 113, 27);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 262, 396);
		contentPane.add(scrollPane);
	    try {
			this.arr_list=ubz.getUserModule(Login_UI.username_Field.getText().toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    for(int i=0;i<arr_list.size();i++)
	    System.out.println(arr_list.get(i));
	    DefaultMutableTreeNode RootNode=new DefaultMutableTreeNode("用户权限");
	    try {
			myuser=ubz.finduser(Login_UI.username_Field.getText().toString());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    int uid=0;
		try {
			uid = ubz.getUserID(Login_UI.username_Field.getText().toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    List<HashMap<String,String>> userRights=ubz.AllocatedByUserID(uid);//用户获得的权限与模块名
	    for(int i=0;i<arr_list.size();i++){
	    	DefaultMutableTreeNode node=new DefaultMutableTreeNode(arr_list.get(i));
	    	RootNode.add(node);
	    	for(int j1=0;j1<userRights.size();j1++){
	    		System.out.println(userRights.get(j1).values().toString());
	    		/*if(userRights.get(j1).values().toString().equals("["+arr_list.get(i)+"]")){
	    			DefaultMutableTreeNode node1=new DefaultMutableTreeNode(userRights.get(j1).keySet().toString());
	    	    	node.add(node1);
	    		}*/
	    		for(Map.Entry<String, String> arg:userRights.get(j1).entrySet()){
	    			if(arg.getValue().equals(arr_list.get(i))){
		    			DefaultMutableTreeNode node1=new DefaultMutableTreeNode(arg.getKey());
		    	    	node.add(node1);
		    		}
            	}
	    	}
	    	
	    
	    }
		DefaultTreeModel treemodel=new DefaultTreeModel(RootNode);
		tree.setModel(treemodel);
		scrollPane.setViewportView(tree);
		this.setTitle(ubz.Get_Currenttime());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
