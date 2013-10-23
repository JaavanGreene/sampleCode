package parser;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Hashtable;

public class ConstantMemory {

	// stores values for assignment to variables
	public static Hashtable<String, Float> mem = new Hashtable<String, Float>();

	// saving remembered named constants
	public static void saveMem() throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("ConstMem.xml");
		System.out.println("file saved.");
		XMLEncoder e = new XMLEncoder(fos);
		e.writeObject(mem);
		e.close();
	}

	// loading remembered named constants
	public static void loadMem() throws FileNotFoundException {
		FileInputStream fis = new FileInputStream("ConstMem.xml");
		System.out.println("file loaded");
		XMLDecoder e = new XMLDecoder(fis);
		Hashtable<String, Float> tmp = (Hashtable<String, Float>) e.readObject();
		e.close();
		mem = tmp;
	}

	// print the constants out (mainly for testing)
	public static void printMem() {
		for (String c : mem.keySet()) {
			System.out.println("variable name: " + c + " - value: "
					+ mem.get(c));
		}
	}

	public static float find(String varName) {
		return mem.get(varName);
	}

	public static void set(String ch, float val) {
		mem.put(ch, val);
	}

	public static boolean search(String c) {
		if (mem.containsKey(c))
			return true;
		else
			return false;
	}
}
