package azzazazzaz;

public class Main {
	/**
	 * Show the usage of FSAUtil
	 * @param args
	 */
	public static void main(String[] args) {
		
		// FSA machine
		FSA fsa = new FSA();
		fsa.addState(0, 1, 2, false);
		fsa.addState(1, 3, 4, false);
		fsa.addState(2, 3, 5, false);
		fsa.addState(3, 3, 3, false);
		fsa.addState(4, 6, 7, false);
		fsa.addState(5, 3, 3, true);
		fsa.addState(6, 0, 3, false);
		fsa.addState(7, 8, 3, false);
		fsa.addState(8, 9, 3, false);
		fsa.addState(9, 3, 10, false);
		fsa.addState(10, 7, 11, true);
		fsa.addState(11, 12, 3, false);
		fsa.addState(12, 13, 3, false);
		fsa.addState(13, 5, 14, false);
		fsa.addState(14, 11, 6, false);
		
		// Reduce the machine
		FSAUtil.reduce(fsa);
		
		// Standardize the machine
		FSAUtil.standardize(fsa);
		
		// Output the machine to console
		fsa.print();
	}
}
