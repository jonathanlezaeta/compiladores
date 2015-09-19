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






//#line 2 "GramaticaSinErrores.y"
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
    0,    1,    1,    3,    3,    5,    5,    4,    4,    2,
    2,    2,    8,    8,    9,    9,    9,    9,   12,   10,
   10,   15,   16,   16,   17,   11,   13,    7,    7,   18,
   18,   19,   19,   20,   20,   21,    6,   14,   14,   22,
   22,   22,   23,   23,   23,   24,   24,   24,   24,   24,
};
final static short yylen[] = {                            2,
    3,    2,    1,    3,    1,    3,    1,    1,    1,    2,
    2,    1,    2,    1,    1,    1,    1,    1,    4,    5,
    3,    4,    3,    1,    5,    5,    5,    6,    5,    2,
    1,    3,    1,    1,    1,    1,    5,    1,    1,    3,
    3,    1,    3,    3,    1,    2,    1,    1,    2,    1,
};
final static short yydefred[] = {                         0,
    9,    8,    0,    0,    0,    3,    0,    5,    0,    0,
    0,    0,    0,    0,    0,    2,    0,    0,   14,   15,
   16,   17,   18,    0,    7,    0,   48,   47,   50,    0,
   39,    0,    0,    0,   45,    0,    0,    0,    0,   24,
    0,    0,    0,    1,    0,   11,   13,    0,    0,    4,
    0,   46,   49,    0,    0,    0,    0,    0,    0,   38,
    0,    0,    0,    0,    0,   36,    0,   34,   33,    0,
   31,    0,   35,    0,   21,    6,    0,    0,   37,   43,
   44,    0,   22,    0,   23,    0,   19,    0,    0,   30,
    0,    0,    0,   27,   26,   29,    0,   32,   20,   25,
   28,
};
final static short yydgoto[] = {                          4,
    5,   15,    6,    7,   26,   31,   17,   18,   19,   20,
   21,   22,   23,   32,   24,   41,   37,   70,   71,   72,
   73,   60,   34,   35,
};
final static short yysindex[] = {                      -196,
    0,    0,   17,    0, -206,    0, -224,    0,  -45,   23,
   34, -186, -179,  -32, -167,    0, -162, -223,    0,    0,
    0,    0,    0, -178,    0,   18,    0,    0,    0, -228,
    0,   61,   56,   38,    0,  -45, -161, -156, -162,    0,
 -151,  -45, -230,    0, -162,    0,    0, -186,   54,    0,
 -150,    0,    0,  -33,  -33,   57,  -33,  -33,  -35,    0,
 -186,   74, -169,   23,   22,    0,   -8,    0,    0, -230,
    0, -224,    0, -154,    0,    0,   38,   38,    0,    0,
    0,  -45,    0,   60,    0,   63,    0,   64,   -5,    0,
   29,   66,   62,    0,    0,    0,   67,    0,    0,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -112,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   65,  -40,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -111,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -39,  -34,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  -28,  116,  -21,   52,   16,  109,    9,    5,    0,
    0,    0,    0,  -18,    0,  -15,   68,    0,   58,    0,
    0,  120,  -25,   26,
};
final static int YYTABLESIZE=238;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         30,
   42,   40,   42,   40,   42,   40,   41,   54,   41,   55,
   41,   30,   12,   10,   67,    8,   40,   59,   42,   40,
    8,   68,   47,   65,   41,   45,    1,   10,   77,   78,
   11,   12,   74,   13,   10,    2,   52,   11,   12,   25,
   13,   89,   53,   66,    3,   83,   14,   63,   68,   47,
    1,   10,   40,   14,   11,   12,    9,   13,   69,    2,
    1,   51,   36,   93,   54,   40,   55,   47,    3,    2,
   14,   10,   51,   38,   11,   12,   50,   13,    3,   57,
   87,   48,   80,   81,   58,   69,   39,   98,   10,   42,
   43,   11,   12,   49,   13,   10,   56,   61,   11,   12,
   85,   13,  100,   54,   54,   55,   55,   38,   44,   38,
   62,   64,   75,   76,   84,   79,   88,   92,   94,   97,
   16,   95,   96,   91,   99,  101,   46,   90,   33,    0,
    0,   86,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   12,   10,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   27,   28,
    0,    0,    0,    0,    0,   29,    0,   42,   40,    3,
   27,   28,   82,   41,    0,    0,    0,   29,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         45,
   41,   41,   43,   43,   45,   45,   41,   43,   43,   45,
   45,   45,  125,  125,   43,    0,   12,   36,   59,   59,
    5,   43,   18,   42,   59,   17,  257,  258,   54,   55,
  261,  262,   48,  264,  258,  266,  265,  261,  262,  264,
  264,   70,  271,  274,  275,   61,  277,   39,   70,   45,
  257,  258,   48,  277,  261,  262,   40,  264,   43,  266,
  257,   44,   40,   82,   43,   61,   45,   63,  275,  266,
  277,  258,   44,   40,  261,  262,   59,  264,  275,   42,
   59,  260,   57,   58,   47,   70,  273,   59,  258,  269,
  123,  261,  262,  272,  264,  258,   41,  259,  261,  262,
  270,  264,   41,   43,   43,   45,   45,   43,  276,   45,
  267,  263,   59,  264,   41,   59,  125,  272,   59,  125,
    5,   59,   59,   72,   59,   59,   18,   70,    9,   -1,
   -1,   64,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  276,  276,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  264,  265,
   -1,   -1,   -1,   -1,   -1,  271,   -1,  268,  268,  275,
  264,  265,  268,  268,   -1,   -1,   -1,  271,
};
}
final static short YYFINAL=4;
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
"declaraciones : declaraciones declaracion",
"declaraciones : declaracion",
"declaracion : tipo_basico lista_variables ';'",
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
"seleccion : seleccion_simple ELSE bloque ENDIF ';'",
"seleccion : seleccion_simple ENDIF ';'",
"seleccion_simple : IF condicion THEN bloque",
"bloque : BEGIN sentencias_ejecutables END",
"bloque : sentencia",
"condicion : '(' expresion COMPARADOR expresion ')'",
"iteracion : LOOP bloque UNTIL condicion ';'",
"print : PRINT '(' STRING ')' ';'",
"sentencia_ambito : NOMBRE '{' declaraciones_amb sentencias '}' ';'",
"sentencia_ambito : NOMBRE '{' sentencias '}' ';'",
"declaraciones_amb : declaraciones_amb declaracion_amb",
"declaraciones_amb : declaracion_amb",
"declaracion_amb : tipo lista_variables ';'",
"declaracion_amb : conversion",
"tipo : tipo_basico",
"tipo : tipo_amb",
"tipo_amb : MY",
"conversion : TODOUBLE '(' expresion_simple ')' ';'",
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

//#line 212 "GramaticaSinErrores.y"

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

//#line 342 "Parser.java"
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
case 4:
//#line 42 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(30));
					Enumeration e = ((Vector<Token>)val_peek(1).obj).elements();
					while (e.hasMoreElements()){
					  Token token = (Token) e.nextElement();
					  if(token.getETS().getTipo() == null){
						  token.getETS().setTipo(token.getTipo());
					  }
					}
					}
break;
case 6:
//#line 55 "GramaticaSinErrores.y"
{ Vector<Token> tokens = (Vector<Token>)val_peek(2).obj;
									Token token = (Token)val_peek(0).obj;
									token.setTipo("int");
									tokens.add(token);
									yyval.obj = tokens;}
break;
case 7:
//#line 60 "GramaticaSinErrores.y"
{Vector<Token> tokens = new Vector<Token>();
		Token token = (Token)val_peek(0).obj;
		token.setTipo("int");
		tokens.add(token);
		yyval.obj = tokens;}
break;
case 19:
//#line 88 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(31));}
break;
case 20:
//#line 91 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(32));}
break;
case 21:
//#line 92 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(32));}
break;
case 23:
//#line 99 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(35));}
break;
case 24:
//#line 100 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(36));}
break;
case 26:
//#line 107 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(33));}
break;
case 27:
//#line 110 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(34));}
break;
case 28:
//#line 113 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(34));}
break;
case 32:
//#line 122 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(), analizador.getMensaje(30));
					Enumeration e = ((Vector<Token>)val_peek(1).obj).elements();
					while (e.hasMoreElements()){
					  Token token = (Token) e.nextElement();
					  if (token.getETS().getTipo() == null){
						token.getETS().setTipo(token.getTipo());
					  }
					}
					}
break;
case 37:
//#line 141 "GramaticaSinErrores.y"
{manejador.estructuraSintactica(analizador.getNroLinea(),analizador.getMensaje(37));}
break;
case 46:
//#line 158 "GramaticaSinErrores.y"
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
case 49:
//#line 184 "GramaticaSinErrores.y"
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
//#line 621 "Parser.java"
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
