package textrpg;

public class TextRPG {

	private TextRPG() {
	}

	private static TextRPG instance = new TextRPG();

	public static TextRPG getInstance() {
		return instance;
	}

	private boolean isRun = true;
	
	public void run() {
		GameManager.instance.init();
		while(isRun) {
			isRun = GameManager.instance.changeStage();
			break;
		}
	}

}
