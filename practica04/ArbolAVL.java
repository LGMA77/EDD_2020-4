/**
 * <p>
 * Clase para árboles AVL.</p>
 *
 * <p>
 * Un árbol AVL cumple que para cada uno de sus nodos, la diferencia entre la
 * áltura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 *
 * @param <T>
 */
public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

    /**
     * Clase interna protegida para nodos de árboles AVL. La única diferencia
     * con los nodos de árbol binario, es que tienen una variable de clase para
     * la altura del nodo.
     */
    protected class NodoAVL extends ArbolBinario.Nodo {

        /**
         * La altura del nodo.
         */
        protected int altura;

        /**
         * Constructor único que recibe un elemento.
         *
         * @param elemento el elemento del nodo.
         */
        public NodoAVL(T elemento) {
            // Aquí va su código.
        }
	
	/**
	 * Recomendamos usar este método auxiliar para que en el método público
	 * hagas el cast del objeto o, a NodoAVL y dejar el trabajo a este método.
  	 * Si no quieres usarlo, siéntete libre de eliminar esta firma.
	 */
        private boolean equals(NodoAVL v, NodoAVL v2) {
	    //Aquí va tu código.
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>.
         *
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         * {@link NodoAVL}, su elemento es igual al elemento de éste nodo, los
         * descendientes de ambos son recursivamente iguales, y las alturas son
         * iguales; <code>false</code> en otro caso.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (getClass() != o.getClass()) {
                return false;
            }
            @SuppressWarnings("unchecked")
            NodoAVL nodo = (NodoAVL) o;
            // Aquí va su código.
        }

        @Override
        public String toString() {
            String s = super.toString();
            return s += " alt=" + altura;
        }
    }

    public ArbolAVL() {

    }

    public ArbolAVL(Coleccionable<T> coleccion) {
        super(coleccion);
    }

    private void actualizaAltura(NodoAVL v) {
        // Aquí va su código.
    }

    private void rebalancea(NodoAVL nodo) {
        // Aquí va su código.
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioBusqueda#agrega}, y después balancea el árbol girándolo como
     * sea necesario. La complejidad en tiempo del método es <i>O</i>(log
     * <i>n</i>) garantizado.
     *
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agregar(T elemento) {
	// Aquí va su código.
    }

    /**
     * Elimina un elemento del árbol. El método elimina el nodo que contiene el
     * elemento, y gira el árbol como sea necesario para rebalancearlo. La
     * complejidad en tiempo del método es <i>O</i>(log <i>n</i>) garantizado.
     *
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override
    public void eliminar(T elemento) {
	// Aquí va su código.
    }

    private int getAltura(Nodo nodo) {
        // Aquí va su código.
    }

    /**
     * Convierte el nodo (visto como instancia de {@link
     * Nodo}) en nodo (visto como instancia de {@link
     * NodoAVL}). Método auxililar para hacer este cast en un único lugar.
     *
     * @param nodo el nodo de árbol binario que queremos como nodo AVL.
     * @return el nodo recibido visto como nodo AVL.
     */
    protected NodoAVL nodoAVL(Nodo nodo) {
        return (NodoAVL) nodo;
    }
}
