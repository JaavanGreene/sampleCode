package uielements;

import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class CalcWindow {
	private static Window w = new Window("viewport", CalcSkin.skin());

	public static Window window() {
		w.setSize(480, 480);
		w.defaults().spaceBottom(10);
		w.layout();
		w.pack();
		return w;
	}

}
