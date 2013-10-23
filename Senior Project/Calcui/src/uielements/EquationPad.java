package uielements;

import parser.ExpressionStatement;
import parser.Variable;
import screens.CalcScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.me.calculator.EquationStorage;
import com.me.calculator.methods;

public class EquationPad {
	private Button solve, balance, display, evaluate;
	private Table eqops;
	Skin skin;
	methods m = new methods();

	/**
	 * @wbp.parser.entryPoint
	 */
	public Table EquationPad() {
		//m = new methods();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

		eqops = new Table();

		evaluate = new TextButton("EVALUATE", skin);
		evaluate.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				m.evaluateEquation(CalcScreen.eqstore.get());
			}
		});

		display = new TextButton("DISPLAY", skin);
		display.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				CalcScreen.eqstore.get().display();
			}
		});

		//(ffs+fsdf)/432=6

		solve = new TextButton("SOLVE", skin);
		solve.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				Dialog solvedialog = new Dialog("Pick Variable", skin, "dialog") {
					protected void result(Object object) {
						Variable choice = (Variable) object;
						System.out.println("Chosen: " + choice.getName());
						m.solveEquation(CalcScreen.eqstore.get(), choice);
						//CalcScreen.eqstore.get().display();
					}
				};
				solvedialog.text("select the variable to solve for.");
				ExpressionStatement l = CalcScreen.eqstore.get().getexpr1();
				ExpressionStatement r = CalcScreen.eqstore.get().getexpr2();
				for (int i = 0; i < l.size(); i++) {
					if (l.get(i) instanceof Variable) {
						solvedialog.button(l.get(i).getName(), l.get(i));
					}
				}
				for (int i = 0; i < r.size(); i++) {
					if (r.get(i) instanceof Variable) {
						solvedialog.button(r.get(i).getName(), r.get(i));
					}
				}
				solvedialog.show(CalcScreen.stage);
			}
		});

		balance = new TextButton("BALANCE", skin);
		balance.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				Dialog balancedialog = new Dialog("Pick Variable", skin,
						"dialog") {
					protected void result(Object object) {
						Variable choice = (Variable) object;
						System.out.println("Chosen: " + choice.getName());
						m.balanceEquation(CalcScreen.eqstore.get(), choice);
					}
				};
				balancedialog.text("select the variable to balance.");
				ExpressionStatement l = CalcScreen.eqstore.get().getexpr1();
				ExpressionStatement r = CalcScreen.eqstore.get().getexpr2();
				for (int i = 0; i < l.size(); i++) {
					if (l.get(i) instanceof Variable) {
						balancedialog.button(l.get(i).getName(), l.get(i));
					}
				}
				for (int i = 0; i < r.size(); i++) {
					if (r.get(i) instanceof Variable) {
						balancedialog.button(r.get(i).getName(), r.get(i));
					}
				}
				balancedialog.show(CalcScreen.stage);
			}
		});

		eqops.row();
		eqops.add(evaluate).minSize(50, 50).expandX().fillX().colspan(1);
		eqops.row();
		eqops.add(display).minSize(50, 50).expandX().fillX().colspan(1);
		eqops.row();
		eqops.add(solve).minSize(50, 50).expandX().fillX().colspan(1);
		eqops.row();
		eqops.add(balance).minSize(50, 50).expandX().fillX().colspan(1);

		return eqops;

	}
}
