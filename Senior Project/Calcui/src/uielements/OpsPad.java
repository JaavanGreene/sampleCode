package uielements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.me.calculator.methods;

public class OpsPad {
	private Button plus, minus, mult, divide, clear, equals, enter,
			openparen, closeparen, power;
	private Table ops;
	Skin skin;
	methods m = new methods();

	/**
	 * @wbp.parser.entryPoint
	 */
	public Table OpsPad() {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

		clear = new TextButton("CLEAR", skin);
		clear.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText("");
				CalcWindow.window().clear();
			}
		});

		openparen = new TextButton("(", skin);
		openparen.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "(");
			}
		});

		closeparen = new TextButton(")", skin);
		closeparen.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + ")");
			}
		});

		divide = new TextButton("/", skin);
		divide.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "/");
			}
		});

		power = new TextButton("^", skin);
		power.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "^");
			}
		});

		mult = new TextButton("*", skin);
		mult.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "*");
			}
		});

		equals = new TextButton("=", skin);
		equals.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "=");
			}
		});

		plus = new TextButton("+", skin);
		plus.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "+");
			}
		});

		minus = new TextButton("-", skin);
		minus.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "-");
			}
		});

		enter = new TextButton("ENTER", skin);
		enter.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				m.parseInputText();
				TextEntry.textfield().setText("");

			}
		});

		ops = new Table();
		ops.row();
		ops.add(openparen).minSize(50, 40).expandX().fillX().colspan(1);
		ops.add(closeparen).minSize(50, 40).expandX().fillX().colspan(1);
		ops.row();
		ops.add(plus).minSize(50, 40).expandX().fillX().colspan(1);
		ops.add(minus).minSize(50, 40).expandX().fillX().colspan(1);
		ops.row();
		ops.add(mult).minSize(50, 40).expandX().fillX().colspan(1);
		ops.add(divide).minSize(50, 40).expandX().fillX().colspan(1);
		ops.row();
		ops.add(power).minSize(50, 40).expandX().fillX().colspan(1);
		ops.add(equals).minSize(50, 40).expandX().fillX().colspan(1);
		ops.row();
		ops.add(clear).minSize(50, 40).expandX().fillX().colspan(1);
		ops.add(enter).minSize(50, 40).expandX().fillX().colspan(1);

		return ops;

	}
}
