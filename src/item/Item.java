package item;

public class Item {
	private final int WEAPON = 1;
	private final int ARMOR = 2;
	private final int RING = 3;

	private int kind;
	private String name;
	private int power;
	private int price;

	public void setItem(int k, String n, int p, int pr) {

		kind = k;
		name = n;
		power = p;
		price = pr;
	}
}
