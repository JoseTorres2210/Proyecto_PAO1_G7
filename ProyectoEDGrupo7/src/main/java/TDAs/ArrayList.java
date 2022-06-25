package TDAs;

import java.io.Serializable;
import java.util.Iterator;

public class ArrayList<E> implements List<E>,Iterable<E>,Serializable{
    private int capacity= 100;
    private E[] elements=null;
    private int effectiveSize= 0;

    public ArrayList(){
        this.elements= (E[])(new Object[capacity]);
        this.effectiveSize=0;
    }

    private boolean isFull(){
        return elements.length== effectiveSize;
    }

    private void addCapacity(){
        E[] tmp =  (E[]) (new Object[capacity*2]);
        for (int i =0; i<capacity; i++){
            tmp[i]=elements[i];
        }
        elements=tmp;
        capacity= capacity*2;
    }
    @Override
    public boolean addFirst(E element) {
        if (element == null){
            return false;
        } else if (isEmpty()){
            elements[effectiveSize++]=element;
            return true;
        } else if (capacity==effectiveSize){
            addCapacity();
        }
        for (int i= effectiveSize - 1; i>=0; i--){
            elements[i+1]= elements[i];
        }
        elements[0]= element;
        effectiveSize++;
        return true;
    }
    @Override
    public boolean addLast(E element) {
        if (element == null){
            return false;
        } else if (isEmpty()){
            elements[effectiveSize++]=element;
            return true;
        } else if (capacity == effectiveSize){
            addCapacity();
        }
        int index=effectiveSize;
        elements[index]= element;
        effectiveSize++;  
        return true;
    }
    @Override
    public E removeFirst() {
        return remove(0);
    }
    @Override
    public E removeLast() {
        return remove(this.effectiveSize-1);
    }
    @Override
    public int size() {    
        return effectiveSize;
    }
    @Override
    public boolean isEmpty() {
        return effectiveSize==0;
    }
    @Override
    public void clear() {
        effectiveSize=0;
    }
    @Override
    public void add(int index, E element) {
        if (element==null){
            throw new NullPointerException("El elemento no puede ser nulo");
        } else if(index < 0 || index>this.effectiveSize){
            throw new IndexOutOfBoundsException();
        }else if (isEmpty()){
            elements[effectiveSize++]=element;
        }else if (capacity==effectiveSize){
            addCapacity();
        }
        effectiveSize++;
        for(int i= effectiveSize; i > index; i--){
            elements[i]=elements[i-1];
        }
        elements[index]=element;     
    }

    @Override
    public E remove(int index) {
        E elementToRemove=null;
        if (this.isEmpty() || index >= this.effectiveSize || index<0){
            throw new IndexOutOfBoundsException();
        } else {
            elementToRemove= elements[index];
            for (int i=index; i< this.effectiveSize - 1; i++){
                elements[i]= elements[i+1];
            }
            this.effectiveSize--;
        }     
        return elementToRemove;
    }
    @Override
    public E get(int index) {
        if (index >= this.effectiveSize || index<0){
            throw new IndexOutOfBoundsException();
        }else{
            return elements[index];
        }
    }
    @Override
    public E set(int index, E element) {
        if (index >= this.effectiveSize || index<0){
            throw new IndexOutOfBoundsException();
        }
        E oldElement= elements[index];
        elements[index]=element;
        return oldElement;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it=new Iterator<E>(){
            
            int pointer=0;
            public boolean hasNext() {
                return pointer<effectiveSize-1;
            }

            @Override
            public E next() {
                E element= elements[pointer++];
                return element;
            }
            
        };
        return it;
    }
    
    
    public String toString(){
        String s = "[";
        for(int i = 0;i<this.size();i++){
            s+=elements[i]+" ";
        }
        return s+"]";
    }
    

    
}
