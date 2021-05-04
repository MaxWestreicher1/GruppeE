package A02_Heap;

import java.util.ArrayList;

public class TaskHeapArrayList {


	/**
	 * Internes Task-Array f�r den Heap
	 * Ansonsten keine anderen Variablen verwenden!
	 */
	private ArrayList<Task> tasks;

	/**
	 * Konstruktor
	 */
	public TaskHeapArrayList()
	{
		tasks = new TaskHeapArrayList();
	}

	/**
	 * Neuen Task in den Heap einf�gen
	 * @param t Einzuf�gender Task
	 */
	public void insert(Task t)
	{
		tasks.add(t)
		swim()
			// TODO: Your implementation
	}-

	/**
	 * Das oberste Element (mit kleinster Priorit�t entfernen)
	 * @return Task mit kleinster Priorit�t
	 */
	public Task remove()
	{
		// TODO: Your implementation

		return null;
	}

	//Arraylist richtigstellen
	private void swim(int pos)
	{
		// TODO: Your implementation of swim
	}

	private void sink(int pos)
	{
		// TODO: Your implementation of sink
	}

	private int parent(int pos) {
		return pos / 2;
	}

	private int left(int pos) {
		return pos * 2;
	}

	private int right(int pos)
	{
		return pos * 2 + 1;
	}

	private boolean exists(int pos) {
		return (pos < tasks.size() && pos > 0);
	}

	private int prio(int pos) {
		return tasks.get(pos).getPriority();
	}

	private void exchange(int pos1, int pos2) {
		Task temp;
		temp = tasks.get(pos1);
		tasks.set(pos1, tasks.get(pos2));
		tasks.set(pos2, temp);
	}

	private boolean hasChildren(int pos) {
		return exists(left(pos));
	}

	private int minChild(int pos) {
		int min, l, r;
		l = left(pos);
		r = right(pos);
		min = l;
		if (exists(r) && prio(r) < prio(l)) {
			min = r;
		}
		return min;
	}

}
