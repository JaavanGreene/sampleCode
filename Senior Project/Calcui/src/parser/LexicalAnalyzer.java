package parser;

import java.util.List;

public class LexicalAnalyzer {

	public List<String> input;
	TokenizeMath t = new TokenizeMath();

	public LexicalAnalyzer(String fileName) {
		input = t.getTokens(fileName);

	}

	String getLexeme() {
		String lex = "x";
		if (!input.isEmpty())
			lex = input.remove(0);
		return lex;
	}

	public void returnLexeme(String lex) {
		input.add(0, lex);
		System.out.println("RETURNING token " + lex
				+ " cuz we're gonna need to use " + lex + " later");
	}

}
