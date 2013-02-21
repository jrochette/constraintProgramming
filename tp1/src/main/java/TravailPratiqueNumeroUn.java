import static choco.Choco.*;

import java.util.ArrayList;

import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.Solver;

public class TravailPratiqueNumeroUn {

	public static void main(String[] args) {
		// Construit le modele
		Model m = new CPModel();

		// Rangée 1
		IntegerVariable f1 = makeIntVar("face 1", 1, 4);
		IntegerVariable f2 = makeIntVar("face 2", 1, 4);
		IntegerVariable f3 = makeIntVar("face 3", 1, 4);
		IntegerVariable f4 = makeIntVar("face 4", 1, 4);

		// Rangée 2
		IntegerVariable f5 = makeIntVar("face 5", 1, 4);
		IntegerVariable f6 = makeIntVar("face 6", 1, 4);
		IntegerVariable f7 = makeIntVar("face 7", 1, 4);
		IntegerVariable f8 = makeIntVar("face 8", 1, 4);

		// Rangée 3
		IntegerVariable f9 = makeIntVar("face 9", 1, 4);
		IntegerVariable f10 = makeIntVar("face 10", 1, 4);
		IntegerVariable f11 = makeIntVar("face 11", 1, 4);
		IntegerVariable f12 = makeIntVar("face 12", 1, 4);

		// Rangé 4
		IntegerVariable f13 = makeIntVar("face 13", 1, 4);
		IntegerVariable f14 = makeIntVar("face 14", 1, 4);
		IntegerVariable f15 = makeIntVar("face 15", 1, 4);
		IntegerVariable f16 = makeIntVar("face 16", 1, 4);

		// tableau représentant le cube 1
		ArrayList<int[]> cube1 = prepareCube1();

		// tableau représentant le cube 2
		ArrayList<int[]> cube2 = prepareCube2();

		// tableau représentant le cube 3
		ArrayList<int[]> cube3 = prepareCube3();

		// tableau représentant le cube 4
		ArrayList<int[]> cube4 = prepareCube4();

		// Ajout des variables dans le model
		m.addVariable(f1);
		m.addVariable(f2);
		m.addVariable(f3);
		m.addVariable(f4);
		m.addVariable(f5);
		m.addVariable(f6);
		m.addVariable(f7);
		m.addVariable(f8);
		m.addVariable(f9);
		m.addVariable(f10);
		m.addVariable(f11);
		m.addVariable(f12);
		m.addVariable(f13);
		m.addVariable(f14);
		m.addVariable(f15);
		m.addVariable(f16);

		// Ajout des contraintes tableaux représentant les cubes
		m.addConstraint(feasTupleAC(cube1, f1, f5, f9, f13));
		m.addConstraint(feasTupleAC(cube2, f2, f6, f10, f14));
		m.addConstraint(feasTupleAC(cube3, f3, f7, f11, f15));
		m.addConstraint(feasTupleAC(cube4, f4, f8, f12, f16));

		// Ajout des contraintes allDifferent pour les lignes
		m.addConstraint(allDifferent(f1, f2, f3, f4));
		m.addConstraint(allDifferent(f5, f6, f7, f8));
		m.addConstraint(allDifferent(f9, f10, f11, f12));
		m.addConstraint(allDifferent(f13, f14, f15, f16));

		Solver solveur = new CPSolver(); // Creation du solveur
		solveur.read(m); // Lecture du modele par le solveur
		// Si le solveur trouve une solution
		if (solveur.solve()) {
			System.out.println(f1 + " " + f2 + " " + f3 + " " + f4);
			System.out.println(f5 + " " + f6 + " " + f7 + " " + f8);
			System.out.println(f9 + " " + f10 + " " + f11 + " " + f12);
			System.out.println(f13 + " " + f14 + " " + f15 + " " + f16);
			System.out.println(solveur.getVar(f1).getVal() + " " + solveur.getVar(f2).getVal() + " " + solveur.getVar(f3).getVal() + " "
					+ solveur.getVar(f4).getVal());
			System.out.println(solveur.getVar(f5).getVal() + " " + solveur.getVar(f6).getVal() + " " + solveur.getVar(f7).getVal() + " "
					+ solveur.getVar(f8).getVal());
			System.out.println(solveur.getVar(f9).getVal() + " " + solveur.getVar(f10).getVal() + " " + solveur.getVar(f11).getVal() + " "
					+ solveur.getVar(f12).getVal());
			System.out.println(solveur.getVar(f13).getVal() + " " + solveur.getVar(f14).getVal() + " " + solveur.getVar(f15).getVal() + " "
					+ solveur.getVar(f16).getVal());
		} else {
			System.out.println("Aucune solution trouvee.");
		}

		System.out.println("Probleme resolu en " + solveur.getTimeCount() + " millisecondes.");
		System.out.println("Probleme resolu avec " + solveur.getBackTrackCount() + " retours arrieres.");
	}

	private static ArrayList<int[]> prepareCube4() {
		ArrayList<int[]> cube4 = new ArrayList<int[]>();
		cube4.add(new int[] { 1, 3, 4, 4 });
		cube4.add(new int[] { 1, 4, 4, 3 });
		cube4.add(new int[] { 1, 3, 2, 4 });
		cube4.add(new int[] { 1, 4, 2, 3 });
		cube4.add(new int[] { 2, 4, 1, 3 });
		cube4.add(new int[] { 2, 3, 1, 4 });
		cube4.add(new int[] { 3, 4, 4, 1 });
		cube4.add(new int[] { 3, 1, 4, 4 });
		cube4.add(new int[] { 3, 2, 4, 1 });
		cube4.add(new int[] { 3, 1, 4, 2 });
		cube4.add(new int[] { 4, 4, 1, 3 });
		cube4.add(new int[] { 4, 3, 1, 4 });
		cube4.add(new int[] { 4, 1, 3, 4 });
		cube4.add(new int[] { 4, 4, 3, 1 });
		cube4.add(new int[] { 4, 1, 3, 2 });
		cube4.add(new int[] { 4, 2, 3, 1 });
		return cube4;
	}

	private static ArrayList<int[]> prepareCube3() {
		ArrayList<int[]> cube3 = new ArrayList<int[]>();
		cube3.add(new int[] { 1, 4, 3, 2 });
		cube3.add(new int[] { 1, 2, 3, 4 });
		cube3.add(new int[] { 2, 1, 4, 3 });
		cube3.add(new int[] { 2, 3, 4, 1 });
		cube3.add(new int[] { 2, 4, 4, 4 });
		cube3.add(new int[] { 3, 2, 1, 4 });
		cube3.add(new int[] { 3, 4, 1, 2 });
		cube3.add(new int[] { 4, 1, 2, 3 });
		cube3.add(new int[] { 4, 3, 2, 1 });
		cube3.add(new int[] { 4, 4, 4, 2 });
		cube3.add(new int[] { 4, 2, 4, 4 });
		cube3.add(new int[] { 4, 4, 2, 4 });
		return cube3;
	}

	private static ArrayList<int[]> prepareCube2() {
		ArrayList<int[]> cube2 = new ArrayList<int[]>();
		cube2.add(new int[] { 1, 4, 3, 3 });
		cube2.add(new int[] { 1, 3, 3, 4 });
		cube2.add(new int[] { 2, 4, 2, 3 });
		cube2.add(new int[] { 2, 3, 2, 4 });
		cube2.add(new int[] { 3, 2, 4, 2 });
		cube2.add(new int[] { 3, 3, 1, 4 });
		cube2.add(new int[] { 3, 4, 1, 3 });
		cube2.add(new int[] { 3, 1, 4, 3 });
		cube2.add(new int[] { 3, 3, 4, 1 });
		cube2.add(new int[] { 4, 2, 3, 2 });
		cube2.add(new int[] { 4, 3, 3, 1 });
		cube2.add(new int[] { 4, 1, 3, 3 });
		return cube2;
	}

	private static ArrayList<int[]> prepareCube1() {
		ArrayList<int[]> cube1 = new ArrayList<int[]>();
		cube1.add(new int[] { 1, 2, 2, 4 });
		cube1.add(new int[] { 1, 4, 2, 2 });
		cube1.add(new int[] { 1, 2, 3, 1 });
		cube1.add(new int[] { 1, 1, 3, 2 });
		cube1.add(new int[] { 1, 1, 2, 3 });
		cube1.add(new int[] { 1, 3, 2, 1 });
		cube1.add(new int[] { 2, 2, 4, 1 });
		cube1.add(new int[] { 2, 1, 4, 2 });
		cube1.add(new int[] { 2, 4, 1, 2 });
		cube1.add(new int[] { 2, 2, 1, 4 });
		cube1.add(new int[] { 2, 3, 1, 1 });
		cube1.add(new int[] { 2, 1, 1, 3 });
		cube1.add(new int[] { 3, 1, 1, 2 });
		cube1.add(new int[] { 3, 2, 1, 1 });
		cube1.add(new int[] { 4, 1, 2, 2 });
		cube1.add(new int[] { 4, 2, 2, 1 });
		return cube1;
	}
}
