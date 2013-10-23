package screens;

import uielements.CalcSkin;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class LaunchScreen extends AbstractScreen {
	Label title;
	Stage stage;
	TextButton calcbutton, graphingbutton;
	Table actgroup;
	private SpriteBatch batch;
	private OrthographicCamera camera;

	public LaunchScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();

		actgroup = new Table();
		String titletext = "Advanced Calculator\nSelect Mode:";

		title = new Label(titletext, CalcSkin.skin());
		title.setAlignment(Align.center, Align.center);
		title.setFontScale(2);
		title.setColor(Color.WHITE);

		calcbutton = new TextButton("Calculator", CalcSkin.skin());
		calcbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("calc screen clicked");
				game.setScreen(new CalcScreen(game));
			}
		});

		graphingbutton = new TextButton("Graphing", CalcSkin.skin());
		graphingbutton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GraphingScreen(game));
			}
		});

		actgroup.add(title);
		actgroup.row();
		actgroup.add(calcbutton);
		actgroup.row();
		actgroup.add(graphingbutton);
		actgroup.setPosition(
				(Gdx.graphics.getWidth() / 2) - actgroup.getWidth(),
				(Gdx.graphics.getHeight() / 2) - actgroup.getHeight());
		actgroup.layout();

		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false);
		Gdx.input.setInputProcessor(stage);
		stage.addActor(actgroup);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// sprite.draw(batch);
		batch.end();
	}

}
