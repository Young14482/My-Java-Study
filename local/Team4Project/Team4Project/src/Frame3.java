import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Frame3 extends JFrame {
	private JTextField textField;
	private static FontFile fontFile = new FontFile(); // 폰트 클래스 참조
	private int lottoCountNum = 0; // 로또 장의 개수

	private static class LabelFactory {
		// 라벨 생성 메소드
		public static JLabel updateLabel(String updateLabel) {
			JLabel newLabel = new JLabel(updateLabel);
			newLabel.setHorizontalTextPosition(JLabel.CENTER);
			newLabel.setVerticalTextPosition(JLabel.CENTER);
			newLabel.setFont(fontFile.getUseFont(Font.BOLD, 20));
			return newLabel;
		}
	}
	

	// 당첨번호와 사용자번호를 비교하는 메소드
	// userLottoNum, lottoNum 비교
	public class CheckLottoNum {

		public String correctCircle(List<Integer> lottoNum, List<String[]> lottoNumArrayList, int pnlNum,
				List<JLabel> lblList, int bonusNum) {
			String resultPnl = null;
			int[] userLottoNum = pnlLottoNum(lottoNumArrayList, pnlNum);
			int counting = 0;
			// boolean으로 보너스 번호의 존재 유무 확인으로 2등 당첨을 구분할 수 있음
			boolean bonusNumContain = false;
//			System.out.println(lottoNum);
//			System.out.println(Arrays.toString(userLottoNum));
			
			int checkSecondIndex = -1;
			
			// 사용자의 입력 번호와 난수 번호가 일치할 경우 색깔공
			for (int i = 0; i < 6; i++) {
				if (lottoNum.contains(userLottoNum[i])) {
					lblList.get(i).setIcon(chooseIcon(userLottoNum[i]));
//					System.out.println("당첨된 인덱스 " + i);
					// 공의 숫자가 같을 경우에 counting++
					counting++;
				} else {
					lblList.get(i).setIcon(chooseBlackIcon());
				}

				if (userLottoNum[i] == bonusNum) {
					bonusNumContain = true;
					checkSecondIndex = i;
				}

			}

//			System.out.println("2등 인덱스 " + checkSecondIndex);
			if (checkSecondIndex >= 0) {
				if (counting == 5) {
					lblList.get(checkSecondIndex).setIcon(chooseIcon(userLottoNum[checkSecondIndex]));
				}
			}

//			System.out.println(counting);

			// 공의 숫자 개수에 따른 등수 출력을 위한 조건문 생성
			if (counting == 6) {
				resultPnl = "1등 당첨";
			} else if (counting == 5) {
				if (bonusNumContain) {
					resultPnl = "2등 당첨";

				} else {
					resultPnl = "3등 당첨";
				}

			} else if (counting == 4) {
				resultPnl = "4등 당첨";
			} else if (counting == 3) {
				resultPnl = "5등 당첨";
			} else {
				resultPnl = "낙첨";
			}

			return resultPnl;
		}
	}

	public List<String[]> readLottoNums() {
		File dataFolder = new File("./\\Lotto");
		File lottofile = new File(dataFolder, "lotto.txt");
		List<String[]> newName = new ArrayList<>();
		StringBuilder newBuilder = new StringBuilder(); // 파일리더는 한 단어씩 받아오기 때문에 단어들을 합해줄 StringBuilder가 필요
		// 메모장에 저장된 값을 불러와서
		// 리스트에 저장한 후
		// 그 리스트를 불러와서 이미지 레이블에 출력할 예정

		FileReader reading = null;
		try {
			reading = new FileReader(lottofile);

			// 인트형으로 받아서 캐릭터형으로 캐스팅
			int ch;
			while ((ch = reading.read()) != -1) {
				newBuilder.append((char) ch); // 아스키 코드(문자형)

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reading != null) {
				try {
					reading.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String builderString = newBuilder.toString();

		// 문자열 첫번째를 불러옴 - 구매 개수 입력 때문에 불러와서 정수로 변환
		char lottoCount = builderString.charAt(0);
		int lottoCountInteger = Integer.parseInt(String.valueOf(lottoCount));
		lottoCountNum = lottoCountInteger; // 파일에서 장수를 읽어와서 입력
		// 스플릿 메서드를 통해 newBuilder의 문자열을 [기준으로 쪼개서 string 배열에 넣어줌
		// 대괄호들 []을 /로 번경해줌
		builderString = builderString.replaceAll("\\]", "/");
		builderString = builderString.replaceAll("\\[", "/");
//		System.out.println(builderString);
//		split를 /로 쪼갬 
		String[] split = builderString.split("/");
//		System.out.println(Arrays.toString(split));
//		System.out.println(split[1].toString());
//		1, 3, 5, 7, 9의 값을 출력
		for (int i = 0; i < lottoCountInteger; i++) {
			newName.add(split[i * 2 + 1].split(", "));
		} // newName 메소드 활용을 위해 return
//		System.out.println(Arrays.toString(newName.get(0)));
		return newName;

	}

	public List<String> readLottoNums2() {
		File dataFolder = new File("./\\Lotto");
		File lottofile = new File(dataFolder, "lotto.txt");
		List<String> newName2 = new ArrayList<>();
		StringBuilder newBuilder = new StringBuilder();

		FileReader reading = null;
		try {
			reading = new FileReader(lottofile);

			int ch;
			while ((ch = reading.read()) != -1) {
				newBuilder.append((char) ch);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reading != null) {
				try {
					reading.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String builderString = newBuilder.toString();
//		builderString = builderString.replaceAll("\\1", "/");
//		builderString = builderString.replaceAll("\\2", "/");
//		builderString = builderString.replaceAll("\\3", "/");
//		builderString = builderString.replaceAll("\\4", "/");
//		builderString = builderString.replaceAll("\\5", "/");
//		
		String[] split = builderString.split("\n");
//		System.out.println(Arrays.toString(split));
//		String autoCheck = split[1].substring(0, 3);
//		String autoCheck2 = split[2].substring(0, 3);
		
//		String autoCheck3 = split[3].substring(0, 3);
//		String autoCheck4 = split[4].substring(0, 3);
//		String autoCheck5 = split[5].substring(0, 3);

		// 0번째 인덱스가 로또 구매 개수의 값이고 그 값을 문자로 불러옴
		char lottoCount = builderString.charAt(0);
		// 문자로 불러온 인덱스0 값에서 나오는 로또 구매 개수를 인트값으로 저장
		int lottoCountInteger = Integer.parseInt(String.valueOf(lottoCount));

		for (int i = 1; i < lottoCountInteger + 1; i++) {
			newName2.add(split[i].substring(0, 3));
		}
		// 자동, 수동, 반자동 값 출력 가능
		return newName2;
	}

	// 파일과 원하는 패널 번호를 파라미터로 입력받아 패널안의 문자열을 인트배열로 변환
	public int[] pnlLottoNum(List<String[]> lottoNumArrayList, int pnlNum) {
		int[] arr = new int[6];
		for (int i = 0; i < 6; i++) {
			arr[i] = Integer.parseInt(lottoNumArrayList.get(pnlNum)[i]);
		}

		return arr;
	}
//	 correctCircle 메서드에서 pnlLottoNum을 호출하여 userLottoNum을 생성하고
	// lottoNum과 비교하여 JLabel의 아이콘을 업데이트

	// 패널 출력 공 for문
	// 패널 출력 공 번호
	// 1~10 : 노랑
	// 11~20 : 빨강
	// 21~30 : 초록
	// 31~40 : 파랑
	// 41~50 : 핑크
	// 낙첨 : 검정

	// 숫자 범위 별로 이미지 출력
	public ImageIcon chooseIcon(int num) {
		if (num <= 10)
			return new ImageIcon(Frame3.class.getResource("노랑.png"));
		else if (num <= 20)
			return new ImageIcon(Frame3.class.getResource("초록.png"));
		else if (num <= 30)
			return new ImageIcon(Frame3.class.getResource("파랑.png"));
		else if (num <= 40)
			return new ImageIcon(Frame3.class.getResource("핑크.png"));

		return new ImageIcon(Frame3.class.getResource("빨강.png"));
	}

	public ImageIcon chooseBlackIcon() {
		return new ImageIcon(Frame3.class.getResource("검정.png"));

	}

	public Frame3() {
		super("로또 당첨 확인창");
		JPanel Panel1 = new JPanel();
		List<String[]> lottoNumArrayList = readLottoNums();
		getContentPane().setLayout(new BorderLayout(0, 0));
		Panel1.setLayout(new GridLayout(6, 0, 3, 3)); // 패널이 6행으로 나뉨
		getContentPane().add(Panel1, BorderLayout.NORTH); // 맨 위의 패널의 값들을 보이게 함
		getContentPane().add(Panel1); // 전체 큰 패널에 패널 추가 기능
//		getContentPane().setBackground(Color.WHITE);
		setSize(715, 900);
		setResizable(false);
		Panel1.setBackground(Color.WHITE);
//		JPanel panel = new JPanel();

		// 난수 생성
		Random r = new Random();

		// 리스트로 저장해서 값들을 저장하고 레이블에 텍스트로 출력하기 위함
		List<Integer> lottoNumList = new ArrayList<>(); // lbl1~5까지 추가할 난수 생성 리스트
		Set<Integer> lotto = new TreeSet<>(); // 중복제거
//
		while (lotto.size() < 6) { // integer lotto 숫자를 확인해서 아래 중복을 허용하지 않고 set에 삽입
			lotto.add(r.nextInt(45) + 1); // list는 size로, set은 index값이 없음

		}
		for (Integer n : lotto) { // 삽입한 set 값을 lottoNumList에 넣어줌
			lottoNumList.add(n); // 해당 리스트를 이용해서 원하는 인덱스 값을 쉽게 사용 가능
		}
//		lottoNumList.add(3);
//		lottoNumList.add(8);
//		lottoNumList.add(9);
//		lottoNumList.add(13);
//		lottoNumList.add(14);
//		lottoNumList.add(35);

//		System.out.println(lottoNumList); // lottoNumList는 랜덤 난수 1~45개중 6개 출력값임

//		List<String[]> lottoNumArrayList = readLottoNums();
//		System.out.println(Arrays.toString(lottoNumArrayList.get(0)));

		// 난수 번호 생성
//		int bonusLottoNum = 32;
		int bonusLottoNum = r.nextInt(45) + 1;
		while (lottoNumList.contains(bonusLottoNum)) {
			bonusLottoNum = r.nextInt(45) + 1;
		}
		

		
		
		Border lineBorder = new LineBorder(Color.BLUE, 5);
//		Border emptyBorder = new EmptyBorder(10, 10, 10, 10);
//		Border combinedBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
		
		JPanel panel_1 = new JPanel();
		Panel1.add(panel_1);
		panel_1.setPreferredSize(new Dimension(600, 100));
		panel_1.setBackground(Color.WHITE);
//		panel_1.setBorder(combinedBorder);
		panel_1.setBorder(lineBorder);
		
		JPanel panel_2 = new JPanel();
		Panel1.add(panel_2);
		panel_2.setPreferredSize(new Dimension(600, 100));
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		Panel1.add(panel_3);
		panel_3.setPreferredSize(new Dimension(600, 100));
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		JPanel panel_4 = new JPanel();
		Panel1.add(panel_4);
		panel_4.setPreferredSize(new Dimension(600, 100));
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_6 = new JPanel();
		Panel1.add(panel_6);
		panel_6.setPreferredSize(new Dimension(600, 100));
		panel_6.setBackground(Color.WHITE);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		JPanel panel_5 = new JPanel();
		Panel1.add(panel_5);
		panel_5.setPreferredSize(new Dimension(600, 100));
		panel_5.setBackground(Color.WHITE);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl1 = LabelFactory.updateLabel(lottoNumList.get(0).toString());
		JLabel lbl2 = LabelFactory.updateLabel(lottoNumList.get(1).toString());
		JLabel lbl3 = LabelFactory.updateLabel(lottoNumList.get(2).toString());
		JLabel lbl4 = LabelFactory.updateLabel(lottoNumList.get(3).toString());
		JLabel lbl5 = LabelFactory.updateLabel(lottoNumList.get(4).toString());
		JLabel lbl6 = LabelFactory.updateLabel(lottoNumList.get(5).toString());
		JLabel lblPlus = new JLabel();
		lblPlus.setIcon(new ImageIcon(Frame3.class.getResource("더하기.png")));
		JLabel bonuslbl7 = LabelFactory.updateLabel(String.valueOf(bonusLottoNum));
		bonuslbl7.setPreferredSize(new Dimension(100, 100));
//		lbl1.setOpaque(true); // 투명도

		// 각 패널 별로 레이블을 넣을 리스트
		ArrayList<JLabel> listCounting = new ArrayList<>();
		ArrayList<JLabel> listCounting2 = new ArrayList<>();
		ArrayList<JLabel> listCounting3 = new ArrayList<>();
		ArrayList<JLabel> listCounting4 = new ArrayList<>();
		ArrayList<JLabel> listCounting5 = new ArrayList<>();
		CheckLottoNum checkLottoNum = new CheckLottoNum();

		// 패널의 세번째 행 레이블 6개 추가
		if (lottoCountNum >= 1) {

			// 수동,자동 여부 레이블에 추가
			JLabel lblNewLabel = LabelFactory.updateLabel(readLottoNums2().get(0));

			// 패널에 번호 및 공 그림 그리고 리스트에 추가
			for (int i = 0; i < 6; i++) {
				listCounting.add(LabelFactory.updateLabel(lottoNumArrayList.get(0)[i]));
			}

			// 당첨 결과 출력창을 리스트에 추가
			listCounting.add(LabelFactory.updateLabel(lottoNumArrayList.get(0)[5]));

			// 당첨 결과 출력창 가운데 정렬 및 크기 설정
			listCounting.get(6).setPreferredSize(new Dimension(100, 50));
			listCounting.get(6).setHorizontalAlignment(JLabel.CENTER);

//			System.out.println(listCounting);
			// 당첨 여부 확인
			String resultPnl1 = checkLottoNum.correctCircle(lottoNumList, lottoNumArrayList, 0, listCounting,
					bonusLottoNum);

			// 당첨 결과를 레이블에 적어줌
			listCounting.get(6).setText(resultPnl1);

			// 수동, 자동 여부 패널 추가
			panel_2.add(lblNewLabel);

			// 공 및 당첨 결과 패널 추가
			for (int i = 0; i < 7; i++) {
				panel_2.add(listCounting.get(i));
			}

		}

		if (lottoCountNum >= 2) {

			for (int i = 0; i < 6; i++) {
				listCounting2.add(LabelFactory.updateLabel(lottoNumArrayList.get(1)[i]));
			}
			listCounting2.add(LabelFactory.updateLabel(lottoNumArrayList.get(1)[5]));

			JLabel lblNewLabel_l1 = LabelFactory.updateLabel(readLottoNums2().get(1));

			listCounting2.get(6).setPreferredSize(new Dimension(100, 50));
			listCounting2.get(6).setHorizontalAlignment(JLabel.CENTER);

			List<JLabel> lblList2 = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				lblList2.add(listCounting2.get(i));
			}

			String resultPnl2 = checkLottoNum.correctCircle(lottoNumList, lottoNumArrayList, 1, lblList2,
					bonusLottoNum);
			listCounting2.get(6).setText(resultPnl2);

			panel_3.add(lblNewLabel_l1);
			for (int i = 0; i < 7; i++) {
				panel_3.add(listCounting2.get(i));
			}

		}

		if (lottoCountNum >= 3) {
			for (int i = 0; i < 6; i++) {
				listCounting3.add(LabelFactory.updateLabel(lottoNumArrayList.get(2)[i]));
			}
			listCounting3.add(LabelFactory.updateLabel(lottoNumArrayList.get(2)[5]));

//		 패널의 네번째 행 레이블 6개 추가
			JLabel lblNewLabel_l11 = LabelFactory.updateLabel(readLottoNums2().get(2));
			listCounting3.get(6).setPreferredSize(new Dimension(100, 50));
			listCounting3.get(6).setHorizontalAlignment(JLabel.CENTER);
			List<JLabel> lblList3 = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				lblList3.add(listCounting3.get(i));
			}

			String resultPnl3 = checkLottoNum.correctCircle(lottoNumList, lottoNumArrayList, 2, lblList3,
					bonusLottoNum);
			listCounting3.get(6).setText(resultPnl3);

			// 네번째 패널
			panel_4.add(lblNewLabel_l11);
			for (int i = 0; i < 7; i++) {
				panel_4.add(listCounting3.get(i));
			}

//					

		}
		if (lottoCountNum >= 4) {

			for (int i = 0; i < 6; i++) {
				listCounting4.add(LabelFactory.updateLabel(lottoNumArrayList.get(3)[i]));
			}
			listCounting4.add(LabelFactory.updateLabel(lottoNumArrayList.get(3)[5]));

//		 패널의 다섯번째 행 레이블 6개 추가
			JLabel lblNewLabel_l111 = LabelFactory.updateLabel(readLottoNums2().get(3));
			listCounting4.get(6).setPreferredSize(new Dimension(100, 50));
			listCounting4.get(6).setHorizontalAlignment(JLabel.CENTER);

			List<JLabel> lblList4 = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				lblList4.add(listCounting4.get(i));
			}

			String resultPnl4 = checkLottoNum.correctCircle(lottoNumList, lottoNumArrayList, 3, lblList4,
					bonusLottoNum);
			listCounting4.get(6).setText(resultPnl4);

			// 다섯번째 패널
			panel_5.add(lblNewLabel_l111);
			for (int i = 0; i < 7; i++) {
				panel_5.add(listCounting4.get(i));
			}

		}

		if (lottoCountNum >= 5) {

			for (int i = 0; i < 6; i++) {
				listCounting5.add(LabelFactory.updateLabel(lottoNumArrayList.get(4)[i]));
			}
			listCounting5.add(LabelFactory.updateLabel(lottoNumArrayList.get(4)[5]));

			// 패널의 여섯번째 행 레이블 6개 추가
			JLabel lblNewLabel_l1111 = LabelFactory.updateLabel(readLottoNums2().get(4));
			listCounting5.get(6).setPreferredSize(new Dimension(100, 50));
			listCounting5.get(6).setHorizontalAlignment(JLabel.CENTER);

			List<JLabel> lblList5 = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				lblList5.add(listCounting5.get(i));
			}

			String resultPnl5 = checkLottoNum.correctCircle(lottoNumList, lottoNumArrayList, 4, lblList5,
					bonusLottoNum);

			listCounting5.get(6).setText(resultPnl5);

//			 여섯번째 패널
			panel_6.add(lblNewLabel_l1111);
			for (int i = 0; i < 7; i++) {
				panel_6.add(listCounting5.get(i));
			}

		}

		// 1번째 패널에 lottoNumList의 랜덤수에 따른 아이콘 넣기
		lbl1.setIcon(chooseIcon(lottoNumList.get(0)));
		lbl2.setIcon(chooseIcon(lottoNumList.get(1)));
		lbl3.setIcon(chooseIcon(lottoNumList.get(2)));
		lbl4.setIcon(chooseIcon(lottoNumList.get(3)));
		lbl5.setIcon(chooseIcon(lottoNumList.get(4)));
		lbl6.setIcon(chooseIcon(lottoNumList.get(5)));
		bonuslbl7.setIcon(chooseIcon(bonusLottoNum));
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		// 첫번째 패널
		panel_1.add(lbl1);
		panel_1.add(lbl2);
		panel_1.add(lbl3);
		panel_1.add(lbl4);
		panel_1.add(lbl5);
		panel_1.add(lbl6);
		panel_1.add(lblPlus);
		panel_1.add(bonuslbl7);
		// 패널 6개로 나눔
		JPanel panel_new1 = new JPanel();
		getContentPane().add(panel_new1, BorderLayout.NORTH);
		panel_new1.setPreferredSize(new Dimension(700, 350));
		
				
				// updateLabel 메소드 생성
				// 패널의 첫번째 행 레이블 6개 추가
				JLabel newLbl = new JLabel();
				newLbl.setIcon(new ImageIcon(Frame3.class.getResource("그림112.png")));
				newLbl.setPreferredSize(new Dimension(700, 350));
				
						// 각 패널에 해당하는 레이블들을 하나의 리스트로 만들어서 correctCircle 메서드를 실행해야됨
				
						// 각 인덱스값에 맞도록 메서드를 실행해줘야함
				//				correctCircle(lottoNumList, lottoNumArrayList, 0, lblList);
				
				//		 2번째 패널에 lottoNumArrayList에 따른 아이콘 넣기
						
						// 각 패널에 해당 레이블 추가
						
						// 디자인 패널
						panel_new1.add(newLbl);

//		 두번째 패널

//		 세번째 패널

//		frame3 correct = new correct();

		int number = 0;
		textField = new JTextField(10);

		for (int i = 0; i < number; i++) {
			JButton btn = new JButton();
//			panel.add(btn);
			setDefaultCloseOperation(EXIT_ON_CLOSE);

			Panel1.revalidate();
			// 레이아웃 새로 계산
			Panel1.repaint();
			// 변경된 지점 새로 그리기
		}
	}

	public class TextLabel extends JLabel {

		public TextLabel(int number) {
			setIcon(new ImageIcon(Frame3.class.getResource("검정.png")));//
			setHorizontalTextPosition(JLabel.CENTER); // 레이블의 텍스트 가로 부분을 가운데로 고정
			setVerticalTextPosition(JLabel.CENTER);// 레이블의 텍스트 세로 부분을 가운데로 고정
			setFont(new Font("Serif", Font.BOLD, 20)); // 폰트 설정
			setForeground(Color.WHITE); // 글자색깔 설정
			setText(String.valueOf(number)); // 글자 설정
			setHorizontalAlignment(SwingConstants.CENTER); // 레이블 자체의 수평 위치를 중간으로
			setVerticalAlignment(SwingConstants.CENTER); // 레이블 자체의 수직 위치를 중간으로
		}
	}

	public static void main(String[] args) {

		new Frame3().setVisible(true);

//		Frame3 frame = new Frame3();
//		new Frame3().readLottoNums();
//		new Frame3().readLottoNums2();
		// String[] line = frame.readLottoNums();
//		System.out.println(Arrays.toString(line));
	}
}

// 난수랑 이미지를 연결?
//			JFrame frame =  new JFrame();
////			getContentPane().add(frame);		
//			ImageIcon icon = new ImageIcon("image/검정.png");
//			JLabel lbl = new JLabel(icon, JLabel.LEFT);
//			lbl.setText("ㅁ");
//			lbl.setIcon(icon);
//			JTextField textField = new JTextField(50);
//			lbl.add(textField);
//			frame.setTitle("당첨 확인 화면");
//			frame.add(lbl);
//			
//			
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.setLocation(200, 200);
//			frame.setSize(500, 300);
////			frame.setVisible(true);