package azzazazzaz;

import java.util.ArrayList;

/*
 *  This class if for when we are converting an NDFSA --> FSA. 
 */
public class NDFSAFinalStates 
{

	private int stateNumber;
	private int zeroTransition;
	private int oneTransition;
	private boolean isAccepting;
	private ArrayList<Integer> stateSets;
	
	/**
	 *  
	 * 
	 * @param _stateNumber
	 * @param _zeroTransition
	 * @param _oneTransition
	 * @param stateSets
	 */
	public NDFSAFinalStates(int _stateNumber, int _zeroTransition, int _oneTransition, int[] _stateSets)
	{
		this.stateNumber = _stateNumber;
		this.zeroTransition = _zeroTransition;
		this.oneTransition = _oneTransition;
		this.stateSets = new ArrayList<Integer>();
		
		for(int i1=0;i1<_stateSets.length;i1++)
		{
			this.stateSets.add(_stateSets[i1]);
		}
	}
	
	public NDFSAFinalStates(int _stateNumber, int[] _stateSets)
	{
		this.stateNumber = _stateNumber;
		this.stateSets = new ArrayList<Integer>();
		
		for(int i1=0;i1<_stateSets.length;i1++)
		{
			this.stateSets.add(_stateSets[i1]);
		}
	}
	/*
	 * BEGIN ACCESSORS AND MUTATORS
	 */
	public int getStateNumber()
	{
		return this.stateNumber;
	}
	public int getZeroTransition()
	{
		return this.zeroTransition;
	}
	public int getOneTransition()
	{
		return this.oneTransition;
	}
	public ArrayList<Integer> getStateSets()
	{
		return this.stateSets;
	}
	public boolean getIsAccepting()
	{
		return this.isAccepting;
	}
	
	
	public void setZeroTransition(int _zeroTransition)
	{
		this.zeroTransition = _zeroTransition;
	}
	public void setOneTransition(int _oneTransition)
	{
		this.oneTransition = _oneTransition;
	}
	public void setIsAccepting(boolean _isAccepting)
	{
		this.isAccepting = _isAccepting;
	}
	/*
	 * END ACCESSORS AND MUTATORS 
	 */
	
		
	
}
