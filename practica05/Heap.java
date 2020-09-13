import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase abstracta para modelar montículos. Las clases concretas pueden ser un montículo mínimo
 * o máximo.
 */
public abstract class Heap<T extends Comparable<T>> implements Coleccionable<T> {

    /**
     * Clase interna para modelar el iterador
     */
    private class Iterador implements Iterator<T> {

        private int siguiente;

        @Override
        public boolean hasNext() {
            return siguiente < tamanio;
        }

        @Override
        public T next() {
            T elemento = arreglo[siguiente];
            siguiente++;
            return elemento;   
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * Arreglo donde se almacenarán los elementos del montículo.
     **/
    private T[] arreglo;
    /**
     * Cantidad de elementos almacenados en el montículo.
     **/
    private int tamanio;
    
    
    /* Truco para crear arreglos genéricos. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
    @SuppressWarnings("unchecked") private T[] creaArregloGenerico(int n) {
        return (T[])(new Comparable[n]);
    }

    /**
     * Constructor que no recibe parámetros, crea un arreglo de un tamaño arbitrario.
     * Se recomienda que sea de un tamaño que sea una potencia de 2.
     **/
    public Heap() {
	    arreglo = creaArregloGenerico(64);
    }

    /**
     * Constructor que recibe una estructura iterable como parámetro.
     * Agrega todos los elementos en el orden en que se recorre la estructura dada.
     **/
    public Heap(Iterable<T> it) {
        arreglo = creaArregloGenerico(64);
	    for(T elem : it){
            agregar(elem);
        }
    }
    
    /**
     * Método abstracto que se va a usar para comparar dos elementos del heap.
     * Se deja la implementación a las clases concretas, pues dependiendo de éstas el orden es
     * uno o el inverso, según sea el caso.
     * @param elemento1
     * @param elemento2
     * @return true si elemento1 tiene mayor prioridad que elemento2, false en otro caso
     */
    abstract protected boolean comparador(T elemento1,T elemento2);


    /**
     * Método que nos da la posición del padre del índice dado
     **/
    private int padre(int indiceElemento) {
        return (indiceElemento -1)/2;
    }

    /**
     * Método que nos da la posición del hijo izquierdo del índice dado
     **/
    private int izquierdo(int indiceElemento) {
        return (indiceElemento * 2)+1;
    }

    /**
     * Método que nos da la posición del hijo derecho del índice dado
     **/
    private int derecho(int indiceElemento) {
        return (indiceElemento * 2)+2;        
    }

    @Override
    public void agregar(T elemento) {
        if(tamanio == arreglo.length){
            crecerArreglo();
        }

        arreglo[tamanio] = elemento;
        rebalanceaHaciaArriba(tamanio);
        tamanio++;
    }

    private void crecerArreglo(){
        T[] arreglo2 = creaArregloGenerico(tamanio *2);

        for(int i = 0; i < arreglo.length; i++){
            arreglo2[i] = arreglo[i];
        }

        arreglo = arreglo2;
    }
    
    /**
     * Metodo para eliminar el elemento que se encuentra en el tope del heap.
     * El método devuelve el valor eliminado.
     */
    public T eliminarTope() {
        T eliminado = arreglo[0];
        intercambia(0, tamanio-1);
        tamanio --;
        rebalanceaHaciaAbajo(0);
        return eliminado;
    }

    /**
     * Método para intercambiar dos elementos en los índices i y j.
     * Antes de usarse debemos asegurarnos de que los índices sean válidos.
     **/
    private void intercambia(int i, int j) {
	    T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    /**
     * Metodo que se encarga de hacer el rebalanceo cuando agregamos un elemento.
     * @param indiceElemento
     */
    private void rebalanceaHaciaArriba(int indiceElemento) {
        int indicePadre = padre(indiceElemento);

        if(indicePadre >= 0){
            T padre = arreglo[indicePadre];
            T elemento = arreglo[indiceElemento];
            if(comparador(elemento, padre)){
                intercambia(indicePadre, indiceElemento);
                rebalanceaHaciaArriba(indicePadre);
            }
       }

    }

    /**
     * Metodo que se encarga de hacer el rebalanceo cuando eliminamos un elemento.
     * @param indiceElemento
     */
    private void rebalanceaHaciaAbajo(int indiceElemento) {
        int i = izquierdo(indiceElemento);
        int d = derecho(indiceElemento);
        int cambiable = this.indiceItercambiable(i, d);

        if(cambiable != -1 && comparador(arreglo[cambiable], arreglo[indiceElemento])){
            intercambia(cambiable, indiceElemento);
            rebalanceaHaciaAbajo(cambiable);
        }
    }

    /**
     * Método que nos dice cuál es el índice del elemento que tenemos que intercambiar con el padre.
     * Se utiliza en rebalanceaHaciaAbajo.
     * Si no hay que hacer intercambios porque ya no hay hijos, debe devolver -1.
     **/
    private int indiceItercambiable( int i, int j) {
        if(indiceValido(i) && indiceValido(j)){
            if(comparador(arreglo[i],arreglo[j])){
                return i;
            }else{
                return j;
            }
        }else if(!indiceValido(i) && indiceValido(j)){
            return j;
        }else if(indiceValido(i) && ! indiceValido(j)){
            return i;
        }else{
            return -1;
        }
    }

    private boolean indiceValido(int i){
        return (i < tamanio && i >= 0);
    }

    /**
     * Metodo para obtener el elemento que se encuentra en el tope del heap
     * @return
     * @throws NoSuchElementException
     */
    public T obtenerPrioritario() throws NoSuchElementException{
	    if(tamanio == 0){
            throw new NoSuchElementException();
        }

        return arreglo[0];
    }

    @Override
    public void eliminar(T elemento) throws NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contiene(T elemento) {
        boolean contiene = false;

	    for(T elem : arreglo){
            if(elemento.equals(elem)){
                contiene = true;
            }
        }

        return contiene;
    }

    @Override
    public boolean esVacia() {
	    if(tamanio == 0){
            return true;
        }

        return false;
    }

    @Override
    public int getTamanio() {
	   return tamanio;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Heap<T> heap = (Heap<T>) o;

        if(tamanio != heap.tamanio){
            return false;
        }
        
        for(int i = 0; i < tamanio; i++){
            if(! arreglo[i].equals(heap.arreglo[i])){
                return false;
            }
        }
        
        return true;
    }

    @Override
    public String toString() {
        if (esVacia()) {
            return "[]";
        }
        String s = "[";
        for (int i = 0; i < tamanio - 1; i++) {
            s += arreglo[i] + ", ";
        }
        s += arreglo[tamanio-1] + "]";

        return s;
    }

}