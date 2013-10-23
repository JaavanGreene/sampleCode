package parser;

import java.util.HashMap;

public class VariableMemory {

	// stores values for assignment to variables
	public static HashMap<String, Float> mem = new HashMap<String, Float>();

	public static float find(String ch) {
		return mem.get(ch);
	}

	public static void set(String varName, float val) {
		mem.put(varName, val);
	}

	public static void remove(String ch) {
		mem.remove(ch);
	}

	public static boolean search(String c) {
		if (mem.containsKey(c))
			return true;
		else
			return false;

	}

}
