package sivatagiVizhalozat;

import java.io.Serializable;

public class PlumberObserver implements IObserver, Serializable {
	
	/**
	 * The Observed class.
	 */
	private Plumber plumber;
	
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
