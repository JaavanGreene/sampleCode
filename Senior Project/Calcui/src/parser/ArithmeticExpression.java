package parser;

public abstract class ArithmeticExpression extends ParenArithmeticExpression {

	@Override
	abstract float get();

	@Override
	public abstract void evaluate();

	@Override
	public abstract void add(ExpressionStatement exst);

}
