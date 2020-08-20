public class Balanceados{


	public static boolean esBalanceado(String cadena){

		Pila<Character> pila = new Pila<>();	

		try{

			for(int i = 0; i < cadena.length(); i++){
				char c = cadena.charAt(i);
				if(c == '('){
					pila.push(c);
				}else{ 
					if(c == ')' && ( pila.top() != '(' || pila.esVacia() )){
						return false;
					}else{
						pila.pop();
					}
				}
			}	

		}catch(Exception e){
			return false;
		}


		@SuppressWarnings("unchecked") Lista<Character> lista = (Lista<Character>) pila;

		if(lista.esVacia() == true){
			return true;
		}else{
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(Balanceados.esBalanceado("(())") );
		System.out.println(Balanceados.esBalanceado(")()("));
		System.out.println(Balanceados.esBalanceado("()()()"));
		System.out.println(Balanceados.esBalanceado(")))"));
		System.out.println(Balanceados.esBalanceado("((("));
		System.out.println(Balanceados.esBalanceado("()((()()))"));
	}

}