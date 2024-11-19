package textrpg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TextRPG {

	private TextRPG() {
	}

	private static TextRPG instance = new TextRPG();

	public static TextRPG getInstance() {
		return instance;
	}

	protected BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	private boolean isRun = true;

	public void run() {
		GameManager.instance.init();
		while (true) {
			isRun = GameManager.instance.changeStage();
			if (isRun == false) {
				break;
			}
		}
		try {
			writer.write("게임이 종료되었습니다.");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
