import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

class SetFormat {
	private Set<Integer> numbers;

	public SetFormat(Set<Integer> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		// 원하는 형식으로 변환. 예: "1, 2, 3, 4, 5, 6"
		return numbers.toString().replaceAll("[\\[\\]]", ""); // 기본 toString()은 "[1, 2, 3]" 형식이므로 괄호 제거
	}
}

public class BuyLotto extends JDialog {
	public static final int MAX_CHECKED = 6; // 최대 체크 가능한 체크박스 개수
	private List<JCheckBox> allCheckBoxes = new ArrayList<>(); // 모든 체크박스를 담는 리스트
	private Map<JPanel, Integer> panelCheckedCount = new HashMap<>(); // 각 패널의 체크된 개수 관리
	private Map<JPanel, Set<Integer>> panelCheckedNumbers = new LinkedHashMap<>(); // 각 패널과 체크된 번호를 매핑
	private JPanel[] panels; // 패널을 저장할 배열
	private int activePnlCount = 0;
	Menu menu = new Menu();

	public BuyLotto(JFrame parent) {
		super(parent, "로또", true);
		setSize(1200, 600);
		setLayout(new BorderLayout(0, 0));

		initializeNorthPanel();
		initializeCenterPanel();
		initializeSouthPanel();
	}

	private void initializeNorthPanel() {
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblNorth = new JLabel("로또 구매");
		lblNorth.setHorizontalAlignment(SwingConstants.CENTER);
		lblNorth.setFont(new Font("휴먼모음T", Font.PLAIN, 16));
		pnlNorth.add(lblNorth);

		JButton btnBuyLotto = new JButton("구매 확정");
		pnlNorth.add(btnBuyLotto, BorderLayout.WEST);

		// 메인 버튼
		JButton btnMain = new JButton("메인으로");
		pnlNorth.add(btnMain, BorderLayout.EAST);

		// 구매확정버튼
		btnBuyLotto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check = 0;
				StringBuilder result = new StringBuilder(activePnlCount + "장 구매:\n");
				File file = new File("./");
				File creatFolder = new File(file, "Lotto");
				if (!creatFolder.exists()) {
					creatFolder.mkdir();
				}

				File customerFile = new File(creatFolder, "lotto.txt"); 

				try (FileWriter writer = new FileWriter(customerFile)) {
					// 각 패널의 체크된 번호를 가져와서 결과 문자열에 추가
					for (int i = 0; i < panels.length; i++) {
						JPanel panel = panels[i];
						Set<Integer> checkedNumbers = panelCheckedNumbers.get(panel);
						JButton btnCancel = (JButton) ((JPanel) panel.getComponent(1)).getComponent(2); // '구매 취소' 버튼
						JButton btnAuto = (JButton) ((JPanel) panel.getComponent(2)).getComponent(1); // '자동' 버튼

						// 체크된 번호를 포함시키는 로직
						if (btnCancel.isEnabled()) { // 로또 구매 취소 버튼이 활성화
							if (btnAuto.isEnabled()) { // 자동 버튼이 비활성화된 경우 수동
								if (checkedNumbers.size() < 6) {
									JOptionPane.showMessageDialog(BuyLotto.this, "6개 선택바랍니다");
									break;
								} else {
									sbHandle(result, i, checkedNumbers, "수 동 ");
								}
							} else { // 자동 버튼이 활성화된 경우 자동 or 반자동
								// 체크된 번호를 스트링 배열화 한 이후 정수화 해서 set에 넣기
								Set<Integer> randomNumbers = new TreeSet<>();
								String[] checkedNum = new SetFormat(checkedNumbers).toString().split(", ");
								for (int j = 0; j < checkedNum.length; j++) {
									try {
										randomNumbers.add(Integer.parseInt(checkedNum[j]));
									} catch (NumberFormatException e2) {
										break;
									}
								}
								autoSelect(result, i, checkedNumbers, randomNumbers);
							}
							check++;
						}
					} 
					writer.write(result.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				showBuySta(check);
			}
		});

		btnMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	// 중앙 패널 생성 메소드
	private void initializeCenterPanel() {
		JPanel pnlCenter = new JPanel(new GridLayout(0, 5)); // 5개의 패널을 가로로 배치
		add(pnlCenter, BorderLayout.CENTER);

		// 5개의 패널을 생성하고 중앙 패널에 추가
		panels = new JPanel[5];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = createBuyPanel(i + 1, i); // 각 패널에 버튼 클릭 시 활성화할 다음 패널 인덱스를 전달
			pnlCenter.add(panels[i]);
		}

		// 초기 상태로 첫 번째 패널만 활성화
		activatePanel(0);
	}

	// 중앙 패널의 번호 선택 패널 생성 메소드
	private JPanel createBuyPanel(int panelNumber, int currentIndex) {
		JPanel pnlBuy = new JPanel(new BorderLayout(0, 0));

		Border border = BorderFactory.createTitledBorder("구매 " + panelNumber);
		pnlBuy.setBorder(border);

		JPanel pnlBuyChk = new JPanel(new GridLayout(0, 5, 0, 0));
		pnlBuy.add(pnlBuyChk, BorderLayout.CENTER);

		JPanel pnlNorth = new JPanel();
		pnlBuy.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnLotto = new JButton("로또 구매");
		pnlNorth.add(btnLotto);

		JLabel lblCheck = new JLabel();
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck.setFont(new Font("휴먼모음T", Font.PLAIN, 16));
		pnlNorth.add(lblCheck);

		JButton btnCancel = new JButton("구매 취소");
		btnCancel.setEnabled(false); // 초기에는 비활성화
		pnlNorth.add(btnCancel);

		for (int i = 1; i < 46; i++) {
			JCheckBox check = new JCheckBox(String.valueOf(i));
			check.addActionListener(new CheckBoxActionListener(pnlBuy, lblCheck));
			pnlBuyChk.add(check);
			allCheckBoxes.add(check);
		}

		JPanel pnlSouth = new JPanel(new GridLayout(0, 3, 0, 0));
		pnlBuy.add(pnlSouth, BorderLayout.SOUTH);

		JButton btnHand = new JButton("수동");
		btnHand.setEnabled(false);
		pnlSouth.add(btnHand);

		JButton btnReset = new JButton("초기화");
		btnReset.setEnabled(false);
		pnlSouth.add(btnReset);

		JButton btnAuto = new JButton("자동");
		btnAuto.setEnabled(false);
		pnlSouth.add(btnAuto);

		// 로또 구매 버튼 액션 리스너
		btnLotto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableComponents(pnlBuyChk, true); // 체크박스 부분 활성화
				btnAuto.setEnabled(true);
				btnCancel.setEnabled(true); // 구매 취소 버튼 활성화
				btnReset.setEnabled(true); // 리셋버튼 활성화
				btnLotto.setEnabled(false); // 로또 구매 버튼 비활성화
				lblCheck.setEnabled(true);
				lblCheck.setText("0");

				int nextIndex = currentIndex + 1;
				if (nextIndex < panels.length) {
					activatePanel(nextIndex); // 다음 패널 활성화
				}
				activePnlCount++;
			}
		});

		// 구매 취소 버튼 액션 리스너
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 현재 패널의 인덱스 확인
				int currentIndex = -1;
				for (int i = 0; i < panels.length; i++) {
					if (panels[i] == pnlBuy) {
						currentIndex = i;
						break;
					}
				}
				// 현재 패널과 그보다 숫자가 큰 패널을 비활성화
				if (currentIndex != -1) {
					for (int i = currentIndex; i < panels.length; i++) {
						panels[i].setEnabled(false);
						enableComponents(panels[i], false); // 내부 컴포넌트 비활성화
						panelCheckedCount.put(panels[i], 0);
						JLabel lblCheck = (JLabel) ((JPanel) panels[i].getComponent(1)).getComponent(1); // '체크 갯수' 라벨
						lblCheck.setText("");
					}
				}
				// 구매 취소
				JButton btnBuy = (JButton) ((JPanel) panels[currentIndex].getComponent(1)).getComponent(0);
				btnBuy.setEnabled(true);
				JOptionPane.showMessageDialog(BuyLotto.this, "구매가 취소되었습니다.");
				lblCheck.setText("");
				activePnlCount = currentIndex;
			}
		});

		// 자동 버튼 액션 리스너 설정
		btnAuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableComponentsAuto(pnlBuyChk, false); // 체크박스 비활성화
				btnAuto.setEnabled(false); // 자동 버튼 비활성화
				btnHand.setEnabled(true); // 수동 버튼 활성화
				btnReset.setEnabled(false); // 초기화 버튼 비활성화
			}
		});

		// 수동 버튼 액션 리스너 설정
		btnHand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableComponentsAuto(pnlBuyChk, true); // 체크박스 활성화
				btnAuto.setEnabled(true); // 자동 버튼 활성화
				btnHand.setEnabled(false); // 수동 버튼 비활성화
				btnReset.setEnabled(true); // 초기화 버튼 활성화
			}
		});

		// 선택 초기화 버튼 액션 리스너 설정
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetCheckBok(pnlBuyChk, false);
				panelCheckedCount.put(pnlBuy, 0);
				lblCheck.setText("0");
			}
		});

		panelCheckedCount.put(pnlBuy, 0);
		panelCheckedNumbers.put(pnlBuy, new TreeSet<>()); // 체크된 번호 초기화

		// 초기 상태: 체크박스 및 버튼 비활성화
		enableComponents(pnlBuyChk, false);
		btnAuto.setEnabled(false);
		btnHand.setEnabled(false);

		return pnlBuy;
	}

	// 하단부 패널 (알림용)
	private void initializeSouthPanel() {
		// If there are components needed in the south panel, initialize them here.
		JPanel pnlSouth = new JPanel();

		JLabel lblInfo = new JLabel("선택한 갯수가 구매, 취소 버튼 사이에 출력됩니다. // 수동에서 선택 후 자동 선택시 반자동입니다.");
		lblInfo.setFont(new Font("휴먼모음T", Font.PLAIN, 16));
		pnlSouth.add(lblInfo);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	// 체크박스 상태 변경을 감지하고 체크 수를 제한하는 ActionListener
	private class CheckBoxActionListener implements ActionListener {
		private JPanel panel;
		private JLabel lbl = null;

		public CheckBoxActionListener(JPanel panel, JLabel lbl) {
			this.panel = panel;
			this.lbl = lbl;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox source = (JCheckBox) e.getSource();

			// 현재 패널의 체크된 개수 및 체크된 번호 가져오기
			int checkedCount = panelCheckedCount.get(panel);
			Set<Integer> checkedNumbers = panelCheckedNumbers.get(panel);

			// 체크박스가 선택될 때와 해제될 때 체크박스 개수를 관리
			if (source.isSelected()) {
				if (checkedCount >= MAX_CHECKED) {
					source.setSelected(false);
				} else {
					checkedNumbers.add(Integer.parseInt(source.getText()));
					panelCheckedCount.put(panel, checkedCount + 1);
					lbl.setText(String.valueOf(checkedCount + 1));
				}
			} else {
				checkedNumbers.remove(Integer.parseInt(source.getText()));
				panelCheckedCount.put(panel, checkedCount - 1);
				lbl.setText(String.valueOf(checkedCount - 1));
			}
		}
	}

	/**
	 * 지정된 패널 내의 모든 체크박스와 버튼을 활성화하거나 비활성화합니다.
	 * 
	 * @param panel  대상 패널
	 * @param enable 활성화 여부
	 */
	private void enableComponents(JPanel panel, boolean enable) {
		for (Component comp : panel.getComponents()) {
			comp.setEnabled(enable);
			if (comp instanceof JPanel) {
				enableComponents((JPanel) comp, enable); // 내부 패널에 대해 재귀 호출
			}
			resetCheckBok(panel, enable);
		}
	}

	private void enableComponentsAuto(JPanel panel, boolean enable) {
		for (Component comp : panel.getComponents()) {
			comp.setEnabled(enable);
			if (comp instanceof JPanel) {
				enableComponents((JPanel) comp, enable); // 내부 패널에 대해 재귀 호출
			}
		}
	}

	private void resetCheckBok(JPanel panel, boolean enable) {
		for (Component comp : panel.getComponents()) {
			if (comp instanceof JCheckBox) {
				((JCheckBox) comp).setSelected(false);
			}
		}
	}

	// 패널 활성화 용 메소드
	private void activatePanel(int indexToActivate) {
		for (int i = 0; i < panels.length; i++) {
			if (i == indexToActivate) {
				// 현재 패널을 활성화하고 '로또 구매' 버튼만 활성화
				panels[i].setEnabled(true);
				JButton btnLotto = (JButton) ((JPanel) panels[i].getComponent(1)).getComponent(0); // '로또 구매' 버튼
				JLabel lblCheck = (JLabel) ((JPanel) panels[i].getComponent(1)).getComponent(1); // '체크 갯수' 라벨
				btnLotto.setEnabled(true);
				lblCheck.setEnabled(true);
			} else if (i < indexToActivate) {
				// 이전 패널들은 모두 활성화
				panels[i].setEnabled(true);
			} else {
				// 나머지 패널은 비활성화
				panels[i].setEnabled(false);
				enableComponents(panels[i], false); // 내부 컴포넌트 비활성화
			}
		}
	}

	// 랜덤 번호 생성 메소드
	public Set<Integer> randomNums(Set<Integer> nums) {
		Random r = new Random();
		while (nums.size() < 6) {
			nums.add(r.nextInt(45) + 1); // 1부터 45까지의 숫자 중 랜덤 선택
		}
		return nums;
	}

	// 구매확정 다이얼로그 띄우는 메소드
	private void showBuySta(int check) {
		if (check != 0 && check == activePnlCount) {
			JOptionPane.showMessageDialog(BuyLotto.this, "구매가 완료되었습니다!");
			dispose();
			CheckMyNum checkMyNumPanel = new CheckMyNum();
			checkMyNumPanel.showInFrame();
		} else if (activePnlCount == 0) {
			JOptionPane.showMessageDialog(BuyLotto.this, "아무것도 구매하시지 않았습니다.");
		}
	}

	// 구매확정 자동 선택시 사이즈에 따른 저장
	private void autoSelect(StringBuilder result, int i, Set<Integer> checkedNumbers, Set<Integer> randomNumbers) {
		if (randomNumbers.size() == 0) {
			randomNumbers = randomNums(randomNumbers);
			sbHandle(result, i, randomNumbers, "자 동 ");
		} else if (randomNumbers.size() == 6) {
			sbHandle(result, i, checkedNumbers, "수 동 ");
		} else {
			randomNumbers = randomNums(randomNumbers);
			sbHandle(result, i, randomNumbers, "반자동 ");
		}
	}

	private void sbHandle(StringBuilder result, int i, Set<Integer> checkedNumbers, String mix) {
		result.append(mix).append(i + 1).append(": ");
		result.append(checkedNumbers.toString()).append("\n");
	}
}
