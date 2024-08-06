import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JFrame {
	private BuyLotto buyLotto;

	public Menu() {
		super("로또 프로그램");

		// 메인 패널
		JPanel pnl = new JPanel(new BorderLayout());

		// 버튼 패널
		JPanel buttonPanel = new JPanel();

		// 버튼 생성
		JButton btnBuy = new JButton("로또사러가기");
//		JButton btnMyNum = new JButton("내 번호 확인");
//		JButton btnCheck = new JButton("부자되러가기");

		// 로또 배경 이미지 추가
		JLabel lblBackground = new JLabel();
		lblBackground.setIcon(new ImageIcon(Menu.class.getResource("로또이미지.jpg")));

		// 버튼 패널에 버튼 추가
		buttonPanel.add(btnBuy);
//		buttonPanel.add(btnMyNum);
//		buttonPanel.add(btnCheck);

		// 메인 패널에 배경 이미지와 버튼 패널 추가
		pnl.add(lblBackground, BorderLayout.CENTER);
		pnl.add(buttonPanel, BorderLayout.SOUTH);

		// 버튼 클릭 이벤트 설정
		btnBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 로또 사러가기 패널로 이동
				buyLotto = new BuyLotto(Menu.this);
				buyLotto.setVisible(true);
			}
		});

//		btnMyNum.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// 내 번호 확인 패널로 이동
//                CheckMyNum checkMyNumPanel = new CheckMyNum();
////              JOptionPane.showMessageDialog(Menu.this, checkMyNumPanel, "내 번호 확인", JOptionPane.PLAIN_MESSAGE);
//				checkMyNumPanel.showInFrame();
//			}
//		});
//
//		btnCheck.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// 부자되러가기 패널로 이동
//				JOptionPane.showMessageDialog(Menu.this, "아직 추가 안함");
//			}
//		});

		// 메인 프레임 설정
		add(pnl);
		setSize(1100, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}