import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p> Clase concreta para modelar la estructura de datos Lista</p>
 * <p>Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Luis Manuel Martinez Damaso <luismanuel@ciencias.unam.mx>
 * @version 1.0
 * @param <T>
 */
public class Lista<T> implements Listable<T>{

    /* Clase interna para construir la estructura */
    protected class Nodo{
        /* Referencias a los nodos anterior y siguiente */
        public Nodo anterior, siguiente;
        /* El elemento que almacena un nodo */
        public T elemento;

        /* Unico constructor de la clase */
        public Nodo(T elemento){
        	this.elemento = elemento;
        	this.siguiente = null;
        	this.anterior = null;
        }
        public boolean equals(Nodo n){
            if(this.elemento == n.elemento){
            	return true;
            }

            return false;
        }
    }

    
    private class IteradorLista implements Iterator<T>{
        /* La lista a recorrer*/
        /* Elementos del centinela que recorre la lista*/
        private Lista<T>.Nodo siguiente;

        public IteradorLista(){
            siguiente = cabeza;
        }

        @Override
        public boolean hasNext() {
            while(siguiente != null){
            	return true;
            }

            return false;
        }

        @Override
        public T next() {
        	T elem = siguiente.elemento;
            siguiente = siguiente.siguiente;
            return elem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    
    /* Atributos de la lista */
    protected Nodo cabeza, cola;
    protected int longitud;

    /**
     *  Constructor por omisión de la clase, no recibe parámetros.
     *  Crea una nueva lista con longitud 0.
     **/
    public Lista(){
        this.cabeza = null;
        this.cola = null;
        this.longitud = 0;
    }

    /**
     *  Constructor copia de la clase. Recibe una lista con elementos.
     *  Crea una nueva lista exactamente igual a la que recibimos como parámetro.
     * @param l
     **/
    public Lista(Lista<T> l){

        for(T elem : l){
            agregarAlFinal(elem);
        }

    }

    /**
     *  Constructor de la clase que recibe parámetros.
     *  Crea una nueva lista con los elementos de la estructura iterable que recibe como parámetro.
     * @param iterable
     **/
    public Lista(Iterable<T> iterable){

        for(T elem : iterable){
            this.agregar(elem);
        }
    }
    
    /**
     * Método que nos dice si las lista está vacía.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    @Override
    public boolean esVacia(){

    	if(this.cabeza != null){
    		return false;
    	}

    	return true;  	
    }
    
    /**
     * Método para eliminar todos los elementos de una lista
     */
    @Override
    public void vaciar(){
        this.cabeza = null;
        this.cola = null;
        this.longitud = 0;
    }
    
    /**
     * Método para obtener el tamaño de la lista
     * @return tamanio Número de elementos de la lista.
     **/
    @Override
    public int getTamanio(){
        return longitud;
    }
    
    /**
     * Método para agregar un elemento a la lista.
     * @param elemento Objeto que se agregará a la lista.
     */
    @Override
    public void agregar(T elemento) throws IllegalArgumentException{

        if(elemento == null){throw new IllegalArgumentException();}

    	Nodo nuevo = new Nodo(elemento);

    	if(this.esVacia() == true){
    		this.cabeza = nuevo;
    		this.cola = nuevo;
    	}else{
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
    	}

    	longitud++;
    }
    
    /**
     * Método para agregar al final un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlFinal(T elemento) throws IllegalArgumentException {

    	if(elemento == null){throw new IllegalArgumentException();}

    	Nodo nuevo = new Nodo(elemento);

    	if(this.esVacia() == true){
    		this.cola = nuevo;
    		this.cabeza = nuevo;
            cola.siguiente = null;
            cabeza.anterior = null;
    	}else{
            nuevo.anterior = this.cola;
            this.cola.siguiente = nuevo;
            this.cola = nuevo;
    	}

    	longitud++;
    }

    /**
     * Método para obtener el primer elemento.
     */
    @Override
    public T getPrimero() throws NoSuchElementException {

    	if(this.esVacia() == true){
    		throw new NoSuchElementException();
    	}

        return this.cabeza.elemento;
    }

    /**
     * Método para obtener el último elemento.
     */
    public T getUltimo() throws NoSuchElementException {
        if(this.esVacia() == true){
        	throw new NoSuchElementException();
        }

        return this.cola.elemento;
    }
    
    /**
     * Método para verificar si un elemento pertenece a la lista.
     * @param elemento Objeto que se va a buscar en la lista.
     * @return <code>true</code> si el elemento esta en el lista y false en otro caso.
     */
    @Override
    public boolean contiene(T elemento) throws NoSuchElementException {

    	if(this.esVacia() == true){throw new NoSuchElementException();}
        
        Nodo aux = this.getNodo(elemento);

        if(aux == null){
            return false;
        }
        return true;
    }

    /**
     * Método para eliminar un elemento de la lista.
     * @param elemento Objeto que se eliminara de la lista.
     * todo
     */
    @Override
    public void eliminar(T elemento) throws NoSuchElementException {
        if(this.esVacia() == true 
            || this.contiene(elemento)!= true){
            throw new NoSuchElementException();
        }

        Nodo eliminado = this.getNodo(elemento);

        if(eliminado != null){

            if (this.esVacia() == true) {this.vaciar();}
            else if (this.cabeza == eliminado) {this.eliminarPrimero();}
            else if (this.cola == eliminado) {this.eliminarUltimo();} 

            else {
                eliminado.anterior.siguiente = eliminado.siguiente;
                eliminado.siguiente.anterior = eliminado.anterior;
            }
            
            longitud --;
        }

    }

    /**
     * Método para eliminar el primer elemento de la lista.
     */
    @Override
    public void eliminarPrimero() throws NoSuchElementException {

    	if(this.esVacia() == true){throw new NoSuchElementException();}
        if(this.longitud == 1){this.vaciar();}

        //Nodo aux = this.cabeza.siguiente;

        //aux.anterior = null;
        //this.cabeza = aux;

        this.cabeza = this.cabeza.siguiente;
        this.cabeza.anterior = null;

    	longitud--;
    }

    /**
     * Método para eliminar el primer elemento de la lista.
     */
    public void eliminarUltimo() throws NoSuchElementException {

        if(this.esVacia() == true){throw new NoSuchElementException();}
        if(this.esVacia() == true){this.vaciar();}

        //Nodo aux = this.cola.anterior;

        //aux.siguiente = null;
        //this.cola = aux;

        this.cola = this.cola.anterior;
        this.cola.siguiente = null;

        longitud--;
    }

    /**
     * Método que devuelve la posición en la lista que tiene la primera
     * aparición del <code> elemento</code>.
     * @param elemento El elemnto del cuál queremos saber su posición.
     * @return i la posición del elemento en la lista, -1, si no se encuentra en ésta.
     */
    @Override
    public int indiceDe(T elemento) throws NoSuchElementException{

        if(this.esVacia() == true){
            throw new NoSuchElementException();
        }

        if(this.contiene(elemento) == false){
            return -1;
        }

        Nodo aux = this.cabeza;
        int index = 0;

        while(aux.elemento.equals(elemento) != true){
            aux = aux.siguiente;
            index++;
        }

        return index;
    }

    /**
     * Método que nos devuelve el elemento que esta en la posición i
     * @param i La posición cuyo elemento deseamos conocer.
     * @return <code> elemento </code> El elemento que contiene la lista,
     * <code>null</code> si no se encuentra
     * @throws IndexOutOfBoundsException Si el índice es < 0 o >longitud()
     */
    @Override
    public T getElemento(int i)throws IndexOutOfBoundsException{
        
        if(i > this.longitud-1 || i< 0){
            throw new IndexOutOfBoundsException();
        }

        Nodo aux = this.cabeza;
        int index = 0;
        
        while(index < i){
            aux = aux.siguiente;
            index++;
        }

        return aux.elemento;

    }

    /**
     * Método que devuelve una copia de la lista, pero en orden inverso
     * @return Una copia con la lista l revés.
     */
    @Override
    public Lista<T> reversa(){
        Lista<T> lista = new Lista<T>();

        if(this.esVacia()){return lista;}

        Nodo aux = this.cabeza;

        while(aux != null){
            lista.agregar(aux.elemento);
            aux = aux.siguiente;
        }

        return lista;
    }

    /**
     * Método que devuelve una copia exacta de la lista
     * @return la copia de la lista.
     */
    @Override
    public Lista<T> copia(){
        Lista<T> lista = new Lista<T>();

        if(this.esVacia()){return lista;}

        Nodo aux = this.cabeza;

        while(aux != null){
            lista.agregarAlFinal(aux.elemento);
            aux = aux.siguiente;
        }

        return lista;
    }

    /**
     * Método que nos dice si una lista es igual que otra.
     * @param o objeto a comparar con la lista.
     * @return <code>true</code> si son iguales, <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o){
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>) o;
        
        if(o instanceof Lista != true){return false;}

        if(lista.longitud != this.longitud){return false;}

        Nodo aux = this.cabeza;
        Nodo aux2 = lista.cabeza;

        while(aux != null){
            if(aux.equals(aux2) != true){
                return false;
            }
            aux = aux.siguiente;
            aux2 = aux2.siguiente;
        }

        return true;
    }

    /**
     * Método que devuelve un iterador sobre la lista
     * @return java.util.Iterador -- iterador sobre la lista
     */
    @Override
    public java.util.Iterator<T> iterator(){
        return new IteradorLista();
    }


    @Override
    public String toString() {
        if (esVacia()) {
            return "[]";
        }
        Nodo nodo = cabeza;
        String cad = "[" + nodo.elemento;
        while (nodo.siguiente != null) {
            nodo = nodo.siguiente;
            cad += ", " + nodo.elemento;
        }
        return cad + "]";
    }
      
    
    /* Método auxiliar para obtener una referencia a un nodo con un elemento
    específico. Si no existe tal nodo, devuelve <code> null </code> */
    private Nodo getNodo(T elem) throws NoSuchElementException {
        
        if(this.esVacia() == true){
        	throw new NoSuchElementException();
        }

        Nodo aux = this.cabeza;

        while(aux != null){
        	if(aux.elemento.equals(elem) == true){
        		return aux;
        	}
        	aux = aux.siguiente;
        }

        return null;

    }
}