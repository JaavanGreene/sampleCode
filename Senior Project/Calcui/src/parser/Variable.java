package parser;

import javax.swing.JFrame;

import operations.CheckVariables;
import uielements.CalcSkin;
import uielements.VariableEvalDialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Variable implements Operand {
	private String varName;
	public float value;
	private int index;

	// build and return variable with no value

	public Variable(String name) {
		varName = name;
	}

	public void setValue(float val) {
		VariableMemory.set(varName, val);
		value = val;
	}

	protected String getVariableName() {
		return varName;
	}

	@Override
	public void setIndex(int i) {
		index = i;
	}

	@Override
	public float value() {
		if (!VariableMemory.search(varName)) {
			CheckVariables c = new CheckVariables(this);
			c.checkvariables();
			
			return VariableMemory.find(varName);
		} else {
			return VariableMemory.find(varName);
		}
	}

	@Override
	public void display(Table t) {
		String tip;
		Button viz;
		if (VariableMemory.search(varName)) {
			viz = new TextButton(VariableMemory.find(varName) + "",
					CalcSkin.skin());
			viz.setColor(Color.GREEN);
			tip = "[v: " + varName + ", " + VariableMemory.find(varName) + "]";
			System.out.print(tip);
			if (!(varName == "answer")) {
				/*
				 * viz.addMouseListener(new MouseAdapter() {
				 * 
				 * @Override public void mouseClicked(MouseEvent arg0) { try{
				 * VariableChangeDialog evaldiag = new VariableChangeDialog(new
				 * JFrame(), varName); evaldiag.setVisible(true); } catch
				 * (Exception e) { e.printStackTrace(); } } });
				 */
			}
		}

		else {
			viz = new TextButton(varName, CalcSkin.skin());
			viz.setColor(Color.GREEN);
			tip = "variable: " + varName;
			System.out.println(tip);
			if (!(varName == "answer")) {
				/*
				 * viz.addMouseListener(new MouseAdapter() {
				 * 
				 * @Override public void mouseClicked(MouseEvent arg0) { try{
				 * VariableAddValueDialog evaldiag = new
				 * VariableAddValueDialog(new JFrame(), varName);
				 * evaldiag.setVisible(true); } catch (Exception e) {
				 * e.printStackTrace(); } } });
				 */}
		}
		t.add(viz);

	}

	@Override
	public void evaluate() {
		if (!isReservedFunction()) {
			if (varName == "answer") {
				VariableMemory.remove("answer");
				if (!VariableMemory.search(varName)) {
					System.out.println("variable " + varName + " has no value");
					// setValue((Float) CalcScreen.eqstore.get().expr1.get());
					// System.out.println("now it's"+ value);
				}
				/*
				 * else{
				 * System.out.println("variable "+varName+" has old value "+
				 * value); setValue(CalcScreen.eqstore.get().expr1.get());
				 * System.out.println("now it's"+ value); }
				 */
			} else {
				if (!VariableMemory.search(varName)) {
					try {
						VariableEvalDialog evaldiag = new VariableEvalDialog(
								new JFrame(), varName);
						evaldiag.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	boolean isReservedFunction() {
		if (varName.equals("sin") | varName.equals("cos")
				| varName.equals("tan") | varName.equals("arcsin")
				| varName.equals("arccos") | varName.equals("arctan")
				| varName.equals("log") | varName.equals("ln")
				| varName.equals("lg")) {
			return true;
		} else
			return false;
	}

	@Override
	public void add(ExpressionStatement exst) {
		exst.add(new Variable(varName));

	}

	@Override
	public String getName() {
		return varName;
	}

}
