package parser;

import java.util.ArrayList;
import java.util.List;

public class TokenizeMath {// tokenizes program and creates an array

	private String delimiter = "[ ]"; // tokenize by space
	private String input;

	private List<String> result = new ArrayList<String>();

	public TokenizeMath() {

	}

	public List<String> getTokens(String s) {

		// remove spaces
		String tmp = s.replaceAll(" ", "");

		System.out.println(tmp);

		// String [] cut = tmp.split(delimiter); OLD CODE, REQUIRES SPACE BAR

		// -------------------------------------------------------------------
		// NEW CODE IDEA FOR TOKENIZING, DOES NOT REQUIRE SPACES, ALLOWS FOR
		// MULTI-CHAR VARIABLES

		String tokentemp;
		System.out.println("made temp string, looking now");
		System.out.println("length of the input is " + tmp.length());
		List<String> cut = new ArrayList<String>();

		for (int x = 0; x <= tmp.length(); x++) {
			x = 0;
			System.out.println("starting with character " + tmp.charAt(x));

			// split letter words
			if (Character.isLetter(tmp.charAt(x))) {
				System.out.println("looking for letters");
				int y = x + 1;
				if (tmp.length() <= 1) {
					y = x + 1;
				} else {
					System.out.println("checking for letter-ness of: "
							+ tmp.charAt(y));
					for (int z = y; z < tmp.length(); z++) {
						if (!Character.isLetter(tmp.charAt(z))) {
							System.out.println(tmp.charAt(z)
									+ " ain't a letter");
							break;
						} else
							y++;
					}
				}

				tokentemp = tmp.substring(x, y);
				System.out.println("token added: " + tokentemp);
				tmp = tmp.replaceFirst(tokentemp, "");
				System.out.println("remaining" + tmp);
			}

			// split number values
			else if (Character.isDigit(tmp.charAt(x))) {
				System.out.println("looking for numbers");
				int y = x + 1;

				if (tmp.length() <= 1) {
					y = x + 1;
				}

				else {
					System.out.println("checking for number-ness of: "
							+ tmp.charAt(y));
					for (int z = y; z < tmp.length(); z++) {
						if (tmp.charAt(z) == ".".charAt(0)) {
							y++;
						} else if (!Character.isDigit(tmp.charAt(z))) {
							System.out.println(tmp.charAt(z)
									+ " ain't numerical");
							break;
						} else
							y++;
					}
				}
				tokentemp = tmp.substring(x, y);
				System.out.println("token added: " + tokentemp);
				tmp = tmp.replaceFirst(tokentemp, "");
				System.out.println("remaining: " + tmp);
			}

			// split parentheses
			else if (tmp.charAt(x) == "(".charAt(0)
					|| tmp.charAt(x) == ")".charAt(0)) {
				System.out.println("it's a parenthesis");
				tokentemp = tmp.substring(x, x + 1);
				// regex fix nonsense
				if (tokentemp.equals("(")) {
					tmp = tmp.replaceFirst("\\(", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals(")")) {
					tmp = tmp.replaceFirst("\\)", "");
					System.out.println("fix'd.");
				}

				System.out.println("token added: " + tokentemp);
				System.out.println("remaining: " + tmp);
			}
			// test this with 2-5+s=34*g/x

			// split symbols
			else {
				int y = x + 1;
				System.out.println("checking for symbol-ness of: "
						+ tmp.charAt(y));
				for (int z = y; z < tmp.length(); z++) {
					String r = Character.toString(tmp.charAt(z));
					if (!(r.equals("=") | r.equals(">") | r.equals("<")
							| r.equals("!") | r.equals("+") | r.equals("-")
							| r.equals("*") | r.equals("/") | r.equals("^"))) {
						System.out.println(tmp.charAt(z) + " ain't a symbol");
						break;
					} else
						y++;
				}
				System.out.println("looking for symbols");
				tokentemp = tmp.substring(x, y);
				System.out.println("token added: " + tokentemp);

				// fixer for stupid java regex
				System.out.println("is it a math symbol?");
				if (tokentemp.equals("+")) {
					tmp = tmp.replaceFirst("\\+", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals("-")) {
					tmp = tmp.replaceFirst("\\-", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals("*")) {
					tmp = tmp.replaceFirst("\\*", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals("/")) {
					tmp = tmp.replaceFirst("\\/", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals("=")) {
					tmp = tmp.replaceFirst("\\=", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals(">")) {
					tmp = tmp.replaceFirst("\\>", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals("<")) {
					tmp = tmp.replaceFirst("\\<", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals("<=")) {
					tmp = tmp.replaceFirst("\\<=", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals(">=")) {
					tmp = tmp.replaceFirst("\\>=", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals("!=")) {
					tmp = tmp.replaceFirst("\\!=", "");
					System.out.println("fix'd.");
				} else if (tokentemp.equals("^")) {
					tmp = tmp.replaceFirst("\\^", "");
					System.out.println("fix'd.");
				} else
					tmp = tmp.replaceFirst(tokentemp, "");
				System.out.println("token added: " + tokentemp);
			}
			// add split token into list
			cut.add(tokentemp);

		}

		// testing using L - rrrr+sdss/4363.56=lololol *7 - g

		// -------------------------------------------------------------------

		// List<String> out =new ArrayList<String>();

		List<String> out = cut;

		// for(String s1 : cut){
		// out.add(s1);
		// }

		out.add("[EOL]");

		return out;
	}

	public String printTokens() {
		StringBuffer build = new StringBuffer();
		for (String s : result) {
			build.append(s + " | ");
		}
		String out = build.toString();
		return out;
	}
}
