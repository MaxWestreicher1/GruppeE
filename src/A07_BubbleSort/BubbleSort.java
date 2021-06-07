package A07_BubbleSort;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		Person tmp;
		for (int i = 0; i< personen.length; i++){
			for (int j = 0; j < personen.length-1;j++){
				if (personen[j].compareTo(personen[j+1]) > 0){
					tmp = personen[j];
					personen[j] = personen[j+1];
					personen[j+1] = tmp;
				}
			}
		}
	}
	
}
