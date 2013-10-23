package com.me.calculator;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import operations.Balancer;
import operations.Solver;
import parser.BooleanExpression;
import parser.EqParse;
import parser.EquationBody;
import parser.ExpressionStatement;
import parser.Parse;
import parser.ParserException;
import parser.TokenizeMath;
import parser.Variable;
import parser.VariableMemory;
import screens.CalcScreen;
import uielements.CalcSkin;
import uielements.CalcWindow;
import uielements.TextEntry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class methods {
	
	public methods(){}

	public void eqVisualize(String name, Color col, Table t) {
		Button viz = new TextButton(name, CalcSkin.skin());
		viz.setColor(col);
		t.add(viz);
	}

	/*******************************************************************************/
	public void display() {

	}

	/*******************************************************************************/

	public void evaluateEquation(EquationBody eq) {
		eq.evaluate();
	}

	/*******************************************************************************/

	public void parseInputText() {
		Parse p;
		String input = TextEntry.textfield().getText();
		TokenizeMath t = new TokenizeMath();

		VariableMemory.remove("answer");
		p = new Parse();
		System.out.println("\nTokenizer test");
		ArrayList<String> test = (ArrayList<String>) t.getTokens(input);
		for (String s : test) {
			System.out.println("the lexeme is: " + "'" + s + "'");
		}
		System.out.println("\n");

		try {
			EqParse eq = p.parse(input);
			System.out.println("-----------------");
			System.out.println("All done parsing. RUN IT!");
			System.out.println("-----------------");

			eq.run();
		} catch (ParserException e1) {
			e1.printStackTrace();
		}

	}

	/*********************************************************************************************/
	public void changeVariable(String varName) {

	}

	public void balanceEquation(EquationBody eq, Variable v) {
		
		Balancer b = new Balancer(eq, v);
		b.balance();
		/*eq.setExpr1(b.getExpr1());
		eq.setExpr2(b.getExpr2());
		eq.setRel_op(b.getRelOp());
		*/CalcScreen.eqstore.set(eq);
		CalcScreen.eqstore.get().display();
		CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	}

	public void solveEquation(EquationBody equ, Variable v) {
		EquationBody temp = equ;
				
		if(equ.getexpr1().size()>1){
			balanceEquation(equ, v);
		}
		
		CalcWindow.window().row();
		CalcWindow.window().add(new Label("Now Solving", CalcSkin.skin()));
		CalcWindow.window().row();
		
		//CalcScreen.eqstore.set(equ);
		Solver s = new Solver(equ, v);
		s.solve();
				
		CalcScreen.eqstore.set(temp);
		CalcScreen.eqstore.get().display();
		// (342+x)/89=23/5
		// (sdsdss+654)/jyf
	}
}
