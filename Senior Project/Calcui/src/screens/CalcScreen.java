package screens;

import java.io.FileNotFoundException;

import parser.ConstantMemory;
import parser.ParserException;
import uielements.AlgebraButtonPanels;
import uielements.CalcSkin;
import uielements.CalcWindow;
import uielements.PhysicsButtonPanels;
import uielements.TextEntry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.me.calculator.EquationStorage;
import com.me.calculator.OperationStorage;

public class CalcScreen extends AbstractScreen {
	public static TextField textfield;
	ScrollPane scroll;
	Window window;
	Group bg;
	Table abuttonpanel, pbuttonpanel;
	Table t;
	SelectBox dropdown;
	PhysicsButtonPanels p = new PhysicsButtonPanels();
	AlgebraButtonPanels a = new AlgebraButtonPanels();
	public static EquationStorage eqstore = new EquationStorage();
	public static OperationStorage opstore = new OperationStorage();


	public static Stage stage;

	public CalcScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
	
		try {
			ConstantMemory.loadMem();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		dropdown = new SelectBox(new String[] { "Select Subject", "Algebra",
				"Physics", "Calculus" }, CalcSkin.skin());

		textfield = TextEntry.textfield();

		window = CalcWindow.window();
		ScrollPane scrollPane = new ScrollPane(window);

		try {
			abuttonpanel = a.ButtonPanels();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
		abuttonpanel.setVisible(true);
		abuttonpanel.setSize(480, 250);

		try {
			pbuttonpanel = p.ButtonPanels();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
		pbuttonpanel.setVisible(false);
		pbuttonpanel.setSize(480, 250);

		bg = new Group();
		bg.addActor(abuttonpanel);
		bg.addActor(pbuttonpanel);
		bg.setWidth(480);
		bg.setPosition(0, 0);

		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false);
		Gdx.input.setInputProcessor(stage);

		dropdown.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				int selectionnumber = 0;
				Gdx.app.log("UITest", "selection: " + dropdown.getSelection());
				if (dropdown.getSelection() == "Simple") {

				} else if (dropdown.getSelection() == "Algebra") {
					abuttonpanel.setVisible(true);
					pbuttonpanel.setVisible(false);
				} else if (dropdown.getSelection() == "Calculus") {

				} else if (dropdown.getSelection() == "Physics") {
					pbuttonpanel.setVisible(true);
					abuttonpanel.setVisible(false);
				}

			}
		});
		/*
		 * t.setPosition(0, 0); t.setSize(480, 800);
		 * 
		 * t.row().fill().expandX();
		 * t.add(dropdown).fillX().expandX().colspan(3);
		 * 
		 * t.row().fill().expandX();
		 * t.add(textfield).fillX().expandX().colspan(3);
		 * 
		 * t.row().fill().expandX(); t.add(window).minSize(480, 480).colspan(3);
		 * 
		 * t.row().fill().expandX(); t.add(abuttonpanel);
		 * t.defaults().spaceBottom(10);
		 * 
		 * t.layout(); t.pack();
		 */

		t = new Table();
		t.setPosition(0, 250);
		t.setSize(480, 800);
		t.row().fill().expandX();
		t.add(dropdown).fillX().expandX();
		t.row().fill().expandX();
		t.add(textfield).fill().expandX();
		t.row().fill().expandX();
		t.add(scrollPane).fill().minSize(480, 480);
		t.row().fill().expandX();
		//t.layout();
		t.pack();
		t.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

		stage.addActor(t);
		stage.addActor(bg);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
}
