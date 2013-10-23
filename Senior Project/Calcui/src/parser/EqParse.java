package parser;

public class EqParse {
	private EquationBody stmt;

	public EqParse(EquationBody stmt) {
		this.stmt = stmt;
	}

	public void run() throws ParserException {
		stmt.display();
	}
}
