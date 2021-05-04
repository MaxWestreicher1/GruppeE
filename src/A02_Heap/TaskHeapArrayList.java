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
		tasks = new ArrayList<Task>();
		tasks.add(new Task(0,0)); //Für Heap unbrauchbaren dummy Eintrag einfügen
	}

	/**
	 * Neuen Task in den Heap einf�gen
	 * @param t Einzuf�gender Task
	 */
	public void insert( Task t )
	{
		tasks.add(t);
		int lastPos = tasks.size() - 1;

		swim(lastPos);
	}

	/**
	 * Das oberste Element (mit kleinster Priorit�t entfernen)
	 * @return Task mit kleinster Priorit�t
	 */
	public Task remove()
	{
		int lastPos = tasks.size()-1;

		if(lastPos > 0)
		{
			sink(lastPos);
			return tasks.remove(lastPos);
		}

		return null;
	}

	//Arraylist richtigstellen
	private void swim( int pos )
	{
		int swimmerPrio = prio(pos);
		int parentPos;

		while( pos / 2 >= 1 )
		{
			parentPos = parent(pos);

			if(prio(parentPos) > swimmerPrio)
			{
				exchange(parentPos,pos);
				pos = parentPos;
			}
			else
			{
				break;
			}
		}
	}

	private void sink( int pos )
	{
		int sinkerPrio = prio(pos);
		int sinkerPos = 1;
		int size = tasks.size();

		int childPos;

		exchange(1,pos);

		while(2*sinkerPos <= size)
		{
			childPos = minChild(sinkerPos);

			if( prio(childPos) < sinkerPrio )
			{
				exchange(childPos,sinkerPos);
				sinkerPos = childPos;
			}
			else
			{
				break;
			}
		}
	}

	private int parent(int pos)
	{
		return pos / 2;
	}

	private int left(int pos)
	{
		return pos * 2;
	}

	private int right(int pos)
	{
		return pos * 2 + 1;
	}

	private boolean exists(int pos)
	{
		return (pos < tasks.size() && pos > 0);
	}

	private int prio(int pos)
	{
		return tasks.get(pos).getPriority();
	}

	private void exchange(int pos1, int pos2)
	{
		Task temp;
		temp = tasks.get(pos1);
		tasks.set(pos1, tasks.get(pos2));
		tasks.set(pos2, temp);
	}

	private boolean hasChildren(int pos)
	{
		return exists(left(pos));
	}

	private int minChild(int pos)
	{
		int min, l, r;

		l = left(pos);
		r = right(pos);
		min = l;

		if (exists(r) && prio(r) < prio(l))
		{
			min = r;
		}

		return min;
	}

}
