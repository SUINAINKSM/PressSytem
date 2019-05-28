package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
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
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import bll.UserBiz;
import model.Users;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class Main_UI extends JFrame {

	private JPanel contentPane;
    public UserBiz ubz=new UserBiz();
    public JTree tree = new JTree();
    public static Users myuser=new Users();
    public ArrayList<String> arr_list=new ArrayList<String>();
	/**
	 * 
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
    public void  MenuTree(){//生成用户对应的管理权限菜单
    	 try {
 			this.arr_list=ubz.getUserModule(Login_UI.username_Field.getText().toString());//获得当前用户的权限模块名集合,作为树的一级结点
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 	    DefaultMutableTreeNode RootNode=new DefaultMutableTreeNode("用户权限");//设置JTREE的主菜单
 	    try {
 			myuser=ubz.finduser(Login_UI.username_Field.getText().toString());//找出当前用户
 		} catch (SQLException e2) {
 			// TODO Auto-generated catch block
 			e2.printStackTrace();
 		}
 	    int uid=0;
 		try {
 			uid = ubz.getUserID(Login_UI.username_Field.getText().toString());//找出当前用户的id
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 	    List<HashMap<String,String>> userRights=ubz.AllocatedByUserID(uid);//用户获得的权限与模块名
 	    for(int i=0;i<arr_list.size();i++){
 	    	DefaultMutableTreeNode node=new DefaultMutableTreeNode(arr_list.get(i));//将所有用户分配权限对应的模块名作为一级结点
 	    	RootNode.add(node);
 	    	for(int j1=0;j1<userRights.size();j1++){
 	    		for(Map.Entry<String, String> arg:userRights.get(j1).entrySet()){
 	    			if(arg.getValue().equals(arr_list.get(i))){//如果该模块与用户的某权限对应模块名匹配，就将该权限名作为JTREE的二级结点
 		    			DefaultMutableTreeNode node1=new DefaultMutableTreeNode(arg.getKey());//将用户已分配的权限名作为树的二级结点
 		    	    	node.add(node1);
 		    		}
             	}
 	    	}    
 	    }
 		DefaultTreeModel treemodel=new DefaultTreeModel(RootNode);
 		tree.setRootVisible(false);//将用户的根节点设置为不可见，提高UI的美观度
 		tree.addTreeSelectionListener(new TreeSelectionListener() {//点击JTREE的结点的响应函数
 			public void valueChanged(TreeSelectionEvent arg0) {
 				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                 tree.getLastSelectedPathComponent();//获得当前点击的树的节点
 				 Object nodeInfo = node.getUserObject();//获得该节点信息
 				    if (node.isLeaf()) {//若该节点为树叶节点(第二级结点)，对应用户的已分权限
 				        try {
							String windowname=ubz.getRightWindowName(nodeInfo.toString());//根据选择用户的权限可以弹出对应的界面
							 switch(windowname){
						        case "W_xtgl":
						            new XTGL_UI();break;
						        case "W_bpcx":
						        	new BPCX_UI();break;
						        case "W_zgcx":
						        	new ZGCX_UI();break;
						        case "W_tsdj":
						        	new TSDJ_UI();break;
						        case "W_jhfp":
						        	new JHFP_UI();break;
						        case "W_yszd":
						        	new YSZD_UI();break;
						        case "W_jscl":
						        	new JSCL_UI();break;
						        case "W_xsrk":
						        	new XSRK_UI();break;
						        case "W_pdrk":
						        	new PDRK_UI();break;
						        case "W_qxfp":
						        	new QXFP_UI();break;
						        case "W_zhss":
						        	new CombineQuery_UI();break;
						        case "W_mhss":
						        	new FuzzyQuery_UI();break;
						        case "W_wnss":
						        	new OperatorQuery_UI();break;
						        case "W_tskc":
						        	new BB1_UI();break;
						        case "W_tsrk":
						        	new BB2_UI();break;
						        }
						} catch (SQLException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
 				        } 
 				    else {
 				        
 				    }

 				
 			}
 		});
 		tree.setModel(treemodel);
 		DemoRenderer treeCellRenderer;// 获得JTREE渲染器的一个实例。用于美化JTREE
		treeCellRenderer = new DemoRenderer();
		tree.setCellRenderer(treeCellRenderer);
 		tree.putClientProperty("JTree.lineStyle", "None");
 		 tree.setFont(new Font(Font.SANS_SERIF, Font.BOLD|Font.LAYOUT_LEFT_TO_RIGHT, 18));//设置JTREE的字体
 		//字体的大小，样式
 		
    }
	public Main_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setFont(new Font(Font.SANS_SERIF, Font.BOLD|Font.LAYOUT_LEFT_TO_RIGHT, 18));
		setBounds(100, 100, 682, 448);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//界面设置为点击右上角界面退出系统
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel();
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setForeground(Color.RED);
		label.setText("登陆日期:"+Login_UI.comboBox.getSelectedItem().toString());
		//System.out.println(Login_UI.comboBox.getSelectedItem().toString());
		label.setBounds(14, 0, 196, 18);
		contentPane.add(label);
		
		ImageIcon img=new ImageIcon("C:\\Users\\62628\\Pictures\\Saved Pictures\\32.png");
		
		JLabel lblNewLabel = new JLabel("\u91CD\u65B0\u767B\u9646");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.addMouseListener(new MouseAdapter() {//点击重新登陆
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				
				new Login_UI();
			}
		});
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(430, 382, 89, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("关闭");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			    
			}
		});
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setBounds(576, 382, 49, 27);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 25, 225, 384);
		contentPane.add(scrollPane);
		
	    MenuTree();
		scrollPane.setViewportView(tree);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(253, 25, 399, 350);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(img);
		this.setTitle(ubz.Get_Currenttime());
        //setFont(new Font(Font.SANS_SERIF, Font.BOLD|Font.LAYOUT_LEFT_TO_RIGHT, 18));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public class DemoRenderer extends DefaultTreeCellRenderer {  
		  
	    @Override  
	    public Component getTreeCellRendererComponent(JTree tree, Object value,  
	            boolean sel, boolean expanded, boolean leaf, int row,  
	            boolean hasFocus) {  
	        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;  
	        // 根节点从0开始，依次往下  
	        // 分组  
	        if (node.getLevel() == 1) {  
	            if (expanded) {  
	               this.setIcon(new ImageIcon(getClass().getClassLoader().getResource("arrow_down.png")));  
	            } else {  
	               this.setIcon(new ImageIcon(getClass().getClassLoader().getResource("arrow_right.png")));  
	            }  
	        }  
	       
	        if (node.getLevel() == 2) {  
	            this.setIcon(new ImageIcon(getClass().getClassLoader().getResource("item.png")));  
	        }  
	        
	        this.setText(value.toString());  
	        return this;  
	    }  
	      
	} 
}
