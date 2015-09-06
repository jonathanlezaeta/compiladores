package src;

public class As1 extends AccionesSemantica { // Inicializa un token y agrega el s�mbolo le�do.

	@Override
	public Token ejecutar(Token token, char caracter) {
		   token = new Token();
	       token.agregarCaracter(caracter);
	       return token;
	}

}
