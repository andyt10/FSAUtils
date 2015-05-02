package azzazazzaz;

public class Main {
	/**
	 * Show the usage of FSAUtil
	 * @param args
	 */
	public static void main(String[] args) {
		
		NDFSA testy = new NDFSA(1);

		int[] test0 = new int[]{2,3};
		int[] test1 = new int[]{3};
		int[] test2 = new int[]{0,3};
		int[] test3 = new int[]{1};
		int[] test4 = new int[]{0,2};
		int[] test5 = new int[]{2};
		int[] test6 = new int[]{0};
		int[] test7 = new int[]{4};
		int[] test8 = new int[]{4};
		int[] test9 = new int[]{4};

			
		testy.InputTest(0,test0,test1,true,true);
		testy.InputTest(1,test2,test3,false,false);
		testy.InputTest(2,test4,test5,false,false);
		testy.InputTest(3,test6,test7,false,false);
		testy.InputTest(4,test8,test9,false,false);
		
		//testy.PrintIntermediateMachine();
		NDFSAUtil utility = new NDFSAUtil();
		
		utility.CombineTransitions(testy);
		
		
	}
}
