package uielements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CalcSkin {
	private static Skin s = new Skin(Gdx.files.internal("data/uiskin.json"));

	public static Skin skin() {
		return s;
	}

}
