package azzazazzaz;

import java.util.ArrayList;

public class FSA {
	private ArrayList<State> states;

	public FSA() {
		states = new ArrayList<State>();
	}
	
	/**
	 * Add a new state to the FSA
	 * @param name
	 * @param t0
	 * @param t1
	 * @param isAccepting
	 */
	public void addState(int name, int t0, int t1, boolean isAccepting) {
		State state = new State(name, t0, t1, isAccepting);
		states.add(state);
	}
	
	/**
	 * Returns the transition with input 0 for the specified state
	 * @param index The index of the input state
	 * @return
	 */
	public int t0Status(int index) {
		return states.get(index).t0;
	}
	
	/**
	 * Returns the transition with input 1 for the specified state
	 * @param index The index of the input state
	 * @return
	 */
	public int t1Status(int index) {
		return states.get(index).t1;
	}
	
	/**
	 * Whether the input state is accepting or not accepting
	 * @param index The index of the input state
	 * @return
	 */
	public boolean acceptingStatus(int index) {
		return states.get(index).isAccepting;
	}
	
	/**
	 * Returns the index of the input state
	 * @param state The name of the input state
	 * @return
	 */
	public int getIndex(int state) {
		for(int i = 0; i < states.size(); i++) {
			if(states.get(i).name == state) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Relabel all t0 and t1 transitions from fromT to toT
	 * @param from
	 * @param to
	 */
	public void relabelTransitions(int fromT, int toT) {
		for(int i = 0; i < states.size(); i++){
			if(states.get(i).t0 == fromT) {
				states.get(i).t0 = toT;
			}
			if(states.get(i).t1 == fromT) {
				states.get(i).t1 = toT;
			}
		}
	}
	
	/**
	 * Deletes the input state from the FSA
	 * @param state The name of the input state
	 */
	public void deleteState(int state) {
		for(int i = 0; i < states.size(); i++) {
			if(states.get(i).name == state) {
				states.remove(i);
				return;
			}
		}
	}
	
	/**
	 * Delete all states
	 */
	public void deleteAllStates() {
		states = new ArrayList<>();
	}
	
	/**
	 * Return the name of the input state
	 * @param index The index of the input state
	 * @return
	 */
	public int getState(int index) {
		return states.get(index).name;
	}
	
	/**
	 * The size of the FSA
	 * @return
	 */
	public int size() {
		return states.size();
	}
	
	/**
	 * Output information about this FSA
	 */
	public void print() {
		for(int i = 0; i < states.size(); i++) {
			System.out.print(states.get(i).name + " ");
			System.out.print(states.get(i).t0 + " ");
			System.out.print(states.get(i).t1 + " ");
			System.out.print(states.get(i).isAccepting + "\r");
		}
	}
	
	/**
	 * Tests the input string against the FSA
	 * @param string
	 * @return
	 */
	public boolean testInput(String string) {
		int currentState = getState(0);
		for(int i = 0; i < string.length(); i++) {
			int input = Character.getNumericValue(string.charAt(i));
			if(input == 0) {
				currentState = t0Status(getIndex(currentState));
			} else {
				currentState = t1Status(getIndex(currentState));
			}
		}
		return acceptingStatus(getIndex(currentState));
	}

}
