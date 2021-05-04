package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{

    private Node<T> erstes;
    private Node<T> letztes;
    private Node<T> aktuell;
    /**
     * Einfügen einer neuen <T>
     * @param a <T>
     */

    public void add(T a)
    {
        Node<T> tmp = new Node<>(a);

        if (erstes == null)
        {
            erstes = tmp;
            letztes = tmp;

        }
        else
        {
            tmp.setPrevious(letztes);
            letztes.setNext(tmp);
            letztes = tmp;
        }
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset()
    {
        aktuell = erstes;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast()
    {
        aktuell = letztes;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst()
    {
        return erstes;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast()
    {
        return letztes;
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next()
    {
    	T retour = null;

    	if (aktuell != null)
    	{
    	    retour = aktuell.getData();
    	    aktuell = aktuell.getNext();
        }

        return retour;
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous()
    {
        T retour = null;

        if (aktuell != null)
        {
            retour = aktuell.getData();
            aktuell = aktuell.getPrevious();
        }

        return retour;
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext()
    {
        if (aktuell != null)
        {
            aktuell = aktuell.getNext();
        }
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious()
    {
        if (aktuell != null)
        {
            aktuell = aktuell.getPrevious();
        }
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException
    {
        if (aktuell == null)
        {
            throw new CurrentNotSetException();
        }

        return aktuell.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos)
    {

        int nummerierung = 1;

        if (erstes == null)
        {
            return null;
        }

        while (erstes != null && nummerierung != pos)
        {
            nummerierung++;
            erstes = erstes.getNext();
        }

        return erstes.getData();

    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos)
    {
        if(erstes == null)
            return;

        Node currentNode = erstes;

        for(int i = 1;i < pos;i++)
        {
            Node nextNode = currentNode.getNext();

            if(nextNode == null)
                break;

            currentNode = nextNode;
        }
        if(currentNode == aktuell)
            aktuell = null;

        Node tempNode = currentNode.getNext();
        if(tempNode!=null)
        {
            tempNode.setPrevious(currentNode.getPrevious());

            if(currentNode == erstes)
                erstes = tempNode;
        }

        tempNode = currentNode.getPrevious();
        if(tempNode!=null)
        {
            tempNode.setNext(currentNode.getNext());

            if(currentNode == letztes)
                letztes = tempNode;
        }
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException
    {

        if (aktuell == null)
            throw new CurrentNotSetException();

        if (erstes == letztes)
        {
            erstes = null;
            letztes = null;
            aktuell = null;
        }
        else
        {
            Node previousNode = aktuell.getPrevious();
            Node nextNode = aktuell.getNext();

            if(previousNode != null)
            {
                previousNode.setNext(aktuell.getNext());

                if(aktuell == letztes)
                {
                    letztes = previousNode;
                    aktuell = previousNode;
                }
            }



            if(nextNode != null)
            {
                nextNode.setPrevious(aktuell.getPrevious());

                if(aktuell == erstes)
                    erstes = nextNode;

                aktuell = nextNode;
            }
        }
    }

    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException
    {

        if (aktuell == null)
        {
            throw new CurrentNotSetException();
        }

        Node<T> insert = new Node<>(a);

        if (aktuell == letztes)
        {
            aktuell.setNext(insert);
            insert.setPrevious(aktuell);
            letztes = insert;
        }
        else
        {
            insert.setNext(aktuell.getNext());
            aktuell.setNext(insert);
            insert.setPrevious(aktuell);
        }

        aktuell = insert;
    }
}
