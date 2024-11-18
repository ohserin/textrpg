package textrpg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import units.Monster;
import units.Player;
import units.UnitManager;

public class StageBattle extends Stage {

	private final int EMPTY = 0;
	private final int ATTACK = 1;
	private final int SKILL = 2;

	UnitManager unitManager = null;
	ArrayList<Monster> monsterList = new ArrayList<>();
	Random ran = new Random();
	int monstersDead = 0;
	int playersDead = 0;

	public StageBattle() {
		unitManager = UnitManager.getInstance();
		ran = new Random();
	}

	@Override
	public boolean update() {
		boolean run = true;
		int pIdx = 0;
		int mIdx = 0;
		boolean turn = true;

		try {
			writer.write("몬스터와의 전투를 시작합니다. 💥\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (run) {
			if (turn) {
				printCharacter();
				if (pIdx < Player.getGuildSize()) {
					playerAttack(pIdx);
					pIdx += 1;
				} else {
					turn = !turn;
					pIdx = 0;
				}
			} else {
				if (mIdx < monsterList.size()) {
					monsterAttack(mIdx);
					mIdx += 1;
				} else {

					turn = !turn;
					mIdx = 0;
				}
			}

			if (monstersDead >= monsterList.size()) {
				run = false;
				try {
					writer.write("🎉 모든 몬스터를 처치하였습니다! 🎉");
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (playersDead >= Player.getGuildSize()) {
				run = false;
				try {
					writer.write("💀 패배! 모든 플레이어가 처치당하였습니다. 💀");
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public void init() {

	}

	public void monsterAttack(int index) {
		Monster monster = monsterList.get(index);
		if(monster.getCurHp() <= 0)
			return;
		while(true) {
			int idx = ran.nextInt(Player.getGuildSize());
			if(Player.getGuildUnit(idx).getHp() > 0) {
				monster.attack(Player.getGuildUnit(idx));
				break;
			}
		}
	}

	public void printCharacter() {
		try {
			writer.write("[BATTLE]\n");
			writer.write(String.format("⚜%d : %d⚜\n", playersDead, monstersDead));
			writer.write("[PLATER]\n");
			for(int i = 0; i < Player.getGuildSize(); i++) {
				Player.getGuildUnit(i).printData();
			}
			writer.write("[MONSTER]\n");
			for(int i = 0; i < monsterList.size(); i++) {
			monsterList.get(i).printData();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void playerAttack(int index) {
		Player player = Player.getGuildUnit(index);

//		if (player.getHp() <= 0)
//			return;

		try {
			writer.write("📜 [메뉴 선택] 📜");
			writer.write(String.format("[%s] [1.어택] [2.스킬]", player.getName()));
			writer.flush();

			String input = reader.readLine();
			int sel = Integer.parseInt(input);

			if (sel == ATTACK) {
				while (true) {
					int idx = ran.nextInt(monsterList.size());
					if (monsterList.get(idx).getCurHp() > EMPTY) {
						player.attack(monsterList.get(idx));
						break;
					}
				}
			} else if (sel == SKILL) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
