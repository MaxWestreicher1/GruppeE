package A11_DijkstraPQShortestPath;

public class Vertex
{
	public int vertex;
	public int cost;
	public int pred;

	public Vertex(int vertex, int cost,int pred)
	{
		this.vertex = vertex;
		this.cost = cost;
		this.pred = pred;
	}
}
