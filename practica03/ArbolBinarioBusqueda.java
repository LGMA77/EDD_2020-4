import java.util.Iterator;

/**
 * <p>
 * Clase para modelar árboles binarios de búsqueda genéricos.</p>
 *
 * <p>
 * Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 * <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 * descendientes por la izquierda.</li>
 * <li>Cualquier elemento en el árbol es menor o igual que todos sus
 * descendientes por la derecha.</li>
 * </ul>
 *
 * @param <T>
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los nodos por profundidad (DFS). */
        private Pila<Nodo> pila;

        /* Construye un iterador con el nodo recibido. */
        public Iterador() {
        	pila = new Pila<Nodo>();

        	Nodo aux = raiz;

        	while(aux.izquierdo != null){
        		aux = aux.izquierdo;
        		pila.push(aux);
        	}
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
        	return pila.top() != null;
       	}

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override
        public T next() {
        	Nodo aux = pila.pop();
        	Nodo i;

        	if(aux.hayIzquierdo()){
        		i = aux.izquierdo;

        		while(i != null){
        			pila.push(i);
        			i = i.derecho;
        		}
        	}

        	return aux.elemento;
    	}

	}

    /**
     * Constructor que no recibe parámeteros. {@link ArbolBinario}.
     */
    public ArbolBinarioBusqueda() {
    	super();
    }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     *
     * @param coleccion la colección a partir de la cual creamos el árbol
     * binario ordenado.
     */
    public ArbolBinarioBusqueda(Coleccionable<T> coleccion) {
        super(coleccion);
    }

    /**
     * Método recursivo auxiliar que agrega un elemento contenido en el nodo nuevo.
     * Comienza las comparaciones desde el nodo n.
     *
     **/
    protected void agregaNodo(Nodo n, Nodo nuevo) {
		this.tamanio ++;

		if(this.raiz == null){
			this.raiz = nuevo;
		}

		else if(nuevo.elemento.compareTo(n.elemento) < 0){
				if(!n.hayIzquierdo()){
					n.izquierdo = nuevo;
					nuevo.padre = n;
				}else{
					agregaNodo(n.izquierdo, nuevo);
				}
		}else{
			if(!n.hayDerecho()){
				n.derecho = nuevo;
				nuevo.padre = n;
			}else{
				agregaNodo(n.derecho, nuevo);
			}
		}
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     *
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agregar(T elemento) {
        Nodo nuevo = nuevoNodo(elemento);
        agregaNodo(raiz, nuevo);
    }

    /**
     * Método auxiliar que elimina el nodo n. Notemos que en este punto
     * ya tenemos una referencia al nodo que queremos eliminar.
     **/
    protected Nodo eliminaNodo(Nodo n) {
		
		if(n == null){
            return null;
        }
        
        
        if(n == raiz){
            vaciar();
            return null;
        }
        
        Nodo padre = n.padre;
        Nodo izquierdo = n.izquierdo;
        Nodo derecho = n.derecho;
        
        
        if(izquierdo == null){

            if(padre != null){
                if(padre.izquierdo == n){
                    padre.izquierdo = derecho;
                }else{
                    padre.derecho = derecho;
                }
            }else{
                raiz = derecho;
            }

            if(derecho != null){
                derecho.padre = padre;
            }
            tamanio--;
            return padre;
        }

        else {

            if(padre != null){
                if(padre.izquierdo == n){
                    padre.izquierdo = izquierdo;
                }else{
                    padre.derecho = izquierdo;
                }
            }else{
                raiz = izquierdo;
            }

            if(izquierdo != null){
                izquierdo.padre = padre;
            }
            tamanio--;
            return padre;
        }
		
    }


    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     *
     * @param elemento el elemento a eliminar.
     */
    @Override
    public void eliminar(T elemento) {
        Nodo n = buscaNodo(raiz, elemento);
        eliminaNodo(n);
    }

    /**
     * Método que encuentra el elemento máximo en el subárbol izquierdo
     **/
    private Nodo maximoEnSubarbolIzquierdo(Nodo n) {
    	if(n.derecho == null){
    		return n;
    	}else{
    		return maximoEnSubarbolIzquierdo(n.derecho);
    	}
    }

    /**
     * Nos dice si un elemento está contenido en el arbol.
     *
     * @param elemento el elemento que queremos verificar si está contenido en
     * la arbol.
     * @return <code>true</code> si el elemento está contenido en el arbol,
     * <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene(T elemento) {
        return buscaNodo(raiz, elemento) != null;
    }

    /**
     * Método que busca un a elemento en el árbol desde el nodo n
     **/
    protected Nodo buscaNodo(Nodo n, T elemento) {
		if(n == null){
			return null;
		}
		if(n.elemento.equals(elemento)){
			return n;
		}else{
			Nodo izq = buscaNodo(n.izquierdo, elemento);
			Nodo der = buscaNodo(n.derecho, elemento);

			if(izq != null){
				return izq;
			}else{
				return der;
			}
		}
    }

    /**
     * Rota el árbol a la derecha sobre el nodo recibido. Si el nodo no tiene
     * hijo izquierdo, el método no hace nada.
     *
     * @param nodo el nodo sobre el que vamos a rotar.
     */
    protected void rotacionDerecha(Nodo nodo) {
		if(nodo.hayIzquierdo()){
			Nodo aux = nodo.izquierdo;
			nodo.padre = aux;
			aux.derecho = nodo;

			if(aux.hayDerecho()){
				nodo.izquierdo = aux.derecho;
			}
		}
    }

    /**
     * Rota el árbol a la izquierda sobre el nodo recibido. Si el nodo no tiene
     * hijo derecho, el método no hace nada.
     *
     * @param nodo el nodo sobre el que vamos a rotar.
     */
    protected void rotacionIzquierda(Nodo nodo) {
		if(nodo.hayDerecho()){
			Nodo aux = nodo.derecho;
			nodo.padre = aux;
			aux.izquierdo = nodo;

			if(aux.hayIzquierdo()){
				nodo.derecho = aux.izquierdo;
			}
		}
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     *
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

	public void pruebaRotacionIzquierda(T elemento){
        Nodo nodo = this.buscaNodo(raiz, elemento);
        this.rotacionIzquierda(nodo);
    }
    
    public void pruebaRotacionDerecha(T elemento){
        Nodo nodo = this.buscaNodo(raiz, elemento);
        this.rotacionDerecha(nodo);
    }

}