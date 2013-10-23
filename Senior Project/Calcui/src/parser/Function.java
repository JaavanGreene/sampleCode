package parser;

public abstract class Function extends ArithmeticExpression {

	@Override
	abstract float get();

	@Override
	public abstract void evaluate();

	@Override
	public abstract void add(ExpressionStatement exst);

}
