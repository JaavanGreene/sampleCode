package mathCalcs;

import parser.Parse;

public class Physics extends Parse {

	public void Kin_Displacement() {
		// d = Vi*T + (1/2)*A*T^2
		// d = ((Vi + Vf)/2) * T
		return;
	}

	public void Kin_Final_Velocity() {
		// Vf = Vi + A*T
		return;
	}

	public Integer Kin_Final_Velocity_Sqaured(Integer Vf, Integer Vi,
			Integer a, Integer d) {

		// Vf^2 = Vi^2 + 2 *A * D

		// Solve for D:

		if (d == null)
			return d = (Vi ^ 2) / (2 * a) + Vf;
		return d;
	}

}
