import java.util.NoSuchElementException;

/**
 * <p>
 * Clase abstracta para modelar la estructura de datos Arbol Binario</p>
 * Puesto que todos los árboles binarios comparten algunas características
 * similares, esta clase sirve perfectamente para modelarlas. Sin embargo no es
 * lo suficientemente específica para modelar algun árbol completamente. Por lo
 * que la implementación final depende de las clases concretas que hereden de
 * ésta.</p>
 *
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 * @param <T>
 */

public abstract class ArbolBinario<T> implements Coleccionable<T> {

    /**
     * Clase interna protegida para nodos.
     */
    protected class Nodo {

        /**
         * El elemento del nodo.
         */
        public T elemento;
        /**
         * Referencia a los nodos padre, e hijos.
         */
        public Nodo padre, izquierdo, derecho;

        /**
         * Constructor único que recibe un elemento.
         *
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento){
	       this.elemento = elemento;
        }

        /**
         * Nos dice si el nodo tiene un padre.
         *
         * @return <tt>true</tt> si el nodo tiene padre,
         * <tt>false</tt> en otro caso.
         */
        public boolean hayPadre() {
            return this.padre != null;
        }

        /**
         * Nos dice si el nodo tiene un izquierdo.
         *
         * @return <tt>true</tt> si el nodo tiene izquierdo,
         * <tt>false</tt> en otro caso.
         */
        public boolean hayIzquierdo() {
            return this.izquierdo != null;
        }

        /**
         * Nos dice si el nodo tiene un derecho.
         *
         * @return <tt>true</tt> si el nodo tiene derecho,
         * <tt>false</tt> en otro caso.
         */
        public boolean hayDerecho() {
            return this.derecho != null;
        }

        /**
         * Regresa la altura del nodo.
         *
         * @return la altura del nodo.
         */
        public int altura() {
        	return auxAltura(this);
        }

        private boolean esHoja(Nodo n){
        	if(!hayIzquierdo() && !hayDerecho()){
        		return true;
        	}else{
        		return false;
        	}
        }

        private int auxAltura(Nodo n){
        	int alt = 0;

        	if(!esHoja(n)){
        		if(n.izquierdo != null){
        			alt = Math.max(alt , auxAltura(n.izquierdo));
        		}else{
        			alt = 0;
        		}

        		if(n.derecho != null){
        			alt = Math.max(alt, auxAltura(n.derecho));
        		}else{
        			alt = 0;
        		}

        		alt ++;
        	}

        	return alt;
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>. Las clases que extiendan {@link Nodo} deben
         * sobrecargar el método {@link Nodo#equals}.
         *
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         * {@link Nodo}, su elemento es igual al elemento de éste nodo, y los
         * descendientes de ambos son recursivamente iguales; <code>false</code>
         * en otro caso.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            @SuppressWarnings("unchecked")
            Nodo nodo = (Nodo) o;
            return elemento.equals(nodo.elemento);

        }

        /**
         * Regresa una representación en cadena del nodo.
         *
         * @return una representación en cadena del nodo.
         */
        @Override
        public String toString() {
            return elemento.toString();
        }
    }

    /**
     * La raíz del árbol.
     */
    protected Nodo raiz;
    /**
     * El número de elementos
     */
    protected int tamanio;

    /**
     * Constructor sin parámetros.
     */
    public ArbolBinario() {
        this. raiz = null;
        this.tamanio = 0;
    }

    /**
     * Construye un árbol binario a partir de una colección. El árbol binario
     * tendrá los mismos elementos que la colección recibida.
     *
     * @param iterable
     */
    public ArbolBinario(Iterable<T> iterable) {
		
    	for(T elem : iterable){
			this.agregar(elem);
		}
    }

    /**
     * Construye un nuevo nodo, usando una instancia de {@link Nodo}. Para crear
     * nodos se debe utilizar este método en lugar del operador
     * <code>new</code>, para que las clases herederas de ésta puedan
     * sobrecargarlo y permitir que cada estructura de árbol binario utilice
     * distintos tipos de nodos.
     *
     * @param elemento el elemento dentro del nodo.
     * @return un nuevo nodo con el elemento recibido dentro del mismo.
     */
    protected Nodo nuevoNodo(T elemento) {
        return new Nodo(elemento);
    }

    /**
     * Regresa la altura del árbol. La altura de un árbol es la altura de su
     * raíz.
     *
     * @return la altura del árbol.
     */
    public int altura() {
        return raiz.altura();
    }

    /**
     * Regresa el número de elementos que se han agregado al árbol.
     *
     * @return el número de elementos en el árbol.
     */
    @Override
    public int getTamanio() {
	   return this.tamanio;
    }

    /**
     * Regresa el nodo que contiene la raíz del árbol.
     *
     * @return el nodo que contiene la raíz del árbol.
     * @throws NoSuchElementException si el árbol es vacío.
     */
    protected Nodo raiz() {
        return this.raiz;
    }

    /**
     * Nos dice si el árbol es vacío.
     *
     * @return <code>true</code> si el árbol es vacío, <code>false</code> en
     * otro caso.
     */
    @Override
    public boolean esVacia() {
	   return this.raiz != null;
    }

    /**
     * Limpia el árbol de elementos, dejándolo vacío.
     */
    public void vaciar() {
        this.raiz = null;
        this.tamanio = 0;
    }

    /**
     * Regresa una Cola con el los elementos en inorden del árbol.
     *
     * @return Cola con los elementos del arbol.
     */
    public Lista<T> inOrden() {
    	return auxInOrden(this.raiz);
    }

    /**
     * Método auxiliar para inOrden.
     */
    private Lista<T> auxInOrden(Nodo n){
    	if(n == null){ return null; }

    	Lista<T> l = new Lista<T>();

    	auxInOrden(n.izquierdo);
    	l.agregarAlFinal(n.elemento);
    	auxInOrden(n.derecho);

    	return l;
    }

    /**
     * Regresa una Cola con el los elementos en inorden del árbol.
     *
     * @return Cola con los elementos del arbol.
     */
    public Lista<T> preOrden() {
	//Aquí va tu código
    	return auxPreOrden(this.raiz);
    }

    /**
     * Método auxiliar para preOrden.
     */
    private Lista<T> auxPreOrden(Nodo n){
    	if(n == null){ return null; }

    	Lista<T> l = new Lista<T>();

    	l.agregarAlFinal(n.elemento);
    	auxPreOrden(n.izquierdo);
    	auxPreOrden(n.derecho);

    	return l;
    }

    /**
     * Regresa una Cola con el los elementos en inorden del árbol.
     *
     * @return Cola con los elementos del arbol.
     */
    public Lista<T> postOrden() {
	//Aquí va tu código
    	return auxPostOrden(this.raiz);
    }

    /**
     * Método auxiliar para preOrden.
     */
    private Lista<T> auxPostOrden(Nodo n){
    	if(n == null){ return null; }

    	Lista<T> l = new Lista<T>();

    	auxPostOrden(n.izquierdo);
    	auxPostOrden(n.derecho);
    	l.agregarAlFinal(n.elemento);

    	return l;
    }

    /**
     * Compara el árbol con un objeto.
     *
     * @param o el objeto con el que queremos comparar el árbol.
     * @return <code>true</code> si el objeto recibido es un árbol binario y los
     * árboles son iguales; <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        ArbolBinario<T> arbol = (ArbolBinario<T>) o;

        return true;
    }

    /**
     * Regresa una representación en cadena del árbol.
     *
     * @return una representación en cadena del árbol.
     */
    @Override
    public String toString() {

        if (raiz == null) {
            return "";
        }

        boolean[] r = new boolean[altura() + 1];
        for (int i = 0; i < altura() + 1; i++) {
            r[i] = false;
        }
        return cadena(raiz, 0, r);

    }

    private String cadena(Nodo v, int n, boolean[] r) {
        String s = v + "\n";
        r[n] = true;
        if (v.izquierdo != null && v.derecho != null) {
            s += dibujaEspacios(n, r);
            s += "├─›";
            s += cadena(v.izquierdo, n + 1, r);
            s += dibujaEspacios(n, r);
            s += "└─»";
            r[n] = false;
            s += cadena(v.derecho, n + 1, r);
        } else if (v.izquierdo != null) {
            s += dibujaEspacios(n, r);
            s += "└─›";
            r[n] = false;
            s += cadena(v.izquierdo, n + 1, r);
        } else if (v.derecho != null) {
            s += dibujaEspacios(n, r);
            s += "└─»";
            r[n] = false;
            s += cadena(v.derecho, n + 1, r);
        }
        return s;
    }

    private String dibujaEspacios(int n, boolean[] r) {
        String s = "";
        for (int i = 0; i < n; i++) {
            if (r[i]) {
                s += "│  ";
            } else {
                s += "   ";
            }
        }
        return s;
    }
}