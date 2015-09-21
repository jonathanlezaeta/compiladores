package src;

public class AnalizadorLexico {
	public static final int FIN = 276;

	private int nroLinea; // numero de linea
	private String codFuente; // codigo fuente tomado del panel de texto grafica
	private TablaSimbolos tablaSimb; // tabla de simbolos que contiene los
										// tokens
	private Mensajes msj; // lleva los msj a la interfaz
	private Token token; // token actual
	private int estado; // estado actual
	private int posicion; // posicion en el codigo fuente
	private AccionesSemantica as1, as2, as3, as4, as5, as6, as7, as8, as9,
			as10, as11, as12, as13; // acciones semanticas
	private boolean eof; // booleana que indica si se lleg� al final del archivo
	private boolean fin;
		 // | c | d | "d"-"D" | @ | + | - | / | * | > | < | = | (  | ) | _ | i | { | } | " | . | , | ; | otro | /n | tab | EOF
		//	  0   1       2     3   4   5   6   7   8   9   10  11  12  13  14   15  16  17  18  19  20   21    22    23    24    25
	//        l	  d	  "D" "d"	@	+	-	/	*	>	<	=	(	)	 _	 i	 "	 .	 ,	 {	 }	" "	   ;    otro   /n	 tab	EOF

	private int[][] matrizEstados = {
//			  0  1  2  3  4  5  6  7  8 9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
			{ 1, 3, 1, 2,-1,13,-1,-1,10,10,10,-1,-1,-1,1,11, 5,-1,-1,-1, 0,-1,-1, 0, 0, 0 },	// estado 0
			{ 1, 1, 1, 2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },	// estado 1
			{ 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },	// estado 2
			{-1, 3, 7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1, 6,-1,-1,-1,-1,-1,-1,-1,-1,-1 }, 	// estado 3
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },   // estado 4
			{-1, 6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },	// estado 5
			{-1, 6, 7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },	// estado 6
			{-1, 9,-1,-1, 8, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },	// estado 7
			{-1, 9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },	// estado 8
			{-1, 9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 }, 	// estado 9
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 }, 	// estado 10
			{11,11,11,11,11,11,11,11,11,11,11,11,11,11,11, 0,11,11,11,11,11,11,11,12,11,11 }, 	// estado 11
			{-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 }, 	// estado 12
			{-1,-1,-1,-1,-1,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },	// estado 13
			{14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14, 0,14,14 },}; // estado 14
	
	//	1	3	1	2	f	13	f	f	10	10	10	f	f	f	1	11	5	f	f	f	0	f	f	0	0	0
	//	1	1	1	2	f	f	f	f	f	f	f	f	f	f	1	f	f	f	f	f	f	f	f	f	f	f
	//	1	f	1	f	f	f	f	f	f	f	f	f	f	f	1	f	f	f	f	f	f	f	f	f	f	f
	//	f	3	7	f	f	f	f	f	f	f	f	f	f	4	f	f	6	f	f	f	f	f	f	f	f	f
	//	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f
	//	f	6	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f
	//	f	6	7	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f
	//	f	9	f	f	8	8	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f
	//	f	9	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f
	//	f	9	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f
	//	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f
	//	11	11	11	11	11	11	11	11	11	11	11	11	11	11	11	0	11	11	11	11	11	11	11	12	11	11
	//	f	f	f	f	f	11	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f
	//	f	f	f	f	f	14	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f	f
	//	14	14	14	14	14	14	14	14	14	14	14	14	14	14	14	14	14	14	14	14	14	14	14	0	14	14
	
	
	private AccionesSemantica[][] matrizAS;

	public AnalizadorLexico(String cf, Mensajes m) {
		tablaSimb = new TablaSimbolos();
		msj = m;
		this.codFuente = cf;
		this.nroLinea = 1;
		eof = false;
		fin = false;
		posicion = 0;
		inicializarAS();
	}

	public int yylex() { // devuelve el ID del token pedido por el parser
		this.estado = 0;
		this.token = new Token();
		char caracter;
		int simbolo;
		while (estado != -1 && !eof) {
			if (posicion == codFuente.length()){
				eof = true;
				System.out.println("voy con EOF");		
			    caracter = '\u00A0';
			    simbolo = getColumna(caracter);
			}else{
			    caracter = codFuente.charAt(posicion);
				System.out.println(caracter);
				simbolo = getColumna(caracter);
			}
			token = (matrizAS[estado][simbolo]).ejecutar(token, caracter);
			if (!token.consumioCaracter())
				posicion--;
			if (caracter == '\n' && token.consumioCaracter())
				nroLinea++;
			posicion++;
			
			System.out.println("ESTADO NUEVO: " + matrizEstados[estado][simbolo]);
			estado = matrizEstados[estado][simbolo];			
		}
		if(estado != -1){
			if (eof && !fin) { // Cuando llegamos al final del archivo
				msj.tablaDeSimbolos();
				fin = true;
				return FIN; // Primero devolvemos el "FIN"
			}
	
			if (eof && fin) // Cuando llegamos al final del archivo
				return 0; // Luego de devolver el "FIN" devolvemos el "0"
		}
		
		System.out.println("VALOR TOKEN: " + token.getId().intValue());
		return token.getId().intValue();
	}

	// retrocede la posici�n para releer un caracter
	public void releerCaracter() {
		posicion--;
	}

	// devuelve el n�mero de l�nea actual
	public int getNroLinea() {
		return this.nroLinea;
	}

	// devuelve el token actual
	public Token getToken() {
		return token;
	}

	// devuelve la tabla de simbolos
	public TablaSimbolos getTablaDeSimbolos() {
		return tablaSimb;
	}

	private void inicializarAS() {

		matrizAS = new AccionesSemantica[15][26];
		as1 = new As1();
		as2 = new As2();
		as3 = new As3(tablaSimb, this, msj);
		as4 = new As4(tablaSimb, msj, this);
		as5 = new As5(msj, this);
		as6 = new As6(msj, this);
		as7 = new As7();
		as8 = new As8(msj, this);
		as9 = new As9(msj, this, tablaSimb);
		as10 = new As10();
		as11 = new As11(msj, this);
		as12 = new As12(msj, this, tablaSimb);
		as13 = new As13(tablaSimb, this, msj);

		// Estado 0
		for (int i = 0; i <= 25; i++)
			matrizAS[0][i] = as11;
			matrizAS[0][0] = as1;
			matrizAS[0][1] = as1;
			matrizAS[0][2] = as1;
			matrizAS[0][3] = as1;
			matrizAS[0][4] = as8;
			matrizAS[0][5] = as1;
			matrizAS[0][6] = as8;
			matrizAS[0][7] = as8;
			matrizAS[0][8] = as1;
			matrizAS[0][9] = as1;
			matrizAS[0][10] = as1;
			matrizAS[0][11] = as8;
			matrizAS[0][12] = as8;
			matrizAS[0][14] = as1;			
			matrizAS[0][15] = as7;
			matrizAS[0][16] = as1;
			matrizAS[0][17] = as8;
			matrizAS[0][18] = as8;
			matrizAS[0][19] = as8;
			matrizAS[0][20] = as7;
			matrizAS[0][21] = as8;
			matrizAS[0][23] = as7;
			matrizAS[0][24] = as7;
			matrizAS[0][25] = as7;
		
		//Estado 1
		
		for (int i = 0; i <= 25; i++)
			matrizAS[1][i] = as4;
			matrizAS[1][0] = as2;
			matrizAS[1][1] = as2;
			matrizAS[1][2] = as2;
			matrizAS[1][3] = as2;
			matrizAS[1][14] = as2;
			
		// estado 2
		for (int i = 0; i <= 25; i++)
			matrizAS[2][i] = as11;
			matrizAS[2][0] = as2;
			matrizAS[2][2] = as2;
			matrizAS[2][14] = as2;

		// estado 3
		for (int i = 0; i <= 25; i++)
			matrizAS[3][i] = as11;
			matrizAS[3][1] = as2;
			matrizAS[3][2] = as2;
			matrizAS[3][13] = as2;
			matrizAS[3][16] = as2;

		// estado 4
		for (int i = 0; i <= 25; i++)
			matrizAS[4][i] = as11;
			matrizAS[4][14] = as13;

		// estado 5
		for (int i = 0; i <= 25; i++)
			matrizAS[5][i] = as11;
			matrizAS[5][1] = as2;

		// estado 6
		for (int i = 0; i <= 25; i++)
			matrizAS[6][i] = as3;
			matrizAS[6][1] = as2;
			matrizAS[6][2] = as2;

		// estado 7
		for (int i = 0; i <= 25; i++)
			matrizAS[7][i] = as11;
			matrizAS[7][1] = as2;
			matrizAS[7][4] = as2;
			matrizAS[7][5] = as2;

		// estado 8
		for (int i = 0; i <= 25; i++)
			matrizAS[8][i] = as11;
			matrizAS[8][1] = as2;

		// estado 9
		for (int i = 0; i <= 25; i++)
			matrizAS[9][i] = as3;
			matrizAS[9][1] = as2;

		// estado 10
		for (int i = 0; i <= 25; i++)
			matrizAS[10][i] = as6;
			matrizAS[10][20] = as6;

		// estado 11
		for (int i = 0; i <= 25; i++)
			matrizAS[11][i] = as7;

		// estado 12
		for (int i = 0; i <= 25; i++)
			matrizAS[12][i] = as11;
			matrizAS[12][5] = as7;

		// estado 13
		for (int i = 0; i <= 25; i++)
			matrizAS[13][i] = as6;
			matrizAS[13][5] = as2;
				
		// estado 14
		for (int i = 0; i <= 25; i++)
			matrizAS[14][i] = as7;
				
	}

	private int getColumna(int caracter) { // obtiene la columna en la que est�
											// cada caracter en la matriz de
											// estados
		// comprueba que llegue una letra
		if ((caracter >= 65 && caracter <= 90)
				|| (caracter >= 97 && caracter <= 122))
			if ((caracter != 'D') && (caracter != 'd') && (caracter != 'i')) // B o b se trata en
														// otra columna
				return 0; // Retorna columna de letras
		if (caracter >= 48 && caracter <= 57)
			return 1; // Retorna columna de digitos
		if ((caracter == 'D') || (caracter == 'd'))
			return 2;
		if (caracter == '@')
			return 3;
		if (caracter == '+')
			return 4;
		if (caracter == '-')
			return 5;
		if (caracter == '/')
			return 6;
		if (caracter == '*')
			return 7;
		if (caracter == '>')
			return 8;
		if (caracter == '<')
			return 9;
		if (caracter == '=') 
			return 10;
		if (caracter == '(')
			return 11;
		if (caracter == ')')
			return 12;
		if (caracter == '_')
			return 13;
		if (caracter == 'i')
			return 14;
		if (caracter == '"')
			return 15;
		if (caracter == '.')
			return 16;
		if (caracter == ',')
			return 17;
		if (caracter == '{')
			return 18;
		if (caracter == '}')
			return 19;
		if (caracter == 32)
			return 20; // ESPACIO BLANCO 
		if (caracter == ';')
			return 21;	
		if (caracter == '\n')
			return 23;			
		if (caracter == 9)
			return 24;					
		if (caracter == 255) {
			eof = true;
			return 25; // FIN DE ARCHIVO
		}
		return 22;	
	}

	public String getMensaje(int nro) {
		switch (nro) {
		// ERRORES LEXICOS
		case 1:
			return "Constante double fuera del rango permitido";
		case 2:
			return "Car�cter no identificado";
		case 3:
			return "Construcci�n de token err�neo";
		case 20:
			return "Constante entero fuera de rango permitido";

			// ERRORES SINTACTICOS
		case 4:
			return "No se encontr� el fin de archivo";
		case 5:
			return "Falta el bloque de sentencias ejecutables";
		case 6:
			return "Falta el bloque de sentencias declarativas";
		case 7:
			return "Se esperaba un ';'";
		case 8:
			return "Falta el tipo de la declaraci�n";
		case 9:
			return "Sentencia declarativa incorrecta";
		case 11:
			return "Falta el identificador de la asignaci�n";
		case 12:
			return "Falta el identificador de la asignaci�n y se esperaba un ';'";
		case 13:
			return "Bloque de sentencias sin finalizar falta '}'";
		case 14:
			return "Bloque de sentencias sin inicializar falta '{'";
		case 15:
			return "Falta abrir par�ntesis '('";
		case 16:
			return "Falta cerrar par�ntesis ')'";
		case 17:
			return "Par�metro del imprimir incorrecto";
		case 18:
			return "Falta palabra reservada 'imprimir'";
		case 19:
			return "Sentencia incorrecta";

			// ESTRUCTURAS SINTACTICAS
		case 30:
			return "Sentencia declarativa";
		case 31:
			return "Sentencia de asignaci�n";
		case 32:
			return "Sentencia de selecci�n";
		case 33:
			return "Sentencia de iteraci�n";
		case 34:
			return "Sentencia de impresi�n de caracteres";
		case 35:
			return "Bloque de sentencias";
		case 37:
			return "Sentencia declaracion de vector";
		case 38:
			return "Falta abrir corchetes '['";
		case 39:
			return "Falta cerrar corchetes ']'";
		case 40:
			return "Falta declarar los dos puntos ..";
		case 41:
			return "Falta agregar el tipo";
		case 42:
			return "Falta agregar el tipo 'vector'";
		case 43:
			return "Asignacion de vector";
		case 44:
			return "Falta agregar la expresion en la asignacion del vector";
		case 45:
			return "Error en el token de asgnacion se espera un ':='";

		}
		return null;
	}

	void setCodigoFuente(String codigoFuente){
		this.codFuente = codigoFuente;
	}
	
}
