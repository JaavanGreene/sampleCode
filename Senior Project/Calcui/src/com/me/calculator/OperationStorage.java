package com.me.calculator;

import java.util.ArrayList;
import java.util.List;

import parser.EquationBody;

public class OperationStorage {
	private List<EquationBody> opstore = new ArrayList<EquationBody>();

	public void set(EquationBody eq) {
		opstore.add(0, eq);
	}

	public EquationBody get() {
		return opstore.get(0);
	}

}
