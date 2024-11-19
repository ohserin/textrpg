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
	public ArrayList<Monster> monList = new ArrayList<>();
	private String packageName = "units.";
	private String[] monsters = { "DeathKnight", "Vampire", "FleshGolem" };

	private Random random = new Random();
	public Player player = new Player();

	public void init() {
		player.init();
	}

	public void generateMonsters(int size) {
		for (int i = 0; i < size; i++) {
			int rNum = random.nextInt(monsters.length);
			String monsterClassName = packageName + monsters[rNum];

			try {
				Class<?> clazz = Class.forName(monsterClassName);
				Monster monster = (Monster) clazz.getDeclaredConstructor().newInstance();

				int hp = random.nextInt(100) + 200;
				int attPower = random.nextInt(40) + 20;
				monster.init(hp, attPower);

				monList.add(monster);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Player getPlayer() {
		return player;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public List<Monster> getMonsterList() {
		return monList;
	}

	public void setPlayer(Player newPlayer) {
		this.player = newPlayer;
	}
}
