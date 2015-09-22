//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "Gramatica2.y"
package src;
import java.util.Vector;
import java.util.Enumeration;
//#line 21 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short INT=257;
public final static short IF=258;
public final static short THEN=259;
public final static short ELSE=260;
public final static short PRINT=261;
public final static short LOOP=262;
public final static short UNTIL=263;
public final static short ID=264;
public final static short CTE=265;
public final static short DOUBLE=266;
public final static short STRING=267;
public final static short COMPARADOR=268;
public final static short ASIGNACION=269;
public final static short END=270;
public final static short CTEENTERA=271;
public final static short ENDIF=272;
public final static short BEGIN=273;
public final static short MY=274;
public final static short TODOUBLE=275;
public final static short FIN=276;
public final static short NOMBRE=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    1,    1,    3,    3,    3,    3,    3,
    5,    5,    4,    4,    2,    2,    2,    8,    8,    9,
    9,    9,    9,   12,   12,   12,   12,   10,   10,   10,
   10,   10,   10,   15,   16,   16,   16,   16,   16,   16,
   16,   17,   11,   13,    7,    7,   18,   18,   19,   19,
   19,   19,   19,   20,   20,   21,    6,    6,    6,    6,
    6,   14,   14,   22,   22,   22,   23,   23,   23,   24,
   24,   24,   24,   24,
};
final static short yylen[] = {                            2,
    3,    3,    3,    2,    1,    3,    3,    3,    3,    1,
    3,    1,    1,    1,    2,    2,    1,    2,    1,    1,
    1,    1,    1,    4,    4,    4,    3,    5,    3,    5,
    5,    3,    3,    4,    4,    3,    1,    3,    3,    2,
    1,    5,    5,    5,    6,    5,    2,    1,    3,    2,
    3,    3,    1,    1,    1,    1,    5,    5,    5,    5,
    5,    1,    1,    3,    3,    1,    3,    3,    1,    2,
    1,    1,    2,    1,
};
final static short yydefred[] = {                         0,
    0,   14,   13,    0,    0,    0,    5,    0,   10,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   19,   20,   21,   22,   23,    0,    0,    0,    0,
    0,    0,    4,    0,   12,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   72,
   71,   74,    0,   63,    0,    0,    0,   69,    2,    9,
    0,    0,   16,   18,    0,    0,    0,    0,    0,    3,
    1,    8,    7,    6,    0,   62,    0,   33,    0,    0,
    0,   40,    0,    0,    0,    0,    0,    0,    0,   56,
    0,   54,   53,    0,   48,    0,   55,   70,   73,    0,
    0,    0,    0,    0,   11,   32,    0,   29,    0,    0,
    0,   26,    0,    0,   34,    0,   36,    0,    0,   39,
    0,   25,   24,   61,    0,    0,    0,   47,    0,    0,
    0,    0,   58,   67,   68,    0,    0,   59,   60,   57,
   31,    0,   44,   35,   43,   51,   46,    0,   52,   49,
   30,   28,   42,   45,
};
final static short yydgoto[] = {                          5,
    6,   18,    7,    8,   19,   54,   20,   21,   22,   23,
   24,   25,   26,   55,   27,   45,   40,   94,   95,   96,
   97,   76,   57,   58,
};
final static short yysindex[] = {                      -187,
  -40,    0,    0,  -37,    0,  -73,    0, -220,    0, -169,
    9,   40,  -64, -176,   61,   30,   32, -158,   -5,  -88,
 -216,    0,    0,    0,    0,    0, -222,   32,   32,   23,
 -176, -121,    0,   97,    0,  -27,   32, -229,   32,  -94,
 -103,  113,   50,  -93,  -85,   32,   32, -153,  -39,    0,
    0,    0, -138,    0,   85,  134,   20,    0,    0,    0,
  -83,  -88,    0,    0,  127,  -64,  128,  149,  -36,    0,
    0,    0,    0,    0,   15,    0,  -64,    0,  -41,  -64,
  154,    0,  137,   57,  140,    9,  -33,  160,   25,    0,
   78,    0,    0, -153,    0, -180,    0,    0,    0,  -20,
  -20,  146,  -20,  -20,    0,    0, -181,    0,  148,  151,
  152,    0,  -60,   32,    0,  155,    0, -169,  156,    0,
  158,    0,    0,    0,   37,  166,   83,    0,  180,   48,
   20,   20,    0,    0,    0,  182,  184,    0,    0,    0,
    0,  106,    0,    0,    0,    0,    0,  190,    0,    0,
    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   53,    0,    0,    0,    0,    0,    0,
 -119,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -178,    0, -137,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  105,  -30,    0,    0,    0,
    0, -116,    0,    0,    0,    0,    0,  105,  105,    0,
    0,    0,    0,    0, -118,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  105,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -131,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -95,
   -8,   14,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    8,  207,  -28,   10,   16,  236,    4,    6,    0,
    0,    0,    0,  -16,   -2,   54,  172,    0,  165,    0,
    0,   24,   36,   11,
};
final static int YYTABLESIZE=327;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         17,
   17,  100,   29,  101,  111,   17,   27,   38,   15,  100,
   66,  101,   66,   32,   66,    9,   61,   36,   44,   92,
   75,    9,   79,   62,   53,  123,   64,   38,   66,   87,
   77,   74,   64,   65,   64,   34,   64,   66,   61,   10,
   56,   11,   78,   35,   12,   13,   84,   31,   39,   67,
   64,   68,   69,   60,   65,   91,   65,  100,   65,  101,
   16,  103,   17,   93,   17,   92,  104,   64,    1,    2,
   88,   44,   65,  112,  136,  129,   53,   41,    3,   41,
   61,   41,   44,   35,   41,   44,   38,    4,   11,   64,
  137,   61,   46,   41,   66,  146,   12,  142,  125,   37,
   47,  127,   89,    2,   11,  130,  150,   12,   13,   93,
   31,   12,    3,  134,  135,   38,   64,   59,   37,  107,
   90,    4,   37,   16,   38,   37,   98,  100,   38,  101,
  113,   38,   99,  115,   37,  131,  132,   27,   65,   27,
   38,   27,   27,   27,   27,   27,  153,   62,  100,   62,
  101,   27,   48,   27,   71,   72,   17,   27,   27,   15,
   50,   50,   50,   81,   80,   50,   50,   10,   50,   11,
   50,   82,   12,   13,  102,   31,   85,   86,   50,   50,
  105,   50,   30,    2,   11,  106,  108,   12,   13,  109,
   31,   10,    3,   11,  116,  117,   12,   13,  120,   31,
  124,    4,  126,   16,  133,   42,  138,  148,   43,  139,
  140,  141,   33,  143,  144,   10,  145,   11,   28,  110,
   12,   13,  122,   14,  147,   66,  114,   66,   73,   66,
   66,   66,   66,   66,   15,   15,   16,   66,  149,   66,
  151,   66,  152,   50,   51,   66,   66,   64,  154,   64,
   52,   64,   64,   64,   64,   64,   63,  121,  128,   64,
    0,   64,    0,   64,    0,    0,    0,   64,   64,   65,
    0,   65,    0,   65,   65,   65,   65,   65,    0,    0,
   11,   65,   11,   65,    0,   65,   35,   49,   35,   65,
   65,   37,    0,   37,    0,   50,   51,   15,   70,   15,
    0,    0,   52,    0,    0,   10,    4,   11,    0,    0,
   12,   13,  118,   31,   11,    0,    0,   12,   13,   83,
   31,    0,    0,    0,    0,    0,  119,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   40,   43,   40,   45,   41,  125,  125,   10,  125,   43,
   41,   45,   43,    6,   45,    0,   44,    8,   13,   48,
   37,    6,   39,   20,   45,   59,   21,   30,   59,   46,
  260,   59,   41,  256,   43,  256,   45,  260,   44,  256,
   17,  258,  272,  264,  261,  262,   43,  264,   40,  272,
   59,   28,   29,   59,   41,   48,   43,   43,   45,   45,
  277,   42,   40,   48,   40,   94,   47,   62,  256,  257,
   47,   66,   59,   59,  256,  256,   45,  256,  266,   40,
   44,  260,   77,  264,  263,   80,   89,  275,  258,   84,
  272,   44,  269,  272,  125,   59,   44,  114,   89,  269,
   40,   94,  256,  257,  258,   96,   59,  261,  262,   94,
  264,   59,  266,  103,  104,  118,  125,  276,  256,   66,
  274,  275,  260,  277,  256,  263,  265,   43,  260,   45,
   77,  263,  271,   80,  272,  100,  101,  256,  125,  258,
  272,  260,  261,  262,  263,  264,   41,   43,   43,   45,
   45,  270,  123,  272,  276,   59,  276,  276,  277,  276,
  256,  257,  258,  267,  259,  261,  262,  256,  264,  258,
  266,   59,  261,  262,   41,  264,  270,  263,  274,  275,
  264,  277,  256,  257,  258,   59,   59,  261,  262,   41,
  264,  256,  266,  258,   41,   59,  261,  262,   59,  264,
   41,  275,  125,  277,   59,  270,   59,  125,  273,   59,
   59,  272,    6,   59,   59,  256,   59,  258,  256,  256,
  261,  262,  256,  264,   59,  256,  268,  258,  256,  260,
  261,  262,  263,  264,  275,  275,  277,  268,   59,  270,
   59,  272,   59,  264,  265,  276,  277,  256,   59,  258,
  271,  260,  261,  262,  263,  264,   21,   86,   94,  268,
   -1,  270,   -1,  272,   -1,   -1,   -1,  276,  277,  256,
   -1,  258,   -1,  260,  261,  262,  263,  264,   -1,   -1,
  258,  268,  258,  270,   -1,  272,  264,  256,  264,  276,
  277,  269,   -1,  269,   -1,  264,  265,  275,  276,  275,
   -1,   -1,  271,   -1,   -1,  256,  275,  258,   -1,   -1,
  261,  262,  256,  264,  258,   -1,   -1,  261,  262,  270,
  264,   -1,   -1,   -1,   -1,   -1,  270,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=277;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"INT","IF","THEN","ELSE","PRINT","LOOP",
"UNTIL","ID","CTE","DOUBLE","STRING","COMPARADOR","ASIGNACION","END",
"CTEENTERA","ENDIF","BEGIN","MY","TODOUBLE","FIN","NOMBRE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : declaraciones sentencias FIN",
"programa : error sentencias FIN",
"programa : declaraciones error FIN",
"declaraciones : declaraciones declaracion",
"declaraciones : declaracion",
"declaracion : tipo_basico lista_variables ';'",
"declaracion : tipo_basico lista_variables error",
"declaracion : tipo_basico error ';'",
"declaracion : error lista_variables ';'",
"declaracion : conversion",
"lista_variables : lista_variables ',' ID",
"lista_variables : ID",
"tipo_basico : DOUBLE",
"tipo_basico : INT",
"sentencias : sentencia_ambito sentencias_ejecutables",
"sentencias : sentencias_ejecutables sentencia_ambito",
"sentencias : sentencias_ejecutables",
"sentencias_ejecutables : sentencias_ejecutables sentencia",
"sentencias_ejecutables : sentencia",
"sentencia : seleccion",
"sentencia : iteracion",
"sentencia : asignacion",
"sentencia : print",
"asignacion : ID ASIGNACION expresion ';'",
"asignacion : ID ASIGNACION expresion error",
"asignacion : error ASIGNACION expresion ';'",
"asignacion : error ASIGNACION expresion",
"seleccion : seleccion_simple ELSE bloque ENDIF ';'",
"seleccion : seleccion_simple ENDIF ';'",
"seleccion : seleccion_simple ELSE bloque error ';'",
"seleccion : error seleccion_simple ELSE bloque ENDIF",
"seleccion : seleccion_simple error ';'",
"seleccion : error seleccion_simple ENDIF",
"seleccion_simple : IF condicion THEN bloque",
"bloque : BEGIN sentencias_ejecutables END ';'",
"bloque : BEGIN END ';'",
"bloque : sentencia",
"bloque : BEGIN sentencias_ejecutables error",
"bloque : sentencia END ';'",
"bloque : END ';'",
"bloque : END",
"condicion : '(' expresion COMPARADOR expresion ')'",
"iteracion : LOOP bloque UNTIL condicion ';'",
"print : PRINT '(' STRING ')' ';'",
"sentencia_ambito : NOMBRE '{' declaraciones_amb sentencias '}' ';'",
"sentencia_ambito : NOMBRE '{' sentencias '}' ';'",
"declaraciones_amb : declaraciones_amb declaracion_amb",
"declaraciones_amb : declaracion_amb",
"declaracion_amb : tipo lista_variables ';'",
"declaracion_amb : tipo lista_variables",
"declaracion_amb : error lista_variables ';'",
"declaracion_amb : tipo error ';'",
"declaracion_amb : conversion",
"tipo : tipo_basico",
"tipo : tipo_amb",
"tipo_amb : MY",
"conversion : TODOUBLE '(' expresion_simple ')' ';'",
"conversion : error '(' expresion_simple ')' ';'",
"conversion : TODOUBLE error expresion_simple ')' ';'",
"conversion : TODOUBLE '(' expresion_simple error ';'",
"conversion : error TODOUBLE '(' expresion_simple ')'",
"expresion : expresion_simple",
"expresion : conversion",
"expresion_simple : expresion '+' termino",
"expresion_simple : expresion '-' termino",
"expresion_simple : termino",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : '-' CTE",
"factor : CTE",
"factor : ID",
"factor : '-' CTEENTERA",
"factor : CTEENTERA",
};

//#line 175 "Gramatica2.y"

void yyerror(String s) {
	if(s.contains("under"))
		System.out.println("par:"+s);
}

AnalizadorLexico analizador;
Mensajes manejador;
TablaSimbolos tabla;

public void setLexico(AnalizadorLexico al) {
	analizador = al;
	tabla = al.getTablaDeSimbolos();
}

public void setMensajes(Mensajes ms) {
	manejador = ms;
}

int yylex()
{
	int val = analizador.yylex();
	yylval = new ParserVal(analizador.getToken());
	yylval.ival = analizador.getNroLinea();
	
	return val;
}
//#line 404 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 6:
//#line 44 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(30));}
break;
case 7:
//#line 45 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(7),"SINTACTICO");}
break;
case 8:
//#line 46 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(9),"SINTACTICO");}
break;
case 9:
//#line 47 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(8),"SINTACTICO");}
break;
case 24:
//#line 78 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(31));}
break;
case 25:
//#line 79 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
case 26:
//#line 80 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(11),"SINTACTICO");}
break;
case 27:
//#line 81 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(12),"SINTACTICO");}
break;
case 28:
//#line 84 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(32));}
break;
case 29:
//#line 85 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(32));}
break;
case 30:
//#line 86 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(21),"SINTACTICO");}
break;
case 31:
//#line 87 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
case 32:
//#line 88 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(21),"SINTACTICO");}
break;
case 33:
//#line 89 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
case 35:
//#line 96 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(35));}
break;
case 36:
//#line 97 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(5),"SINTACTICO");}
break;
case 37:
//#line 98 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(36));}
break;
case 38:
//#line 99 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(13),"SINTACTICO");}
break;
case 39:
//#line 100 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(14),"SINTACTICO");}
break;
case 40:
//#line 101 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(14),"SINTACTICO");}
break;
case 41:
//#line 102 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(14),"SINTACTICO");}
break;
case 42:
//#line 106 "Gramatica2.y"
{System.out.println("Sentencia de condicion");}
break;
case 43:
//#line 109 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(33));}
break;
case 44:
//#line 112 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(34));}
break;
case 45:
//#line 115 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(34));}
break;
case 49:
//#line 124 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(30));
					Enumeration e = ((Vector<Token>)val_peek(1).obj).elements();
					while (e.hasMoreElements()){
					  Token token = (Token)e.nextElement();
					  if (token.getETS().getTipo() == null){
						token.getETS().setTipo(token.getTipo());
					  }
					}
					}
break;
case 50:
//#line 133 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(7),"SINTACTICO");}
break;
case 51:
//#line 134 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(8),"SINTACTICO");}
break;
case 52:
//#line 135 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(9),"SINTACTICO");}
break;
case 57:
//#line 146 "Gramatica2.y"
{manejador.estructuraSintactica(analizador.getNroLinea(),analizador.getMensaje(37));}
break;
case 58:
//#line 147 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
case 59:
//#line 148 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(15),"SINTACTICO");}
break;
case 60:
//#line 149 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(16),"SINTACTICO");}
break;
case 61:
//#line 150 "Gramatica2.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
//#line 697 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
