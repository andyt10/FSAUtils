package azzazazzaz;

import java.util.ArrayList;
import java.util.Collections;

public class NDFSAUtil 
{
	private ArrayList<NDFSAFinalStates> finalStates = new ArrayList<NDFSAFinalStates>();
	
	/**
	 * Converts a NDFSA with multiple transitions to an FSA. Starts with the initial state, then uses the 'discovery' process to eliminate inaccessible states.
	 * 
	 *  @param myNDFSA - Accepts an NDFSA with null transitions removed.
	 *  @return FSA - Returns a Finite State Acceptor of the type FSA.
	 */
	public FSA CombineTransitions(NDFSA myNDFSA)
	{
		//NDFSA finalNDFSA = new NDFSA(2);
		
		
		/*
		 * Find all initial states in myNDFSA
		 */
		ArrayList<NDFSAIntermediateStates> initialStates = new ArrayList<NDFSAIntermediateStates>();
		ArrayList<NDFSAIntermediateStates> existingStates = myNDFSA.getIntermediateStates();
		for(int i1=0;i1<myNDFSA.getNumberOfStates(1);i1++)
		{
			if(existingStates.get(i1).getIsInitial() == true)
			{
				initialStates.add(existingStates.get(i1));
				System.out.println("State: " + existingStates.get(i1).getStateNumber() + " is initial.");
			}
		}
		
		/*
		 * Combine in to first state of new machine 'finalNDFSA', determine transitions, and state acceptance . Finally add to new machine. 
		 */
		boolean isInitialAccepting = false;
		ArrayList<Integer> zeroStateSets = new ArrayList<Integer>();
		ArrayList<Integer> oneStateSets = new ArrayList<Integer>();
		
		for(int i1=0;i1<initialStates.size();i1++)
		{
			if(initialStates.get(i1).getIsAccepting() == true)
			{
				isInitialAccepting = true;
				
			}
			
			for(int i2=0;i2<initialStates.get(i1).getZeroTransitions().size();i2++)
			{
				/*
				 * Taking the union of the states 0 and 1 transitions.
				 */
				if(!zeroStateSets.contains(initialStates.get(i1).getZeroTransitions().get(i2)))//If we don't already have a record of this state. 
				{
					zeroStateSets.add(initialStates.get(i1).getZeroTransitions().get(i2));
					
				}
			}
			System.out.println(zeroStateSets.toString());
			
			for(int i2=0;i2<initialStates.get(i1).getOneTransitions().size();i2++)
			{
				/*
				 * Taking the union of the states 0 and 1 transitions.
				 */
				if(!oneStateSets.contains(initialStates.get(i1).getOneTransitions().get(i2)))
				{
					oneStateSets.add(initialStates.get(i1).getOneTransitions().get(i2));
				}
			}
			System.out.print("ONE: ");
			System.out.println(oneStateSets.toString());
		
		}
		
		System.out.println("------------");

        
		int[] thisStateSets = new int[initialStates.size()];
		
		for(int i1=0;i1<thisStateSets.length;i1++)
		{
			thisStateSets[i1] = initialStates.get(i1).getStateNumber();
		}		
		finalStates.add(new NDFSAFinalStates(0,thisStateSets));
		finalStates.get(0).setIsAccepting(isInitialAccepting);
		//Do we have a state yet?
		int zeroTransitionState = stateExists(zeroStateSets);
		int oneTransitionState = stateExists(oneStateSets);
		
	
		if(zeroTransitionState == -1 && oneTransitionState == -1)
		{
			if(zeroStateSets.equals(oneStateSets))
			{
			finalStates.get(0).setZeroTransition(lastStateInMachine() + 1);
			finalStates.get(0).setOneTransition(lastStateInMachine() + 1);
			System.out.println("same state transition");

			}
			else
			{
				finalStates.get(0).setZeroTransition(lastStateInMachine() + 1);
				finalStates.get(0).setOneTransition(lastStateInMachine() + 2);
				System.out.println("diff state transition");
				
			}
			
		}
		else if(zeroTransitionState == -1 && oneTransitionState != -1)
		{
			finalStates.get(0).setZeroTransition(lastStateInMachine() + 1);	
		}
		else if(zeroTransitionState != -1 && oneTransitionState == -1)
		{
			finalStates.get(0).setOneTransition(lastStateInMachine() + 1);		
		}
//Sort out the states that the initial state transitions to.
		int[] zeroStateTransitionSets = new int[zeroStateSets.size()];
		for(int i1=0;i1<zeroStateTransitionSets.length;i1++)
		{
			zeroStateTransitionSets[i1] = zeroStateSets.get(i1);
		}	
		finalStates.add(new NDFSAFinalStates(finalStates.get(0).getZeroTransition(),zeroStateTransitionSets));
		
		
		if(!zeroStateSets.equals(oneStateSets))
		{
			int[] oneStateTransitionSets = new int[oneStateSets.size()];
			
			for(int i1=0;i1<oneStateTransitionSets.length;i1++)
			{
				oneStateTransitionSets[i1] = oneStateSets.get(i1);
			}			
			
			finalStates.add(new NDFSAFinalStates(finalStates.get(0).getOneTransition(),oneStateTransitionSets));			
		}
		


		System.out.println("ADDED A NEW STATE AND SET TRANSITIONS");
//End
		for(int i1=0;i1<finalStates.size();i1++)
		{
			System.out.print("STATE: " + finalStates.get(i1).getStateNumber());
			System.out.print(" has ZERO TRANSITION: " + finalStates.get(i1).getZeroTransition());
			System.out.print(" has ONE TRANSITION: " + finalStates.get(i1).getOneTransition());		
			System.out.println("");
			System.out.println("AND IS COMPRISED OF STATES: " + finalStates.get(i1).getStateSets().toString() );
			if(finalStates.get(i1).getIsAccepting()){
				System.out.println("AND IS ACCEPTING");
			}
			else
			{
				System.out.println("AND IS NOT ACCEPTING");			
			}
			System.out.println("-----------------");
		}
		
		
			
			
		/*
		/*
		 * Continue with normal combination process 'discovered state combinations' in machine NDFSA.
		 */
		
		return null;
	}
	
	/**
	 *  Determine if the state with corresponding 'sets' exists in new 'final' machine. Returns the number of the state if it exists, or -1 if not found
	 * @param stateSets The zero OR one transitions to be examined.
	 * @return int The state number, or -1 if the state does not exist.
	 */
	@SuppressWarnings("unused")
	private int stateExists(ArrayList<Integer> stateSets)
	{
		for(int i1=0;i1<this.finalStates.size();i1++)
		{
			if(finalStates.get(i1).getStateSets().equals(stateSets))
			{
			return finalStates.get(i1).getStateNumber();
			}
		}
		return -1;
	}
	
	/**
	 *  Determines the last state in the new final machine. Used to decide the stateNumber to .add for finalStates. It is not safe to assume the machine will have last state number
	 *  of finalStates.length because state numbers may not increment by 1 from zero.
	 * @return int The last state in the machine.
	 */
	private int lastStateInMachine()
	{
		int lastState = 0;
		for(int i1=0;i1<this.finalStates.size();i1++)
		{
			if(finalStates.get(i1).getStateNumber() > lastState)
			{
				lastState = finalStates.get(i1).getStateNumber();
			}
		
		}
		return lastState;
	}
		
		
	

}
	
