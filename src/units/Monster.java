package units;

import java.io.BufferedWriter;
import java.io.IOException;
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

	public int getCurHp() {
		return this.curHp;
	}

	public void attack(Unit target) {
		target.hp -= (power - target.defense);
		try {
			writer.write("------------------------------------------------------");
			writer.write(String.format("%s가 %s에게 %d만큼의 데미지를 입혔습니다", name, target.name, (power - target.defense)));
			if (target.hp <= 0) {
				writer.write(String.format("%s를 처치했습니다.", target.name));
				target.hp = 0;
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void printData() {
		try {
			writer.write(String.format("name:[%s] hp:[%d/%d]power:[%d]", name, curHp, maxHp, power));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
