package textrpg;

import java.io.IOException;
import java.util.Random;

import units.UnitManager;

public class StageSetting extends Stage {

	private Random ran = new Random();
	private UnitManager um = null;

	@Override
	public boolean update() {
		while (true) {
			try {
				writer.write("?");
				writer.write("[1.길드관리]\\t\\t[2.상점]\\t\\t[3.인벤토리]");
				writer.write("[4.저장]\\t\\t[5.로드]\\t\\t[0.종료]");

				String input = reader.readLine();
				int sel = Integer.parseInt(input);

				if (sel == 1) {
					
				} else if (sel == 2) {
				} else {
					GameManager.nextStage = "";
					break;
				}
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public void init() {
		um = UnitManager.getInstance();

	}

}
