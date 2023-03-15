package EstructurasDeDatos;
import java.util.Iterator;

public class GenericIterableResizingStack <Item> implements Iterable <Item> {

    //Atributos
    private Item a[];
    private int count;

    //Constructor
    public GenericIterableResizingStack(){
        a = (Item[]) new Object[1];
        count = 0;
    }

    //Métodos funcionales
    //adicionar
    public void push(Item item){
        if(count == a.length)
            resize(2*a.length);
        a[count++] = item;
        //count++;  // count += 1; count = count +1;
    }

    //eliminar
    public Item pop(){
        Item temp = a[--count];
        a[count] = null;
        if(count == a.length/4)
            resize(a.length/2);
        return temp;
    }

    public Item peak(){
        return a[count-1];
    }

    //conocer si está vacía
    public boolean isEmpty(){
        return count==0;
        //return count==0?true:false;
        /*
        if(count == 0)
            return true;
        else
            return false;
            */
    }

    //conocer su tamaño
    public int size(){
        return count;
    }

    private void resize(int maxCap){
        Item temp[] = (Item [])new Object[maxCap];
        for (int i = 0; i < count; i++)
            temp[i] = a[i];
        a = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{

        private int i = count;

        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

    }
}