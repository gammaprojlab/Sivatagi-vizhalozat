package sivatagiVizhalozat;

import java.io.Serializable;

public class SaboteurObserver implements IObserver, Serializable {
	/**
	 * The Observed class.
	 */
	private Saboteur saboteur;
	
	/**
	 * Doesn't do anything
	 */
	@Override
	public FieldElement Clicked(int x, int y) {
		return null;
	}

	/**
	 * Doesn't do anything
	 */
	@Override
	public void Move(int x, int y) {
	}

	/**
	 * Updates the UIController.
	 */
	@Override
	public void Update() {
		
	}

}
