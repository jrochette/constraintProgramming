import static choco.Choco.*;
import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.cp.solver.search.integer.varselector.MinDomain;
import choco.kernel.model.Model;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.Solver;

public class TravailPratiqueNumeroDeux {

	private static Model model;

	// employé 1
	private static IntegerVariable debut1;
	private static IntegerVariable pause1;
	private static IntegerVariable fin1;

	// employé 2
	private static IntegerVariable debut2;
	private static IntegerVariable pause2;
	private static IntegerVariable fin2;

	// employé 3
	private static IntegerVariable debut3;
	private static IntegerVariable pause3;
	private static IntegerVariable fin3;

	// employé 4
	private static IntegerVariable debut4;
	private static IntegerVariable pause4;
	private static IntegerVariable fin4;

	// employé 5
	private static IntegerVariable debut5;
	private static IntegerVariable pause5;
	private static IntegerVariable fin5;

	public static void main(String[] args) {
		int n = 5;
		int sommeMagique = n * (n * n + 1) / 2;
		int p = 16;

		model = new CPModel();

		debut1 = makeIntVar("Début 1", 1, 7);
		pause1 = makeIntVar("Pause 1", 5, 12);
		fin1 = makeIntVar("Fin 1", 10, 16);

		addEmployeeVariableToModel(model, debut1, pause1, fin1);
		addEmployeeConstraintToModel(model, debut1, pause1, fin1);

		debut2 = makeIntVar("Début 1", 1, 7);
		pause2 = makeIntVar("Pause 1", 5, 12);
		fin2 = makeIntVar("Fin 1", 10, 16);

		addEmployeeVariableToModel(model, debut2, pause2, fin2);
		addEmployeeConstraintToModel(model, debut2, pause2, fin2);

		debut3 = makeIntVar("Début 1", 1, 7);
		pause3 = makeIntVar("Pause 1", 5, 12);
		fin3 = makeIntVar("Fin 1", 10, 16);

		addEmployeeVariableToModel(model, debut3, pause3, fin3);
		addEmployeeConstraintToModel(model, debut3, pause3, fin3);

		debut4 = makeIntVar("Début 1", 1, 7);
		pause4 = makeIntVar("Pause 1", 5, 12);
		fin4 = makeIntVar("Fin 1", 10, 16);

		addEmployeeVariableToModel(model, debut4, pause4, fin4);
		addEmployeeConstraintToModel(model, debut4, pause4, fin4);

		debut5 = makeIntVar("Début 1", 1, 7);
		pause5 = makeIntVar("Pause 1", 5, 12);
		fin5 = makeIntVar("Fin 1", 10, 16);

		addEmployeeVariableToModel(model, debut5, pause5, fin5);
		addEmployeeConstraintToModel(model, debut5, pause5, fin5);

		// Construction du tableau d'offre
		int[] offre = new int[p];
		for (int i = 0; i < p; i++) {
			if (checkIfEmployeeOneIsWorking(i + 1, debut1, fin1, pause1)) {
				offre[i]++;
			}
		}

		// Declare une matrice de variables.
		IntegerVariable[][] variablesParLigne = new IntegerVariable[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				variablesParLigne[i][j] = Choco.makeIntVar("x[" + i + "][" + j + "]", 1, n * n);
				model.addVariable(variablesParLigne[i][j]);
			}
		}

		// On place toutes les varibles dans un vecteur
		IntegerVariable[] vecteurVariables = new IntegerVariable[n * n];
		for (int i = 0; i < n * n; i++) {
			vecteurVariables[i] = variablesParLigne[i / n][i % n];
		}
		// On ajoute la contrainte allDifferent afin que les variables
		// prennent des valeurs distinctes
		model.addConstraint(Choco.allDifferent(vecteurVariables));

		// Pour toutes les lignes de la matrice
		for (int i = 0; i < n; i++) {
			model.addConstraint(eq(sum(variablesParLigne[i]), sommeMagique));
		}

		// Cree la transpose de la matrice afin d'ajouter des contraintes
		// sur les colonnes.
		IntegerVariable[][] variablesParColonne = new IntegerVariable[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				variablesParColonne[i][j] = variablesParLigne[j][i];
			}
			model.addConstraint(eq(sum(variablesParColonne[i]), sommeMagique));
		}

		// Cree deux tableaux contenant les variables des deux diagonales
		// de la matrice.
		IntegerVariable[] variablesDiagonale1 = new IntegerVariable[n];
		IntegerVariable[] variablesDiagonale2 = new IntegerVariable[n];
		for (int i = 0; i < n; i++) {
			variablesDiagonale1[i] = variablesParLigne[i][i];
			variablesDiagonale2[i] = variablesParLigne[n - i - 1][i];
		}
		model.addConstraint(eq(sum(variablesDiagonale1), sommeMagique));
		model.addConstraint(eq(sum(variablesDiagonale2), sommeMagique));

		Solver solveur = new CPSolver(); // Creation du solveur
		solveur.read(model); // Lecture du modele par le solveur
		// Ajout de l'heuristique de choix de variables MinDomain. Cette
		// heuristique choisit la variable avec le plus petit domaine.
		solveur.setVarIntSelector(new MinDomain(solveur, solveur.getVar(vecteurVariables)));
		// Si le solveur trouve une solution
		if (solveur.solve()) {
			// Affiche le carre magique
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(solveur.getVar(variablesParLigne[i][j]).getVal() + " ");
				}
				System.out.println("");
			}
		} else {
			System.out.println("Aucune solution trouvee.");
		}

		System.out.println("Probleme resolu en " + solveur.getTimeCount() + " millisecondes.");
		System.out.println("Probleme resolu avec " + solveur.getBackTrackCount() + " retours arrieres.");
	}

	private static boolean checkIfEmployeeOneIsWorking(int periode, IntegerVariable debut, IntegerVariable fin, IntegerVariable pause) {
		// if (periode >= debut) {
		// if (periode <= fin) {
		// if (periode != pause) {
		// return true;
		// }
		// }
		// }
		return false;
	}

	private static void addEmployeeConstraintToModel(Model model, IntegerVariable debut1, IntegerVariable pause1, IntegerVariable fin1) {
		model.addConstraint(leq(7, minus(minus(fin1, debut1), 1)));
		model.addConstraint(geq(5, minus(minus(fin1, debut1), 1)));
		model.addConstraint(geq(4, minus(fin1, pause1)));
		model.addConstraint(geq(4, minus(pause1, debut1)));
	}

	private static void addEmployeeVariableToModel(Model m, IntegerVariable debut1, IntegerVariable pause1, IntegerVariable fin1) {
		m.addVariable(debut1);
		m.addVariable(pause1);
		m.addVariable(fin1);
	}
}
