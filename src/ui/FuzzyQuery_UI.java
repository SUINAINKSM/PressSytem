package ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import bll.FuzzyQueryDao;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FuzzyQuery_UI extends JFrame {

	private JPanel contentPane;
	private JTable bookTable;
	private JTextField searchTextField;
	public static String[] columnNames1 = { "���", "����", "����", "ͼ�����", "����", "ӡ��", "����", "����ʱ��", "����", "�༭��", "���α༭" };
	private FuzzyQueryDao f=new FuzzyQueryDao();
	public Object[][] obj1;
	private JScrollPane scrollPane;
	private JButton serachButton;
	private JButton exitButton;
	private DefaultTableCellRenderer tcr1;
	public void updateTable() {//����ͼ���б�
		
		bookTable.setRowHeight(30);
		tcr1 = new DefaultTableCellRenderer();
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);// ����Ϊ����
		bookTable.setDefaultRenderer(Object.class, tcr1);
		bookTable.setFont(new Font("���Ŀ���", Font.PLAIN, 16));
		try {
			obj1 = f.getBookList(searchTextField.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookTable.setModel(new DefaultTableModel(obj1, columnNames1));
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		FitTableColumns(bookTable);//�����б�����ݶ�̬�ĵ�����Ӧ���п�
		
		bookTable.repaint();
		bookTable.updateUI();
		// bookTable.setRowSelectionInterval(0, 0);
	}

	/**
	 * ����table���п������е����ݶ��仯
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
						// ���ô˿�����Ϊfalse����ʾ�ر�֮��BeautyEye LNF��Ĭ����true
						BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
					} catch (Exception e) {
						// TODO exception
					}
					FuzzyQuery_UI frame = new FuzzyQuery_UI();
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
	public FuzzyQuery_UI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setResizable(false);
		setTitle("\u56FE\u4E66\u4FE1\u606F\u67E5\u8BE2\uFF08\u591A\u5B57\u6BB5\u6A21\u7CCA\u67E5\u8BE2\uFF09");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 799, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel bookPanel = new JPanel();
		bookPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bookPanel.setBounds(14, 10, 752, 462);
		contentPane.add(bookPanel);
		bookPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 731, 439);
		bookPanel.add(scrollPane);
		
		bookTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		bookTable.setSelectionBackground(Color.LIGHT_GRAY);
		bookTable.setRowHeight(30);
		tcr1 = new DefaultTableCellRenderer();// ����table���ݾ���
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
		bookTable.setDefaultRenderer(Object.class, tcr1);
		bookTable.setFont(new Font("���Ŀ���", Font.PLAIN, 16));
		try {
			obj1 = f.getBookList("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookTable.setModel(new DefaultTableModel(obj1, columnNames1));
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		FitTableColumns(bookTable);
		scrollPane.setViewportView(bookTable);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchPanel.setBounds(14, 485, 752, 44);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel label = new JLabel("\u68C0\u7D22");
		label.setFont(new Font("����", Font.PLAIN, 17));
		label.setBounds(14, 11, 35, 22);
		searchPanel.add(label);
		
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("����", Font.PLAIN, 17));
		searchTextField.setBounds(63, 10, 446, 24);
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		serachButton = new JButton("\u641C\u7D22");
		serachButton.setFont(new Font("����", Font.PLAIN, 17));
		serachButton.setBounds(535, 12, 69, 22);
		searchPanel.add(serachButton);
		
		exitButton = new JButton("\u5173\u95ED");
		exitButton.setFont(new Font("����", Font.PLAIN, 17));
		exitButton.setBounds(629, 12, 69, 22);
		searchPanel.add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		serachButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText=searchTextField.getText();
				//System.out.println(searchText);
				updateTable();
				
			}
		});
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
