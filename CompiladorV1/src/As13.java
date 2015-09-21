package src;

public class As13 extends AccionesSemantica { // Empaqueta el token controlando el rango de un Entero y sin consumir el ultimo caracter

	public static final Short CONSTANTE = 265; 
	public final static short CTEENTERO = 271;
	private TablaSimbolos ts;
	private AnalizadorLexico al;
	private Mensajes ms;
	

	public As13(TablaSimbolos ts, AnalizadorLexico al, Mensajes ms) {
		this.ts = ts;
		this.al = al;
		this.ms = ms;
	}


	@Override
	public Token ejecutar(Token token, char caracter) {
		
		token.setId(CTEENTERO);
		String aux = token.getLexema().replace("_","");
		Double e = Double.valueOf(aux);
		token.setLexema(aux);
		if(e> Short.MIN_VALUE && e< Short.MAX_VALUE ) { 
			if(ts.contieneLexema(token.getLexema())) {
				ts.getEntradaTS(token.getLexema()).incrementarCont();
				token.setEntradaTS(ts.getEntradaTS(token.getLexema()));
			}
			else {
				token.setId(CTEENTERO);
				ts.addETS(token.getLexema(), token.getETS());
				ts.getEntradaTS(token.getLexema()).setTipo("int");
			}
			
		}
		else {
			ms.error(al.getNroLinea(), al.getMensaje(20), "LEXICO"); 
		}
		token.seAgregoCaracterLeido();
		return token;
	}
}
