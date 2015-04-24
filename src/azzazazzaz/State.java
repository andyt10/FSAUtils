package azzazazzaz;

public class State {
	
	/**
	 * Name of the state.
	 */
	public int name;
	/**
	 * Name of state to transition to with input 0.
	 */
	public int t0;
	/**
	 * Name of state to transition to with input 1.
	 */
	public int t1;
	/**
	 * Whether the state is accepting.
	 */
	public boolean isAccepting;
	
	public State(int p_name, int p_t0, int p_t1, boolean p_isAccepting) {
		name = p_name;
		t0 = p_t0;
		t1 = p_t1;
		isAccepting = p_isAccepting; 
	}
}
