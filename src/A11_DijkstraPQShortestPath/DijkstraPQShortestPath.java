package A11_DijkstraPQShortestPath;

import java.util.ArrayList;

public class DijkstraPQShortestPath extends FindWay
{
	private int[] dist;
	private VertexHeap heap;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	 /**
	 * Startentfernung initialisieren
	 */
	protected void initPathSearch()
	{
		int numv = graph.numVertices();
		dist = new int[numv];
		heap = new VertexHeap(numv);

		for (int i = 0; i < numv; i++)
		{
			dist[i] = Integer.MAX_VALUE; // Summen im Graph dürfen nie mehr ergeben
		}
	}

	 /**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * @param from Startknoten
	 */
	protected boolean calculatePath(int from, int to)
	{
		initPathSearch();

		heap.insert(new Vertex(from,0,from));

		while(!heap.isEmpty())
		{
			Vertex vertex = heap.remove();
			int distance = dist[vertex.vertex];

			if(distance != Integer.MAX_VALUE)
			{
				dist[vertex.vertex] = vertex.cost;
				pred[vertex.cost] = vertex.pred;

				for (WeightedEdge we: graph.getEdges(vertex.vertex))
				{
					heap.insert(new Vertex(we.to_vertex, dist[we.from_vertex]+ we.weight, we.from_vertex));
				}
			}
		}

		return dist[to] != Integer.MAX_VALUE;
	}
}
