package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bll.GBiz;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;

public class TEST7_UI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public  Object[][] obj;
	public  JLabel label_1 = new JLabel();
	public  static String[] columnNames = {"编辑室名称","图书种类","书名"};	
	public  JLabel label = new JLabel("添加");
	public  JButton btnNewButton = new JButton("D:\u5220\u9664");
	public  int uid;
	private JComboBox bjsNameComboBox = new JComboBox();
	private JComboBox bookTypeComboBox = new JComboBox();
	private JTextField bookNameTextField;
	private List<String> bjsNameList=new ArrayList<String>();
	private List<String> bookTypeList=new ArrayList<String>();
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TEST7_UI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void updateTable(){
          try {
			obj=new GBiz().getBjsnameList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          table.setModel(new DefaultTableModel(obj,columnNames));
          table.repaint();
		  table.updateUI();
	}
	public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
 }
	public TEST7_UI() {
		setTitle("\u4FE1\u606F\u540D\u5E94\u7528\uFF1A\u76F4\u63A5\u8C03\u7528\u57FA\u672C\u4FE1\u606F\u540D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.WHITE);
		//getContentPane().setBackground(Color.WHITE);
		contentPane.setLayout(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookName=bookNameTextField.getText().toString();
				int selection = JOptionPane.showConfirmDialog(null,"确认删除该编辑室？","注意",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if( selection == JOptionPane.OK_OPTION ){
					try {
						new GBiz().deleteBookName(bookName);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateTable();
					try {
						label_1.setText("共"+new GBiz().getBjsnameListNumber()+"项");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnNewButton.setEnabled(false);
					label.setText("添加");;
					bjsNameComboBox.setSelectedIndex(0);
					bookTypeComboBox.setSelectedIndex(0);
					bookNameTextField.setText(null);
					bookNameTextField.requestFocus();
				}
				else{
					
				}
				
			}
		});
		
		
		btnNewButton.setEnabled(false);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
		btnNewButton.setBounds(711, 391, 102, 42);
		contentPane.add(btnNewButton);
		
		JButton btnR = new JButton("R:\u590D\u4F4D");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				table.setRowSelectionAllowed(false);
				label.setText("添加");;
				bjsNameComboBox.setSelectedIndex(0);
				bookTypeComboBox.setSelectedIndex(0);
				bookNameTextField.setText(null);
				bookNameTextField.requestFocus();
			}
		});
		btnR.setFont(new Font("宋体", Font.BOLD, 20));
		btnR.setBounds(559, 453, 102, 42);
		contentPane.add(btnR);
		
		JButton button_1 = new JButton("S:\u4FDD\u5B58");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(label.getText().equals("添加")){
			   String bjsname=bjsNameComboBox.getSelectedItem().toString();
			   String bookType=bookTypeComboBox.getSelectedItem().toString();
			   String shum=bookNameTextField.getText().toString();
			   try {
				   if(shum.equals("")){
					   JOptionPane.showMessageDialog(null,"输入图书名称为空!","提示",JOptionPane.INFORMATION_MESSAGE);
					   return;
				   }
				int flag=new GBiz().AddBjsInformation(bjsname, bookType, shum);
				if(flag==0){//注册失败
					JOptionPane.showMessageDialog(null,"已有同名图书，添加信息失败!","提示",JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					updateTable();
					try {
						int index=0;
						for(int i=0;i<obj.length;i++)
					    for(int j=0;j<3;j++){
							if(obj[i][j].equals(shum))
								index=i;
							}
						table.setRowSelectionInterval(index, index);
						bookNameTextField.setText(null);
						bjsNameComboBox.setSelectedIndex(0);
						bookTypeComboBox.setSelectedItem(0);
						bookNameTextField.requestFocus();
						label_1.setText("共"+new GBiz().getBjsnameListNumber()+"项");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			}
			else if (label.getText().toString().equals("修改")){
				try {
					String bjsname=bjsNameComboBox.getSelectedItem().toString();
					String bookType=bookTypeComboBox.getSelectedItem().toString();
					String shum=bookNameTextField.getText().toString();
				    if(new GBiz().getBookName(uid).equals(bookNameTextField.getText().toString())==false){
				    	int flag=new GBiz().updateBookName(uid, shum);
						if(flag==0){
							JOptionPane.showMessageDialog(null,"已有同名图书，该图书名称修改失败!","错误",JOptionPane.ERROR_MESSAGE);
							bookNameTextField.requestFocus();
						}
				    }
				    if(new GBiz().getBjsName(uid)!=bjsname){
				    	int flag=new GBiz().updateBjsName(uid, bjsname);
				    }
				    if(new GBiz().getBookType(uid)!=bookType){
				    	int flag=new GBiz().updateBookType(uid, bookType);
				    }
					updateTable();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			}
		});
		button_1.setFont(new Font("宋体", Font.BOLD, 20));
		button_1.setBounds(559, 391, 102, 42);
		contentPane.add(button_1);
		
		JButton btnE = new JButton("E:\u5173\u95ED");
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnE.setFont(new Font("宋体", Font.BOLD, 20));
		btnE.setBounds(711, 453, 102, 42);
		contentPane.add(btnE);
		
		
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setForeground(new Color(178, 34, 34));
		label.setBounds(14, 342, 58, 18);
		contentPane.add(label);
		
		try {
			label_1.setFont(new Font("宋体", Font.PLAIN, 17));
			label_1.setText("共"+new GBiz().getBjsListNumber()+"项");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label_1.setBounds(0, 7, 72, 18);
		contentPane.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(52, 0, 854, 283);
		contentPane.add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		table = new JTable(){
			 public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
		};
		table.setRowHeight(30);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table.setRowSelectionAllowed(true);
				label.setText("修改");
				int index=table.getSelectedRow();
				String bjsName=table.getValueAt(index, 0).toString();
				String bookType=table.getValueAt(index, 1).toString();
				String shum=table.getValueAt(index, 2).toString();
				try {
					uid=new GBiz().getID(shum);
					System.out.println(uid);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bjsNameComboBox.setSelectedItem(bjsName);
				bookTypeComboBox.setSelectedItem(bookType);
				bookNameTextField.setText(shum);
				btnNewButton.setEnabled(true);
				
			}
		});
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		table.setDefaultRenderer(Object.class, tcr);
		table.setFont(new Font("华文楷体", Font.PLAIN, 18));
		try {
			obj=new GBiz().getBjsnameList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setModel(new DefaultTableModel(obj, columnNames));
		scrollPane.setViewportView(table);
		
		JLabel label_3 = new JLabel("\u7F16\u8F91\u5BA4\u540D\u79F0");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(24, 373, 102, 27);
		contentPane.add(label_3);
		
        try {
			bjsNameList=new GBiz().getBjsNameList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bjsNameComboBox.setFont(new Font("宋体", Font.PLAIN, 18));
		bjsNameComboBox.setModel(new DefaultComboBoxModel(bjsNameList.toArray()));   //初始化日期
		bjsNameComboBox.setBounds(140, 374, 196, 26);
		bjsNameComboBox.setBackground(Color.WHITE);
		contentPane.add(bjsNameComboBox);
		
		JLabel label_2 = new JLabel("\u56FE\u4E66\u7C7B\u578B");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(24, 431, 92, 27);
		contentPane.add(label_2);
		
		try {
			bookTypeList=new GBiz().getBookTypeNameList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bookTypeComboBox.setFont(new Font("宋体", Font.PLAIN, 18));
		bookTypeComboBox.setModel(new DefaultComboBoxModel(bookTypeList.toArray()));
		bookTypeComboBox.setBounds(140, 434, 196, 27);
		bookTypeComboBox.setBackground(Color.WHITE);
		contentPane.add(bookTypeComboBox);
		
		bookNameTextField = new JTextField();
		bookNameTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNameTextField.setBounds(140, 489, 282, 25);
		contentPane.add(bookNameTextField);
		bookNameTextField.setColumns(10);
		
		JLabel label_4 = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(24, 487, 92, 27);
		contentPane.add(label_4);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
}
