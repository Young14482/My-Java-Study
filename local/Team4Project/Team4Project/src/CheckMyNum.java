import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CheckMyNum extends JPanel {
	JFrame frame;
    public CheckMyNum() {
        setLayout(new BorderLayout());
        
        StringBuilder numbersString = new StringBuilder("고객님의 구매하신 로또 번호입니다\n\n");
        try (BufferedReader reader = new BufferedReader(new FileReader("./\\Lotto\\lotto.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String index = parts[0].trim();
                    String numberPart = parts[1].trim();
                    numbersString.append(index).append(": ").append(numberPart).append("\n");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "파일을 읽는 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        JTextArea textArea = new JTextArea(numbersString.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        
        // "부자되러가기" 버튼 생성
        JButton btnRich = new JButton("부자되러가기");

        // 버튼 클릭 이벤트 설정
        btnRich.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Frame3 lottoDialog = new Frame3();
                lottoDialog.setVisible(true);
                frame.setVisible(false);
            }
        });

        buttonPanel.add(btnRich);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // 메서드를 사용하여 CheckMyNum 패널을 JFrame에서 보여줍니다.
    public void showInFrame() {
        frame = new JFrame("내 번호 확인");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // 화면 중앙에 배치
        frame.setVisible(true);
    }
}
