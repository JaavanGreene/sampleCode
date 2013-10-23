package com.me.calculator;

import java.util.ArrayList;
import java.util.List;

import parser.EquationBody;

public class EquationStorage {
	private List<EquationBody> eqstore = new ArrayList<EquationBody>();

	public void set(EquationBody eq) {
		eqstore.add(eq);
	}

	public EquationBody get() {
		return eqstore.get(eqstore.size()-1);
	}

}
