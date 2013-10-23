package graphing;

import uielements.CalcSkin;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class GraphWindow {
	static final int MAX_LINES = 1000;
	OrthographicCamera camera;
	Mesh lineMesh;
	float[] lineVertices;
	int vertexIndex = 0;
	Vector3 unprojectedVertex = new Vector3();
	private  Window w = new Window("viewport", CalcSkin.skin());

}
