package units;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnitManager {

	private UnitManager() {
	}

	private static UnitManager instance = new UnitManager();

	public static UnitManager getInstance() {
		return instance;
	}

	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Monster> monList = new ArrayList<>();
	private String packageName = "";
	private String[] monsterTypes = { "DeathKnight", "Vampire", "FleshGolem" };

	private Random random = new Random();
	private Player player = new Player();

	public void init() {
		player.init();
	}

	public void generateMonsters(int size) {
		for (int i = 0; i < size; i++) {
			int rIdx = random.nextInt(monsterTypes.length);
			String monsterClassName = packageName + monsterTypes[rIdx];

			try {
				Class<?> clazz = Class.forName(monsterClassName);
				Monster monster = (Monster) clazz.getDeclaredConstructor().newInstance();

				int hp = random.nextInt(100) + 100;
				int attPower = random.nextInt(10) + 10;
				monster.init(hp, attPower);

				monList.add(monster);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public List<Monster> getMonsterList() {
		return monList;
	}

}
