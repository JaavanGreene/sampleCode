package parser;

public abstract class BooleanExpression extends ParenArithmeticExpression {
	public enum RelativeOperator {
		equal, notequal, greaterthan, greaterthaneq, lessthaneq, lessthan;

		public void display() {
			System.out.println("display class for rel op");
		}
	};

	protected ParenArithmeticExpression ex1, ex2;

	public BooleanExpression() {
	}

	public abstract void gather(ExpressionStatement expr1,
			ExpressionStatement expr2);

	public abstract boolean check();

}
