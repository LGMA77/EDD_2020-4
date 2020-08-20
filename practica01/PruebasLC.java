public class PruebasLC{


	public static void pruebaConstructorCopiaPila(){
		System.out.println("-------------------------------------------");

		Pila<Integer> pila = new Pila<>();

		pila.push(4);
		pila.push(3);
		pila.push(2);
		pila.push(1);

		Pila<Integer> copia = new Pila<>(pila);

		String pilaString = pila.toString();
		String copiaString = copia.toString();

		System.out.println("Lo que hay: " + pilaString);
		System.out.println("Lo que queria: " + copiaString);

		if(pilaString.equals(copiaString) == true){
			System.out.println("Constructor copia pila correcto :)");
		}else{
			System.out.println("Constructor copia pila incorrecto :( ");
		}
		System.out.println("-------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");
	}

	public static void pruebaPush(){
		System.out.println("-------------------------------------------");

		Pila<Integer> pila = new Pila<>();
		pila.push(1);
		pila.push(2);
		pila.push(3);
		pila.push(4);

		String pilaString = pila.toString();
		System.out.println("Lo que hay: " + pilaString);

		String objetivo = "[4,3,2,1]";
		System.out.println("Lo que queria: " + objetivo);

		if(objetivo.equals(pilaString) != false){
			System.out.println("Push correcto :)");
		}else{
			System.out.println("Push incorrecto :( ");
		}

		System.out.println("-------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");
	}

	public static void pruebaPop(){
		System.out.println("-------------------------------------------");

		Pila<Integer> pila = new Pila<>();
		pila.push(1);
		pila.push(2);
		pila.push(3);
		pila.push(4);

		pila.pop();
		pila.pop();

		String pilaString = pila.toString();
		System.out.println("Lo que hay: " + pilaString);
		String objetivo = "[2,1]";
		System.out.println("Lo que queria: " + objetivo);

		if(objetivo.equals(pilaString) != false){
			System.out.println("Pop correcto :)");
		}else{
			System.out.println("Pop incorrecto :( ");
		}
		System.out.println("-------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");
	}

	public static void pruebaTop(){
		System.out.println("-------------------------------------------");

		Pila<Integer> pila = new Pila<>();
		pila.push(1);
		pila.push(2);
		pila.push(3);
		pila.push(4);

		Integer tope = pila.top();

		System.out.println("Lo que hay: " + tope);
		System.out.println("Lo que queria: " + 4);

		if(tope == 4){
			System.out.println("Top correcto :) ");
		}else{
			System.out.println("Top incorrecto :( ");
		}
		System.out.println("-------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");
	}

	public static void pruebaConstructorCopiaCola(){
		System.out.println("-------------------------------------------");

		Cola<Integer> cola = new Cola<>();
		cola.queue(1);
		cola.queue(2);
		cola.queue(3);
		cola.queue(4);

		Cola<Integer> aux = new Cola<>(cola);

		String colaString = cola.toString();
		String auxString = aux.toString();

		System.out.println("Lo que hay: " + colaString);
		System.out.println("Lo que queria: " + auxString);

		if(colaString.equals(auxString) == true){
			System.out.println("Constructor copia cola correcto :) ");
		}else{
			System.out.println("Constructor copia cola incorrecto :( ");
		}
		System.out.println("-------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");
	}

	public static void pruebaQueue(){
		System.out.println("-------------------------------------------");

		Cola<Integer> cola = new Cola<>();
		cola.queue(1);
		cola.queue(2);
		cola.queue(3);
		cola.queue(4);

		String colaString = cola.toString();
		System.out.println("Lo que hay: " + colaString);
		String objetivo = "[1,2,3,4]";
		System.out.println("Lo que queria: " + objetivo);

		if(colaString.equals(objetivo) == true){
			System.out.println("Queue correcto :)");
		}else{
			System.out.println("Queue incorrecto :(");
		}
		System.out.println("-------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");
	}

	public static void pruebaDequeue(){
		System.out.println("-------------------------------------------");

		Cola<Integer> cola = new Cola<>();
		cola.queue(1);
		cola.queue(2);
		cola.queue(3);
		cola.queue(4);

		cola.dequeue();
		cola.dequeue();

		String colaString = cola.toString();
		System.out.println("Lo que hay: " + colaString);
		String objetivo = "[3,4]";
		System.out.println("Lo que queria: "+ objetivo);

		if(colaString.equals(objetivo) == true){
			System.out.println("Dequeue correcto :)");
		}else{
			System.out.println("Dequeue incorrecto :(");
		}

		System.out.println("-------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");

	}

	public static void pruebaPeek(){
		System.out.println("-------------------------------------------");

		Cola<Integer> cola = new Cola<>();
		cola.queue(1);
		cola.queue(2);
		cola.queue(3);
		cola.queue(4);

		int visto = cola.peek();
		System.out.println("Lo que hay: " + visto);
		System.out.println("Lo que queria: " + 1);
		if(visto == 1){
			System.out.println("Peek correcto :)");
		}else{
			System.out.println("Peek incorrecto :(");
		}
		System.out.println("-------------------------------------------");
		System.out.println(" ");
		System.out.println(" ");
	}

	public static void main(String[] args) {
		pruebaConstructorCopiaPila();
		pruebaPush();
		pruebaPop();
		pruebaTop();
		pruebaConstructorCopiaCola();
		pruebaQueue();
		pruebaDequeue();
		pruebaPeek();
	}
}