package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ZGCX_UI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZGCX_UI frame = new ZGCX_UI();
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
	public ZGCX_UI() {
		setTitle("\u7EC4\u7A3F\u67E5\u8BE2\u754C\u9762");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//����Ϊ������Ͻ�ֻ�رյ�ǰ����
		setBounds(100, 100, 561, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
