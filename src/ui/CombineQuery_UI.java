package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import bll.Search1_BLL;
import model.Book;
import model.Search1_model;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import java.util.Date;
import java.util.Calendar;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CombineQuery_UI extends JFrame {
	Search1_BLL s1_bll=new Search1_BLL();
	private JPanel contentPane;
	private JComboBox cb_articletype;
	private JComboBox cb_bjs;
	private JComboBox cb_booktype;
	private JButton btn_search;
	private JButton btn_quit;
	private JScrollPane scrollPane_book;
	private JTable book_tb;
	private MyTableModel model; 
	private Vector data;
	private JSpinner sp_startdate;
	private JSpinner sp_enddate;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Search1_model> booklist=new ArrayList<Search1_model>();
	private JLabel label_5;
	private JTextField tf_minprice;
	private JTextField tf_maxprice;
	private SpinnerDateModel start_model;
	private SpinnerDateModel end_model;
	private JTextField tf_author;
	private JLabel label_8;
	private JTextField tf_bookname;
	private JRadioButton rb_publishtime;
	private JButton btn_repaint;
	private JPanel query_panel;
	private JLabel label_4;
	private JLabel label_2;
	private JLabel label;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					try {
						// org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
						BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
						org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
						UIManager.put("RootPane.setupButtonVisible", false);
						// 设置此开关量为false即表示关闭之，BeautyEye LNF中默认是true
						BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
					} catch (Exception e) {
						// TODO exception
					}
					CombineQuery_UI frame = new CombineQuery_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CombineQuery_UI() throws SQLException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setResizable(false);
		setTitle("图书信息（组合查询.retrieve技术）");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btn_search = new JButton("S \u68C0\u7D22");
		btn_search.setFont(new Font("宋体", Font.PLAIN, 17));
		btn_search.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						List<String> querylist=new ArrayList<String>();//查询条件集合
						String author=tf_author.getText();
						boolean author_f=isNull(author);
						String bookname=tf_bookname.getText();
						boolean bookname_f=isNull(bookname);
						String booktype=cb_booktype.getSelectedItem().toString();
						boolean booktype_f=isNull(booktype);
						String bjs=cb_bjs.getSelectedItem().toString();
						boolean bjs_f=isNull(bjs);
						String startdate=sdf.format(sp_startdate.getValue());
						String enddate=sdf.format(sp_enddate.getValue());
						String minprice=tf_minprice.getText();
						boolean minprice_f=isNull(minprice);
						String maxprice=tf_maxprice.getText();
						boolean maxprice_f=isNull(maxprice);
						String articletype=cb_articletype.getSelectedItem().toString();
						boolean art_f=isNull(articletype);
						
						//不为空的条件则加入查询
						if(author_f)
							querylist.add(author);
						if(bookname_f)
							querylist.add(bookname);
						if(booktype_f)
							querylist.add(booktype);
						if(bjs_f)
							querylist.add(bjs);
						if(art_f)
							querylist.add(articletype);
						
						if(rb_publishtime.isSelected())
						{	
							try {
								if(minprice_f==false&&maxprice_f==false) 
								{
									booklist.clear();
									booklist=s1_bll.QueryWithDate(querylist,startdate,enddate);
									RepaintTable();
								}
								else
								{
									booklist.clear();
									booklist=s1_bll.QueryDateAndPrice(querylist,startdate,enddate,minprice,maxprice);
									RepaintTable();
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else
						{
							try {
								if(querylist.isEmpty()&&minprice_f==false&&maxprice_f==false)
								{		
									JOptionPane.showMessageDialog(null, "请输入查询信息！", "错误", JOptionPane.ERROR_MESSAGE);
								}
								else if(querylist.isEmpty()==false&&minprice_f==false&&maxprice_f==false)
								{
									booklist.clear();
									booklist=s1_bll.QueryWithNothing(querylist);
									RepaintTable();
								}
								else 
								{
									booklist.clear();
									booklist=s1_bll.QueryWithPrice(querylist,minprice,maxprice);
									RepaintTable();
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
		query_panel = new JPanel();
		query_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u67E5\u8BE2\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(205, 92, 92)));
		query_panel.setBounds(10, 425, 764, 126);
		contentPane.add(query_panel);
		query_panel.setLayout(null);
		Init();
		InitSpinner();
	}
	
	public void Init() throws SQLException
	{
		
		
		cb_articletype = new JComboBox();
		cb_articletype.setFont(new Font("宋体", Font.PLAIN, 17));
		cb_articletype.setModel(new DefaultComboBoxModel(new String[] {"", "中文", "英文"}));
		cb_articletype.setBounds(533, 86, 117, 26);
		query_panel.add(cb_articletype);
		query_panel.validate();
		query_panel.repaint();
		label = new JLabel("\u6587    \u79CD");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		label.setBounds(443, 86, 78, 26);
		query_panel.add(label);
		
		label_1 = new JLabel("\u7F16\u8F91\u5BA4");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		label_1.setBounds(14, 52, 54, 26);
		query_panel.add(label_1);
		
		cb_bjs = new JComboBox();
		cb_bjs.setFont(new Font("宋体", Font.PLAIN, 15));
		cb_bjs.setModel(new DefaultComboBoxModel(new String[] {"", "自然编辑室", "文学编辑室", "小说编辑室", "生化编辑室", "哲学编辑室"}));
		cb_bjs.setBounds(73, 53, 117, 26);
		query_panel.add(cb_bjs);
		query_panel.validate();
		query_panel.repaint();
		label_2 = new JLabel("\u56FE\u4E66\u5206\u7C7B");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		label_2.setBounds(443, 18, 68, 26);
		query_panel.add(label_2);
		
		cb_booktype = new JComboBox();
		cb_booktype.setFont(new Font("宋体", Font.PLAIN, 15));
		cb_booktype.setModel(new DefaultComboBoxModel(new String[] {"", "自然科学", "小说", "动力工程", "教学理论", "生物工程"}));
		cb_booktype.setBounds(533, 18, 117, 26);
		query_panel.add(cb_booktype);
		query_panel.validate();
		query_panel.repaint();
		
		btn_search.setBounds(664, 18, 92, 26);
		query_panel.add(btn_search);
		
		label_4 = new JLabel("\u81F3");
		label_4.setFont(new Font("宋体", Font.PLAIN, 17));
		label_4.setBounds(443, 52, 63, 26);
		query_panel.add(label_4);
		
		btn_quit = new JButton("Q \u5173\u95ED");
		btn_quit.setFont(new Font("宋体", Font.PLAIN, 17));
		btn_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btn_quit.setBounds(664, 86, 92, 26);
		query_panel.add(btn_quit);
		
		label_5 = new JLabel("最低价");
		label_5.setFont(new Font("宋体", Font.PLAIN, 17));
		label_5.setBounds(14, 86, 54, 26);
		query_panel.add(label_5);
		
		tf_minprice = new JTextField();
		tf_minprice.setFont(new Font("宋体", Font.PLAIN, 17));
		tf_minprice.setBounds(72, 88, 118, 26);
		query_panel.add(tf_minprice);
		tf_minprice.setColumns(10);
		
		label_6 = new JLabel("最高价");
		label_6.setFont(new Font("宋体", Font.PLAIN, 17));
		label_6.setBounds(234, 86, 54, 26);
		query_panel.add(label_6);
		
		tf_maxprice = new JTextField();
		tf_maxprice.setFont(new Font("宋体", Font.PLAIN, 17));
		tf_maxprice.setBounds(302, 88, 117, 26);
		query_panel.add(tf_maxprice);
		tf_maxprice.setColumns(10);
		
		label_7 = new JLabel("\u4F5C  \u8005");
		label_7.setFont(new Font("宋体", Font.PLAIN, 17));
		label_7.setBounds(14, 18, 54, 26);
		query_panel.add(label_7);
		
		tf_author = new JTextField();
		tf_author.setFont(new Font("宋体", Font.PLAIN, 17));
		tf_author.setBounds(73, 19, 117, 26);
		query_panel.add(tf_author);
		tf_author.setColumns(10);
		
		label_8 = new JLabel("书  名");
		label_8.setFont(new Font("宋体", Font.PLAIN, 17));
		label_8.setBounds(234, 18, 54, 26);
		query_panel.add(label_8);
		
		tf_bookname = new JTextField();
		tf_bookname.setFont(new Font("宋体", Font.PLAIN, 17));
		tf_bookname.setBounds(302, 18, 117, 26);
		query_panel.add(tf_bookname);
		tf_bookname.setColumns(10);
		
		btn_repaint = new JButton("R 复位");
		btn_repaint.setFont(new Font("宋体", Font.PLAIN, 17));
		btn_repaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RepaintUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_repaint.setBounds(664, 52, 92, 26);
		query_panel.add(btn_repaint);
		
		booklist=s1_bll.getAllBook();
		InitTable();
		scrollPane_book = new JScrollPane();
		scrollPane_book.setViewportView(book_tb);
		scrollPane_book.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//需要时显现水平滚动条
		scrollPane_book.setBounds(10, 10, 764, 402);
		contentPane.add(scrollPane_book);
		query_panel.validate();
		query_panel.repaint();
	}
	
	public void InitTable() throws SQLException
	{
		book_tb=new JTable();
		Vector<String> colname = new Vector<String>();//列名
		colname.add("书号");
		colname.add("书名");
		colname.add("作者");
		colname.add("出版时间");
		colname.add("图书分类");
		colname.add("编辑室");
		colname.add("开本");
		colname.add("印数");
		colname.add("价格");
		colname.add("文种");
		colname.add("编辑");
		model = new MyTableModel(data, colname);
		book_tb.setModel(model);
		book_tb.setRowHeight(30);
		
		for (int i = 0; i < booklist.size(); i++) {
			Search1_model book = booklist.get(i);
			Vector<String> bk = new Vector<String>();
			bk.add(book.getBookNumber());
			bk.add(book.getBookName());
			bk.add(book.getAuthorName());
			bk.add(sdf.format(book.getPublishTime()));
			bk.add(book.getBookType());
			bk.add(book.getBjsName());
			bk.add(book.getSize());
			bk.add(String.valueOf(book.getPrintNumber()));
			bk.add(String.valueOf(book.getPrice()));
			bk.add(book.getArticleType());
			bk.add(book.getEditorName());
			data = ((MyTableModel) book_tb.getModel()).getDataVector();
			data.add(bk);
		}
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();// 设置table内容居中
		book_tb.setDefaultRenderer(Object.class, tcr1);
		book_tb.setFont(new Font("华文楷体", Font.PLAIN, 16));
		book_tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		FitTableColumns(book_tb);
		
	}
	public void InitSpinner()
	{
		rb_publishtime = new JRadioButton("出版时间");
		rb_publishtime.setFont(new Font("宋体", Font.PLAIN, 17));
		rb_publishtime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rb_publishtime.isSelected())
				{
					sp_startdate.setEnabled(true);
					sp_enddate.setEnabled(true);
				}
				else
				{
					sp_startdate.setEnabled(false);
					sp_enddate.setEnabled(false);
				}
			}
		});
		rb_publishtime.setBounds(200, 52, 102, 26);
		query_panel.add(rb_publishtime);
		
		start_model=new SpinnerDateModel();
		sp_startdate = new JSpinner(start_model);
		sp_startdate.setFont(new Font("宋体", Font.PLAIN, 16));
		sp_startdate.setEnabled(false);
		JSpinner.DateEditor editor1=new JSpinner.DateEditor(sp_startdate,"yyyy-MM-dd");
		sp_startdate.setEditor(editor1);
		sp_startdate.setBounds(302, 52, 117, 26);
		query_panel.add(sp_startdate);
		
		end_model=new SpinnerDateModel();
		sp_enddate = new JSpinner(end_model);
		sp_enddate.setFont(new Font("宋体", Font.PLAIN, 16));
		sp_enddate.setEnabled(false);
		JSpinner.DateEditor editor2=new JSpinner.DateEditor(sp_enddate,"yyyy-MM-dd");
		sp_enddate.setEditor(editor2);
		sp_enddate.setBounds(533, 52, 117, 26);
		query_panel.add(sp_enddate);
	    this.setVisible(true);
	}
	/**
	 * 重绘表格
	 * @throws SQLException
	 */
	public void RepaintTable() throws SQLException
	{
		model.setRowCount(0);
		contentPane.remove(scrollPane_book);
		scrollPane_book = new JScrollPane();
		InitTable();
		scrollPane_book.setViewportView(book_tb);
		scrollPane_book.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//需要时显现水平滚动条
		scrollPane_book.setBounds(10, 10, 764, 427);
		contentPane.add(scrollPane_book);
		contentPane.invalidate();
		contentPane.repaint();
	}
	/**
	 * 判断编辑框是否为空
	 * @param str
	 * @return
	 */
	public boolean isNull(String str)
	{
		if(str.length()<=0)
			return false;
		else
			return true;
	}
	/**
	 * 根据内容自动设置列宽
	 * @param myTable
	 */
	public void FitTableColumns(JTable myTable) {
		book_tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭表格自动调整的状态
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(
				column.getIdentifier());
			int width = (int) myTable.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(myTable,
							column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable,
								myTable.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column);
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}
	/**
	 * 复位
	 * @throws SQLException
	 */
	public void RepaintUI() throws SQLException{
		Date date=Calendar.getInstance().getTime();
		model.setRowCount(0);
		contentPane.remove(scrollPane_book);
		query_panel.remove(tf_author);
		query_panel.remove(tf_bookname);
		query_panel.remove(tf_minprice);
		query_panel.remove(tf_maxprice);
		query_panel.remove(label);
		query_panel.remove(label_1);
		query_panel.remove(label_2);
		query_panel.remove(label_6);
		query_panel.remove(label_4);
		query_panel.remove(label_5);
		query_panel.remove(label_7);
		query_panel.remove(label_8);
		query_panel.remove(cb_articletype);
		query_panel.remove(cb_booktype);
		query_panel.remove(cb_bjs);
		Init();
		rb_publishtime.setSelected(false);
		sp_startdate.setValue(date);
		sp_startdate.setEnabled(false);
		sp_enddate.setValue(date);
		sp_enddate.setEnabled(false);
		cb_booktype.setSelectedIndex(0);
		cb_bjs.setSelectedIndex(0);
		cb_articletype.setSelectedIndex(0);
		contentPane.validate();
		contentPane.repaint();
		
		

	}
}
