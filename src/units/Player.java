package units;

public class Player extends Unit {

	public static int money;

	public static Guild guild = new Guild();
//	public static Inventory inven=new Inventory();

	public Player() {

	}

	public Player(String name, int level, int maxhp, int att, int def, int exp) {
		super(name, level, maxhp, att, def, exp);
	}

	public Player(String name, int level, int maxhp, int att, int def, int exp, boolean party) {
		super(name, level, maxhp, att, def, exp, false);
	}

	public void init() {
		money = 100000;
		guild.clear();
		guild.setGuild();
	}

	public static Player getGuildUnit(int num) {
		return guild.getGuildUnit(num);
	}

	public static int getGuildSize() {
		return guild.getGuildList().size();
	}

}
