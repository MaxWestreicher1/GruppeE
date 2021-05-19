package A05_Breitensuche;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class Breitensuche extends BaseTree<Integer> {



	@Override
	protected int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
	 * @param start Startknoten für Teilbaum
	 * @return Liste der Knoten in Breitenfolge
	 */
	public List<Integer> getBreadthFirstOrder(Node<Integer> start)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		boolean end = false;
		ArrayList<Node<Integer>> nextEbene = new ArrayList<Node<Integer>>();
		nextEbene.add(start);


		while (end == false) {
			ArrayList<Node<Integer>> thisEbene = nextEbene;
			nextEbene = new ArrayList<Node<Integer>>();

			end = true;
			for (Node<Integer> i : thisEbene) {
				result.add(i.getValue());

				if (i.getLeft() != null) {
					nextEbene.add(i.getLeft());
					end = false;
				}
				if (i.getRight() != null) {
					nextEbene.add(i.getRight());
					end = false;
				}
			}
		}

		return result;
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
	 * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
	 * @param start Startknoten für Teilbaum
	 * @param level Nur Knoten dieser Ebene ausgeben
	 * @return Liste aller Knoten
	 */
	public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {

		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Node<Integer>> nextEbene = new ArrayList<Node<Integer>>();
		ArrayList<Node<Integer>> thisEbene = new ArrayList<Node<Integer>>();

		nextEbene.add(start);
		int current_level = 1;


		while (current_level <= level) {
			thisEbene= nextEbene;
			nextEbene = new ArrayList<Node<Integer>>();

			for (Node<Integer> i : thisEbene) {

				if (i.getLeft() != null) {
					nextEbene.add(i.getLeft());
				}
				if (i.getRight() != null) {
					nextEbene.add(i.getRight());
				}
			}
			current_level++;
		}
		for (Node<Integer> i : thisEbene){
			result.add(i.getValue());
		}
		return result;
	}

}
