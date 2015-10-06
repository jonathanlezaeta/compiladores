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
   19,   19,   19,   19,   19,   19,   13,   13,   15,   15,
   15,   15,   15,   15,    9,    9,    9,    9,    9,   20,
   20,   21,   21,   21,   21,   21,    6,    6,    6,    6,
   16,   16,   22,   22,   22,   23,   23,   23,   24,   24,
   24,   24,   24,
};
final static short yylen[] = {                            2,
    3,    3,    3,    2,    1,    3,    3,    3,    3,    1,
    3,    1,    1,    1,    1,    1,    1,    2,    2,    1,
    2,    1,    1,    1,    1,    1,    4,    3,    4,    4,
    4,    5,    3,    4,    4,    1,    4,    3,    4,    4,
    5,    4,    5,    5,    5,    5,    5,    5,    5,    5,
    4,    4,    5,    5,    6,    5,    5,    6,    5,    2,
    1,    3,    2,    3,    3,    1,    5,    5,    5,    5,
    1,    1,    3,    3,    1,    3,    3,    1,    2,    1,
    1,    2,    1,
};
final static short yydefred[] = {                         0,
    0,   17,   16,    0,    0,    0,    5,    0,   10,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   22,   23,
   24,   25,   26,    0,   81,   80,   83,    0,    0,   72,
    0,    0,    0,   78,    0,    0,    0,    4,    0,   12,
    0,    0,    0,    0,    0,    0,   71,    0,    0,    0,
    0,    0,   36,    0,    0,    0,    0,    3,    8,    0,
    0,   19,   21,    0,    0,    0,   79,   82,    0,    0,
    0,    0,    0,    2,    1,    9,    7,    6,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    0,    0,   14,   66,
    0,   13,    0,   61,   11,    0,   33,    0,    0,    0,
    0,    0,   76,   77,   29,    0,    0,    0,    0,    0,
   34,   51,    0,   52,    0,    0,    0,    0,    0,    0,
   30,   31,   27,    0,    0,    0,    0,    0,    0,   60,
    0,   70,   68,   67,   69,   54,    0,    0,    0,   42,
    0,   53,   50,   49,   40,   37,   39,   35,   48,   47,
   64,   57,   59,   56,   65,   62,    0,   32,   46,   41,
   44,   43,   45,   58,   55,
};
final static short yydgoto[] = {                          5,
    6,   15,    7,    8,   16,   30,  101,  102,   17,   18,
   19,   20,   21,   22,   23,   31,   24,   54,   46,  103,
  104,   47,   33,   34,
};
final static short yysindex[] = {                      -152,
  105,    0,    0,   13,    0,  -68,    0, -212,    0,  -34,
   21,  -26,   68,  -89, -199,   35,  114,  123,    0,    0,
    0,    0,    0, -200,    0,    0,    0,   29, -214,    0,
  102,   48,   91,    0,  -40,  -89, -194,    0,   77,    0,
   -4,   29, -181,   29,   30, -153,    0,   79, -202,   47,
 -213,   81,    0, -136,   29,   26,  -83,    0,    0, -123,
  114,    0,    0,   68,   94,  -18,    0,    0,  -44,  -44,
  104,  -44,  -44,    0,    0,    0,    0,    0,    5,  120,
   45,   29,   29,   68,  -94,  124,  -39,   88,  109,   98,
   21,   72,  110,   76,   38,    0,  117,   52,    0,    0,
 -124,    0, -165,    0,    0,  -88,    0,  -70,  -55,   91,
   91,  -69,    0,    0,    0,  136,   29,   29,  103,  -38,
    0,    0,  138,    0,  -50,  140,  -56,  -34,  -43,  -42,
    0,    0,    0,   41,  -54,  -41,  144,   54,   83,    0,
  145,    0,    0,    0,    0,    0,  -33,  -19,  -46,    0,
  -31,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -37,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   67,    0,    0,    0, -105,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  106,  -30,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -104,    0,    0,    0,    0,  106,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -106,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -13,
    4,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -135,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   61,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   19,  206,  -20,   23,   27,    0,    0,  198,   18,
  121,    0,    0,    0,    0,   25,    0,  -25,  131,    0,
  141,   34,   33,   87,
};
final static int YYTABLESIZE=387;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
   29,  125,  151,  144,   69,   43,   70,  170,  154,   69,
   75,   70,   75,   49,   75,  158,  160,  164,   28,   20,
   18,  175,  109,   69,   37,   70,    9,   73,   75,   73,
   41,   73,    9,   57,   61,   45,   99,   32,  106,   60,
  108,   97,   55,   39,   74,   73,   74,   69,   74,   70,
   67,   40,   28,   86,   78,   56,   68,   29,  121,   64,
   44,   66,   74,  115,   87,   29,   79,   88,   81,   90,
   29,   65,   69,   29,   70,   98,   58,   43,   60,   92,
   94,   75,   99,  100,   60,   80,   43,   69,   71,   70,
   95,    2,   11,   59,   75,   12,   13,   60,   36,  161,
    3,  110,  111,    1,    2,   84,  119,  120,   96,    4,
   12,   73,  166,    3,   69,   45,   70,  134,   69,   85,
   70,  139,    4,  138,   38,   12,   91,   38,   74,  100,
  131,  137,   72,   53,  133,   76,   38,   73,   63,   40,
  105,  147,  148,  149,   69,   69,   70,   70,   71,   28,
   71,   28,  107,   28,   28,   28,   28,   28,  113,  114,
  116,  122,  112,   28,  123,   28,   55,  127,  132,   28,
   20,   18,   95,    2,   11,  135,  136,   12,   13,   56,
   36,   63,    3,  141,   53,  142,  145,   35,    2,   11,
   96,    4,   12,   13,  146,   36,  152,    3,  155,  156,
  143,  162,  165,  168,   53,  153,    4,  167,   63,  172,
   63,   38,  157,  159,  163,   62,  124,  150,  174,   25,
   26,  130,  169,   40,  173,   75,   27,   75,   42,   75,
   75,   75,   75,   75,   42,   74,  171,   75,   75,   75,
   48,   75,   73,  140,   73,   75,   73,   73,   73,   73,
   73,   77,    0,    0,   73,   73,   73,    0,   73,   74,
    0,   74,   73,   74,   74,   74,   74,   74,    0,    0,
    0,   74,   74,   74,    0,   74,   25,   26,    0,   74,
    0,   93,    0,   27,   25,   26,    0,    4,    0,   25,
   26,   27,   25,   26,    0,    4,   27,   82,   83,   27,
    4,   40,   10,    4,   11,    0,   42,   12,   13,    0,
   51,    0,  117,  118,    0,   42,   63,   63,   63,    0,
    0,   63,   63,   50,   63,   11,   63,    0,   12,   13,
    0,   51,    0,    0,   63,   63,   10,    0,   11,    0,
   52,   12,   13,   10,   51,   11,    0,    0,   12,   13,
   89,   51,    0,  128,    0,   11,    0,  126,   12,   13,
   10,   51,   11,    0,    0,   12,   13,  129,   14,   10,
    0,   11,    0,    0,   12,   13,    0,   51,   10,    0,
   11,    0,    0,   12,   13,    0,   36,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   45,   41,   41,   59,   43,   40,   45,   41,   59,   43,
   41,   45,   43,   40,   45,   59,   59,   59,  125,  125,
  125,   59,   41,   43,    6,   45,    0,   41,   59,   43,
    8,   45,    6,  123,   17,   11,   57,    4,   64,   44,
   59,  125,  256,  256,   41,   59,   43,   43,   45,   45,
  265,  264,   40,  256,   59,  269,  271,   45,   84,  260,
   40,   28,   59,   59,  267,   45,   42,   50,   44,   52,
   45,  272,   43,   45,   45,   57,  276,   40,   44,   55,
   56,  276,  103,   57,   44,  267,   40,   43,   41,   45,
  256,  257,  258,   59,  125,  261,  262,   44,  264,   59,
  266,   69,   70,  256,  257,  259,   82,   83,  274,  275,
   44,  125,   59,  266,   43,   91,   45,   95,   43,   41,
   45,  103,  275,  101,  260,   59,  263,  263,  125,  103,
   59,  256,   42,   13,   59,   59,  272,   47,   18,  264,
  264,  117,  118,   41,   43,   43,   45,   45,   43,  256,
   45,  258,   59,  260,  261,  262,  263,  264,   72,   73,
   41,  256,   59,  270,   41,  272,  256,   59,   59,  276,
  276,  276,  256,  257,  258,   59,  125,  261,  262,  269,
  264,   61,  266,  272,   64,  256,  256,  256,  257,  258,
  274,  275,  261,  262,   59,  264,   59,  266,   59,  256,
  256,  256,   59,   59,   84,  256,  275,  125,   88,  256,
   90,    6,  256,  256,  256,   18,  256,  256,  256,  264,
  265,   91,  256,  264,  256,  256,  271,  258,  269,  260,
  261,  262,  263,  264,  269,  276,  256,  268,  269,  270,
  267,  272,  256,  103,  258,  276,  260,  261,  262,  263,
  264,  256,   -1,   -1,  268,  269,  270,   -1,  272,  256,
   -1,  258,  276,  260,  261,  262,  263,  264,   -1,   -1,
   -1,  268,  269,  270,   -1,  272,  264,  265,   -1,  276,
   -1,  256,   -1,  271,  264,  265,   -1,  275,   -1,  264,
  265,  271,  264,  265,   -1,  275,  271,  268,  269,  271,
  275,  264,  256,  275,  258,   -1,  269,  261,  262,   -1,
  264,   -1,  268,  269,   -1,  269,  256,  257,  258,   -1,
   -1,  261,  262,  256,  264,  258,  266,   -1,  261,  262,
   -1,  264,   -1,   -1,  274,  275,  256,   -1,  258,   -1,
  273,  261,  262,  256,  264,  258,   -1,   -1,  261,  262,
  270,  264,   -1,  256,   -1,  258,   -1,  270,  261,  262,
  256,  264,  258,   -1,   -1,  261,  262,  270,  264,  256,
   -1,  258,   -1,   -1,  261,  262,   -1,  264,  256,   -1,
  258,   -1,   -1,  261,  262,   -1,  264,
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
"bloque : error sentencias_ejecutables END ';'",
"condicion : '(' expresion COMPARADOR expresion ')'",
"condicion : expresion ASIGNACION expresion error",
"condicion : expresion COMPARADOR expresion ')' error",
"condicion : '(' expresion ASIGNACION expresion error",
"condicion : expresion ASIGNACION expresion ')' error",
"condicion : '(' expresion COMPARADOR expresion error",
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

//#line 179 "Gramatica.y"

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
//#line 432 "Parser.java"
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
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(8),"SINTACTICO");}
break;
case 9:
//#line 46 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(12),"SINTACTICO");}
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
case 34:
//#line 96 "Gramatica.y"
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
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(15),"SINTACTICO");}
break;
case 45:
//#line 112 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(15),"SINTACTICO");}
break;
case 46:
//#line 113 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(17),"SINTACTICO");}
break;
case 47:
//#line 117 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(37));}
break;
case 48:
//#line 118 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 49:
//#line 121 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(39));}
break;
case 50:
//#line 122 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 51:
//#line 123 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(16),"SINTACTICO");}
break;
case 52:
//#line 124 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(17),"SINTACTICO");}
break;
case 53:
//#line 125 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(18),"SINTACTICO");}
break;
case 54:
//#line 126 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(19),"SINTACTICO");}
break;
case 55:
//#line 131 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(38));}
break;
case 56:
//#line 132 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(38));}
break;
case 57:
//#line 133 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(12),"SINTACTICO");}
break;
case 58:
//#line 134 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 59:
//#line 135 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 60:
//#line 139 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(40));}
break;
case 61:
//#line 140 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(40));}
break;
case 62:
//#line 143 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(40));}
break;
case 63:
//#line 144 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(6),"SINTACTICO");}
break;
case 64:
//#line 145 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(8),"SINTACTICO");}
break;
case 65:
//#line 146 "Gramatica.y"
{manejador.error(analizador.getNroLinea(), analizador.getMensaje(12),"SINTACTICO");}
break;
case 67:
//#line 151 "Gramatica.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(41));}
break;
case 68:
//#line 152 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(6),"SINTACTICO");}
break;
case 69:
//#line 153 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(16),"SINTACTICO");}
break;
case 70:
//#line 154 "Gramatica.y"
{manejador.error(analizador.getNroLinea(),analizador.getMensaje(17),"SINTACTICO");}
break;
//#line 777 "Parser.java"
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
