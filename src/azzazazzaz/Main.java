package azzazazzaz;

public class Main {
	/**
	 * Show the usage of FSAUtil
	 * @param args
	 */
	public static void main(String[] args) {
		
		NDFSA testy = new NDFSA(1);

		int[] test0 = new int[]{0,2};
		int[] test1 = new int[3];
		int[] test0b = new int[]{4,5};
		int[] test0c = new int[]{9,12};
		int[] test1d = new int[]{4,5};

			
		testy.InputTest(0,test1d,test0c,false,true);
		testy.InputTest(1,test0,test1,true,false);
		testy.InputTest(2,test1d,test0c,true,true);
		
		
		//testy.PrintIntermediateMachine();
		NDFSAUtil utility = new NDFSAUtil();
		
		utility.CombineTransitions(testy);
		
		
	}
}
