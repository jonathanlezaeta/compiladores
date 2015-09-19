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






//#line 2 "Gramatica.y"
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
   10,   10,   10,   15,   16,   16,   16,   16,   17,   11,
   13,    7,    7,   18,   18,   19,   19,   19,   19,   19,
   20,   20,   21,    6,    6,    6,    6,    6,   14,   14,
   22,   22,   22,   23,   23,   23,   24,   24,   24,   24,
   24,
};
final static short yylen[] = {                            2,
    3,    3,    3,    2,    1,    3,    2,    3,    3,    1,
    3,    1,    1,    1,    2,    2,    1,    2,    1,    1,
    1,    1,    1,    4,    4,    4,    3,    5,    3,    5,
    5,    3,    3,    4,    3,    1,    3,    1,    5,    5,
    5,    6,    5,    2,    1,    3,    2,    3,    3,    1,
    1,    1,    1,    5,    5,    5,    5,    5,    1,    1,
    3,    3,    1,    3,    3,    1,    2,    1,    1,    2,
    1,
};
final static short yydefred[] = {                         0,
    0,   14,   13,    0,    0,    0,    5,    0,   10,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   19,   20,   21,   22,   23,    0,    0,    0,    0,
    0,    0,    4,    0,   12,    0,    0,    0,    0,    0,
    0,   38,    0,   36,    0,    0,    0,    0,    0,   69,
   68,   71,    0,   60,    0,    0,    0,   66,    2,    8,
    0,    0,   16,   18,    0,    0,    0,    0,    0,    3,
    1,    9,    6,    0,   59,    0,   33,    0,    0,    0,
    0,    0,    0,    0,    0,   53,    0,   51,   50,    0,
   45,    0,   52,   67,   70,    0,    0,    0,    0,    0,
   11,   32,    0,   29,    0,    0,    0,   26,    0,    0,
   34,    0,    0,   35,    0,   25,   24,   58,    0,    0,
    0,   44,    0,    0,    0,    0,   55,   64,   65,    0,
    0,   56,   57,   54,   31,    0,   41,   40,   48,   43,
    0,   49,   46,   30,   28,   39,   42,
};
final static short yydgoto[] = {                          5,
    6,   18,    7,    8,   19,   54,   20,   21,   22,   23,
   24,   25,   26,   55,   27,   45,   40,   90,   91,   92,
   93,   75,   57,   58,
};
final static short yysindex[] = {                      -176,
  -40,    0,    0,  -37,    0,  -95,    0, -233,    0, -183,
    5,   39,  -71, -173,   63,   18,   32, -152,   34,  -51,
 -128,    0,    0,    0,    0,    0, -222,   32,   32,   23,
 -173, -144,    0,   88,    0,   38,   32, -202,   32,  -91,
  -97,    0,  -51,    0,  -87,   32,   32, -190,  -39,    0,
    0,    0, -134,    0,  105,  131,    7,    0,    0,    0,
  -80,  -51,    0,    0,  118,  -71,  127,  147,  -36,    0,
    0,    0,    0,    3,    0,  -71,    0,  -41,  -71,  148,
  -61,    5,  -33,  155,   25,    0,   73,    0,    0, -190,
    0, -232,    0,    0,    0,  -20,  -20,  145,  -20,  -20,
    0,    0, -203,    0,  149,  153,  156,    0,  -66,   32,
    0,  158, -183,    0,  166,    0,    0,    0,   45,  170,
   89,    0,  180,   56,    7,    7,    0,    0,    0,  182,
  184,    0,    0,    0,    0,   78,    0,    0,    0,    0,
  190,    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   76,    0,    0,    0,    0,    0,    0,
 -119,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -83,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  108,  -30,    0,    0,    0,
    0, -116,    0,    0,    0,    0,    0,  108,  108,    0,
    0,    0,    0, -118,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  108,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -220,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -148,   -8,   14,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    8,  251,  -29,    9,   16,  237,    1,   26,    0,
    0,    0,    0,  -19,   -2,   46,  177,    0,  171,    0,
    0,   13,   59,   65,
};
final static int YYTABLESIZE=307;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         17,
   17,   96,   29,   97,  107,   17,   27,   38,   15,   96,
   63,   97,   63,   32,   63,    9,   36,   74,   88,   78,
   62,    9,   34,  123,   53,  117,   83,   38,   63,   56,
   35,   35,   61,   65,   61,   37,   61,   66,   44,   37,
   68,   69,   37,   81,   39,   96,   64,   97,   99,   67,
   61,   37,  130,  100,   62,   87,   62,   76,   62,   84,
   88,  108,   17,   89,   17,   85,    2,   11,  131,   77,
   12,   13,   62,   31,   11,    3,   53,   61,   41,    1,
    2,   61,   38,   86,    4,   37,   16,   64,   61,    3,
  136,   44,   60,  119,   63,   46,   73,  121,    4,   61,
  124,   44,   47,  139,   44,   89,   64,   47,   47,   47,
   38,  103,   47,   47,  143,   47,   61,   47,  146,   12,
   96,  109,   97,   59,  111,   47,   47,   10,   47,   11,
   94,   71,   12,   13,   12,   31,   95,   27,   62,   27,
   48,   27,   27,   27,   27,   27,   72,   96,   16,   97,
   59,   27,   59,   27,  125,  126,   17,   27,   27,   15,
   30,    2,   11,  128,  129,   12,   13,   79,   31,   80,
    3,   98,    7,    7,    7,   82,  102,    7,    7,    4,
    7,   16,    7,  101,   10,  104,   11,  105,  112,   12,
   13,    7,   31,    7,  113,  118,   11,  120,   42,   12,
   13,   43,   31,  127,   10,  135,   11,  132,  114,   12,
   13,  133,   31,  141,  134,   10,  137,   11,   28,  106,
   12,   13,  116,   14,  138,   63,  110,   63,  140,   63,
   63,   63,   63,   63,   15,   15,   16,   63,  142,   63,
  144,   63,  145,   50,   51,   63,   63,   61,  147,   61,
   52,   61,   61,   61,   61,   61,   33,   63,  115,   61,
  122,   61,    0,   61,    0,    0,    0,   61,   61,   62,
    0,   62,    0,   62,   62,   62,   62,   62,    0,    0,
   11,   62,   11,   62,    0,   62,   35,   49,   35,   62,
   62,   37,    0,   37,    0,   50,   51,   15,   70,   15,
    0,    0,   52,    0,    0,    0,    4,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   40,   43,   40,   45,   41,  125,  125,   10,  125,   43,
   41,   45,   43,    6,   45,    0,    8,   37,   48,   39,
   20,    6,  256,  256,   45,   59,   46,   30,   59,   17,
  264,  264,   41,  256,   43,  256,   45,  260,   13,  260,
   28,   29,  263,   43,   40,   43,   21,   45,   42,  272,
   59,  272,  256,   47,   41,   48,   43,  260,   45,   47,
   90,   59,   40,   48,   40,  256,  257,  258,  272,  272,
  261,  262,   59,  264,  258,  266,   45,   44,   40,  256,
  257,   44,   85,  274,  275,  269,  277,   62,   44,  266,
  110,   66,   59,   85,  125,  269,   59,   90,  275,   44,
   92,   76,   40,   59,   79,   90,   81,  256,  257,  258,
  113,   66,  261,  262,   59,  264,  125,  266,   41,   44,
   43,   76,   45,  276,   79,  274,  275,  256,  277,  258,
  265,  276,  261,  262,   59,  264,  271,  256,  125,  258,
  123,  260,  261,  262,  263,  264,   59,   43,  277,   45,
   43,  270,   45,  272,   96,   97,  276,  276,  277,  276,
  256,  257,  258,   99,  100,  261,  262,  259,  264,  267,
  266,   41,  256,  257,  258,  263,   59,  261,  262,  275,
  264,  277,  266,  264,  256,   59,  258,   41,   41,  261,
  262,  275,  264,  277,  256,   41,  258,  125,  270,  261,
  262,  273,  264,   59,  256,  272,  258,   59,  270,  261,
  262,   59,  264,  125,   59,  256,   59,  258,  256,  256,
  261,  262,  256,  264,   59,  256,  268,  258,   59,  260,
  261,  262,  263,  264,  275,  275,  277,  268,   59,  270,
   59,  272,   59,  264,  265,  276,  277,  256,   59,  258,
  271,  260,  261,  262,  263,  264,    6,   21,   82,  268,
   90,  270,   -1,  272,   -1,   -1,   -1,  276,  277,  256,
   -1,  258,   -1,  260,  261,  262,  263,  264,   -1,   -1,
  258,  268,  258,  270,   -1,  272,  264,  256,  264,  276,
  277,  269,   -1,  269,   -1,  264,  265,  275,  276,  275,
   -1,   -1,  271,   -1,   -1,   -1,  275,
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
"declaracion : tipo_basico lista_variables",
"declaracion : error lista_variables ';'",
"declaracion : tipo_basico error ';'",
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
"bloque : BEGIN sentencias_ejecutables END",
"bloque : sentencia",
"bloque : BEGIN sentencias_ejecutables error",
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

//#line 233 "Gramatica.y"

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
//#line 394 "Parser.java"
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
//#line 44 "Gramatica.y"
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
case 7:
//#line 53 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(7),"SINTACTICO");}
break;
case 8:
//#line 54 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(8),"SINTACTICO");}
break;
case 9:
//#line 55 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(9),"SINTACTICO");}
break;
case 11:
//#line 60 "Gramatica.y"
{ Vector<Token> tokens = (Vector<Token>)val_peek(2).obj;
									Token token = (Token)val_peek(0).obj;
									token.setTipo("int");
									tokens.add(token);
									yyval.obj = tokens;}
break;
case 12:
//#line 65 "Gramatica.y"
{Vector<Token> tokens = new Vector<Token>();
		Token token = (Token)val_peek(0).obj;
		token.setTipo("int");
		tokens.add(token);
		yyval.obj = tokens;}
break;
case 24:
//#line 93 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(31));}
break;
case 25:
//#line 94 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
case 26:
//#line 95 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(11),"SINTACTICO");}
break;
case 27:
//#line 96 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(12),"SINTACTICO");}
break;
case 28:
//#line 99 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(32));}
break;
case 29:
//#line 100 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(32));}
break;
case 30:
//#line 101 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(21),"SINTACTICO");}
break;
case 31:
//#line 102 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
case 32:
//#line 103 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(21),"SINTACTICO");}
break;
case 33:
//#line 104 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
case 35:
//#line 111 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(35));}
break;
case 36:
//#line 112 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(36));}
break;
case 37:
//#line 113 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(13),"SINTACTICO");}
break;
case 38:
//#line 114 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(14),"SINTACTICO");}
break;
case 40:
//#line 121 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(33));}
break;
case 41:
//#line 124 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(34));}
break;
case 42:
//#line 127 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(34));}
break;
case 46:
//#line 136 "Gramatica.y"
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
case 47:
//#line 145 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(7),"SINTACTICO");}
break;
case 48:
//#line 146 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(8),"SINTACTICO");}
break;
case 49:
//#line 147 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(9),"SINTACTICO");}
break;
case 54:
//#line 158 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(),analizador.getMensaje(37));}
break;
case 55:
//#line 159 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
case 56:
//#line 160 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(15),"SINTACTICO");}
break;
case 57:
//#line 161 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(16),"SINTACTICO");}
break;
case 58:
//#line 162 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(7),"SINTACTICO");}
break;
case 67:
//#line 179 "Gramatica.y"
{	String lexema = ((Token)val_peek(0).obj).getLexema();
							EntradaTS entrada = (EntradaTS)tabla.getTabla().get(lexema);
							String nuevoLexema = "-"+lexema;
							if (entrada.getContRef() == 1){
								if (tabla.getTabla().contains(nuevoLexema))
									((EntradaTS)tabla.getTabla().get(nuevoLexema)).incrementarCont();
								else {	EntradaTS nuevaEntrada = new EntradaTS(CTE, nuevoLexema);
										tabla.addETS(nuevaEntrada.getLexema(), nuevaEntrada);
										nuevaEntrada.setTipo("double");
								}
								tabla.getTabla().remove(lexema);
							}
							else {
								entrada.decContRef();
								if (tabla.getTabla().containsKey(nuevoLexema))
									((EntradaTS)tabla.getTabla().get(nuevoLexema)).incrementarCont();
								else{
									EntradaTS nuevaEntrada = new EntradaTS(CTE, nuevoLexema);
									tabla.addETS(nuevaEntrada.getLexema(), nuevaEntrada);
									nuevaEntrada.setTipo("double");
								}
							}
							yyval.obj = tabla.getTabla().get(nuevoLexema);
						}
break;
case 70:
//#line 205 "Gramatica.y"
{	String lexema = ((Token)val_peek(0).obj).getLexema();
							EntradaTS entrada = (EntradaTS)tabla.getTabla().get(lexema);
							String nuevoLexema = "-"+lexema;
							if (entrada.getContRef() == 1){
								if (tabla.getTabla().contains(nuevoLexema))
									((EntradaTS)tabla.getTabla().get(nuevoLexema)).incrementarCont();
								else {	EntradaTS nuevaEntrada = new EntradaTS(CTE, nuevoLexema);
										tabla.addETS(nuevaEntrada.getLexema(), nuevaEntrada);
										nuevaEntrada.setTipo("int");
								}
								tabla.getTabla().remove(lexema);
							}
							else {
								entrada.decContRef();
								if (tabla.getTabla().containsKey(nuevoLexema))
									((EntradaTS)tabla.getTabla().get(nuevoLexema)).incrementarCont();
								else{
									EntradaTS nuevaEntrada = new EntradaTS(CTE, nuevoLexema);
									tabla.addETS(nuevaEntrada.getLexema(), nuevaEntrada);
									nuevaEntrada.setTipo("int");
								}
							}
							yyval.obj = tabla.getTabla().get(nuevoLexema);
						}
break;
//#line 749 "Parser.java"
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
