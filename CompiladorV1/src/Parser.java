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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    1,    1,    3,    3,    3,    3,    3,
    5,    5,    7,    7,    8,    4,    4,    2,    2,    2,
   10,   10,   11,   11,   11,   11,   14,   14,   14,   14,
   14,   12,   12,   17,   18,   18,   18,   18,   18,   18,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   13,
   13,   15,   15,   15,   15,   15,   15,    9,    9,    9,
    9,    9,   20,   20,   21,   21,   21,   21,   21,    6,
    6,    6,    6,   16,   16,   22,   22,   22,   23,   23,
   23,   24,   24,   24,   24,   24,
};
final static short yylen[] = {                            2,
    3,    3,    3,    2,    1,    3,    3,    3,    3,    1,
    3,    1,    1,    1,    1,    1,    1,    2,    2,    1,
    2,    1,    1,    1,    1,    1,    4,    3,    4,    4,
    4,    5,    3,    4,    4,    1,    4,    3,    4,    4,
    5,    4,    5,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    5,    4,    4,    5,    5,    6,    5,    5,
    6,    5,    2,    1,    3,    2,    3,    3,    1,    5,
    5,    5,    5,    1,    1,    3,    3,    1,    3,    3,
    1,    2,    1,    1,    2,    1,
};
final static short yydefred[] = {                         0,
    0,   17,   16,    0,    0,    0,    5,    0,   10,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   22,   23,
   24,   25,   26,    0,   84,   83,   86,    0,    0,   75,
    0,    0,    0,   81,    0,    0,    0,    4,    0,   12,
    0,    0,    0,    0,    0,    0,   74,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    3,    8,    0,
    0,   19,   21,    0,    0,    0,   82,   85,    0,    0,
    0,    0,    0,    2,    1,    9,    7,    6,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   15,    0,    0,   14,
   69,    0,   13,    0,   64,   11,    0,   33,    0,    0,
    0,    0,    0,   79,   80,   29,    0,    0,    0,    0,
    0,    0,    0,   34,   54,    0,   55,    0,    0,    0,
    0,    0,    0,   30,   31,   27,    0,    0,    0,    0,
    0,    0,   63,    0,   73,   71,   70,   72,   57,    0,
    0,    0,    0,    0,    0,   42,    0,   56,   53,   52,
   37,   39,   35,   40,   51,   50,   67,   60,   62,   59,
   68,   65,    0,   32,   49,   48,   47,   46,   41,   44,
   43,   45,   61,   58,
};
final static short yydgoto[] = {                          5,
    6,   15,    7,    8,   16,   30,  102,  103,   17,   18,
   19,   20,   21,   22,   23,   31,   24,   54,   46,  104,
  105,   47,   33,   34,
};
final static short yysindex[] = {                      -152,
  115,    0,    0,   13,    0,   78,    0, -180,    0,  -34,
   21,  -26, -134,  -89, -249,   -3,  122,  129,    0,    0,
    0,    0,    0, -190,    0,    0,    0,   40, -163,    0,
   35,   10,   91,    0,  -40,  -89, -239,    0,   41,    0,
   -4,   40, -160,   26,   48, -142,    0,   95, -164, -196,
  -54,   98,    0, -122,   40,   38,  -83,    0,    0, -118,
  122,    0,    0, -134,  106,    3,    0,    0,  -44,  -44,
  110,  -44,  -44,    0,    0,    0,    0,    0,    5,  141,
  -84,   54,   40,   40, -134,  -93,  144,  -39,  128,  105,
  131,   21,    9,  134,   22,   29,    0,  140,   75,    0,
    0, -158,    0, -113,    0,    0,  -69,    0,  -68,  -55,
   91,   91,  -51,    0,    0,    0,  150,   40,   40,   43,
   40,  238,  -38,    0,    0,  152,    0,  -50,  -31,  -34,
  -43,  -12,  -42,    0,    0,    0,   50,   -2,  -41,  153,
   52,  133,    0,  163,    0,    0,    0,    0,    0,  285,
  329,  212,  -33,  -19,   14,    0,   15,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   57,    0,    0,    0, -105,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   97,  -30,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   85,    0,    0,    0,    0,    0,    0,    0,
 -104,    0,    0,    0,    0,   97,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -106,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -13,    4,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -74,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   63,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   17,  255,  -32,   23,   33,    0,    0,  251,   62,
   25,    0,    0,    0,    0,   76,    0,  -28,  183,    0,
  185,   31,   65,  104,
};
final static int YYTABLESIZE=393;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
   29,  128,  157,  147,   69,   43,   70,  179,  160,   69,
   78,   70,   78,   49,   78,  163,  166,  170,   28,   20,
   18,  184,   37,   69,  100,   70,   58,   76,   78,   76,
   41,   76,    9,   57,   32,  107,   75,   53,    9,   60,
   60,   98,   63,  110,   77,   76,   77,   69,   77,   70,
   71,   69,   28,   70,   78,   59,  124,   29,   66,   55,
   44,  109,   77,  116,   69,   29,   70,  134,   43,   64,
   29,  100,   56,   99,   52,   39,   63,   69,   61,   70,
  136,   65,   29,   40,   29,   63,   45,   29,   53,  101,
   69,   87,   70,   60,   78,   60,   69,  140,   70,   76,
   12,   67,   88,    1,    2,   40,   80,   68,  167,   53,
  172,   76,   90,    3,   63,   12,   85,   79,  137,   82,
  142,   10,    4,   11,  141,   52,   12,   13,   77,   50,
   93,   95,   72,  111,  112,   86,  101,   73,   51,   74,
   92,   74,   96,    2,   11,  106,   52,   12,   13,   28,
   36,   28,    3,   28,   28,   28,   28,   28,  122,  123,
   97,    4,  125,   28,  108,   28,   55,   45,  113,   28,
   20,   18,   96,    2,   11,  114,  115,   12,   13,   56,
   36,  117,    3,  118,  126,   38,  129,  145,   38,  132,
   97,    4,  135,  150,  151,  153,  154,   38,  138,  139,
  146,   10,  144,   11,  148,  159,   12,   13,  149,   50,
  158,  171,  162,  165,  169,   89,  127,  156,  183,   25,
   26,  174,  178,   40,  161,   78,   27,   78,   42,   78,
   78,   78,   78,   78,   42,   74,  180,   78,   78,   78,
   48,   78,   76,  164,   76,   78,   76,   76,   76,   76,
   76,   77,  177,  168,   76,   76,   76,  173,   76,   77,
   38,   77,   76,   77,   77,   77,   77,   77,   62,  181,
  182,   77,   77,   77,  133,   77,   25,   26,  155,   77,
   69,   81,   70,   27,   25,   26,    0,    4,  143,   25,
   26,   27,   40,   94,    0,    4,   27,   42,  152,    0,
    4,   25,   26,   25,   26,    0,   25,   26,   27,  119,
   27,    0,    4,   27,    4,   83,   84,    4,   66,   66,
   66,  120,  121,   66,   66,  175,   66,   69,   66,   70,
    0,    0,    0,   35,    2,   11,   66,   66,   12,   13,
   22,   36,   22,    3,   36,   22,   22,   36,   22,    0,
    0,    0,    4,   10,   22,   11,   36,    0,   12,   13,
  130,   50,   11,    0,    0,   12,   13,   91,   50,  176,
   10,   69,   11,   70,  131,   12,   13,   10,   14,   11,
    0,    0,   12,   13,   10,   50,   11,    0,    0,   12,
   13,    0,   36,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   45,   41,   41,   59,   43,   40,   45,   41,   59,   43,
   41,   45,   43,   40,   45,   59,   59,   59,  125,  125,
  125,   59,    6,   43,   57,   45,  276,   41,   59,   43,
    8,   45,    0,  123,    4,   64,  276,   13,    6,   44,
   44,  125,   18,   41,   41,   59,   43,   43,   45,   45,
   41,   43,   40,   45,   59,   59,   85,   45,   28,  256,
   40,   59,   59,   59,   43,   45,   45,   59,   40,  260,
   45,  104,  269,   57,   13,  256,   52,   43,   17,   45,
   59,  272,   45,  264,   45,   61,   11,   45,   64,   57,
   43,  256,   45,   44,  125,   44,   43,  256,   45,   59,
   44,  265,  267,  256,  257,  264,  267,  271,   59,   85,
   59,  125,   51,  266,   90,   59,  259,   42,   96,   44,
  104,  256,  275,  258,  102,   64,  261,  262,  125,  264,
   55,   56,   42,   69,   70,   41,  104,   47,  273,   43,
  263,   45,  256,  257,  258,  264,   85,  261,  262,  256,
  264,  258,  266,  260,  261,  262,  263,  264,   83,   84,
  274,  275,  256,  270,   59,  272,  256,   92,   59,  276,
  276,  276,  256,  257,  258,   72,   73,  261,  262,  269,
  264,   41,  266,  268,   41,  260,   59,  256,  263,   59,
  274,  275,   59,  118,  119,  120,  121,  272,   59,  125,
  256,  256,  272,  258,  256,  256,  261,  262,   59,  264,
   59,   59,  256,  256,  256,  270,  256,  256,  256,  264,
  265,   59,  256,  264,  256,  256,  271,  258,  269,  260,
  261,  262,  263,  264,  269,  276,  256,  268,  269,  270,
  267,  272,  256,  256,  258,  276,  260,  261,  262,  263,
  264,  256,   41,  256,  268,  269,  270,  125,  272,  256,
    6,  258,  276,  260,  261,  262,  263,  264,   18,  256,
  256,  268,  269,  270,   92,  272,  264,  265,   41,  276,
   43,  256,   45,  271,  264,  265,   -1,  275,  104,  264,
  265,  271,  264,  256,   -1,  275,  271,  269,  256,   -1,
  275,  264,  265,  264,  265,   -1,  264,  265,  271,  256,
  271,   -1,  275,  271,  275,  268,  269,  275,  256,  257,
  258,  268,  269,  261,  262,   41,  264,   43,  266,   45,
   -1,   -1,   -1,  256,  257,  258,  274,  275,  261,  262,
  256,  264,  258,  266,  260,  261,  262,  263,  264,   -1,
   -1,   -1,  275,  256,  270,  258,  272,   -1,  261,  262,
  256,  264,  258,   -1,   -1,  261,  262,  270,  264,   41,
  256,   43,  258,   45,  270,  261,  262,  256,  264,  258,
   -1,   -1,  261,  262,  256,  264,  258,   -1,   -1,  261,
  262,   -1,  264,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=276;
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
"CTEENTERA","ENDIF","BEGIN","MY","TODOUBLE","FIN",
};
final static String yyrule[] = {
"$accept : programa",
"programa : declaraciones sentencias FIN",
"programa : declaraciones error FIN",
"programa : error sentencias FIN",
"declaraciones : declaraciones declaracion",
"declaraciones : declaracion",
"declaracion : tipo_basico lista_variables ';'",
"declaracion : tipo_basico lista_variables error",
"declaracion : error lista_variables ';'",
"declaracion : tipo_basico error ';'",
"declaracion : conversion",
"lista_variables : lista_variables ',' ID",
"lista_variables : ID",
"tipo : tipo_amb",
"tipo : tipo_basico",
"tipo_amb : MY",
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
"asignacion : ID ASIGNACION expresion",
"asignacion : error ASIGNACION expresion ';'",
"asignacion : ID error expresion ';'",
"asignacion : ID ASIGNACION error ';'",
"seleccion : seleccion_simple ELSE bloque ENDIF ';'",
"seleccion : seleccion_simple ENDIF ';'",
"seleccion_simple : IF condicion THEN bloque",
"bloque : BEGIN sentencias_ejecutables END ';'",
"bloque : sentencia",
"bloque : BEGIN END ';' error",
"bloque : BEGIN sentencias_ejecutables error",
"bloque : BEGIN sentencias_ejecutables END error",
"bloque : sentencias_ejecutables END ';' error",
"condicion : '(' expresion COMPARADOR expresion ')'",
"condicion : expresion ASIGNACION expresion error",
"condicion : expresion COMPARADOR expresion ')' error",
"condicion : '(' expresion ASIGNACION expresion error",
"condicion : expresion ASIGNACION expresion ')' error",
"condicion : '(' expresion COMPARADOR expresion error",
"condicion : '(' expresion COMPARADOR error ')'",
"condicion : '(' expresion error expresion ')'",
"condicion : '(' error COMPARADOR expresion ')'",
"iteracion : LOOP bloque UNTIL condicion ';'",
"iteracion : LOOP bloque UNTIL condicion error",
"print : PRINT '(' STRING ')' ';'",
"print : PRINT '(' STRING ')' error",
"print : PRINT STRING ')' error",
"print : PRINT '(' STRING error",
"print : PRINT '(' error ')' ';'",
"print : error '(' STRING ')' ';'",
"sentencia_ambito : ID '{' declaraciones_amb sentencias '}' ';'",
"sentencia_ambito : ID '{' sentencias '}' ';'",
"sentencia_ambito : ID '{' '}' ';' error",
"sentencia_ambito : ID '{' declaraciones_amb sentencias '}' error",
"sentencia_ambito : ID '{' sentencias '}' error",
"declaraciones_amb : declaraciones_amb declaracion_amb",
"declaraciones_amb : declaracion_amb",
"declaracion_amb : tipo lista_variables ';'",
"declaracion_amb : tipo lista_variables",
"declaracion_amb : error lista_variables ';'",
"declaracion_amb : tipo error ';'",
"declaracion_amb : conversion",
"conversion : TODOUBLE '(' expresion_simple ')' ';'",
"conversion : TODOUBLE '(' expresion_simple ')' error",
"conversion : TODOUBLE expresion_simple ')' ';' error",
"conversion : TODOUBLE '(' expresion_simple ';' error",
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

//#line 182 "Gramatica.y"

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
//#line 440 "Parser.java"
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
case 2:
//#line 34 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(4),"SINTACTICO");}
break;
case 3:
//#line 35 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(5),"SINTACTICO");}
break;
case 6:
//#line 43 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(31));}
break;
case 7:
//#line 44 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(6),"SINTACTICO");}
break;
case 8:
//#line 45 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(7),"SINTACTICO");}
break;
case 9:
//#line 46 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(8),"SINTACTICO");}
break;
case 27:
//#line 84 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(32));}
break;
case 28:
//#line 85 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 29:
//#line 86 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(10),"SINTACTICO");}
break;
case 30:
//#line 87 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(11),"SINTACTICO");}
break;
case 31:
//#line 88 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(12),"SINTACTICO");}
break;
case 32:
//#line 91 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(33));}
break;
case 33:
//#line 92 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(34));}
break;
case 35:
//#line 99 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(42));}
break;
case 36:
//#line 100 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(35));}
break;
case 37:
//#line 101 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(4),"SINTACTICO");}
break;
case 38:
//#line 102 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(13),"SINTACTICO");}
break;
case 39:
//#line 103 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 40:
//#line 104 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(14),"SINTACTICO");}
break;
case 41:
//#line 108 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(36));}
break;
case 42:
//#line 109 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(15),"SINTACTICO");}
break;
case 43:
//#line 110 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(16),"SINTACTICO");}
break;
case 44:
//#line 111 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(17),"SINTACTICO");}
break;
case 45:
//#line 112 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(16),"SINTACTICO");}
break;
case 46:
//#line 113 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(17),"SINTACTICO");}
break;
case 47:
//#line 114 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(12),"SINTACTICO");}
break;
case 48:
//#line 115 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(15),"SINTACTICO");}
break;
case 49:
//#line 116 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(12),"SINTACTICO");}
break;
case 50:
//#line 120 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(37));}
break;
case 51:
//#line 121 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 52:
//#line 124 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(39));}
break;
case 53:
//#line 125 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 54:
//#line 126 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(16),"SINTACTICO");}
break;
case 55:
//#line 127 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(17),"SINTACTICO");}
break;
case 56:
//#line 128 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(18),"SINTACTICO");}
break;
case 57:
//#line 129 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(19),"SINTACTICO");}
break;
case 58:
//#line 134 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(38));}
break;
case 59:
//#line 135 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(38));}
break;
case 60:
//#line 136 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(12),"SINTACTICO");}
break;
case 61:
//#line 137 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 62:
//#line 138 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 65:
//#line 146 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(40));}
break;
case 66:
//#line 147 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(6),"SINTACTICO");}
break;
case 67:
//#line 148 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(7),"SINTACTICO");}
break;
case 68:
//#line 149 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(8),"SINTACTICO");}
break;
case 70:
//#line 154 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(41));}
break;
case 71:
//#line 155 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 72:
//#line 156 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(16),"SINTACTICO");}
break;
case 73:
//#line 157 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(17),"SINTACTICO");}
break;
//#line 785 "Parser.java"
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
