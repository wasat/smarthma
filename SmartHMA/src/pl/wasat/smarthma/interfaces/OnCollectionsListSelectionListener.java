package pl.wasat.smarthma.interfaces;

public interface OnCollectionsListSelectionListener {
	
	public void onCollectionSelected(Integer chosenCollectionId, String urlArgs);

	boolean isTwoPaneMode();

}
