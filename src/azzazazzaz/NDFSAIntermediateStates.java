package azzazazzaz;

import java.util.ArrayList;

public class NDFSAIntermediateStates {

	private int stateNumber;
	private ArrayList<Integer> zeroTransitions = new ArrayList<Integer>();
	private ArrayList<Integer> oneTransitions = new ArrayList<Integer>();
	private boolean isAccepting;
	private boolean isInitial;
	
 	/**
 	 * Add an input machine state
 	 * @param stateNumber The number of the state in the machine.
 	 * @param zeroTransitions an int[] containing all the zero transitions.
 	 * @param oneTransitions an int[] containing all the one transitions.
 	 * @param isAccepting 
 	 * 
 	 * @return void 
 	 */
	public NDFSAIntermediateStates(int _stateNumber, int[] _zeroTransitions, int[] _oneTransitions, boolean _isAccepting, boolean _isInitial)
	{
		this.stateNumber = _stateNumber;
		this.isAccepting = _isAccepting;
		this.isInitial = _isInitial;
		
		for(int i1=0;i1<_zeroTransitions.length;i1++)
		{
			this.zeroTransitions.add(_zeroTransitions[i1]);
		}
	
		for(int i1=0;i1<_oneTransitions.length;i1++)
		{
			this.oneTransitions.add(_oneTransitions[i1]);
		}
		
	}
	
	
	public int getStateNumber()
	{
		return this.stateNumber;
	}
	
	public ArrayList<Integer> getZeroTransitions()
	{
		return this.zeroTransitions;
	}
	
	public ArrayList<Integer> getOneTransitions()
	{
		return this.oneTransitions;
	}
	
	public boolean getIsAccepting()
	{
		return isAccepting;
	}
	
	public boolean getIsInitial()
	{
		return isInitial;
	}
	
}
