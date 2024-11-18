package units;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public abstract class Monster {
	protected int curHp;
	protected int maxHp;
	protected int power;
	protected String name;
	protected String state = "노말";
	
	protected BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	Monster(String name, int hp, int att) {
		this.name = name;
		this.curHp = hp;
		this.maxHp = hp;
		this.power = att;
	}

	public void init(int hp, int att) {
		this.curHp = hp;
		this.maxHp = hp;
		this.power = att;
	}

	public void init(String name, int hp, int att) {
		this.name = name;
		this.curHp = hp;
		this.maxHp = hp;
		this.power = att;
	}

	public void attack(Unit target) {
		target.hp -= (power - target.defense);
		
	}
}
