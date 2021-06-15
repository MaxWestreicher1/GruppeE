package A09_GraphZusammen;
public class ConnectedComponents {

	/**
	 * Retourniert die Anzahl der zusammenh�ngenden Komponenten eines Graphen
	 *
	 * @param g zu pr�fender Graph
	 * @return Anzahl der Komponenten
	 */


	private void depthFirstSearch(Graph g, int node, boolean[] vis) {


		if (vis[node])
			return;

		vis[node] = true;

		for (WeightedEdge w : g.getEdges(node)) {
			depthFirstSearch(g, w.to_vertex, vis);
		}
	}

	public int getNumberOfComponents(Graph g) {

		int anzahlK = 0;
		int SummeN = g.numVertices();
		boolean[] vis = new boolean[SummeN];

		if (SummeN == 0)
			return 0;

		if (SummeN == 1)
			return 1;


		for (int i = 0; i < vis.length;i++) {

			if (!vis[i]) {
				depthFirstSearch(g, i, vis);
				anzahlK++;
			}
		}

	return anzahlK;}
}
