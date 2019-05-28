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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import bll.BookInformationBiz;
import bll.EBiz;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TSDJ_UI extends JFrame {

	private JPanel contentPane;
	private TitledBorder titledBorder1, titledBorder2, titledBorder3, titledBorder4;
	private JLabel PriceLabel;
	public Object[][] obj1;
	public static int count, isSave = 1;
	public static String[] columnNames1 = { "书号", "书名", "作者", "图书分类", "开本", "印数", "定价", "出版时间", "文种", "编辑室", "责任编辑" };
	private JTextField searchTextField;
	private JTable bookTable;
	private ButtonGroup group;
	private JTextField bookNameTextField;
	private JTextField bookNumTextField;
	private JTextField printNumTextField;
	private JTextField authorNameTextField;
	private JComboBox bookTypeComboBox;
	private JComboBox bjsNameComboBox;
	private JRadioButton bookNameRadioButton;
	private JRadioButton bookNumRadioButton;
	private JRadioButton bjsNameRadioButton;
	private JRadioButton publishTimeRadioButton;
	private JPanel bookPanel;
	private JPanel operationPanel;
	private JComboBox sizeComboBox;
	private JComboBox languageComboBox;
	private JSpinner priceSpinner;
	private JSpinner publishTimeSpinner;
	private JComboBox EditorComboBox;
	private JButton saveButton;
	private List<String> array = new ArrayList<String>();
	private List<String> array1;
	private List<String> array2;
	private List<String> array3;
	private List<String> array4;
	private JButton clearButton;
	private JButton deleteButton;
	private JButton closeButton;
	public static int sortNum=1;
	private int index;
	private JScrollPane scrollPane;
	private int bid;
	private JRadioButton bookTypeRadioButton;
	private JLabel label_6;

	/**
	 * Launch the application.
	 */
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
					TSDJ_UI frame = new TSDJ_UI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void updateTable() {
		try {
			obj1 = new BookInformationBiz().getBookList(searchTextField.getText(), sortNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookTable.setModel(new DefaultTableModel(obj1, columnNames1));
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		FitTableColumns(bookTable);

		bookTable.repaint();
		bookTable.updateUI();
		// bookTable.setRowSelectionInterval(0, 0);
	}

	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public void resetUI() {
		try {
			titledBorder1 = new TitledBorder(null, "共" + new BookInformationBiz().getBookTableNumber() + "本",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		sortNum=1;
		titledBorder1.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		bookPanel.setBorder(titledBorder1);
		titledBorder2 = new TitledBorder(null, "添加", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(51, 102, 153));
		titledBorder2.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		operationPanel.setBorder(titledBorder2);
		bookNameTextField.setText(null);
		bookNumTextField.setText(null);
		printNumTextField.setText(null);
		authorNameTextField.setText(null);
		bookTypeComboBox.setSelectedIndex(0);
		bjsNameComboBox.setSelectedIndex(0);
		sizeComboBox.setSelectedIndex(0);
		languageComboBox.setSelectedIndex(0);
		priceSpinner.setValue(17.55);
		publishTimeSpinner.setValue(new Date());
		EditorComboBox.setSelectedIndex(0);
		deleteButton.setEnabled(false);
		bookNumRadioButton.setSelected(true);
		bookNameTextField.requestFocus();
		updateTable();
	}
	public void resetUI1() {
		try {
			titledBorder1 = new TitledBorder(null, "共" + new BookInformationBiz().getBookTableNumber() + "本",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		titledBorder1.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		bookPanel.setBorder(titledBorder1);
		titledBorder2 = new TitledBorder(null, "添加", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(51, 102, 153));
		titledBorder2.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		operationPanel.setBorder(titledBorder2);
		bookNameTextField.setText(null);
		bookNumTextField.setText(null);
		printNumTextField.setText(null);
		authorNameTextField.setText(null);
		bookTypeComboBox.setSelectedIndex(0);
		bjsNameComboBox.setSelectedIndex(0);
		sizeComboBox.setSelectedIndex(0);
		languageComboBox.setSelectedIndex(0);
		priceSpinner.setValue(17.55);
		publishTimeSpinner.setValue(new Date());
		EditorComboBox.setSelectedIndex(0);
		deleteButton.setEnabled(false);
		bookNameTextField.requestFocus();
		updateTable();
	}
	/**
	 * 设置table的列宽随着列的内容而变化
	 * 
	 * @param myTable
	 */
	public void FitTableColumns(JTable myTable) {
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) myTable.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column);
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}

	public void saveOrModify() {
		if (titledBorder2.getTitle().equals("添加")) {
			System.out.println(++count);
			String bookName = bookNameTextField.getText();
			String authorName = authorNameTextField.getText();
			String bookNum = bookNumTextField.getText();
			String bookTypeName = bookTypeComboBox.getSelectedItem().toString();
			String sizeName = sizeComboBox.getSelectedItem().toString();
			if (isNumeric(printNumTextField.getText())==false) {// 印数不为正整数
				JOptionPane.showMessageDialog(null, "印数输入不为正整数！", "提示", JOptionPane.ERROR_MESSAGE);
				printNumTextField.setText("");
				printNumTextField.requestFocus();
				return;
			}
			int printNum = Integer.valueOf(printNumTextField.getText());
			double price = Double.valueOf(priceSpinner.getValue().toString());
			// Date date1=new Date();
			String publishTime = publishTimeSpinner.getValue().toString();
			String languageName = languageComboBox.getSelectedItem().toString();
			String bjsName = bjsNameComboBox.getSelectedItem().toString();
			String editorName = EditorComboBox.getSelectedItem().toString();
			if (bookName.equals("")) {// 未输入书名
				JOptionPane.showMessageDialog(null, "未输入书名！", "提示", JOptionPane.ERROR_MESSAGE);
				bookNameTextField.setText("");
				bookNameTextField.requestFocus();
				return;
			}
			isSave = 0;
			if (authorName.equals("")) {// 未输入作者
				JOptionPane.showMessageDialog(null, "未输入作者！", "提示", JOptionPane.ERROR_MESSAGE);
				authorNameTextField.setText("");
				authorNameTextField.requestFocus();
				return;
			}
			if (bookNum.equals("")) {// 未输入书号
				JOptionPane.showMessageDialog(null, "未输入书号！", "提示", JOptionPane.ERROR_MESSAGE);
				bookNumTextField.setText("");
				bookNumTextField.requestFocus();
				return;
			}
			if (bookNumTextField.getText().equals("")) {// 未输入印数
				JOptionPane.showMessageDialog(null, "未输入印数！", "提示", JOptionPane.ERROR_MESSAGE);
				printNumTextField.setText("");
				printNumTextField.requestFocus();
				return;
			}
		

			try {
				int flag;
				flag = new BookInformationBiz().AddBookInformation(bookName, authorName, bookNum, bookTypeName,
						sizeName, printNum, price, publishTime, languageName, bjsName, editorName);
				System.out.println("第" + count + "次" + flag);
				if (flag == 0) {
					JOptionPane.showMessageDialog(null, "该书名已经被使用！", "提示", JOptionPane.ERROR_MESSAGE);
					bookNameTextField.setText("");
					bookNameTextField.requestFocus();
				} else if (flag == -1) {
					JOptionPane.showMessageDialog(null, "该图书号已经被使用!", "提示", JOptionPane.ERROR_MESSAGE);
					bookNumTextField.setText("");
					bookNumTextField.requestFocus();
				} else {
					resetUI1();
					//updateTable();
					try {
						titledBorder1 = new TitledBorder(null,
								"共" + new BookInformationBiz().getBookTableNumber() + "本", TitledBorder.LEADING,
								TitledBorder.TOP, null, new Color(51, 102, 153));
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					titledBorder1.setTitleFont(new Font("宋体", Font.PLAIN, 15));
					bookPanel.setBorder(titledBorder1);
					int index = 0;
					for (int i = 0; i < obj1.length; i++)
						for (int j = 0; j < 11; j++) {
							if (obj1[i][j].equals(bookName))
								index = i;
						}
					bookTable.setRowSelectionInterval(index, index);
					bookTable.scrollRectToVisible(bookTable.getCellRect(index, 0, true));
					bookTable.setSelectionBackground(Color.LIGHT_GRAY);

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {// 修改
			try {
				String bookName = bookNameTextField.getText();
				String authorName = authorNameTextField.getText();
				String bookNum = bookNumTextField.getText();
				String bookTypeName = bookTypeComboBox.getSelectedItem().toString();
				String sizeName = sizeComboBox.getSelectedItem().toString();
				int printNum = Integer.valueOf(printNumTextField.getText());
				double price = Double.valueOf(priceSpinner.getValue().toString());
				// Date date1=new Date();
				String publishTime = publishTimeSpinner.getValue().toString();
				String languageName = languageComboBox.getSelectedItem().toString();
				String bjsName = bjsNameComboBox.getSelectedItem().toString();
				String editorName = EditorComboBox.getSelectedItem().toString();
				System.out.println(bid);
				if (bookName.equals("")) {// 未输入书名
					JOptionPane.showMessageDialog(null, "未输入书名！", "提示", JOptionPane.ERROR_MESSAGE);
					bookNameTextField.setText(new BookInformationBiz().getbookName(bid));
					bookNameTextField.requestFocus();
				}
				if (authorName.equals("")) {// 未输入作者
					JOptionPane.showMessageDialog(null, "未输入作者！", "提示", JOptionPane.ERROR_MESSAGE);
					authorNameTextField.setText(new BookInformationBiz().getAuthorName(bid));
					authorNameTextField.requestFocus();
				}
				if (bookNum.equals("")) {// 未输入书号
					JOptionPane.showMessageDialog(null, "未输入书号！", "提示", JOptionPane.ERROR_MESSAGE);
					bookNumTextField.setText(new BookInformationBiz().getbookNum(bid));
					bookNumTextField.requestFocus();
				}
				if (printNumTextField.getText().equals("")) {// 未输入印数
					JOptionPane.showMessageDialog(null, "未输入印数！", "提示", JOptionPane.ERROR_MESSAGE);
					printNumTextField.setText(new BookInformationBiz().getprintNum(bid));
					printNumTextField.requestFocus();
				}
				if (new BookInformationBiz().getbookName(bid).equals(bookNameTextField.getText().toString()) == false) {
					int flag = new BookInformationBiz().updateBookName(bid, bookName);
					if (flag == 0) {
						JOptionPane.showMessageDialog(null, "已有同名图书，该图书名称修改失败!", "错误", JOptionPane.ERROR_MESSAGE);
						bookNameTextField.setText(new BookInformationBiz().getbookName(bid));
						bookNameTextField.requestFocus();
					}
				}
				if (new BookInformationBiz().getbookNum(bid).equals(bookNumTextField.getText().toString()) == false) {
					int flag = new BookInformationBiz().updateBookNum(bid, bookNum);
					if (flag == 0) {
						JOptionPane.showMessageDialog(null, "已有同号图书，该书号修改失败!", "错误", JOptionPane.ERROR_MESSAGE);
						bookNumTextField.setText(new BookInformationBiz().getbookNum(bid));
						bookNumTextField.requestFocus();
					}
				}
				BookInformationBiz bbz = new BookInformationBiz();
				bbz.updateAuthorName(bid, authorName);
				bbz.updateBjsID(bid, bjsName);
				bbz.updateBookTypeName(bid, bookTypeName);
				bbz.updateSizeID(bid, sizeName);
				if (isNumeric(String.valueOf(printNum)))
					bbz.updatePrintNum(bid, printNum);
				else {
					JOptionPane.showMessageDialog(null, "输入图书的印数不为正整数!", "错误", JOptionPane.ERROR_MESSAGE);
					printNumTextField.setText(new BookInformationBiz().getprintNum(bid));
					printNumTextField.requestFocus();
				}
				bbz.updatePrice(bid, price);
				Date date = null;
				try {
					date = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(publishTime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 格式化
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sDate = sdf.format(date);
				bbz.updatePublishTime(bid, date);
				bbz.updateLanguageID(bid, languageName);
				bbz.updateEditorNameID(bid, editorName);
				updateTable();
				int index = bookTable.getSelectedRow();
				for (int i = 0; i < obj1.length; i++)
					for (int j = 0; j < 11; j++) {
						if (obj1[i][j].equals(bookName))
							index = i;
					}
				bookTable.setRowSelectionInterval(index, index);
				bookTable.scrollRectToVisible(bookTable.getCellRect(index, 0, true));
				bookTable.setSelectionBackground(Color.LIGHT_GRAY);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public TSDJ_UI() {
		setResizable(false);
		setTitle("\u56FE\u4E66\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置为点击右上角只关闭当前界面
		setBounds(100, 100, 800, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);

		contentPane.setLayout(null);

		JPanel searchPanel = new JPanel();
		titledBorder3 = new TitledBorder(null, "检索", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(51, 102, 153));
		titledBorder3.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		searchPanel.setBorder(titledBorder3);
		searchPanel.setBounds(0, 0, 387, 60);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);

		searchTextField = new JTextField();
		searchTextField.setBounds(14, 23, 254, 24);
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);

		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		searchButton.setBackground(UIManager.getColor("Button.light"));
		searchButton.setFont(new Font("宋体", Font.PLAIN, 18));
		searchButton.setBounds(282, 22, 80, 27);
		searchPanel.add(searchButton);

		JPanel sortPanel = new JPanel();
		titledBorder4 = new TitledBorder(null, "排序", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(51, 102, 153));
		titledBorder4.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		sortPanel.setBorder(titledBorder4);
		sortPanel.setBounds(392, 0, 392, 60);
		contentPane.add(sortPanel);
		sortPanel.setLayout(null);
		group = new ButtonGroup();
		bookNumRadioButton = new JRadioButton("\u4E66\u53F7");
		bookNumRadioButton.setSelected(true);
		bookNumRadioButton.setBounds(10, 24, 59, 27);
		sortPanel.add(bookNumRadioButton);
		group.add(bookNumRadioButton);
		bookNameRadioButton = new JRadioButton("\u4E66\u540D");
		bookNameRadioButton.setBounds(75, 24, 59, 27);
		sortPanel.add(bookNameRadioButton);
        group.add(bookNameRadioButton);
		bjsNameRadioButton = new JRadioButton("\u7F16\u8F91\u5BA4");
		bjsNameRadioButton.setBounds(140, 24, 73, 27);
		sortPanel.add(bjsNameRadioButton);
        group.add(bjsNameRadioButton);
		publishTimeRadioButton = new JRadioButton("\u51FA\u7248\u65F6\u95F4");
		publishTimeRadioButton.setBounds(212, 24, 89, 27);
		sortPanel.add(publishTimeRadioButton);
        group.add(publishTimeRadioButton);
		bookTypeRadioButton = new JRadioButton("\u56FE\u4E66\u5206\u7C7B");
		bookTypeRadioButton.setBounds(303, 24, 83, 27);
		sortPanel.add(bookTypeRadioButton);
        group.add(bookTypeRadioButton);
     // 构造一个监听器，响应checkBox事件
        ActionListener actionListener = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          
            // 通过getSource()
            Object sourceObject = e.getSource();
            if (sourceObject instanceof JRadioButton) {
              JRadioButton sourceButton = (JRadioButton) sourceObject;
              System.out.printf("selected JRadioButton is %s\n",
                  sourceButton.getText());
              String str=sourceButton.getText();
              if(str.equals("书号")){
            	  sortNum=1;
            	  updateTable();
              }
              else if(str.equals("书名")){
            	  sortNum=2;
            	  updateTable();
              }
              else if(str.equals("编辑室")){
            	  sortNum=3;
            	  updateTable();
              }
              else if(str.equals("出版时间")){
            	  sortNum=4;
            	  updateTable();
              }
              else if(str.equals("图书分类")){
            	  sortNum=5;
            	  updateTable();
              }
              
            }
           
            
          }
        };
        bookNumRadioButton.addActionListener(actionListener);
        this.bookNameRadioButton.addActionListener(actionListener);
        this.bjsNameRadioButton.addActionListener(actionListener);
        this.bookTypeRadioButton.addActionListener(actionListener);
        this.publishTimeRadioButton.addActionListener(actionListener);
		bookPanel = new JPanel();
		try {
			titledBorder1 = new TitledBorder(null, "共" + new BookInformationBiz().getBookTableNumber() + "本",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		titledBorder1.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		bookPanel.setBorder(titledBorder1);
		bookPanel.setBounds(0, 65, 793, 288);
		contentPane.add(bookPanel);

		bookPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 23, 765, 252);
		bookPanel.add(scrollPane);

		bookTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		bookTable.setSelectionBackground(Color.LIGHT_GRAY);
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// table.setRowSelectionAllowed(true);
				titledBorder2 = new TitledBorder(null, "修改", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(51, 102, 153));
				titledBorder2.setTitleFont(new Font("宋体", Font.PLAIN, 15));
				operationPanel.setBorder(titledBorder2);
				index = bookTable.getSelectedRow();
				deleteButton.setEnabled(true);

				/*
				 * String bjsName=bookTable.getValueAt(index, 0).toString();
				 * String bookType=table.getValueAt(index, 1).toString(); String
				 * shum=table.getValueAt(index, 2).toString();
				 */
				String bookName = bookTable.getValueAt(index, 1).toString();
				bookNameTextField.setText(bookName);
				try {
					bid = new BookInformationBiz().getID(bookName);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String authorName = bookTable.getValueAt(index, 2).toString();
				authorNameTextField.setText(authorName);
				String bookNum = bookTable.getValueAt(index, 0).toString();
				bookNumTextField.setText(bookNum);
				String bookTypeName = bookTable.getValueAt(index, 3).toString();
				String sizeName = bookTable.getValueAt(index, 4).toString();
				int printNum = Integer.valueOf(bookTable.getValueAt(index, 5).toString());
				printNumTextField.setText(String.valueOf(printNum));
				double price = Double.valueOf(bookTable.getValueAt(index, 6).toString());
				// Date date1=new Date();
				String publishTime = bookTable.getValueAt(index, 7).toString();
				String languageName = bookTable.getValueAt(index, 8).toString();
				String bjsName = bookTable.getValueAt(index, 9).toString();
				String editorName = bookTable.getValueAt(index, 10).toString();
				// btnNewButton.setEnabled(true);
				bookTypeComboBox.setSelectedItem(bookTypeName);
				bjsNameComboBox.setSelectedItem(bjsName);
				sizeComboBox.setSelectedItem(sizeName);
				languageComboBox.setSelectedItem(languageName);
				priceSpinner.setValue(price);

				try {
					publishTimeSpinner.setValue(new BookInformationBiz().getPublishTime(bookName));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EditorComboBox.setSelectedItem(editorName);
			}
		});
		bookTable.setRowHeight(30);

		// bookTable.setRowHeight(50);
		ActionMap am = (ActionMap) UIManager.get("Table.actionMap");
		am.put("selectNextRowCell", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titledBorder2.setTitle("修改");
				operationPanel.repaint();
				// 自己的处理代码
				String bookName = bookTable.getValueAt(bookTable.getSelectedRow(), 1).toString();
				bookNameTextField.setText(bookName);
				try {
					bid = new BookInformationBiz().getID(bookName);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String authorName = bookTable.getValueAt(bookTable.getSelectedRow(), 2).toString();
				authorNameTextField.setText(authorName);
				System.out.println(authorName);
				String bookNum = bookTable.getValueAt(bookTable.getSelectedRow(), 0).toString();
				bookNumTextField.setText(bookNum);
				String bookTypeName = bookTable.getValueAt(bookTable.getSelectedRow(), 3).toString();
				String sizeName = bookTable.getValueAt(bookTable.getSelectedRow(), 4).toString();
				int printNum = Integer.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 5).toString());
				printNumTextField.setText(String.valueOf(printNum));
				double price = Double.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 6).toString());
				// Date date1=new Date();
				String publishTime = bookTable.getValueAt(bookTable.getSelectedRow(), 7).toString();
				String languageName = bookTable.getValueAt(bookTable.getSelectedRow(), 8).toString();
				String bjsName = bookTable.getValueAt(bookTable.getSelectedRow(), 9).toString();
				String editorName = bookTable.getValueAt(bookTable.getSelectedRow(), 10).toString();
				// btnNewButton.setEnabled(true);
				bookTypeComboBox.setSelectedItem(bookTypeName);
				bjsNameComboBox.setSelectedItem(bjsName);
				sizeComboBox.setSelectedItem(sizeName);
				languageComboBox.setSelectedItem(languageName);
				priceSpinner.setValue(price);

				try {
					publishTimeSpinner.setValue(new BookInformationBiz().getPublishTime(bookName));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EditorComboBox.setSelectedItem(editorName);
				bookNameTextField.requestFocus();

				// CURRENTID =
				// BooksDao.getInstance().QueryIDByBookName(bookName);//获得当前选中的id
				deleteButton.setEnabled(true);
				saveButton.setEnabled(true);
			}
		});
		bookTable.setActionMap(am);
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		bookTable.setDefaultRenderer(Object.class, tcr1);
		bookTable.setFont(new Font("华文楷体", Font.PLAIN, 16));
		try {
			obj1 = new BookInformationBiz().getBookList(searchTextField.getText(),sortNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookTable.setModel(new DefaultTableModel(obj1, columnNames1));
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		FitTableColumns(bookTable);
		scrollPane.setViewportView(bookTable);

		operationPanel = new JPanel();
		titledBorder2 = new TitledBorder(null, "添加", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(51, 102, 153));
		titledBorder2.setTitleFont(new Font("宋体", Font.PLAIN, 15));
		operationPanel.setBorder(titledBorder2);
		System.out.println(titledBorder2.getTitle());
		operationPanel.setBounds(0, 367, 793, 212);
		contentPane.add(operationPanel);
		operationPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u4E66  \u540D");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		lblNewLabel.setBounds(14, 22, 55, 18);
		operationPanel.add(lblNewLabel);

		JLabel label = new JLabel("\u4E66  \u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		label.setBounds(14, 62, 55, 18);
		operationPanel.add(label);

		JLabel label_1 = new JLabel("\u5F00  \u672C");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		label_1.setBounds(14, 102, 55, 18);
		operationPanel.add(label_1);

		JLabel label_2 = new JLabel("\u6587  \u79CD");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		label_2.setBounds(14, 142, 55, 18);
		operationPanel.add(label_2);

		bookNameTextField = new JTextField();
		bookNameTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				authorNameTextField.requestFocus();
			}
		});
		bookNameTextField.setFont(new Font("宋体", Font.PLAIN, 17));
		bookNameTextField.setBounds(83, 18, 285, 27);
		operationPanel.add(bookNameTextField);
		bookNameTextField.setColumns(10);

		bookNumTextField = new JTextField();
		bookNumTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeComboBox.requestFocus();
			}
		});
		bookNumTextField.setFont(new Font("宋体", Font.PLAIN, 17));
		bookNumTextField.setColumns(10);
		bookNumTextField.setBounds(83, 58, 285, 27);
		operationPanel.add(bookNumTextField);

		sizeComboBox = new JComboBox();
		array1 = new ArrayList<String>();
		try {
			array1 = new BookInformationBiz().getSizeNameList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sizeComboBox.setModel(new DefaultComboBoxModel(array1.toArray()));
		sizeComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					printNumTextField.requestFocus();
				}
			}
		});

		sizeComboBox.setFont(new Font("宋体", Font.PLAIN, 16));
		sizeComboBox.setBounds(83, 98, 93, 27);
		operationPanel.add(sizeComboBox);

		JLabel label_3 = new JLabel("\u5370  \u6570");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		label_3.setBounds(190, 102, 55, 18);
		operationPanel.add(label_3);

		printNumTextField = new JTextField();
		printNumTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priceSpinner.requestFocus();
			}
		});
		printNumTextField.setFont(new Font("宋体", Font.PLAIN, 18));
		printNumTextField.setBounds(248, 99, 120, 27);
		operationPanel.add(printNumTextField);
		printNumTextField.setColumns(10);

		languageComboBox = new JComboBox();
		array2 = new ArrayList<String>();
		try {
			array2 = new BookInformationBiz().getLanguageNameList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		languageComboBox.setModel(new DefaultComboBoxModel(array2.toArray()));
		languageComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					bjsNameComboBox.requestFocus();
				}
			}
		});
		languageComboBox.setFont(new Font("宋体", Font.PLAIN, 16));
		languageComboBox.setBounds(83, 138, 93, 27);
		operationPanel.add(languageComboBox);

		JLabel label_4 = new JLabel("\u7F16\u8F91\u5BA4");
		label_4.setFont(new Font("宋体", Font.PLAIN, 17));
		label_4.setBounds(190, 142, 55, 18);
		operationPanel.add(label_4);

		bjsNameComboBox = new JComboBox();
		bjsNameComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				// System.out.println(bjsNameComboBox.getSelectedItem().toString());

				array4 = new ArrayList<String>();
				try {

					array4 = new BookInformationBiz().getBjsEitorList(bjsNameComboBox.getSelectedItem().toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EditorComboBox.setModel(new DefaultComboBoxModel(array4.toArray()));
			}
		});
		array3 = new ArrayList<String>();
		try {
			array3 = new BookInformationBiz().getBjsNameList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bjsNameComboBox.setModel(new DefaultComboBoxModel(array3.toArray()));
		bjsNameComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					EditorComboBox.requestFocus();
				}
			}
		});
		bjsNameComboBox.setFont(new Font("宋体", Font.PLAIN, 15));
		bjsNameComboBox.setBounds(248, 139, 120, 27);
		operationPanel.add(bjsNameComboBox);

		JLabel label_5 = new JLabel("\u4F5C    \u8005");
		label_5.setFont(new Font("宋体", Font.PLAIN, 17));
		label_5.setBounds(383, 22, 70, 18);
		operationPanel.add(label_5);

		authorNameTextField = new JTextField();
		authorNameTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookNumTextField.requestFocus();
			}
		});
		authorNameTextField.setFont(new Font("宋体", Font.PLAIN, 17));
		authorNameTextField.setColumns(10);
		authorNameTextField.setBounds(467, 20, 296, 26);
		operationPanel.add(authorNameTextField);

		label_6 = new JLabel("\u56FE\u4E66\u5206\u7C7B");
		label_6.setFont(new Font("宋体", Font.PLAIN, 17));
		label_6.setBounds(383, 62, 70, 18);
		operationPanel.add(label_6);

		bookTypeComboBox = new JComboBox();
		array = new ArrayList<String>();
		try {
			array = new BookInformationBiz().getBookTypeNameList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bookTypeComboBox.setModel(new DefaultComboBoxModel(array.toArray()));
		bookTypeComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					sizeComboBox.requestFocus();
				}
			}
		});

		bookTypeComboBox.setFont(new Font("宋体", Font.PLAIN, 17));
		bookTypeComboBox.setBounds(466, 59, 297, 27);
		operationPanel.add(bookTypeComboBox);

		JLabel label_7 = new JLabel("\u5B9A    \u4EF7");
		label_7.setFont(new Font("宋体", Font.PLAIN, 17));
		label_7.setBounds(383, 102, 70, 18);
		operationPanel.add(label_7);

		priceSpinner = new JSpinner();
		priceSpinner.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					publishTimeSpinner.requestFocus();
				}
			}
		});
		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(12.55, 0.00001, 100.0, 0.01);
		priceSpinner.setModel(spinnerNumberModel);
		// priceSpinner.setModel(new SpinnerNumberModel(new Double(2.00), null,
		// null, new Double(1)));
		priceSpinner.setFont(new Font("宋体", Font.PLAIN, 16));
		priceSpinner.setBounds(467, 99, 101, 26);
		operationPanel.add(priceSpinner);

		JLabel label_8 = new JLabel("\u51FA\u7248\u65F6\u95F4");
		label_8.setFont(new Font("宋体", Font.PLAIN, 17));
		label_8.setBounds(574, 102, 70, 18);
		operationPanel.add(label_8);

		publishTimeSpinner = new JSpinner();
		publishTimeSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				System.out.println(publishTimeSpinner.getValue().toString());
			}
		});
		publishTimeSpinner.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					languageComboBox.requestFocus();
				}
			}
		});
		/*
		 * SpinnerDateModel model = new SpinnerDateModel(); //获得JSPinner对象
		 * JSpinner year = new JSpinner(model); year.setValue(new Date());
		 * //设置时间格式 JSpinner.DateEditor editor = new
		 * JSpinner.DateEditor(year,"yyyy-MM-dd HH:mm:ss");
		 * year.setEditor(editor); year.setBounds(34, 67, 219, 22);
		 */
		SpinnerDateModel model = new SpinnerDateModel();
		publishTimeSpinner.setModel(model);
		publishTimeSpinner.setValue(new Date());
		JSpinner.DateEditor editor = new JSpinner.DateEditor(publishTimeSpinner, "yyyy-MM-dd");
		publishTimeSpinner.setEditor(editor);
		publishTimeSpinner.setFont(new Font("宋体", Font.PLAIN, 16));
		publishTimeSpinner.setBounds(650, 99, 113, 27);
		operationPanel.add(publishTimeSpinner);

		JLabel label_9 = new JLabel("\u8D23\u4EFB\u7F16\u8F91");
		label_9.setFont(new Font("宋体", Font.PLAIN, 17));
		label_9.setBounds(383, 142, 81, 18);
		operationPanel.add(label_9);

		EditorComboBox = new JComboBox();
		array4 = new ArrayList<String>();
		try {

			array4 = new BookInformationBiz().getBjsEitorList(bjsNameComboBox.getSelectedItem().toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EditorComboBox.setModel(new DefaultComboBoxModel(array4.toArray()));
		EditorComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					saveButton.requestFocus();
				}
			}
		});
		EditorComboBox.setFont(new Font("宋体", Font.PLAIN, 16));
		EditorComboBox.setBounds(467, 139, 101, 27);
		operationPanel.add(EditorComboBox);

		clearButton = new JButton("\u6E05\u9664");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetUI();
			}
		});
		clearButton.setBackground(UIManager.getColor("Button.light"));
		clearButton.setFont(new Font("宋体", Font.PLAIN, 18));
		clearButton.setBounds(363, 176, 81, 27);
		operationPanel.add(clearButton);
		deleteButton = new JButton("\u5220\u9664");
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bookName = bookNameTextField.getText();
				int selection = JOptionPane.showConfirmDialog(null, "确认删除该图书？", "注意", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (selection == JOptionPane.OK_OPTION) {
					try {
						new BookInformationBiz().deleteBook(bookName);
						resetUI1();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					resetUI();
				} else {
				}
			}
		});
		deleteButton.setBackground(UIManager.getColor("Button.light"));
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 18));
		deleteButton.setBounds(467, 176, 81, 27);
		operationPanel.add(deleteButton);
		closeButton = new JButton("\u5173\u95ED");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		closeButton.setBackground(UIManager.getColor("Button.light"));
		closeButton.setFont(new Font("宋体", Font.PLAIN, 18));
		closeButton.setBounds(679, 176, 81, 27);
		operationPanel.add(closeButton);

		saveButton = new JButton("\u4FDD\u5B58");
		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (count != 1) {
					System.out.println("哈哈2");
					saveOrModify();
					bookNameTextField.requestFocus();
					count = 0;
				}

			}
		});
		saveButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					System.out.println("哈哈1");
					saveOrModify();
					count = 1;

				}
			}
		});

		saveButton.setFont(new Font("宋体", Font.PLAIN, 18));
		saveButton.setBackground(UIManager.getColor("Button.light"));
		saveButton.setBounds(574, 176, 81, 27);
		operationPanel.add(saveButton);
		bookNameTextField.requestFocus();
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		bookNameTextField.requestFocus();
	}
}
