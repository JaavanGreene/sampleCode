package operations;

import parser.Variable;
import screens.CalcScreen;
import uielements.CalcSkin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;

public class CheckVariables {
	private Variable v;
	private Skin skin = CalcSkin.skin();

	public CheckVariables(Variable v) {
		this.v = v;
	}

	public void checkvariables() {
		System.out.println("creating dialog for fixing variable "+ v.getName());
		CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		Dialog valdialog = new Dialog("Give a Value", skin, "dialog") {
			protected void result(Object object) {
				TextField t = (TextField) object;
				String choice = t.getText();
				System.out.println("Chosen: " + choice);
				v.setValue(Float.parseFloat(choice));
				System.out.println("set variable " + v.getName() + " to "
						+ Float.parseFloat(choice));
			}
		};
		valdialog.text("Variable " + v.getName() + "needs a value");
		TextField textfield = new TextField("", skin);
		textfield.setTextFieldListener(new TextFieldListener() {
			public void keyTyped(TextField textField, char key) {
				if (key == '\n')
					textField.getOnscreenKeyboard().show(false);
			}
		});
		CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		valdialog.add(textfield);
		valdialog.button("Enter", textfield);
		valdialog.show(CalcScreen.stage);
		CalcScreen.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		System.out.println("if it showed up were all good");

	}
	
}
