package textrpg;

import java.io.IOException;

public class StageLobby extends Stage {

	@Override
	public boolean update() {
		try {
			writer.write("\n====== 🏛️ LOBBY 🏛️ ======\n");
			writer.write("➖➖➖➖➖➖➖➖➖➖➖➖\n");
			writer.write("   [1]      [2]      [0]\n");
			writer.write("[⚔️ 전투] [⚙️ 설정] [⛔ 종료]\n");
			writer.flush();

			String input = reader.readLine();
			try {
				int sel = Integer.parseInt(input);

				if (sel > 2 || sel < 0) {
					writer.write("없는 메뉴입니다.\n");
					writer.flush();
					return true;
				}

				if (sel == 1) {
					GameManager.setNextStage("BATTLE");
					GameManager.getInstance().changeStage();
				} else if (sel == 2) {
					GameManager.setNextStage("SETTINGS");
				} else if (sel == 0) {
					writer.write("게임을 종료합니다.");
					writer.flush();
					return false;
				}

			} catch (NumberFormatException e) {
				writer.write("숫자로 입력해주세요.");
				writer.flush();
				return true;
			}
			return false;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void init() {

	}

}
