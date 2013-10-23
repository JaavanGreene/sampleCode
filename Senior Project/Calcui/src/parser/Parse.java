package parser;

//((s)+(34/x)*8)			(3x +56(d*w))=(1(d-4))
import java.util.ArrayList;
import java.util.List;
import screens.CalcScreen;
import com.me.calculator.EquationStorage;

public class Parse {
	public List<ParenArithmeticExpression> arithexpressions = new ArrayList<ParenArithmeticExpression>();
	public boolean isFunctionNeeded = true;
	boolean trigger = false;
	public LexicalAnalyzer lex;

	public EqParse parse(String input) throws ParserException {
		System.out.println("let's get this party started.");
		/* parses statements and formats equation */
		lex = new LexicalAnalyzer(input);
		EquationBody equation = getEquationBody();
		return new EqParse(equation);
	}

	private EquationBody getEquationBody() throws ParserException {
		BooleanExpression rel_op;
		System.out.println("-First we gotta get all our equation info in.");
		System.out.println("--Let's look for an arithmetic expression.");
		System.out.println("LEFT SIDE.");
		ExpressionStatement exst1 = buildExpressionStatement();
		// add in the = sign and answer variable if it's not there
		if (isEOLNext()) {
			lex.returnLexeme("answer");
			lex.returnLexeme("=");
		}
		System.out.println("--now we need a relative operator");

		rel_op = getBooleanExpression();
		arithexpressions.removeAll(arithexpressions);

		System.out
				.println("--Now for the other side's arithmetic expressions.");
		System.out.println("RIGHT SIDE.");
		ExpressionStatement exst2 = buildExpressionStatement();

		arithexpressions.removeAll(arithexpressions);

		EquationBody eq = new EquationBody(exst1, rel_op, exst2);
		CalcScreen.eqstore.set(eq);

		return eq;
	}

	private ExpressionStatement buildExpressionStatement()
			throws ParserException {
		ExpressionStatement exst = new ExpressionStatement();
		ParenArithmeticExpression expr1 = null;
		do {
			System.out.println("Parenthesis check!!");
			if (isOpenParenNext()) {
				ParenArithmeticExpression expr1t = getParenArithmeticExpression();
				expr1 = expr1t;
			} else {
				ArithmeticExpression expr1t = getArithmeticExpression();
				expr1 = expr1t;
			}

		} while (!isTriggerNext());

		System.out.println("THIS SIDE IS DONE-------------------------------");
		System.out.println("MAKE THE LIST------------------------------");
		expr1.add(exst);
		return exst;
	}

	private ArithmeticExpression getArithmeticExpression()
			throws ParserException {
		// determines whether assigning a value or doing math
		System.out
				.println("-aight, we in the arithmetic expression gettin' class.");
		ArithmeticExpression expr = null;
		Operand op1;
		String funID = getNextToken();
		lex.returnLexeme(funID);
		System.out.println("a-exp class wants an operand.");
		System.out.println("PARENTHESIS CHECK!");
		if (isOpenParenNext()) {
			op1 = getParenArithmeticExpression();
		} else
			op1 = getOperand();

		isFunctionNeeded = true;
		System.out.println("isfunctionneeded changed to true");
		// m.eqVisualize("TRUE", Calculator.gridbag, Calculator.fillrow,
		// Color.LIGHT_GRAY, null);
		System.out.println("back to the a-exp class, checkin unary vs binary");
		String s = getNextToken();

		if (!isValidArithmeticOperator(s)) {
			System.out.println("we on some unary nonsense");
			lex.returnLexeme(s);

			System.out.println("will this have to be a function?");
			if (!isEOLNext()) {
				if (!isRelOpNext()) {
					if (isFunctionNeeded) {
						System.out.println("test the possible funID: " + funID);
						Function tempfun = getFunction(op1, funID);
						expr = new UnaryExpression(tempfun);
					}
				} else {
					System.out.println("no need for a function.");
					expr = new UnaryExpression(op1);
				}
			} else {
				System.out.println("no need for a function.");
				expr = new UnaryExpression(op1);
			}
			arithexpressions.add(expr);
			System.out.println("unary added to the list at "
					+ (arithexpressions.size() - 1) + ". SHOW THAT SUCKA!");

		}

		else {
			UnaryExpression un1;
			System.out.println("change that first term to some unary nonsense");
			un1 = new UnaryExpression(op1);
			System.out
					.println("looks like a binary arithmetic operator. Lets getArithmeticOperatin'.");
			lex.returnLexeme(s);
			s = getNextToken();
			Operand op2;
			BinaryExpression.ArithmeticOperator operator = getArithmeticOperator(s);
			System.out
					.println("got our arithmetic operator. now for the second operand in this mess.");

			System.out.println("PARENTHESIS CHECK!");
			if (isOpenParenNext()) {
				op2 = getParenArithmeticExpression();

			} else {
				arithexpressions.removeAll(arithexpressions);
				op2 = getOperand();
			}

			expr = constructBinaryExpression(un1, operator, op2);
			System.out
					.println("ARITHMETIC EXPRESSION done been constructed. AWWWYEAH!!!");
		}

		System.out.println("is a function coming up?");
		if (!isEOLNext()) {
			if (!isRelOpNext()) {
				if (!isArithOpNext()) {
					if (isOpenParenNext()) {
						arithexpressions.removeAll(arithexpressions);

						System.out.println("test the possible funID: " + funID);
						Function tempfun = getFunction(expr, funID);
						expr = new UnaryExpression(tempfun);
					} else
						System.out.println("naw its all good.");
				} else
					System.out.println("naw its all good.");
			} else
				System.out.println("naw its all good.");
		} else
			System.out.println("naw its all good.");

		isFunctionNeeded = true;
		System.out.println("true on the function needed");

		return expr;
	}

	private ParenArithmeticExpression getParenArithmeticExpression()
			throws ParserException {
		System.out.println("in the paren exp class");
		System.out.println("wiping the list.");
		arithexpressions.removeAll(arithexpressions);
		System.out.println("WIPE");
		ParenArithmeticExpression expr = null;
		reservedword("(");
		String funID = getNextToken();
		lex.returnLexeme(funID);
		System.out.println("initial reserved word checks out");
		Operand op1;
		do {
			System.out
					.println("got our open parenthesis, now we need the first operand");
			System.out.println("PARENTHESIS CHECK!");
			if (isOpenParenNext()) {
				System.out.println("found one! gotta get a paren expression");
				op1 = getParenArithmeticExpression();
				System.out.println("got the paren expression");
			} else {
				op1 = getOperand();
			}

			isFunctionNeeded = true;
			System.out.print("isfunctionneeded changed to true");
			System.out
					.println("back to the paren a-exp class, checkin ()unary vs ()binary");
			String s = getNextToken();

			if (!isValidArithmeticOperator(s)) {
				System.out.println("we on some  ()unary nonsense");
				lex.returnLexeme(s);
				if (!isEOLNext()) {
					if (!isRelOpNext()) {
						if (!isCloseParenNext()) {
							if (isFunctionNeeded) {
								System.out.println("test the possible funID: "
										+ funID);
								Function tempfun = getFunction(op1, funID);
								UnaryExpression tempun = new UnaryExpression(
										tempfun);
								expr = new ParenUnaryExpression(tempun);
							}
						} else {
							expr = new ParenUnaryExpression(op1);
						}
					} else {
						expr = new ParenUnaryExpression(op1);
					}
					// isFunctionNeeded=false;
					// System.out.println("isFN back to false since the function's been made.");
					// m.eqVisualize("FALSE", Calculator.gridbag,
					// Calculator.fillrow, Color.LIGHT_GRAY, null);
				} else {
					expr = new ParenUnaryExpression(op1);
				}
				arithexpressions.add(expr);
				System.out.println("()unary added to the list at "
						+ (arithexpressions.size() - 1) + ". SHOW THAT SUCKA!");
				// visualization of the memory add
				// m.eqVisualize("adding at "+
				// (arithexpressions.size()-1), Calculator.gridbag,
				// Calculator.c, Color.ORANGE, null);
				// arithexpressions.get(arithexpressions.size()-1).display();
				// m.eqVisualize(null, Calculator.gridbag,
				// Calculator.fillrow, Color.CYAN, null);
				// Calculator.resultBox.updateUI();
			}

			else {
				UnaryExpression un1;
				System.out
						.println("change that first term to some unary nonsense");
				un1 = new UnaryExpression(op1);
				System.out
						.println("looks like a ()binary arithmetic operator. Lets getArithmeticOperatin'.");
				lex.returnLexeme(s);
				s = getNextToken();
				ParenBinaryExpression.ArithmeticOperator operator = getParenArithmeticOperator(s);
				System.out
						.println("got our arithmetic operator. now for the second operand in this mess.");
				Operand op2;
				System.out.println("PARENTHESIS CHECK!");
				if (isOpenParenNext()) {
					op2 = getParenArithmeticExpression();
				} else {
					arithexpressions.removeAll(arithexpressions);
					/*
					 * m.eqVisualize("WIPE", Calculator.gridbag,
					 * Calculator.fillrow, Color.CYAN, null);
					 * System.out.println("WIPE");
					 */
					op2 = getOperand();
				}

				if (!isCloseParenNext()) {
					BinaryExpression.ArithmeticOperator operatornoparen = getArithmeticOperator(s);
					expr = constructBinaryExpression(un1, operatornoparen, op2);
				} else {
					expr = constructParenBinaryExpression(un1, operator, op2);
				}

				System.out
						.println("ARITHMETIC EXPRESSION done been constructed. AWWWYEAH!!!");
			}
		} while (!isCloseParenNext());
		reservedword(")");

		System.out.println("is a function coming up?");
		if (!isEOLNext()) {
			if (!isRelOpNext()) {
				if (!isArithOpNext()) {
					if (isOpenParenNext()) {
						/*
						 * m.eqVisualize("WIPE", Calculator.gridbag,
						 * Calculator.fillrow, Color.CYAN, null);
						 * System.out.println("yup. soooooo... WIPE");
						 */
						arithexpressions.removeAll(arithexpressions);

						System.out.println("test the possible funID: " + funID);
						Function tempfun = getFunction(expr, funID);
						expr = new UnaryExpression(tempfun);
					} else
						System.out.println("naw its all good.");
				} else
					System.out.println("naw its all good.");
			} else
				System.out.println("naw its all good.");
		} else
			System.out.println("naw its all good.");

		isFunctionNeeded = true;
		System.out.println("true on the function needed");
		// m.eqVisualize("TRUE", Calculator.gridbag, Calculator.fillrow,
		// Color.LIGHT_GRAY, null);

		return expr;
	}

	private ArithmeticExpression constructBinaryExpression(Operand op1,
			BinaryExpression.ArithmeticOperator operator, Operand op2) {
		System.out
				.println("Seems we got all we need for this expression. BUILD IT.");
		ArithmeticExpression expr;
		if (operator == BinaryExpression.ArithmeticOperator.add)
			expr = new ExpressionAddition(op1, op2);
		else if (operator == BinaryExpression.ArithmeticOperator.subtract)
			expr = new ExpressionSubtraction(op1, op2);
		else if (operator == BinaryExpression.ArithmeticOperator.multiply)
			expr = new ExpressionMultiplication(op1, op2);
		else if (operator == BinaryExpression.ArithmeticOperator.divide)
			expr = new ExpressionDivision(op1, op2);
		else
			expr = new ExpressionExponent(op1, op2);
		System.out
				.println("It's built, now lets add it to the arithmetic expression list.");
		arithexpressions.add(expr);
		// -------memory log print, for debugging
		// m.eqVisualize("adding at "+(arithexpressions.size()-1),
		// Color.ORANGE, null);
		// arithexpressions.get(arithexpressions.size()-1).display();
		// m.eqVisualize(null, Calculator.gridbag, Calculator.fillrow,
		// Color.CYAN, null);
		// Calculator.resultBox.updateUI();

		return expr;
	}

	private ParenArithmeticExpression constructParenBinaryExpression(
			Operand op1, ParenBinaryExpression.ArithmeticOperator operator,
			Operand op2) {
		System.out
				.println("Seems we got all we need for this expression. BUILD IT.");
		ParenArithmeticExpression expr;
		if (operator == ParenBinaryExpression.ArithmeticOperator.add)
			expr = new ParenAddition(op1, op2);
		else if (operator == ParenBinaryExpression.ArithmeticOperator.subtract)
			expr = new ParenSubtraction(op1, op2);
		else if (operator == ParenBinaryExpression.ArithmeticOperator.multiply)
			expr = new ParenMultiplication(op1, op2);
		else if (operator == ParenBinaryExpression.ArithmeticOperator.divide)
			expr = new ParenDivision(op1, op2);
		else
			expr = new ParenExponent(op1, op2);
		System.out
				.println("It's built, now lets add it to the arithmetic expression list.");
		arithexpressions.add(expr);
		// --------memory log print again
		// m.eqVisualize("adding at "+ (arithexpressions.size()-1),
		// Color.ORANGE, null);
		// arithexpressions.get(arithexpressions.size()-1).display();
		// m.eqVisualize(null, Calculator.gridbag, Calculator.fillrow,
		// Color.CYAN, null);
		// Calculator.resultBox.updateUI();

		return expr;
	}

	private BinaryExpression.ArithmeticOperator getArithmeticOperator(
			String s) throws ParserException {
		System.out.println("-we in the getArithmeticOperator class now");
		BinaryExpression.ArithmeticOperator op;
		// String s = getNextToken();
		if (s.equals("+")) {
			op = BinaryExpression.ArithmeticOperator.add;
			System.out.println("PLUS sign SON!");
		} else if (s.equals("-")) {
			op = BinaryExpression.ArithmeticOperator.subtract;
			System.out.println("MINUS sign SON!");
		} else if (s.equals("*")) {
			op = BinaryExpression.ArithmeticOperator.multiply;
			System.out.println("yo baby lets MULTIPLY");
		} else if (s.equals("/")) {
			op = BinaryExpression.ArithmeticOperator.divide;
			System.out.println("DIVIDE DAT SUCKA");
		} else if (s.equals("^")) {
			op = BinaryExpression.ArithmeticOperator.exponent;
			System.out.println("I GOT THE POWAH!!!");
		} else {
			System.out.println("NOPE NOPE NOPE NO ARITHMETIC EXPRESSION");
			throw new ParserException("arithmetic operator expected");
		}
		return op;
	}

	private ParenFunction.ParenOperator getParenOperator(String s)
			throws ParserException {
		System.out.println("let's grab a prarn function");
		ParenFunction.ParenOperator fun;
		if (s.equals("sin")) {
			fun = ParenFunction.ParenOperator.sin;
			System.out.println("Sine");
		} else if (s.equals("cos")) {
			fun = ParenFunction.ParenOperator.cos;
			System.out.println("Cosine");
		} else if (s.equals("tan")) {
			fun = ParenFunction.ParenOperator.tan;
			System.out.println("Tangent");
		} else if (s.equals("arctan")) {
			fun = ParenFunction.ParenOperator.arctan;
			System.out.println("arc tangent");
		} else if (s.equals("arccos")) {
			fun = ParenFunction.ParenOperator.arccos;
			System.out.println("arc cosine");
		} else if (s.equals("arcsin")) {
			fun = ParenFunction.ParenOperator.arcsin;
			System.out.println("Arc sine");
		} else if (s.equals("log")) {
			fun = ParenFunction.ParenOperator.log;
			System.out.println("log");
		} else {
			System.out.println("It's a general function");
			fun = ParenFunction.ParenOperator.parenmult;
		}
		return fun;
	}

	private ParenFunction constructParenFunction(Operand op1,
			ParenFunction.ParenOperator operator, Operand op2)
			throws ParserException {
		System.out
				.println("Seems we got all we need for this expression. BUILD IT.");
		ParenFunction fun;
		if (operator == ParenFunction.ParenOperator.arccos)
			fun = new FunArcCos(op1, op2);
		else if (operator == ParenFunction.ParenOperator.arcsin)
			fun = new FunArcSin(op1, op2);
		else if (operator == ParenFunction.ParenOperator.arctan)
			fun = new FunArcTan(op1, op2);
		else if (operator == ParenFunction.ParenOperator.cos)
			fun = new FunCos(op1, op2);
		else if (operator == ParenFunction.ParenOperator.sin)
			fun = new FunSin(op1, op2);
		else if (operator == ParenFunction.ParenOperator.tan)
			fun = new FunTan(op1, op2);
		else if (operator == ParenFunction.ParenOperator.parenmult)
			fun = new FunParenMult(op1, op2);
		else if (operator == ParenFunction.ParenOperator.log)
			fun = new FunLog(op1, op2);
		else
			throw new ParserException("oops");
		System.out
				.println("function's built, now lets add it to the arithmetic expression list.");
		arithexpressions.add(fun);
		// -------memory log print, for debugging
		// System.out.println("added to list at "+(arithexpressions.size()-1));
		// m.eqVisualize("adding at "+(arithexpressions.size()-1),
		// Color.ORANGE, null);
		// arithexpressions.get(arithexpressions.size()-1).display();
		// m.eqVisualize(null, Calculator.gridbag, Calculator.fillrow,
		// Color.CYAN, null);
		// Calculator.resultBox.updateUI();

		return fun;
	}

	private ParenBinaryExpression.ArithmeticOperator getParenArithmeticOperator(
			String s) throws ParserException {
		System.out.println("-we in the getParenArithmeticOperator class now");
		ParenBinaryExpression.ArithmeticOperator op;

		if (s.equals("+")) {
			op = ParenBinaryExpression.ArithmeticOperator.add;
			System.out.println("PLUS sign SON!");
		} else if (s.equals("-")) {
			op = ParenBinaryExpression.ArithmeticOperator.subtract;
			System.out.println("MINUS sign SON!");
		} else if (s.equals("*")) {
			op = ParenBinaryExpression.ArithmeticOperator.multiply;
			System.out.println("yo baby lets MULTIPLY");
		} else if (s.equals("/")) {
			op = ParenBinaryExpression.ArithmeticOperator.divide;
			System.out.println("DIVIDE DAT SUCKA");
		} else if (s.equals("^")) {
			op = ParenBinaryExpression.ArithmeticOperator.exponent;
			System.out.println("I GOT THE POWAH!!!");
		} else {
			System.out.println("NOPE NOPE NOPE NO ARITHMETIC EXPRESSION");
			throw new ParserException("arithmetic operator expected");
		}
		return op;
	}

	private BooleanExpression.RelativeOperator getRelativeOperator()
			throws ParserException {
		System.out.println("in the getRelativeOperatorclass");

		// determines which relative operator to use in construct of boolean
		// operator
		BooleanExpression.RelativeOperator op = null;
		String s = getNextToken();
		if (isValidRelativeOperator(s)) {
			// lex.returnLexeme(s);
			if (s.equals("=")) {
				op = BooleanExpression.RelativeOperator.equal;
				System.out.println("Its an equals sign!");
			} else if (s.equals("!=")) {
				op = BooleanExpression.RelativeOperator.notequal;
				System.out.println("aint equal");
			} else if (s.equals("<=")) {
				op = BooleanExpression.RelativeOperator.lessthaneq;
				System.out.println("less than or equal is what this is");
			} else if (s.equals("<")) {
				op = BooleanExpression.RelativeOperator.lessthan;
				System.out.println("OMG less than!");
			} else if (s.equals(">")) {
				op = BooleanExpression.RelativeOperator.greaterthan;
				System.out.println("greater than THE BEST THE BEST");
			} else if (s.equals(">=")) {
				op = BooleanExpression.RelativeOperator.greaterthaneq;
				System.out.println("greater than or equal to. ALSO THE BEST");
			}
			System.out.println("we got our relative op! RADICAL!!!!");
			return op;
		} else {
			lex.returnLexeme(s);
			throw new ParserException("invalid relative op");
		}
	}

	private BooleanExpression getBooleanExpression()
			throws ParserException {
		System.out.println("gimme a relative operator");
		BooleanExpression.RelativeOperator op = getRelativeOperator();
		System.out.println("got the relative operator.");
		return constructBooleanExpression(op);
	}

	private BooleanExpression constructBooleanExpression(
			BooleanExpression.RelativeOperator operator) {
		BooleanExpression expr;
		System.out.println("constructing boolean expression");
		if (operator == BooleanExpression.RelativeOperator.equal) {
			expr = new EqualTo(/* op1, op2 */);
			System.out.println("made the E");
		} else if (operator == BooleanExpression.RelativeOperator.greaterthaneq) {
			expr = new GreaterThanEqual(/* op1, op2 */);
			System.out.println("made the GE");
		} else if (operator == BooleanExpression.RelativeOperator.greaterthan) {
			expr = new GreaterThan(/* op1, op2 */);
			System.out.println("made the G");
		} else if (operator == BooleanExpression.RelativeOperator.lessthaneq) {
			expr = new LessThanEqual(/* op1, op2 */);
			System.out.println("made the LE");
		} else if (operator == BooleanExpression.RelativeOperator.lessthan) {
			expr = new LessThan(/* op1, op2 */);
			System.out.println("made the L");
		} else {
			expr = new NotEqualTo(/* op1, op2 */);
			System.out.println("made the NE");
		}
		System.out.println("constructed!");
		return expr;
	}

	public Operand getOperand() throws ParserException {
		System.out.println("in the getOperand class now.");
		Operand op = null;
		System.out.println("is anything in the saved expressions list?");
		if (arithexpressions.size() > 0) {
			System.out.println("yup. STEALING IT.");
			// /debug visualization for the code
			// m.eqVisualize("using "+(arithexpressions.size()-1),
			// Color.orange, null);
			// arithexpressions.get(arithexpressions.size()-1).display();
			// m.eqVisualize(null, Calculator.gridbag, Calculator.fillrow,
			// Color.CYAN, null);
			// Calculator.resultBox.updateUI();
			op = arithexpressions.remove(arithexpressions.size() - 1);
			/*
			 * System.out.println("wipe the list"); m.eqVisualize("WIPE",
			 * Calculator.gridbag, Calculator.fillrow, Color.CYAN, null);
			 * System.out.println("WIPE");
			 */
			arithexpressions.removeAll(arithexpressions);
		} else {
			System.out.println("nope.");
			String s = getNextToken();
			lex.returnLexeme(s);

			if (Character.isLetter(s.charAt(0))) {
				System.out
						.println("If "
								+ s
								+ " IS an operand, it starts with a letter, so do we know what it is?");
				if (ConstantMemory.search(s)) {
					System.out.println("Let's get ourselves a named constant.");
					op = getNamedConstant();
				} else {
					System.out.println("brb grabbing a variable.");
					op = getVariable();
				}
			} else if (Character.isDigit(s.charAt(0))) {
				System.out.println("all numbers. lemme get dat constant.");
				op = getConstant();
			} else if (s.equals("=") | s.equals(">=") | s.equals("<=")
					| s.equals("<") | s.equals(">") | s.equals("!=")) {
				System.out.println("yo its a boolean expression");
				op = getBooleanExpression();
			} else if (s.equals("(")) {
				System.out.println("me thinks its a parenthesis");
				op = getParenArithmeticExpression();
			}
			// else{
			// System.out.println("methinks this operand is another arithmetic expression.");
			// op = getArithmeticExpression();
			// }
		}
		return op;
	}

	private Function getFunction(Operand op1, String s)
			throws ParserException {
		System.out.println("we're in the function gettin class");
		Function fun;
		System.out.println("is a parenthesis next?");
		if (isOpenParenNext()) {
			System.out.println("yup. we'll need a parenthetical function");
			ParenFunction.ParenOperator pfun = getParenOperator(s);
			Operand op2 = getParenArithmeticExpression();
			fun = constructParenFunction(op1, pfun, op2);

		} else {
			System.out.println("nope. regular multiplication function");
			Operand op2 = getOperand();
			fun = new FunMultiply(op1, op2);

		}
		return fun;
	}

	private NamedConstant getNamedConstant() throws ParserException {
		System.out.println("getting named constant.");
		String s = getNextToken();
		String cName;
		if (Character.isLetter(s.charAt(0))) {
			cName = s;
			System.out.println("YEEEEA BOIIII GOT DAT NAMED CONSTANT");
		} else {
			System.out.println("NAMED CONSTANT? LOLOL NAH BRAH");
			throw new ParserException("named constant expected");
		}
		return new NamedConstant(cName);
	}

	private Variable getVariable() throws ParserException {
		System.out.println("in the getVariable class.");
		String s = getNextToken();
		System.out.println("getting variable named " + s);
		String vName;
		if (Character.isLetter(s.charAt(0))) {
			vName = s;
			System.out.println("WOOO!!! WE GOT A VARIABLE! LOCK IT IN!");
		} else {
			System.out.println("EPIC VARIABLE FAIL");
			throw new ParserException("variable expected");
		}
		return new Variable(vName);
	}

	private Constant getConstant() throws ParserException {
		System.out.println("getting constant number value");
		// builds constant value
		String s = getNextToken();
		float value;
		try {
			value = Float.parseFloat(s);
		} catch (NumberFormatException e) {
			throw new ParserException("not a number");
		}
		System.out.println("Numerical constant. LIKE A ROCK!!!! #fordvehicles");
		return new Constant(value);
	}

	private String getNextToken() {
		/*
		 * takes token/lexeme from the code list and processes. in doing so, it
		 * removes the entry from the stack
		 */
		String token = lex.getLexeme();
		System.out.println("Getting lexeme token: " + token);
		return token;
	}

	void reservedword(String word) throws ParserException {
		String s = getNextToken();
		if (!s.equals(word)) {
			System.out.println(word + "missing");
			throw new ParserException("expected: " + word);
		} else
			System.out.println(word + " found");
	}

	private boolean isValidArithmeticOperator(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")
				|| s.equals("^");
	}

	private boolean isValidRelativeOperator(String s) {
		return s.equals(">") || s.equals("<") || s.equals("<=")
				|| s.equals(">=") || s.equals("=") || s.equals("!=");
	}

	boolean isArithmeticExpression(Operand op) {
		return (op instanceof ArithmeticExpression || op instanceof ParenArithmeticExpression);
	}

	boolean isParenArithmeticExpression(Operand op) {
		return (op instanceof ParenArithmeticExpression);
	}

	boolean isRelOpNext() {
		String s = getNextToken();
		lex.returnLexeme(s);
		if (s.equals("=") || s.equals("<=") || s.equals(">=") || s.equals("<")
				|| s.equals(">") || s.equals("!="))
			return true;
		else
			return false;

	}

	boolean isTriggerNext() {
		String s = getNextToken();
		lex.returnLexeme(s);
		if (s.equals("=") || s.equals("<=") || s.equals(">=") || s.equals("<")
				|| s.equals(">") || s.equals("!=") || s.equals("[EOL]"))
			return true;
		else
			return false;

	}

	boolean isEOLNext() {
		String s = getNextToken();
		lex.returnLexeme(s);
		if (s.equals("[EOL]"))
			return true;
		else
			return false;
	}

	boolean isArithOpNext() {
		String s = getNextToken();
		lex.returnLexeme(s);
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")
				|| s.equals("^"))
			return true;
		else
			return false;
	}

	private boolean isOpenParenNext() {
		String s = getNextToken();
		lex.returnLexeme(s);
		if (s.equals("("))
			return true;
		else
			return false;
	}

	private boolean isOpenParen(String s2) {
		if (s2.equals("("))
			return true;
		else
			return false;
	}

	private boolean isCloseParenNext() {
		String s = getNextToken();
		lex.returnLexeme(s);
		if (s.equals(")"))
			return true;
		else
			return false;
	}

	private boolean isMultNeeded() {
		String s = getNextToken();
		lex.returnLexeme(s);
		if (!Character.isLetter(s.charAt(0)) | !Character.isDigit(s.charAt(0)))
			return true;
		else
			return false;
	}
}
