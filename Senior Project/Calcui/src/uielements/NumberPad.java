package uielements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class NumberPad {
	private  Button but0, but1, but2, but3, but4, but5, but6, but7, but8,
			but9, butdec;
	private Table numbers;
	Skin skin;

	/**
	 * @wbp.parser.entryPoint
	 */
	public Table NumbPad() {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

		but7 = new TextButton("7", skin);
		but7.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "7");
			}
		});

		but8 = new TextButton("8", skin);
		but8.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "8");
			}
		});

		but9 = new TextButton("9", skin);
		but9.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "9");
			}
		});

		but4 = new TextButton("4", skin);
		but4.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "4");
			}
		});

		but5 = new TextButton("5", skin);
		but5.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "5");
			}
		});

		but6 = new TextButton("6", skin);
		but6.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "6");
			}
		});

		but1 = new TextButton("1", skin);
		but1.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "1");
			}
		});

		but2 = new TextButton("2", skin);
		but2.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "2");
			}
		});

		but3 = new TextButton("3", skin);
		but3.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "3");
			}
		});

		but0 = new TextButton("0", skin);
		but0.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + "0");
			}
		});

		butdec = new TextButton(".", skin);
		butdec.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				TextEntry.textfield().setText(
						TextEntry.textfield().getText() + ".");
			}
		});

		numbers = new Table();
		numbers.row();
		numbers.add(but7).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.add(but8).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.add(but9).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.row();
		numbers.add(but4).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.add(but5).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.add(but6).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.row();
		numbers.add(but1).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.add(but2).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.add(but3).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.row();
		numbers.add(but0).minSize(50, 50).expandX().fillX().colspan(1);
		numbers.add(butdec).minSize(50, 50).expandX().fillX().colspan(1);

		return numbers;

	}
}
