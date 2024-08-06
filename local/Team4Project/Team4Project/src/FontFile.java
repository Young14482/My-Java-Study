import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

// 폰트 사용하기 위한 클래스
public class FontFile {

	private Font font;

	public FontFile() {
		// 폰트의 경로(ttf파일)
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,
					Frame3.class.getResourceAsStream("/font/Cafe24Ssurround-v2.0.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Font getUseFont(int style, float size) {
		return font.deriveFont(style, size);
	}
}
