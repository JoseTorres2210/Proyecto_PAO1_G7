
package TDAs;

import java.util.Iterator;



public class LinkedList<E> implements List<E>,Iterable<E>{
    private NodeList<E> first;
    private NodeList<E> last;
    
    public LinkedList(){
        this.first = null;
        this.last = null;
    }
    
    
    public NodeList<E> getFirst() {
        return first;
    }

    public void setFirst(NodeList<E> first) {
        this.first = first;
    }

    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }
    
    //O(1)
    @Override
    public boolean addFirst(E e) {
        if(e==null){
            return false;
        }
        //Se crea el nodo
        NodeList<E> nodo = new NodeList<>(e);
        
        //Ahora su next sera el first de la lista
        //Esto es el ENLACE
        nodo.setNext(this.getFirst());
        
        //Si la lista esta vacia este nodo tambien debe ser el last
        if(this.isEmpty()){
            this.setLast(nodo);
        }
        //Ahora lo setteamos como first
        this.setFirst(nodo);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        //Ahora solo debemos agregar el nodo al final
        if(e==null){
            return false;
        }
        NodeList<E> nodo = new NodeList<>(e);
        if(isEmpty()){
            this.setFirst(nodo);
        }else{
            this.getLast().setNext(nodo);
        }

        this.setLast(nodo);
        return true;
        
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            throw new NullPointerException();
        }
        E elemento = this.getFirst().getContent();
        //Ahora solo se quita el primer elemento
        this.setFirst(this.getFirst().getNext());
        return elemento;
    }

    @Override
    public E removeLast() {
        //El next del penultimo debe ser null
        //Validaciones
        if(this.isEmpty()){
            throw new NullPointerException();
        }
        E contenido = this.getLast().getContent();
        NodeList<E> t = this.getPrevious(this.getLast());
        this.getPrevious(this.getLast()).setNext(null);
        this.setLast(t);
        return contenido;
    }

    @Override
    public int size() {
        int contador = 0;
        //Debo recorrer toda la lista y acumular las repeticiones del for en una variable
        NodeList<E> t = null;
        for(t = this.getFirst();t!=null;t=t.getNext()){
            contador++;
        }
        return contador;
    }

    @Override
    public boolean isEmpty() {
        return this.first==null&&this.last==null;
    }

    @Override
    public void clear() {
        this.setFirst(null);
        this.setLast(null);
    }

    @Override
    public void add(int index, E element) {
        if(element ==null){
            throw new NullPointerException();
        }
        if(index<0 || index>this.size()){
            throw new IndexOutOfBoundsException();
        }
        //En el caso de que el indice sea 1
        if(index==0){
            this.addFirst(element);
        }else if(index==this.size()){
            this.addLast(element);
        }else{
            //Caso contrario estamos en otro indice entre estos valores
            NodeList<E> t = null;
            NodeList<E> nodoPrevio = null;
            NodeList<E> nodoAdd = new NodeList<>(element);
            
            int contador =0;
            for(t=this.getFirst();t!=null;t = t.getNext()){
                if(contador==index-1){
                    //Obtenemos el nodo anterior
                    nodoPrevio = t;
                }else if(contador==index){
                    //Ahora solo hacemos nuestra magia
                    nodoAdd.setNext(t);
                    nodoPrevio.setNext(nodoAdd);
                }
                contador++;
            }
        }
    }

    @Override
    public E remove(int index) {
        if(index<0||index>=this.size()){
            throw new NullPointerException();
        }
        E contenido = null;
        int contador = 0;
        //Vamos a recorrer la lista
        NodeList<E> t = null;
        if(index==0){
            return this.removeFirst();
        }
        for(t = this.getFirst();t!=null;t = t.getNext()){
            if(contador==index){
                //Llegamos al nodo deseado
                //Se recupera el contenido de este
                contenido = t.getContent();
                
                this.getPrevious(t).setNext(t.getNext());
                //Y de esta manera eliminamos las relaciones
            }
            contador++;
        }
        return contenido;
    }

    @Override
    public E get(int index) {
        /*
        Este metodo debe recuperar el contenido del nodo en el indice dado.
        Ahora, no podemos usar indices directamente, por lo cual debemos ponernos creativos
        con la iteracion del for
        */
        int contador = 0;
        //Validaciones
        if(this.isEmpty()){
            throw new NullPointerException();
        }
        if(index<0||index>=this.size()){
            throw new IndexOutOfBoundsException();
        }
        NodeList<E> t = null;
        E contenido = null;
        for(t = this.getFirst();t!=null;t = t.getNext()){
            if(contador==index){
                contenido = t.getContent();
            }
            contador++;
        }
        return contenido;
    }

    @Override
    public E set(int index, E element) {
        /*
        Solo debo reemplazar el contenido de ese nodo con lo que me
        dan por parametro
        */
        if(element ==null){
            throw new NullPointerException();
        }
        if(this.isEmpty()){
            throw new NullPointerException();
        }
        if(index<0||index>=this.size()){
            throw new IndexOutOfBoundsException();
        }
        int contador = 0;
        NodeList<E> t = null;
        E contenidoPrevio = null;
        for(t = this.getFirst();t!=null;t=t.getNext()){
            if(contador==index){
                //Entonces reemplazamos el contenido de ese indice
                contenidoPrevio = t.getContent();
                t.setContent(element);
            }
            contador++;
        }
        return contenidoPrevio;
    }
    
    
    @Override
    public String toString(){
        String s = "[ ";
        NodeList<E> t = null;
        for(t = this.getFirst();t!=null;t = t.getNext()){
            s+=t.getContent()+" ";
        }
        return s+" ]";
    }
    
    
    private NodeList<E> getPrevious(NodeList<E> nodo){
        NodeList<E> t = null;
        NodeList<E> retorno = null;
        
        for(t = this.getFirst();t!=null;t = t.getNext()){
            if(t.getNext()==nodo){
                retorno =  t;
            }
        }
        return retorno;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it=new Iterator<E>(){
            
            NodeList<E> pointer=first;
            public boolean hasNext() {
                return pointer.getNext()!=null;
                
            }

            @Override
            public E next() {
                E e=pointer.getContent();
                pointer=pointer.getNext();
                return e;
            }
            
        };
        return it;
    }
    
    
}
