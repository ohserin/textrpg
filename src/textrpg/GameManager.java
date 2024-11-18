package textrpg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class GameManager {

	private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	private GameManager() {
	}

	public static GameManager instance = new GameManager();

	public static GameManager getInstance() {
		return instance;
	}

	
	private static String nextStage;
	
	public static String getNextStage() {
		return nextStage;
	}

	public static void setNextStage(String stage) {
		nextStage = stage;
	}

	Map<String, Stage> stageList = new HashMap<>();
	private String curStage = "";

	public void init() {
		stageList.put("START", new StageStart());
		stageList.put("LOBBY", new StageLobby());
		stageList.put("BATTLE", new StageBattle());
		nextStage = "START";
	}

	public boolean changeStage() {
		try {
			writer.write("🔺 : " + curStage);
			writer.newLine();
			writer.write("🔻 : " + nextStage);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();

		}

		if (curStage.equals(nextStage)) {
			return true;
		}

		curStage = nextStage;
		Stage stage = stageList.get(curStage);
		stage.init();

		boolean isUpdate = true;
		while (isUpdate) {
			isUpdate = stage.update();
			if (isUpdate == false)
				break;
		}

		return true;
	}

}
