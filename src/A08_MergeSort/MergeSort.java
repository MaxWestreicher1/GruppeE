package A08_MergeSort;

import java.util.Arrays;


public class MergeSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		// Start des rekursiven Aufrufs
		sort(personen, 0, personen.length - 1);
	}

	/**
	 * Rekursive Funktion zum Sortieren eines Teils des Arrays
	 *
	 * @param personen Zu sortierendes Array
	 * @param start    Startpunkt im Array
	 * @param end      Endpunkt im Array
	 */
	public void sort(Person[] personen, int start, int end) {
		// TODO: Aufteilung & Rekursion implementieren

		int mitte = 0;

		if (start < end) { //falls der start == end ist, w�re das Array eine L�nge von 1 haben und w�re dann nicht mehr teilbar

			mitte = (start + end) / 2; // Die Arrayl�nge wird immer halbiert. Die L�nge entspricht der Summe start und end

			sort(personen, start, mitte); //den ersten Teil von start bis zur Mitte sortieren
			sort(personen, mitte + 1, end); //den zweiten Teil von mitte + 1 und end sortieren
			//warum mitte + 1? da die Value von der mitte bereits im ersten Teil sortiert wird



			// F�r Merge: H�lften in eigene Arrays kopieren
			// Hinweis: bei copyOfRange ist Obergrenze exklusiv, deshalb "+ 1"
			Person[] teil1 = Arrays.copyOfRange(personen, start, mitte + 1);
			Person[] teil2 = Arrays.copyOfRange(personen, mitte + 1, end + 1);
			// Beide H�lften zusammenf�gen und in data-Array schreiben
			merge(teil1, teil2, personen, start);

		}


	}

	/**
	 * Merge zweier Arrays in ein Ergebnis-Array
	 *
	 * @param pers1  Erstes Array
	 * @param pers2  Zweites Array
	 * @param result Ergebnisarray
	 * @param start  Position f�r Speicherung in Ergebnisarray
	 */
	public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {
		// TODO: Merge implementieren

		int counter1 = 0; //pro Person[] wird ein Counter erstellt, um zu sehen an welcher Stelle man sich bewegt
		int counter2 = 0;


		while (counter1 < pers1.length && counter2 < pers2.length) {
			//solange die einzelnen Counter kleiner als die jeweilige Arrayl�nge sind ...
			if (pers1[counter1].compareTo(pers2[counter2]) < 0) { // wird abgepr�ft, welches Array von beiden die fr�her vorkommenden Nachnamen enth�lt
				result[start] = pers1[counter1];
				counter1++;
			} else {
				result[start] = pers2[counter2];
				counter2++;
			}

		start++; // der start wird erh�ht, um sich weiter zuarbeiten

		}
		while (counter1 < pers1.length) { //gegebenenfalls werden alle �brigen Elemente von pers1 eingef�gt (wenn der counter noch nicht der L�nge entspricht, da der Counter bei null beginnt und die L�nge um 1 versetzt)
			result[start] = pers1[counter1];
			start++;
			counter1++;

		}
		while (counter2 < pers2.length) { //gegebenenfalls werden alle �brigen Elemente von pers2 eingef�gt
			result[start] = pers2[counter2];
			start++;
			counter2++;

		}

	}
}


