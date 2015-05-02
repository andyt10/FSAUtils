package azzazazzaz;

import java.util.ArrayList;
/*
 * Interim state class, for when nulls have been removed. Pre-multiple transition removal.
 */
public class NDFSA {

	private ArrayList<NDFSAInputStates> inputStates;
	private ArrayList<NDFSAIntermediateStates> intermediateStates;	
	private ArrayList<NDFSAFinalStates> finalStates;
	private int machineType;
	
	/**
	 *  Constructor
	 * @param switchy for dev purposes, what kind of machine are we making.
	 */
	public NDFSA(int switchy)
	{
		machineType = switchy;
		
		if(switchy == 0)
		{
			
		}
		else if(switchy == 1)
		{
			intermediateStates = new ArrayList<NDFSAIntermediateStates>();					
		}
		else if (switchy == 2)
		{
			finalStates = new ArrayList<NDFSAFinalStates>();
		}
	}
	
	/*
	 * Begin Accessors and Mutators 
	 */
	public int getNumberOfStates(int switchy)
	{
		switch(switchy){
		case 0:
			return inputStates.size();
		case 1:
			return intermediateStates.size();
		case 2:
			return finalStates.size();
			
		
		}
		
		return 2356;
	}

	
	public ArrayList<NDFSAFinalStates> getFinalStates()
	{

			return finalStates;
	}	
	
	public ArrayList<NDFSAIntermediateStates> getIntermediateStates()
	{

			return intermediateStates;
	}	
	
	public void insertFinalState(NDFSAFinalStates finalState)
	{
		this.finalStates.add(finalState);
	}
	
	/*
	 * END Accessors and Mutators 
	 */
	
	/*
	 *  Test code below, remove when complete
	 */

 	/**
 	 * Add an input machine state
 	 * @param stateNumber The number of the state in the machine.
 	 * @param zeroTransitions an int[] containing all the zero transitions.
 	 * @param oneTransitions an int[] containing all the one transitions.
 	 * @param isAccepting Is the state accepting?
 	 * @param isInitial  Is the state an initial state?
 	 * 
 	 * @return void 
 	 */
	public void InputTest(int _stateNumber, int[] _zeroTransitions, int[] _oneTransitions,boolean isAccepting, boolean isInitial)
	{
		intermediateStates.add(new NDFSAIntermediateStates(_stateNumber,_zeroTransitions,_oneTransitions, isAccepting, isInitial));
	
	}
	
	public void PrintIntermediateMachine()
	{
		for(int i1=0;i1< intermediateStates.size();i1++)
		{
			int temp = intermediateStates.get(i1).getStateNumber();
			System.out.println("State Number: " + temp);
			
			ArrayList<Integer> zeroTransitions = intermediateStates.get(i1).getZeroTransitions();
			System.out.print("ZERO TRANSITIONS:");		
			for(int i2=0;i2<zeroTransitions.size();i2++)
			{
				System.out.print(zeroTransitions.get(i2));
				System.out.print(", ");
			}
			
			System.out.println();
			
			ArrayList<Integer> oneTransitions = intermediateStates.get(i1).getOneTransitions();
			System.out.print("ONE TRANSITIONS:");			
			for(int i2=0;i2<oneTransitions.size();i2++)
			{

				System.out.print(oneTransitions.get(i2));
				System.out.print(", ");
			}
			
			System.out.println();
		}
	}
	
	
	
	
}
