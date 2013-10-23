package parser;

public abstract class ParenBinaryExpression extends ParenArithmeticExpression {

	// splits the arithmetic operator into the different kinds of binary
	// expressions
	public enum ArithmeticOperator {
		add, subtract, multiply, divide, exponent
	};

	protected Operand op1;
	protected Operand op2;

	public ParenBinaryExpression() {
	}

}
