package units;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import item.Item;

public abstract class Unit {
	protected String name;
	protected int level;
	protected int hp;
	protected int maxHp;
	protected int attack;
	protected int defense;
	protected int exp;
	protected boolean party;
	protected Item weapon;
	protected Item armor;
	protected Item trinkets;
	String state = "노말";

	protected BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public Unit() {

	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getName() {
		return this.name;
	}

	public Unit(String name, int level, int hp, int att, int def, int exp) {
		this.name = name;
		this.level = level;
		this.maxHp = hp;
		this.hp = maxHp;
		this.attack = att;
		this.defense = def;
		this.exp = exp;
		party = false;
		weapon = null;
		armor = null;
		trinkets = null;
	}

	public Unit(String name, int level, int hp, int att, int def, int exp, boolean party) {
		super();
		this.name = name;
		this.level = level;
		this.maxHp = hp;
		this.hp = maxHp;
		this.attack = att;
		this.defense = def;
		this.exp = exp;
		this.party = party;
		weapon = null;
		armor = null;
		trinkets = null;
	}

	public void setItem(Item weapon, Item armor, Item trinkets) {
		this.weapon = weapon;
		this.armor = armor;
		this.trinkets = trinkets;
	}

	public void printStatus() {
	}

	public void attack(Monster target) {
		target.curHp -= attack;
		try {
			writer.write(String.format("%s가 %s에게 %2d의 데미지를 입혔습니다!\n", name, target.name, attack));
			writer.flush();
			if (target.curHp <= 0) {
				writer.write(String.format("%s을(를) 처치했습니다.\n", target.name));
				target.curHp = 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void printData() {
		try {
			writer.write(String.format("[%3s]  Hp:[%4d / %4d] Power:[%2d]\n", name, hp, maxHp, attack));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
