package src;

import java.util.Hashtable;

public class As4 extends AccionesSemantica{ // Empaqueta el token controlando la longitud sin consumir el s�mbolo le�do

	
		public static final Short IF = 258; 
	    public static final Short THEN = 259;
	    public static final Short ELSE = 260;
	    public static final Short PRINT = 261;
	    public static final Short DOUBLE = 266;
	    public static final Short BEGIN = 272;
	    public static final Short END = 263;
	    public static final Short ENDIF = 262;	    
	    public static final Short INT = 257;
	    public static final Short ID = 264;
	    public static final Short TODOUBLE = 270;
	    public static final Short MY = 271;
	    public static final Short LOOP = 272;
	    public static final Short UNTIL = 273;
	    
	    
	    private TablaSimbolos ts;
	    private Mensajes ms;
	    private AnalizadorLexico al;
	    private Hashtable<String, Short> palabrasReservadas;

	    private void verificarLongitudString(Token token) { // si pasa el limite lo trunca y warning 
		if (token.getLongitud() > 12) {
	            ms.warning("L�nea " + al.getNroLinea() + ": El identificador '" + token.getLexema() + "' ha sido truncado"); 
	            token.truncarId();
		}
	    }
	
	public As4(TablaSimbolos t, Mensajes m, AnalizadorLexico a){
        ts = t;
        ms = m;
        al = a;
        palabrasReservadas = new Hashtable<String, Short>();
        palabrasReservadas.put("if", IF); 
        palabrasReservadas.put("then", THEN);
        palabrasReservadas.put("else", ELSE);
        palabrasReservadas.put("print", PRINT);
        palabrasReservadas.put("double", DOUBLE);
        palabrasReservadas.put("begin", BEGIN);       
        palabrasReservadas.put("end", END);
        palabrasReservadas.put("endif", ENDIF);
        palabrasReservadas.put("int", INT);
        palabrasReservadas.put("todouble", TODOUBLE);
        palabrasReservadas.put("my", MY);
        palabrasReservadas.put("loop", LOOP);
        palabrasReservadas.put("until", UNTIL);
      
    }


	@Override
	public Token ejecutar(Token token, char caracter) {
		if (ts.contieneLexema(token.getLexema())){                       // Si el token es un lexema que ya esta agregado a la tabla de simbolos
            token.noSeAgregoCaracterLeido();                                  //Seteo que el �ltimo caracter le�do no fue agregado al token
            token.setEntradaTS(ts.getEntradaTS(token.getLexema()));
            ms.token(al.getNroLinea(), token.getLexema());
            return token;
        }
        else {
            if (!ts.esPalabraReservada(token.getLexema())){              // Si el token no es una palabra reservada
                verificarLongitudString(token);                             // Verifico la longitud del token
                token.setId(ID);                                            // Seteo el tipo como identificador
                ts.addETS(token.getLexema(), token.getETS());
            }
            else {
                token.setId((Short)palabrasReservadas.get(token.getLexema())); // devuelvo el token con la palabra reservada, sin guardarla en la tabla de simbolos
            }
            ms.token(al.getNroLinea(), token.getLexema());
            token.noSeAgregoCaracterLeido();                                  //Seteo que el �ltimo caracter le�do no fue agregado al token
            return token;
        }
	}

}
