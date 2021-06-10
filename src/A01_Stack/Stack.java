package A01_Stack;


public class Stack<T>
{
	 private Node<T> first;
	 private int count = 0;

    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException 
     */
    public T pop() throws StackEmptyException
    {
        if(first == null)
            throw new StackEmptyException();

        Node temp=first;
        first = temp.getNext();
        count--;
    	return (T)temp.getData();
    }
    
    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i)
    {
        Node a = new Node<T>(i);
        a.setNext(first);
        first = a;
        count++;
    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount()
    {
        return count;
    }
}
