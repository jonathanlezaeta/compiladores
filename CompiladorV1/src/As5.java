package src;

import java.util.Hashtable;

public class As5 extends AccionesSemantica { // Adiciona el simbolo leido y empaqueta el token.

    public static final Short COMPARADOR = 268;
    
    private Mensajes ms;
    private AnalizadorLexico al;
    private Hashtable <String, Short> simbolos;

    public As5(Mensajes m, AnalizadorLexico a){
        ms = m;
        al = a;
        simbolos = new Hashtable<String, Short>(); 
        simbolos.put(">=", COMPARADOR);
        simbolos.put("<=", COMPARADOR);
        simbolos.put("==", COMPARADOR);
    }

    public Token ejecutar(Token token, char c) {
        token.agregarCaracter(c);
        ms.token(al.getNroLinea(), token.getLexema());
        token.setId((Short)simbolos.get(token.getLexema()));
        return token;
    }

}
