package uielements;

import com.badlogic.gdx.Input.TextInputListener;

public class VariableAddValueListener implements TextInputListener {
	public String varval;

	@Override
	public void input(String text) {
		varval = text;
	}

	@Override
	public void canceled() {
	}
}
