package azzazazzaz;

import java.util.ArrayList;

public class FSAUtil {

	/**
	 * Test if the input string is accepted by the FSA
	 * @param fsa
	 * @param input
	 * @return
	 */
	public static boolean testInput(FSA fsa, String input) {
		return fsa.testInput(input);
	}
	
	/**
	 * Reduces the input FSA
	 * @param fsa
	 */
	public static void reduce(FSA fsa) {
		ArrayList<ArrayList<Integer>> table = new ArrayList<ArrayList<Integer>>();

		// Populate first col of equivalence table
		ArrayList<Integer> t0col = new ArrayList<Integer>();
		for (int i = 0; i < fsa.size(); i++) {
			t0col.add(fsa.t0Status(i));
		}
		table.add(t0col);

		// Populate second col of equivalence table
		ArrayList<Integer> t1col = new ArrayList<Integer>();
		for (int i = 0; i < fsa.size(); i++) {
			t1col.add(fsa.t1Status(i));
		}
		table.add(t1col);

		// Populate third col of equivalence table
		ArrayList<Integer> eqiv1 = new ArrayList<Integer>();
		for (int i = 0; i < fsa.size(); i++) {
			if (fsa.acceptingStatus(i)) {
				eqiv1.add(1);
			} else {
				eqiv1.add(0);
			}
		}
		table.add(eqiv1);
		
		// Populate the rest of the equivalence table
		do {
			calcNextColumns(fsa, table);
		} while (!checkEquivalenceCols(table));
		
		//TEMP
		// Print equivalence table
		/*
		 * for(int i = 0; i < fsa.size(); i++) { for(int j = 0; j < table.size(); j++) {
		 * System.out.print(table.get(j).get(i) + " "); } System.out.println(""); }
		 */

		// Relabel equivalent states
		ArrayList<Integer> equivCol = table.get(table.size() - 1);
		ArrayList<Integer> statesToBeDeleted = new ArrayList<>();
		for (int i = 0; i < fsa.size(); i++) {
			int si = fsa.getState(i);
			if (statesToBeDeleted.contains(si)) {
				continue;
			}
			for (int j = 0; j < fsa.size(); j++) {
				if (i != j && equivCol.get(i) == equivCol.get(j)) {
					// Relabel state j as i
					int sj = fsa.getState(j);
					fsa.relabelTransitions(sj, si);
					statesToBeDeleted.add(sj);
				}
			}
		}

		// Delete equivalent states
		for (int i = 0; i < statesToBeDeleted.size(); i++) {
			fsa.deleteState(statesToBeDeleted.get(i));
		}
	}

	/**
	 * Adds the next 0 input, 1 input and equivalence column
	 * @param fsa
	 * @param table
	 */
	private static void calcNextColumns(FSA fsa, ArrayList<ArrayList<Integer>> table) {
		
		// Calculate transitions for 0 input
		ArrayList<Integer> t0col = new ArrayList<Integer>();
		for (int i = 0; i < fsa.size(); i++) {
			int t0 = fsa.t0Status(i);
			int index = fsa.getIndex(t0);
			t0col.add(table.get(table.size() - 1).get(index));
		}
		table.add(t0col);

		// Calculate transitions for 1 input
		ArrayList<Integer> t1col = new ArrayList<Integer>();
		for (int i = 0; i < fsa.size(); i++) {
			int t1 = fsa.t1Status(i);
			int index = fsa.getIndex(t1);
			t1col.add(table.get(table.size() - 2).get(index));
		}
		table.add(t1col);

		// Calculate equivalences
		ArrayList<Integer> equiv = new ArrayList<Integer>();
		ArrayList<int[]> combos = new ArrayList<int[]>();
		for (int i = 0; i < fsa.size(); i++) {
			int a = table.get(table.size() - 3).get(i);
			int b = table.get(table.size() - 2).get(i);
			int c = table.get(table.size() - 1).get(i);

			// Find this combination if it exists
			int combo = -1;
			for (int j = 0; j < combos.size(); j++) {
				if (combos.get(j)[0] == a && combos.get(j)[1] == b && combos.get(j)[2] == c) {
					combo = j;
					break;
				}
			}

			// If the combo wasnt found then add it
			if (combo == -1) {
				combos.add(new int[] { a, b, c });
				combo = combos.size() - 1;
			}
			equiv.add(combo);
		}
		table.add(equiv);
	}

	/**
	 * Checks to see if the second last equivalence column is the same as the last
	 * @param table
	 * @return Returns true if the columns are the same
	 */
	private static boolean checkEquivalenceCols(ArrayList<ArrayList<Integer>> table) {
		for (int i = 0; i < table.get(0).size(); i++) {
			if (table.get(table.size() - 4).get(i) != table.get(table.size() - 1).get(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Standardizes the input FSA
	 * @param fsa
	 */
	public static void standardize(FSA fsa) {
		
		// The values for the new FSA
		// new name, 0 transition, 1 transition, accepting, old name
		ArrayList<int[]> newFSA = new ArrayList<>();

		// Add initial row
		int[] firstRow = new int[5];
		firstRow[0] = 0;
		firstRow[4] = fsa.getState(0);
		newFSA.add(firstRow);
		
		// Populate the rest of the rows and values
		for (int i = 0; i < newFSA.size(); i++) {
			
			// Index of the current row state
			int stateIndex = fsa.getIndex(newFSA.get(i)[4]);

			// 0 transition to be added
			int t0Old = fsa.t0Status(stateIndex);
			int t0New = -1;

			// Look for the transition in the table
			for (int j = 0; j < newFSA.size(); j++) {
				if (t0Old == newFSA.get(j)[4]) {
					t0New = newFSA.get(j)[0];
					break;
				}
			}
			
			// if transition not found then add it
			if (t0New == -1) {
				int[] newRow = new int[5];
				t0New = newFSA.size();
				newRow[0] = t0New;
				newRow[4] = t0Old;
				newFSA.add(newRow);
			}
			
			// Add 0 transition
			newFSA.get(i)[1] = t0New;

			// 1 transition to be added
			int t1Old = fsa.t1Status(stateIndex);
			int t1New = -1;

			// Look for the transition in the table
			for (int j = 0; j < newFSA.size(); j++) {
				if (t1Old == newFSA.get(j)[4]) {
					t1New = newFSA.get(j)[0];
					break;
				}
			}
			
			// if transition not found then add it
			if (t1New == -1) {
				int[] newRow = new int[5];
				t1New = newFSA.size();
				newRow[0] = t1New;
				newRow[4] = t1Old;
				newFSA.add(newRow);
			}
			
			// Add 1 transition
			newFSA.get(i)[2] = t1New;

			// Set accepting status
			if (fsa.acceptingStatus(stateIndex)) {
				newFSA.get(i)[3] = 1;
			} else {
				newFSA.get(i)[3] = 0;
			}
		}

		// Rebuild FSA
		fsa.deleteAllStates();
		for (int i = 0; i < newFSA.size(); i++) {
			boolean isAccepting = false;
			if (newFSA.get(i)[3] == 1) {
				isAccepting = true;
			}
			fsa.addState(newFSA.get(i)[0], newFSA.get(i)[1], newFSA.get(i)[2], isAccepting);
		}
	}
}
