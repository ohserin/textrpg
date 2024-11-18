package textrpg;

import java.io.IOException;

public class StageBattle extends Stage {

	@Override
	public boolean update() {
		try {
			writer.write("⚔️ 전투모드로 진입합니다 ⚔️");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void init() {
		
	}

}
