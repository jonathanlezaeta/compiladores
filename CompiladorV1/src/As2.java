package src;

public class As2 extends AccionesSemantica{ //Agrega el s�mbolo le�do al token recibido.

	@Override
	public Token ejecutar(Token token, char caracter) { 
		token.agregarCaracter(caracter);
		return token;
	}

}
