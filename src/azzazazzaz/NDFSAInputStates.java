package azzazazzaz;

import java.util.ArrayList;

public class NDFSAInputStates {

	private int stateNumber;
	private ArrayList<Integer> zeroTransitions = new ArrayList<Integer>();
	private ArrayList<Integer> oneTransitions = new ArrayList<Integer>();
	private ArrayList<Integer> lambdaTransitions = new ArrayList<Integer>();
	
	
 	/**
 	 * Add an input machine state
 	 * @param stateNumber The number of the state in the machine.
 	 * @param zeroTransitions an int[] containing all the zero transitions.
 	 * @param oneTransitions an int[] containing all the one transitions.
 	 * @param lambdaTransitions an int[] containing all the lambda(null) transitions.
 	 * 
 	 * @return void 
 	 */
	public NDFSAInputStates(int _stateNumber, int[] _zeroTransitions, int[] _oneTransitions, int[] _lambdaTransitions)
	{
		stateNumber = _stateNumber;
		
		for(int i1=0;i1<_zeroTransitions.length;i1++)
		{
			this.zeroTransitions.add(_zeroTransitions[i1]);
		}
	
		for(int i1=0;i1<_oneTransitions.length;i1++)
		{
			this.oneTransitions.add(_oneTransitions[i1]);
		}
	
		for(int i1=0;i1<_lambdaTransitions.length;i1++)
		{
			this.lambdaTransitions.add(_lambdaTransitions[i1]);
		}
		
		
	}
	
	
	
}
