package units;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

public class Guild {
	private final int SIZE = 3;
	private ArrayList<Player> guildList = new ArrayList<>();

	private Random ran = new Random();
	private Unit[] partyList;

	protected BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public ArrayList<Player> getGuildList() {
		return guildList;
	}

	public void setGuild() {
		guildList.add(new Player("전사", 5, 1000, 35, 15, 0));
		guildList.add(new Player("궁수", 8, 700, 15, 15, 0));
		guildList.add(new Player("마법사", 3, 500, 20, 10, 0));

		for (int i = 0; i < SIZE; i++) {
			guildList.get(i).party = true;
		}

		partyList = new Unit[SIZE];
		int n = 0;
		for (int i = 0; i < guildList.size(); i++) {
			if (guildList.get(i).party == true) {
				partyList[n] = guildList.get(i);
				n += 1;
			}
		}
	}

	public Player getGuildUnit(int num) {
		return guildList.get(num);
	}

	public void printAllUnitStaus() {
		try {
			writer.write("================");
			writer.write(String.format("💰 : %d", Player.money));
			writer.write("======= [길드원] ========");
			for (int i = 0; i < guildList.size(); i++) {
				writer.write(String.format("%d)번", i + 1));
				writer.write(String.format("[이름 : %s]", guildList.get(i).name));
				writer.write(String.format("[레벨 : %d]", guildList.get(i).level));
				writer.write(String.format("[체력 : %d / %d]", guildList.get(i).hp, guildList.get(i).maxHp));
				writer.write(String.format("[공격력 : %d]", guildList.get(i).attack));
				writer.write(String.format("[방어력 : %d]", guildList.get(i).defense));
				writer.write(String.format("[파티중 : %s]", guildList.get(i).party ? "파티중" : "파티없음"));
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printUnitStaus(int selUnit) {
		guildList.get(selUnit).printStatus();
	}

	public void printUnitItem(int selUnit) {

	}

	public void guildMenu() {
		while (true) {
			try {
				writer.write("=========== [길드관리] =================");
				writer.write("[1][📜길드목록] [2][👨‍👨‍👦길드원 추가] [3][👤길드원 삭제]");
				writer.write("[4][👥파티원 교체] [5][🔝정렬] [0][↪️뒤로가기]");
				writer.flush();

				int sel = getInputNumber();

				if (sel == 1)
					printAllUnitStaus();
				else if (sel == 2)
					buyUnit();
				else if (sel == 3)
					removeUnit();
				else if (sel == 4)
					partyChange();
				else if (sel == 5)
					unitSort();
				else if (sel == 0) {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void buyUnit() {
		if (Player.money < 3000) {
			System.out.println("골드가 부족합니다.");
			return;
		}
		String[] n1 = { "행복의", "행운의", "깜찍", "마법의,네모", "피치", "달빛", "앙큼" };
		String[] n2 = { "루피", "소라", "네모", "핏치", "천사", "불", "낑깡", "찡긋" };
		String[] n3 = { "킬러", "맨", "걸", "보이", "우먼", "고동", "여우", "빌런" };

		String name = n1[ran.nextInt(n1.length)];
		name += n2[ran.nextInt(n2.length)];
		name += n3[ran.nextInt(n3.length)];

		int rNum = ran.nextInt(n1.length) + 2;
		int hp = rNum * 10;
		int att = rNum + 1;
		int def = rNum / 2 + 1;

		Player temp = new Player(name, 1, hp, att, def, 0);
		try {
			writer.write("=============================");
			writer.write(String.format("[이름 : %s]", name));
			writer.write(String.format("[레벨 : %d]", 1));
			writer.write(String.format("[체력 : %d / %d]", hp, hp));
			writer.write(String.format("[공격력 : %s]", att));
			writer.write(String.format("[방어력 : %s]", def));
			writer.write("길드원 추가 완료 ✅");
			writer.write(String.format("이름 : %s", name));
			writer.flush();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			guildList.add(temp);
			Player.money -= 5000;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void removeUnit() {
		printAllUnitStaus();
		try {
			writer.write("삭제할 번호를 입력하세요.");
			int sel = getInputNumber();

			if (guildList.get(sel).party) {
				writer.write("파티에 참여한 멤버는 삭제할 수 없습니다.");
				return;
			}
			writer.write("=============================");
			writer.write(String.format("\n%s 길드원을 삭제합니다.\n", guildList.get(sel).name));
			writer.write("=============================");
			guildList.remove(sel);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printParty() {
		try {
			writer.write("============== [파티원] ===============");
			for (int i = 0; i < SIZE; i++) {
				Unit pt = partyList[i];
				writer.write(String.format("%d)번", i + 1));
				writer.write(String.format("[이름 : %s]", pt.name));
				writer.write(String.format("[레벨 : %d]", pt.level));
				writer.write(String.format("[체력 : %d / %d]", pt.hp, pt.maxHp));
				writer.write(String.format("[공격력 : %s]", pt.attack));
				writer.write(String.format("[방어력 : %s]", pt.defense));
				writer.write(String.format("[파티중 : %s]", guildList.get(i).party ? "파티중" : "파티없음"));
			}
			writer.write("================================================");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void partyChange() {
		printParty();
		try {
			writer.write("교체할 파티원 번호를 입력하세요.");
			int partyIdx = getInputNumber();

			printAllUnitStaus();
			writer.write("참가할 번호를 입력하세요.");
			int newPartyIdx = getInputNumber();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void unitSort() {
		for (int i = 0; i < guildList.size(); i++) {
			Player temp = guildList.get(i);

			int idx = i;
			for (int j = i; j < guildList.size(); j++) {
				if (temp.level < guildList.get(j).level) {
					temp = guildList.get(j);
					idx = j;
				}
			}
			Player temp2 = guildList.get(idx);
			guildList.set(idx, guildList.get(i));
			guildList.set(i, temp2);
		}
		printAllUnitStaus();
	}

	private int getInputNumber() {
		while (true) {
			try {
				String input = reader.readLine();
				return Integer.parseInt(input);
			} catch (NumberFormatException | IOException e) {
				try {
					writer.write("숫자로 입력해주세요.");
					writer.flush();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}
	}

	public void clear() {
		guildList.clear();
	}

}
