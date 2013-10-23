package uielements;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;

public class TextEntry {
	private static TextField t = new TextField("", CalcSkin.skin());

	public static TextField textfield() {
		t.setMessageText("Enter an Equation");
		t.setTextFieldListener(new TextFieldListener() {
			public void keyTyped(TextField textField, char key) {
				if (key == '\n')
					textField.getOnscreenKeyboard().show(false);
			}
		});

		return t;
	}

}
