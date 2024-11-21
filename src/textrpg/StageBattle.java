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

	UnitManager um = null;
	ArrayList<Monster> monsterList = new ArrayList<>();
	Random ran = new Random();
	int monstersDead = 0;
	int playersDead = 0;

	public StageBattle() {
		um = UnitManager.getInstance();
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
			} else if (!turn) {
				if (mIdx < monsterList.size()) {
					monsterAttack(mIdx);
					mIdx += 1;
				} else {
					turn = !turn;
					mIdx = 0;
				}
			}
			checkLive();
			if (monstersDead <= 0 || playersDead <= 0)
				break;
		}
		GameManager.setNextStage("LOBBY");
		return false;
	}

	@Override
	public void init() {
		um.getMonsterList().clear();
		um.generateMonsters(4);
		um.player.init();
		um.player = new Player();
		monsterList = null;
		monsterList = um.monList;
		monstersDead = monsterList.size();
		playersDead = Player.getGuildSize();
	}

	public void printCharacter() {
		try {
			writer.write("[BATTLE]\n");
			writer.write(String.format("⚜%d : %d⚜\n", playersDead, monstersDead));

			writer.write("[PLAYER]\n");
			writer.flush();
			for (int i = 0; i < Player.getGuildSize(); i++) {
				Player.getGuildUnit(i).printData();
			}

			writer.write("[MONSTER]\n");
			writer.flush();
			for (int i = 0; i < monsterList.size(); i++) {
				monsterList.get(i).printData();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void playerAttack(int index) {
		Player player = Player.getGuildUnit(index);

		if (player.getHp() <= 0)
			return;

		try {
			writer.write("📜 [메뉴 선택] 📜\n");
			writer.write(String.format("🎲[%s]의 차례입니다.\n [1.어택]", player.getName()));
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void monsterAttack(int index) {
		Monster monster = monsterList.get(index);
		if (monster.getCurHp() <= 0)
			return;
		while (true) {
			int idx = ran.nextInt(Player.getGuildSize());
			if (Player.getGuildUnit(idx).getHp() > 0) {
				try {
					monster.attack(Player.getGuildUnit(idx));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public void checkLive() {
		int num = 0;
		for (int i = 0; i < Player.getGuildSize(); i++) {
			if (Player.getGuildUnit(i).getHp() <= 0) {
				num += 1;
			}
		}
		playersDead = Player.getGuildSize() - num;

		num = 0;
		for (int i = 0; i < monsterList.size(); i++) {
			if (monsterList.get(i).getCurHp() <= 0) {
				num += 1;
			}
		}
		monstersDead = monsterList.size() - num;
	}

}
