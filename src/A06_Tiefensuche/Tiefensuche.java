package A06_Tiefensuche;

import java.util.ArrayList;
import java.util.List;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: L�nge des Films
	 */
	protected int compare(Film a, Film b)
	{

		if(a.getL�nge() < b.getL�nge())
			return -1;

		else if(a.getL�nge() == b.getL�nge())
			return 0;

		else
			return 1;
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node)
	{
		List<String> result = new ArrayList<String>();

		if(node.getLeft() != null)
		{
			result.addAll(getNodesInOrder(node.getLeft()));
		}

		result.add(node.getValue().getTitel());

		if(node.getRight() != null)
		{
			result.addAll(getNodesInOrder(node.getRight()));
		}

		return result;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren L�nge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale L�nge des Spielfilms
	 * @param max Maximale L�nge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max)
	{

		List<String> result = new ArrayList<String>();
		result = getMinMaxPreOrder_calc(this.getRoot(), min, max);

		return result;

	}
	public List<String> getMinMaxPreOrder_calc(Node<Film> node, double min, double max)
	{
		List<String> result = new ArrayList<String>();


		if ((node.getValue().getL�nge() > min) && (node.getValue().getL�nge() < max)){
			result.add(node.getValue().getTitel());
		}

		if(node.getLeft() != null)
		{
			result.addAll(getMinMaxPreOrder_calc(node.getLeft(), min, max));
		}


		if(node.getRight() != null)
		{
			result.addAll(getMinMaxPreOrder_calc(node.getRight(), min, max));
		}


		return result;
	}


}
