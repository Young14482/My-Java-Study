package quest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class q1 extends JFrame {
	private int totalPrice = 0;
	private int burgerPrice = 0;
	private JPanel contentPane;
	private JTextField amountTF;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					q1 frame = new q1();
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
	public q1() {
		setTitle("럭키비키니시티 분점");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel mainLbl = new JLabel("집게리아");
		mainLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mainLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		mainLbl.setFont(new Font("HY헤드라인M", Font.PLAIN, 30));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(mainLbl, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel leftPnl = new JPanel();
		centerPanel.add(leftPnl);
		leftPnl.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel menuPnl = new JPanel();
		menuPnl.setBorder(BorderFactory.createTitledBorder("메뉴 선택"));
		leftPnl.add(menuPnl);
		menuPnl.setLayout(new GridLayout(0, 1, 0, 0));

		JRadioButton burgerRdo1 = mainMenuRdo(menuPnl, "게살버거 (5000원)");
		JRadioButton burgerRdo2 = mainMenuRdo(menuPnl, "더블게살버거  (8000원)");
		JRadioButton pizzaRdo = mainMenuRdo(menuPnl, "게살 피자 (10000원)");

		JLabel priceLbl = priceLbl();

		JPanel rightPnl = new JPanel();
		rightPnl.setBorder(new TitledBorder(null, "\uC0AC\uC774\uB4DC \uBA54\uB274", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		centerPanel.add(rightPnl);
		rightPnl.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel sideMPnl = new JPanel();
		rightPnl.add(sideMPnl);
		sideMPnl.setLayout(new GridLayout(0, 1, 0, 0));

		// 반복되는것은 메소드를 만들어서 간단하게 표시
		JCheckBox sideCheckBox1 = sideCheckBox(sideMPnl, "신선한 진흙 푸딩 (2000원)");
		JCheckBox sideCheckBox2 = sideCheckBox(sideMPnl, "산호튀김 (1500원)");
		JCheckBox sideCheckBox3 = sideCheckBox(sideMPnl, "바다감자튀김 (1000원)");
		JCheckBox sideCheckBox4 = sideCheckBox(sideMPnl, "집게링 (4000원)");
		JCheckBox sideCheckBox5 = sideCheckBox(sideMPnl, "닥터켈프 (3000원)");
		JCheckBox sideCheckBox6 = sideCheckBox(sideMPnl, "게살콜라 (2500원)");

		JPanel selPnl = new JPanel();
		rightPnl.add(selPnl);
		selPnl.setLayout(null);

		JButton cancleButton = new JButton("사이드 메뉴 전체 취소");
		cancleButton.setBounds(0, 1, 178, 90);
		selPnl.add(cancleButton);

		JButton allButton = new JButton("사이드 메뉴 전체 선택");
		allButton.setBounds(0, 91, 178, 90);
		selPnl.add(allButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 181, 178, 182);
		lblNewLabel.setIcon(new ImageIcon(q1.class.getResource("/quest/moneyis.jpeg")));
		selPnl.add(lblNewLabel);

		JPanel amountPnl = new JPanel();
		amountPnl.setBorder(new TitledBorder(null, "\uC218\uB7C9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPnl.add(amountPnl);

		amountTF = new JTextField("");
		amountTF.setColumns(5);
		amountPnl.add(amountTF);

		JLabel amountLbl = new JLabel("개");
		amountPnl.add(amountLbl);

		JButton amountOKBtn = new JButton("개수 확정");
		amountPnl.add(amountOKBtn);

		JButton amountResetBtn = new JButton("개수 변경");
		amountPnl.add(amountResetBtn);
		amountResetBtn.setEnabled(false);

		// 메뉴 1번 선택
		burgerRdo1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				burgerPrice = 0;
				int amount = Integer.parseInt(amountTF.getText());
				burgerPrice += burgerRdo1.isSelected() ? 5000 : -5000;
				totalPrice += burgerPrice * amount;
				priceLbl.setText("총 가격: " + totalPrice + "원");
			}
		});
		// 메뉴 2번 선택
		burgerRdo2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				burgerPrice = 0;
				int amount = Integer.parseInt(amountTF.getText());
				burgerPrice += burgerRdo2.isSelected() ? 8000 : -8000;
				totalPrice += burgerPrice * amount;
				priceLbl.setText("총 가격: " + totalPrice + "원");
			}
		});
		// 메뉴 3번 선택
		pizzaRdo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				burgerPrice = 0;
				int amount = Integer.parseInt(amountTF.getText());
				burgerPrice += pizzaRdo.isSelected() ? 10000 : -10000;
				totalPrice += burgerPrice * amount;
				priceLbl.setText("총 가격: " + totalPrice + "원");
			}
		});
		// 전체 선택 버튼
		allButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sideCheckBox1.setSelected(true);
				sideCheckBox2.setSelected(true);
				sideCheckBox3.setSelected(true);
				sideCheckBox4.setSelected(true);
				sideCheckBox5.setSelected(true);
				sideCheckBox6.setSelected(true);
			}
		});
		// 전체 취소 버튼
		cancleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sideCheckBox1.setSelected(false);
				sideCheckBox2.setSelected(false);
				sideCheckBox3.setSelected(false);
				sideCheckBox4.setSelected(false);
				sideCheckBox5.setSelected(false);
				sideCheckBox6.setSelected(false);
			}
		});
		// 사이드 메뉴 1 ~ 6번 상태별 가격 변동
		sideMenuPrice(priceLbl, sideCheckBox1, 2000);
		sideMenuPrice(priceLbl, sideCheckBox2, 1500);
		sideMenuPrice(priceLbl, sideCheckBox3, 1000);
		sideMenuPrice(priceLbl, sideCheckBox4, 4000);
		sideMenuPrice(priceLbl, sideCheckBox5, 3000);
		sideMenuPrice(priceLbl, sideCheckBox6, 2500);
		
		// 모든 선택된 상태를 해제하고 가격을 리셋
		amountResetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				amountTF.setEditable(true);
				buttonGroup.remove(burgerRdo1);
				buttonGroup.remove(burgerRdo2);
				buttonGroup.remove(pizzaRdo);
				burgerRdo1.setSelected(false);
				burgerRdo2.setSelected(false);
				pizzaRdo.setSelected(false);
				buttonGroup.add(burgerRdo1);
				buttonGroup.add(burgerRdo2);
				buttonGroup.add(pizzaRdo);
				burgerRdo1.setEnabled(false);
				burgerRdo2.setEnabled(false);
				pizzaRdo.setEnabled(false);
				sideCheckBox1.setSelected(false);
				sideCheckBox2.setSelected(false);
				sideCheckBox3.setSelected(false);
				sideCheckBox4.setSelected(false);
				sideCheckBox5.setSelected(false);
				sideCheckBox6.setSelected(false);
				amountOKBtn.setEnabled(true);
				amountResetBtn.setEnabled(false);
				totalPrice = 0;
				amountTF.setText("");
				priceLbl.setText("총 가격: " + totalPrice + "원");
			}
		});
		// 개수확정 버튼 >> 텍스트필드 비활성화 and 오류창 띄우기
		amountOKBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (amountTF.getText().length() == 0) {
					JOptionPane.showMessageDialog(q1.this, "개수를 입력해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
				} else if (Integer.parseInt(amountTF.getText()) < 0) {
					JOptionPane.showMessageDialog(q1.this, "0이상의 수만 입력해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					amountTF.setEditable(false);
					burgerRdo1.setEnabled(true);
					burgerRdo2.setEnabled(true);
					pizzaRdo.setEnabled(true);
					amountResetBtn.setEnabled(true);
					amountOKBtn.setEnabled(false);
				}
			}
		});

	}

	private JRadioButton mainMenuRdo(JPanel menuPnl, String s) {
		JRadioButton mainMenuRdo = new JRadioButton(s);
		buttonGroup.add(mainMenuRdo);
		menuPnl.add(mainMenuRdo);
		mainMenuRdo.setEnabled(false);
		return mainMenuRdo;
	}

	private void sideMenuPrice(JLabel priceLbl, JCheckBox sideCheckBox, int side) {
		sideCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				totalPrice += sideCheckBox.isSelected() ? side : -side;
				priceLbl.setText("총 가격: " + totalPrice + "원");
			}
		});
	}

	private JCheckBox sideCheckBox(JPanel sideMPnl, String s) {
		JCheckBox sideCheckBox = new JCheckBox(s);
		sideMPnl.add(sideCheckBox);
		return sideCheckBox;
	}

	private JLabel priceLbl() {
		JLabel priceLbl = new JLabel("총 가격 : " + totalPrice + "원");
		priceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		priceLbl.setFont(new Font("HY헤드라인M", Font.PLAIN, 25));
		contentPane.add(priceLbl, BorderLayout.SOUTH);
		return priceLbl;
	}
}
