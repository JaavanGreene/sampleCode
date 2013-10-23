package parser;

public abstract class ParenArithmeticExpression implements Operand {

	abstract float get();

	@Override
	public abstract void evaluate();

	@Override
	public abstract void add(ExpressionStatement exst);

}
