package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bll.EBiz;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class XSRK_UI extends JFrame {

	private JPanel contentPane;
	private JPanel RKInformationPanel;
	private JPanel RKQueryPanel;
	private JTable bookTable;
	private TitledBorder titledBorder1,titledBorder2;
	JLabel bookNumLabel = new JLabel("\u4E66  \u53F7");
	private JLabel bookNameLabel = new JLabel("\u4E66  \u540D");
	private JLabel priceLabel = new JLabel("\u5355  \u4EF7");
	private JLabel PriceLabel;
	private JTextField bookNumTextField;
	private JTextField bookNameTextField;
	private JTextField bookPriceTextField;
	private JTextField printNumberTextField;
	private JTextField RKNumTextField;
	private JTextField noteTextField;
	private JTable RKQueryTable;
	public  Object[][] obj1,obj2;
	public  static String[] columnNames1 = {"书号","书名","作者","单价","印数","开本"};
	public  static String[] columnNames2 = {"书号","书名","作者","入库日期","入库册数","备注"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XSRK_UI frame = new XSRK_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public XSRK_UI() {
		setTitle("\u65B0\u4E66\u5165\u5E93\u754C\u9762");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置为点击右上角只关闭当前界面
		setBounds(100, 100, 804, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 27, 758, 222);
		contentPane.add(scrollPane);
		
		bookTable = new JTable(){
			public boolean isCellEditable(int row, int column)
            {
                return false;
            }
		};
		bookTable.setRowHeight(30);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		bookTable.setDefaultRenderer(Object.class, tcr);
		bookTable.setFont(new Font("华文楷体", Font.PLAIN, 18));
		/*try {
			//obj=new EBiz().getBjsnameList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		bookTable.setModel(new DefaultTableModel(obj1, columnNames1));
		scrollPane.setViewportView(bookTable);
		
		RKInformationPanel = new JPanel();
		RKInformationPanel.setBackground(SystemColor.control);
		titledBorder1 = new TitledBorder(null, "共XXX本", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder1.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		RKInformationPanel.setBorder(titledBorder1);
		RKInformationPanel.setBounds(0, 0, 786, 263);
		contentPane.add(RKInformationPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 275, 786, 137);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		bookNumLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		bookNumLabel.setBounds(14, 24, 58, 18);
		panel_1.add(bookNumLabel);
		bookNameLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		
		bookNameLabel.setBounds(14, 59, 58, 18);
		panel_1.add(bookNameLabel);
		priceLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
	
		priceLabel.setBounds(14, 93, 58, 18);
		panel_1.add(priceLabel);
		
		bookNumTextField = new JTextField();
		bookNumTextField.setEditable(false);
		bookNumTextField.setBackground(SystemColor.info);
		bookNumTextField.setBounds(70, 21, 213, 24);
		panel_1.add(bookNumTextField);
		bookNumTextField.setColumns(10);
		
		bookNameTextField = new JTextField();
		bookNameTextField.setEditable(false);
		bookNameTextField.setBackground(SystemColor.info);
		bookNameTextField.setColumns(10);
		bookNameTextField.setBounds(70, 55, 213, 24);
		panel_1.add(bookNameTextField);
		
		bookPriceTextField = new JTextField();
		bookPriceTextField.setEditable(false);
		bookPriceTextField.setBackground(SystemColor.info);
		bookPriceTextField.setBounds(69, 90, 74, 24);
		panel_1.add(bookPriceTextField);
		bookPriceTextField.setColumns(10);
		
		JLabel label = new JLabel("\u5370  \u6570");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(146, 93, 58, 18);
		panel_1.add(label);
		
		printNumberTextField = new JTextField();
		printNumberTextField.setEditable(false);
		printNumberTextField.setBackground(SystemColor.info);
		printNumberTextField.setColumns(10);
		printNumberTextField.setBounds(209, 90, 74, 24);
		panel_1.add(printNumberTextField);
		
		JLabel label_1 = new JLabel("\u5165\u5E93\u518C\u6570");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(309, 24, 80, 18);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("\u5907    \u6CE8");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(309, 59, 80, 18);
		panel_1.add(label_2);
		
		RKNumTextField = new JTextField();
		RKNumTextField.setBounds(399, 21, 74, 24);
		panel_1.add(RKNumTextField);
		RKNumTextField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5165\u5E93\u65E5\u671F");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(487, 24, 81, 18);
		panel_1.add(label_3);
		
		noteTextField = new JTextField();
		noteTextField.setBounds(399, 59, 271, 52);
		panel_1.add(noteTextField);
		noteTextField.setColumns(10);
		
		JButton saveButton = new JButton("\u4FDD\u5B58");
		saveButton.setFont(new Font("宋体", Font.PLAIN, 18));
		saveButton.setBackground(UIManager.getColor("Button.light"));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveButton.setBounds(693, 55, 79, 27);
		panel_1.add(saveButton);
		
		JSpinner RKDateSpinner = new JSpinner();
		RKDateSpinner.setBounds(567, 21, 103, 24);
		panel_1.add(RKDateSpinner);
		
		RKQueryPanel = new JPanel();
		RKQueryPanel.setBackground(SystemColor.control);
		titledBorder2 = new TitledBorder(null, "图书入库明细查询（共1条）", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder2.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		RKQueryPanel.setBorder(titledBorder2);
		RKQueryPanel.setBounds(0, 425, 786, 128);
		contentPane.add(RKQueryPanel);
		RKQueryPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 26, 642, 88);
		RKQueryPanel.add(scrollPane_1);
		
		RKQueryTable = new JTable();
		bookTable = new JTable(){
			public boolean isCellEditable(int row, int column)
            {
                return false;
            }
		};
		RKQueryTable.setRowHeight(30);
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		RKQueryTable.setDefaultRenderer(Object.class, tcr1);
		RKQueryTable.setFont(new Font("华文楷体", Font.PLAIN, 18));
		/*try {
			//obj=new EBiz().getBjsnameList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		RKQueryTable.setModel(new DefaultTableModel(obj2, columnNames2));
		scrollPane_1.setViewportView(RKQueryTable);
		
		JSpinner queryDateSpinner = new JSpinner();
		queryDateSpinner.setModel(new SpinnerDateModel(new Date(1555689600000L), null, null, Calendar.DAY_OF_YEAR));
		queryDateSpinner.setBounds(670, 13, 102, 24);
		RKQueryPanel.add(queryDateSpinner);
		
		JButton button = new JButton("\u5173\u95ED");
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		button.setBackground(UIManager.getColor("Button.light"));
		button.setBounds(693, 87, 76, 27);
		RKQueryPanel.add(button);
		
		JButton button_1 = new JButton("\u67E5\u8BE2");
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		button_1.setBackground(UIManager.getColor("Button.light"));
		button_1.setBounds(693, 47, 76, 27);
		RKQueryPanel.add(button_1);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
