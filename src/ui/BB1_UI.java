package ui;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import com.itextpdf.text.DocumentException;

import bll.ALLMethod;
import jxl.write.WriteException;
import model.BookInfo;
import model.sqlyuju;

public class BB1_UI extends JFrame
{

    private ALLMethod method;
    private JTable table;
    private List<BookInfo> binfo;
    private String par[];
    private String sql;
    SimpleDateFormat dateFormat;
    private JPanel contentPane;
    private JScrollPane scrollPane_1;
    private JPanel panel;
    private JTable table_1;
    private Choice choice_1;
    private Choice choice_2;
    private JTextField textField;
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private String username = "123456";

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    BB1_UI frame = new BB1_UI();
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * 
     * @throws ParseException
     */
    public BB1_UI() throws ParseException
    {
        setTitle("\u56FE\u4E66\u5E93\u5B58\u4E00\u89C8");
        setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1080, 809);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        method = new ALLMethod();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 1052, 523);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setRowHeight(30);
        table.setFont(new Font("����", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("����", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
        sql = "select b.id,b.shuh,b.shum,b.zuozhe,b.Tsfl,b.kb,k.cs,b.dj\r\n" + "from books_test8 b,kc_test8 k\r\n"
                + "where b.shuh=k.shuh\r\n";
        par = new String[] {};
        binfo = method.getbookinfo(sql, par);
        BookInfoTableModel bookInfoTableModel = new BookInfoTableModel(binfo);
        table.setModel(bookInfoTableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(10);
        table.getColumnModel().getColumn(6).setPreferredWidth(10);
        table.getColumnModel().getColumn(7).setPreferredWidth(10);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new LineBorder(new Color(0, 153, 204)));
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 543, 894, 227);
        contentPane.add(panel);

        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 10, 410, 207);
        panel.add(scrollPane_1);

        table_1 = new JTable();
        table_1.setRowHeight(30);
        table_1.setFont(new Font("����", Font.PLAIN, 18));
        table_1.getTableHeader().setFont(new Font("����", Font.PLAIN, 18));
        scrollPane_1.setViewportView(table_1);
        sqlTableModel tableModel = new sqlTableModel(method.getsql(username));
        table_1.setModel(tableModel);

        Choice choice = new Choice();
        choice.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        choice.setBounds(430, 74, 92, 30);
        choice.add("and");
        choice.add("or");
        panel.add(choice);

        choice_1 = new Choice();
        choice_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        choice_1.setBounds(538, 74, 106, 30);
        choice_1.add("���");
        choice_1.add("����");
        choice_1.add("����");
        choice_1.add("ͼ�����");
        choice_1.add("����");
        choice_1.add("���");
        choice_1.add("����");
        panel.add(choice_1);

        choice_2 = new Choice();
        choice_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        choice_2.setBounds(661, 74, 92, 30);
        choice_2.add(">");
        choice_2.add("=");
        choice_2.add("<");
        choice_2.add("!=");
        choice_2.add(">=");
        choice_2.add("<=");
        choice_2.add("like");
        panel.add(choice_2);

        textField = new JTextField();
        textField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        textField.setColumns(10);
        textField.setBounds(769, 74, 97, 30);
        panel.add(textField);

        label = new JLabel("\u903B\u8F91\u8FD0\u7B97\u7B26");
        label.setForeground(new Color(0, 153, 204));
        label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        label.setBounds(430, 37, 92, 31);
        panel.add(label);

        label_1 = new JLabel("\u5B57\u6BB5");
        label_1.setForeground(new Color(0, 153, 204));
        label_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        label_1.setBounds(572, 37, 32, 31);
        panel.add(label_1);

        label_2 = new JLabel("\u8FD0\u7B97\u7B26");
        label_2.setForeground(new Color(0, 153, 204));
        label_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        label_2.setBounds(681, 37, 57, 31);
        panel.add(label_2);

        label_3 = new JLabel("\u503C");
        label_3.setForeground(new Color(0, 153, 204));
        label_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        label_3.setBounds(792, 37, 40, 31);
        panel.add(label_3);

        JButton button = new JButton("\u6DFB\u52A0");
        button.setForeground(Color.BLACK);
        button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        button.setFocusPainted(false);
        button.setBackground(SystemColor.menu);
        button.setBounds(430, 169, 106, 36);
        panel.add(button);

        JButton button_1 = new JButton("\u5220\u9664");
        button_1.setEnabled(false);
        button_1.setForeground(Color.BLACK);
        button_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        button_1.setFocusPainted(false);
        button_1.setBackground(SystemColor.menu);
        button_1.setBounds(546, 169, 106, 36);
        panel.add(button_1);

        JButton button_2 = new JButton("\u67E5\u8BE2");
        button_2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int n = table_1.getRowCount();
                String lj;
                String zd;
                String zd1 = "";
                String ys;
                String zh;
                sql = "select b.ID,b.shuh,b.shum,b.zuozhe,b.Tsfl,b.kb,k.cs,b.dj\r\n"
                        + "from books_test8 b,kc_test8 k\r\n" + "where b.shuh=k.shuh\r\n";
                String sql1 = "select b.id,b.shuh,b.shum,b.zuozhe,b.Tsfl,b.kb,k.cs,b.dj\r\n"
                        + "from books_test8 b,kc_test8 k\r\n" + "where b.shuh=k.shuh\r\n";
                List<String> zhs = new ArrayList<String>();
                if (n != 0)
                {
                    lj = (String) table_1.getValueAt(0, 0);
                    zd = (String) table_1.getValueAt(0, 1);
                    zd1 = "";
                    ys = (String) table_1.getValueAt(0, 2);
                    zh = (String) table_1.getValueAt(0, 3);
                    switch (zd)
                    {
                        case "���":
                            zd1 = "b.shuh";
                            break;
                        case "����":
                            zd1 = "shum";
                            break;
                        case "����":
                            zd1 = "zuozhe";
                            break;
                        case "ͼ�����":
                            zd1 = "Tsfl";
                            break;
                        case "����":
                            zd1 = "kb";
                            break;
                        case "���":
                            zd1 = "cs";
                            break;
                        case "����":
                            zd1 = "dj";
                            break;
                        default:
                            break;

                    }
                    if (ys.equals("like"))
                    {

                        StringBuilder sb = new StringBuilder(zh);
                        sb.insert(0, "%");
                        zh = sb.toString() + "%";
                    }
                    zhs.add(zh);
                    sql += " and  " + zd1 + " " + ys + " ? ";
                }
                for (int i = 1; i < n; i++)
                {
                    lj = (String) table_1.getValueAt(i, 0);
                    zd = (String) table_1.getValueAt(i, 1);
                    ys = (String) table_1.getValueAt(i, 2);
                    zh = (String) table_1.getValueAt(i, 3);
                    if (ys.equals("like"))
                    {

                        StringBuilder sb = new StringBuilder(zh);
                        sb.insert(0, "%");
                        zh = sb.toString() + "%";
                    }
                    zhs.add(zh);
                    switch (zd)
                    {
                        case "���":
                            zd1 = "shuh";
                            break;
                        case "����":
                            zd1 = "shum";
                            break;
                        case "����":
                            zd1 = "zuozhe";
                            break;
                        case "ͼ�����":
                            zd1 = "Tsfl";
                            break;
                        case "����":
                            zd1 = "kb";
                            break;
                        case "���":
                            zd1 = "cs";
                            break;
                        case "����":
                            zd1 = "dj";
                            break;
                        default:
                            break;

                    }
                    if (lj.equals("or"))
                        sql += " UNION " + sql1 + " and " + zd1 + " " + ys + " ? ";
                    else
                        sql += " and " + zd1 + " " + ys + " ? ";
                }
                par = zhs.toArray(new String[zhs.size()]);
                try
                {
                    binfo = method.getbookinfo(sql, par);
                } catch (ParseException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                bookInfoTableModel.ChangeData(binfo);
            }
        });
        button_2.setForeground(Color.BLACK);
        button_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        button_2.setFocusPainted(false);
        button_2.setBackground(SystemColor.menu);
        button_2.setBounds(661, 169, 106, 36);
        panel.add(button_2);

        JButton button_3 = new JButton("\u5173\u95ED");
        button_3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               dispose();
            }
        });
        button_3.setForeground(Color.BLACK);
        button_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        button_3.setFocusPainted(false);
        button_3.setBackground(SystemColor.menu);
        button_3.setBounds(777, 169, 106, 36);
        panel.add(button_3);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(914, 543, 148, 227);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        panel_1.setBorder(new LineBorder(new Color(0, 153, 204)));
        panel_1.setBackground(Color.WHITE);

        JButton btnPdf = new JButton("PDF");
        btnPdf.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                myPDF mypdf = new myPDF(binfo);
                try
                {
                    mypdf.createPDF();
                } catch (DocumentException | IOException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "PDF�������ɳɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnPdf.setForeground(Color.BLACK);
        btnPdf.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        btnPdf.setFocusPainted(false);
        btnPdf.setBackground(SystemColor.menu);
        btnPdf.setBounds(20, 75, 106, 36);
        panel_1.add(btnPdf);

        JButton btnExcel = new JButton("EXCEL");
        btnExcel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                myEXCEL myexcel = new myEXCEL(binfo);
                try
                {
                    myexcel.createExcel();
                } catch (WriteException | IOException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "EXCEL�������ɳɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnExcel.setForeground(Color.BLACK);
        btnExcel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        btnExcel.setFocusPainted(false);
        btnExcel.setBackground(SystemColor.menu);
        btnExcel.setBounds(20, 146, 106, 36);
        panel_1.add(btnExcel);

        JLabel label_4 = new JLabel("\u62A5\u8868\u751F\u6210");
        label_4.setForeground(new Color(0, 153, 204));
        label_4.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        label_4.setBounds(20, 20, 92, 31);
        panel_1.add(label_4);

        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                method.insql(username, choice.getSelectedItem(), choice_1.getSelectedItem(), choice_2.getSelectedItem(),
                        textField.getText());
                List<sqlyuju> data = method.getsql(username);
                tableModel.ChangeData(data);
                textField.setText("");
                table_1.setRowSelectionInterval(table_1.getRowCount() - 1, table_1.getRowCount() - 1);
                Rectangle rect = table_1.getCellRect(table_1.getRowCount() - 1, 0, true);
                table_1.scrollRectToVisible(rect);
                button_1.setEnabled(true);
            }
        });

        button_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectrow = table_1.getSelectedRow();
                String zd = (String) table_1.getValueAt(selectrow, 1);
                String ys = (String) table_1.getValueAt(selectrow, 2);
                String zh = (String) table_1.getValueAt(selectrow, 3);
                method.desql(username, zd, ys, zh);
                List<sqlyuju> data = method.getsql(username);
                tableModel.ChangeData(data);
                button_1.setEnabled(false);
            }
        });

        table_1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                button_1.setEnabled(true);
            }
        });
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public class BookInfoTableModel extends AbstractTableModel
    {

        private List<BookInfo> data = new ArrayList<BookInfo>();

        @Override
        public int getRowCount()
        {
            return data.size();
        }

        @Override
        public int getColumnCount()
        {
            // ����ʵ�������������
            return 8;
        }

        @Override
        public String getColumnName(int column)
        {
            // ����ʵ�������������
            switch (column)
            {
                case 0:
                    return "���";
                case 1:
                    return "����";
                case 2:
                    return "����";
                case 3:
                    return "ͼ�����";
                case 4:
                    return "����";
                case 5:
                    return "�����";
                case 6:
                    return "����";
                default:
                    return "������";
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            BookInfo info = data.get(rowIndex);
            if (columnIndex == 0)
                return info.getShuh();
            if (columnIndex == 1)
                return info.getShum();
            if (columnIndex == 2)
                return info.getZuozhe();
            if (columnIndex == 3)
                return info.getFenlei();
            if (columnIndex == 4)
                return info.getKaiben();
            if (columnIndex == 5)
                return info.getKuchun();
            if (columnIndex == 6)
                return info.getDj();
            else
                return info.getKuchun() * info.getDj();
        }

        public BookInfoTableModel(List<BookInfo> data)
        {
            if (data == null)
                throw new IllegalArgumentException("����data����Ϊnull��");

            this.data = data;

            fireTableDataChanged();
        }

        public void ChangeData(List<BookInfo> data)
        {
            if (data == null)
                throw new IllegalArgumentException("����data����Ϊnull��");

            this.data = data;

            fireTableDataChanged();
        }

        public void setData(List<BookInfo> data)
        {
            this.data = data;
        }
    }

    public class sqlTableModel extends AbstractTableModel
    {

        private List<sqlyuju> data = new ArrayList<sqlyuju>();

        @Override
        public int getRowCount()
        {
            return data.size();
        }

        @Override
        public int getColumnCount()
        {
            // ����ʵ�������������
            return 4;
        }

        @Override
        public String getColumnName(int column)
        {
            // ����ʵ�������������
            switch (column)
            {
                case 0:
                    return "�߼������";
                case 1:
                    return "�ֶ���";
                case 2:
                    return "�����";
                default:
                    return "ֵ";
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            sqlyuju info = data.get(rowIndex);
            if (rowIndex == 0 && columnIndex == 0)
                return "";
            if (rowIndex != 0 && columnIndex == 0)
                return info.getLj();
            if (columnIndex == 1)
                return info.getZd();
            if (columnIndex == 2)
                return info.getYs();
            else
                return info.getZh();
        }

        public sqlTableModel(List<sqlyuju> data)
        {
            if (data == null)
                throw new IllegalArgumentException("����data����Ϊnull��");

            this.data = data;

            fireTableDataChanged();
        }

        public void ChangeData(List<sqlyuju> data)
        {
            if (data == null)
                throw new IllegalArgumentException("����data����Ϊnull��");

            this.data = data;

            fireTableDataChanged();
        }

        public void setData(List<sqlyuju> data)
        {
            this.data = data;
        }
    }
}
