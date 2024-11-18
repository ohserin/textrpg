package textrpg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public abstract class Stage {
	protected BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public abstract boolean update();

	public abstract void init();
}
