/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;
/**
 *
 * @author josel
 */
public class CircularDoublyLinkedList<E> implements List<E> {
    
    private Node<E> first;
    
    public CircularDoublyLinkedList(){
        first=null;
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> nodo = new Node<>(element);
        if(element==null)
            return false;
        else if(isEmpty()){
            first=nodo;
            first.setNext(first);
            first.setPrevious(first);
        }
        else{
            Node<E> last=first.getPrevious();
            nodo.setNext(first);
            first.setPrevious(nodo);
            last.setNext(nodo);
            nodo.setPrevious(last);
            first=nodo;
        }
        return true;
    }
    
    public Node<E> getFirst() {
        return first;
    }
    
    @Override
    public boolean addLast(E element) {
        Node<E> nodo = new Node<>(element);
        if(element==null)
            return false;
        else if(isEmpty()){
            first=nodo;
            first.setNext(first);
            first.setPrevious(first);
            
        }
        else{
            Node<E> last=first.getPrevious();
            nodo.setNext(first);
            first.setPrevious(nodo);
            nodo.setPrevious(last);
            last.setNext(nodo);
        }
        return true;
    }

    
    @Override
    public boolean isEmpty() {
        return this.first==null;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        else if(first.getNext()==first){
            E tmp=first.getData();
            first=null;
            return tmp;  
        }
        else{
            E tmp=first.getData();
            Node<E> last=first.getPrevious();
            first=first.getNext();
            first.setPrevious(last);
            last.setNext(first);
            return tmp;
        }
        
    }

    @Override
    public E removeLast() {
        if(isEmpty()){
            return null;
        }
        else if(first.getNext()==first){
            E tmp=first.getData();
            first=null;
            return tmp;  
        }
        else{ 
            Node<E> last=first.getPrevious();
            E tmp=last.getData();
            last=last.getPrevious();
            first.setPrevious(last);
            last.setNext(first);
            return tmp;
        }
        
    }

    @Override
    public int size() {
        int counter=0;
        Node<E> t=first;
        if(first!=null){
            
            do{
               counter++;
               t=t.getNext();
               
            
            }
            while(t!=first);
        }
        return counter;
     }

    @Override
    public void clear() {
        first=null;
    }

    @Override
    public void add(int index, E element) {
        if(index==0 && !isEmpty()){
            this.addFirst(element);
        }
        else if(!isEmpty() && element!=null && index<=size()){
            Node<E> node= new Node(element);
            int counter=0;
            Node<E> pointer=first;
            while(index>counter){
                if(pointer.getNext()!=null){
                    pointer=pointer.getNext();
                }
                counter++;
            }
            if(counter==index){
                node.setPrevious(pointer.getPrevious());
                node.setNext(pointer);
                pointer.getPrevious().setNext(node);
                pointer.setPrevious(node);
            }
        }      
    }

    @Override
    public E remove(int index) {
        if(this.isEmpty()){
            return null;
        }
        if(index==0){
            E content=first.getData();
            this.removeFirst();
            return content;
        }
        else if(!isEmpty() || index<=size()){
            int counter=0;
            Node<E> node=this.first;
            Node<E> previous=this.first;
            
            while(counter!=index){
                previous=node;
                node=node.getNext();
                counter++;
            }
            E content=node.getData();
            previous.setNext(node.getNext());
            node.getNext().setPrevious(previous);
            return content;
        }
        return null;
    }

    @Override
    public E get(int index) {
        if(!isEmpty() && index==0){
            return first.getData();
        }
        else if(!isEmpty() && index<=size()){
            Node<E> pointer=first;
            int counter=0;
            while(counter!=index){
                pointer=pointer.getNext();
                counter++;
            }
            return pointer.getData();
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if(!isEmpty() && index==0){
            E content=first.getData();
            first.setData(element);
            return content;
        }
        else if(!isEmpty() && index<=size() && element!=null){
            Node<E> pointer=first;
            int counter=0;
            while(counter!=index){
                pointer=pointer.getNext();
                counter++;
            }
            E content=pointer.getData();
            pointer.setData(element);
            return content;
        }
        return null;
    }
    
    
    public String toString() {
        String s = "";
        Node<E> t=first;
        if(first!=null){
            
            do{
               s += t.getData() + " ";
               t=t.getNext();
               
            
            }
            while(t!=first);
        }
        return s;
    }

    
}
