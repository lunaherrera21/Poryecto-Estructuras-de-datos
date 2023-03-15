package EstructurasDeDatos;
import java.util.Iterator;

/**
 *
 * @author diego.gutierrezf
 */
public class GenericResizingQueue<Item> implements Iterable<Item> {
    //Attributes
    private Item stck[], quue[];
    private int counterStck,counterQuue,head;

    //Constructor
    public GenericResizingQueue() {
        stck = (Item[]) new Object[1];
        quue = (Item[]) new Object[1];
        counterStck = 0;
        counterQuue=0;
        head=0;
    }

    //Functional Methods
    //Resize
    private Item[] resize(int capacity,Item []arr,int counter) {
        Item temp[] = (Item[]) new Object[capacity];
        for (int i = 0; i < counter; i++) {
            temp[i] = arr[i];
        }
        arr = temp;

        return arr;
    }

    //Add into Queue
    public void enqueue (Item data){
        if (counterQuue == quue.length){
            quue=resize(2*quue.length,quue,counterQuue);
        }
        quue[counterQuue++]=data;

    }

    //Delete into Queue
    public Item dequeue(){
        Item temp = quue[head];
        for(int i = 0;i<quue.length-1;i++){
            quue[i]=quue[i+1];
        }
        counterQuue--;
        if(counterQuue>0 && counterQuue == quue.length /4){
            resize(stck.length / 2,stck,counterStck);
        }
        return temp;
    }

    public boolean isEmpty() {
        return counterQuue == 0;
    }

    //Return size of array
    public int size() {
        return counterStck;
    }

    public Item peek() {
        return stck[counterStck - 1];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i;

        public ReverseArrayIterator(){
            i = counterStck-1;
        }

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {
            return stck[--i];
        }
    }
}