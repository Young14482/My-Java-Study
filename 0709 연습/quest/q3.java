package quest;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class q3 extends JFrame {
	private int x = 0;
	private int y = 0;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					q3 frame = new q3();
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
	public q3() {
		setTitle("달리기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel dun = new JLabel("New label");
		dun.setBounds(x, y, 121, 132);
		dun.setIcon(new ImageIcon(q3.class.getResource("/quest/running.gif")));
		contentPane.add(dun);
		
		JLabel informationLbl = new JLabel("wasd : 상하좌우");
		informationLbl.setFont(new Font("굴림", Font.PLAIN, 26));
		informationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		informationLbl.setBounds(115, 9, 432, 36);
		contentPane.add(informationLbl);
		
		contentPane.setFocusable(true);
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_A) {
					x += -10;
					dun.setBounds(x, y, 121, 132);
				}
				if (keyCode == KeyEvent.VK_D) {
					x += 10;
					dun.setBounds(x, y, 121, 132);
				}
				if (keyCode == KeyEvent.VK_W) {
					y += -10;
					dun.setBounds(x, y, 121, 132);
				}
				if (keyCode == KeyEvent.VK_S) {
					y += 10;
					dun.setBounds(x, y, 121, 132);
				}
				if (keyCode == KeyEvent.VK_SPACE) {
					dun.setIcon(new ImageIcon(q3.class.getResource("/quest/attack.gif")));
				}
				if (keyCode == KeyEvent.VK_C) {
					dun.setIcon(new ImageIcon(q3.class.getResource("/quest/running.gif")));
				}
			}
		});
	}
}
