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

    public void add(T a) {
        Node<T> tmp = new Node<>(a);
        if (erstes == null) {
            erstes = tmp;
        }
        else {
            tmp = erstes.getNext();
            while (tmp != null) {
                tmp = tmp.getNext();
            }
            if (tmp != null) {
                tmp.setNext(tmp);
                letztes.setPrevious(tmp);
            }
        }
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {
    aktuell = erstes;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
    erstes = letztes;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {

        if (erstes == null) {
            return null;
        } else {

            return erstes;
        }
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
        if (letztes == null) {
            return null;
        } else {

            return letztes;
        }
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {

    	T retour;

    	if (aktuell != null){
    	    retour = aktuell.getData();
    	    aktuell = aktuell.getNext();
    	    return retour;
        } else {
    	    return null;
        }
    }
    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {
        T retour;

        if (aktuell != null){
            retour = aktuell.getData();
            aktuell = aktuell.getNext();
            return retour;
        } else {
            return null;
        }
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {

        if (aktuell != null){
            aktuell = aktuell.getNext();
        }

    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if (aktuell != null){
            aktuell = aktuell.getPrevious();
        }
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        if (aktuell == null){
            throw new CurrentNotSetException();
        }
        return aktuell.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {

        int nummerierung = 1;

        if (erstes == null){
            return null;
        }
        while (erstes != null && nummerierung != pos){
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
    public void remove(int pos) {
        int position = 1;
        Node<T> tmp;
        if(erstes != null){
            tmp = erstes.getNext();
            while(tmp != null){
                tmp = tmp.getNext();
                if(position == pos){
                    if(tmp.getNext() != null) {
                        tmp.getNext().setPrevious(tmp.getPrevious());
                        tmp.getPrevious().setNext(tmp.getNext());
                    }else {
                        tmp.getPrevious().setNext(null);
                    }
                    try {
                        if(tmp == getCurrent()) {
                            aktuell = null;
                        }
                    } catch (CurrentNotSetException e) {
                        e.printStackTrace();
                    }
                    tmp = null;
                }
                position++;
            }
        }
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {

        if (aktuell == null)
            throw new CurrentNotSetException();

        if (aktuell == erstes && aktuell == letztes) {
            aktuell = null;
            erstes = null;
            letztes = null;
            return;
        }
        if (aktuell == erstes) {
            erstes = erstes.getNext();
            erstes.setPrevious(null);
            aktuell = erstes;
            return;
        }
        if (aktuell == letztes) {
            letztes = letztes.getPrevious();
            letztes.setNext(null);
            aktuell = letztes;
            return;
        }
        aktuell = aktuell.getNext();
        aktuell.getPrevious().setNext(aktuell);
        aktuell.getNext().setPrevious(aktuell);
        aktuell.setNext(aktuell.getNext().getNext());
        aktuell.setPrevious(aktuell.getPrevious());
    }

    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

        if (aktuell == null){
            throw new CurrentNotSetException();
        }
        Node<T> insert = new Node<>(a);
        if (aktuell == letztes){
            aktuell.setNext(insert);
            insert.setPrevious(aktuell);
            letztes = insert;
        } else {
            insert.setNext(aktuell.getNext());
            aktuell.setNext(insert);
            insert.setPrevious(aktuell);
        }
        aktuell = insert;
    }
}
