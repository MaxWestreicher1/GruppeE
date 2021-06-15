package A10_ZyklenTiefensuche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Zyklen {

	private Graph g;
	List<Integer> visited_verteces;
	List<WeightedEdge> visited_edges;
	int vertex_prev;
	boolean go_on;

	List<Integer> result;

	public Zyklen(Graph g) {
		this.g = g;
	}

	/**
	 * Retourniert einen Zyklus eines Graphen, sofern einer existiert
	 * @param g zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	public List<Integer> getCycle() {
		this.result = new ArrayList<Integer>();
		this.visited_verteces = new ArrayList<Integer>();
		this.visited_edges = new ArrayList<WeightedEdge>();




		if (g.numVertices() <= 2) {
			return null;
		}

		this.recursion(0);

		return result;
	}

	public void recursion( int vertex ){
		if (visited_verteces.contains(vertex)){
			int vert_index = visited_verteces.indexOf(vertex);

			result = visited_verteces.subList(vert_index, visited_verteces.size());
			result.add(vertex);
			go_on = false;
		}
		else if (go_on = true){
			visited_verteces.add(vertex);
			List<WeightedEdge> edges = g.getEdges(vertex);
			for ( WeightedEdge edge : edges ) {
				if (!visited_edges.contains(edge) && edge.to_vertex != vertex_prev && go_on == true){
					visited_edges.add(edge);
					vertex_prev = vertex;
					recursion(edge.to_vertex);
				}

			}
		}
	}
	
	

}
