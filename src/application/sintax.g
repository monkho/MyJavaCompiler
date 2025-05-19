grammar sintax;

@lexer::header {
package application;
}

@header {
package application;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
}

@members {
  HashMap TSG = new HashMap();
  HashMap TSL = new HashMap();
  HashMap<String, Integer> calls = new HashMap<>();
  // Para BTA: Añadimos una tabla para almacenar si las variables son estáticas (1) o dinámicas (0)
  HashMap<String, Integer> staticVars = new HashMap<>();
  List<String> classess = new ArrayList<String>();
  String textOutput="";
  int contarError = 0;

  void pushTSG (String ID, String tipo) {
    Integer existe = (Integer) TSG.get(ID);
    if (existe == null) {
      if (tipo.compareTo("int")==0) TSG.put(ID, 1);
      else if (tipo.compareTo("double")==0) TSG.put(ID, 2);
      else if (tipo.compareTo("float")==0) TSG.put(ID, 3);
      else if (tipo.compareTo("class")==0) TSG.put(ID, 4);
      else if (tipo.compareTo("void")==0) TSG.put(ID, 5);
      // Por defecto, todas las variables son dinámicas al crearse
      staticVars.put(ID, 0);
    } else {
      System.out.println("ERROR! ID: " + ID + " already in TSG.");
      textOutput += "ERROR! ID: " + ID + " already in TSG.\n";
    }
  }

  void pushTSL (String ID, String tipo) {
    Integer existe = (Integer) TSL.get(ID);
    if (existe == null) {
      if (tipo.compareTo("int")==0) TSL.put(ID, 1);
      else if (tipo.compareTo("double")==0) TSL.put(ID, 2);
      else if (tipo.compareTo("float")==0) TSL.put(ID, 3);
      else if (tipo.compareTo("class")==0) TSL.put(ID, 4);
      // Por defecto, todas las variables son dinámicas al crearse
      staticVars.put(ID, 0);
    } else {
      System.out.println("ERROR! ID: " + ID + " already in TSL.");
      textOutput += "ERROR! ID: " + ID + " already in TSL.\n";
    }
  }

  void pushClass (String ID) {
    if (classess.contains(ID)) {
      System.out.println("ERROR! Class: " + ID + ", already declared");
    } else {
      classess.add(ID);
    }
  }

  void pushCall (String ID) {
    System.out.println("ID: " + ID + " found in TSG with type==> " + TSG.get(ID));
    calls.put(ID, (calls.get(ID)==null?1:calls.get(ID)+1));
  }

  void countCalls () {
    calls.forEach( (k, v) -> {
        if(v < 2) {
            System.out.println("WARNING: method: <<" + k + "()>> is only called once or never called");
            textOutput += "WARNING: method: <<" + k + "()>> is only called once or never called\n";
        }
        //System.out.println("Calls for function: " + k + "=" + v);
    });
  }

  Integer findId(String ID) {
    Integer existe = (Integer) TSL.get(ID);
    if (existe == null) {
      // System.out.println("ID: " + ID  + " is not in TSL");
      existe = (Integer) TSG.get(ID);
      
      if (existe == null) {
        System.out.println("ERROR! ID: " + ID + " not declared.");
        textOutput += "ERROR! ID: " + ID + " not declared.\n";
        return 12;
      } else {
        return existe;
      }
    } else {
      return existe;
    }
  }
  
// Detecta si una variable no está inicializada
  boolean isInitialized(String ID) {
    // Verificamos si la variable existe en nuestras tablas
    if(TSL.get(ID) != null || TSG.get(ID) != null) {
      // Verificamos si ya está incluida en la tabla de estáticos
      return staticVars.containsKey(ID);
    }
    return false;
  }
  
  // Para BTA: Verificar si un ID es estático (1) o dinámico (0)
  Integer isStatic(String ID) {
    Integer est = staticVars.get(ID);
    if (est == null) {
      return 0; // Por defecto es dinámico si no existe o no está inicializado
    }
    return est;
  }
  
  int checkTypes(int t1, int t2) {
    if(t1!=t2) {
      //textOutput += "WARNING! mismatch types\n";
      contarError ++;

      //System.out.println("WARNING! mismatch types. Errores contados: " + contarError);
      return 11;
    }
    return t2;
  }
  
  int checkTypes(int t1, int t2, String exp) {
    if(t1!=t2) {
      textOutput += "WARNING! mismatch types on expression: <<"+exp+">>\n";
      contarError ++;

      //System.out.println("WARNING! mismatch types. Errores contados: " + contarError);
      return 11;
    }
    return t2;
  }

  // Allows to take the exception and use it whenever it is needed
  public void displayRecoginitionError(String[] tokenNames, RecognitionException e) {
    String hdr = getErrorHeader(e);
    String msg = getErrorMessage(e, tokenNames);
    System.out.println("Found error!!: : " + hdr + " " + msg);
  }

  public void checkAsignacion(String id, int exprType) {
    int idType = findId(id);
    System.out.println("id tipo: " + idType + ", expr tipo: " + exprType);
  }
}

program  : clase+ { countCalls(); };

contar: CONTAR {
    System.out.println("Cantidad de errores de expresion: " + contarError);
    
    countCalls();
};

clase
    : modificAcceso? 'class' ID
    {
      pushClass($ID.text);
      pushTSG($ID.text, "class");
    }
    '{'
      miembro*
      metodo_principal
    '}'
    {
      TSG.clear();
      staticVars.clear();
    };

miembro
    : metodo
    | propiedad
    ;

// private? int a1, a2;
propiedad
    : modificAcceso? tipo id1=ID
    {
      pushTSG($id1.text, $tipo.text);
    }
    (',' id2=ID
      {
        pushTSG($id2.text, $tipo.text);
      }
    )* SEMICOLON
    ;

metodo
    : modificAcceso? tipo ID
    {
      pushTSG($ID.text, $tipo.text);
    } 
    '(' declaracion_args? ')' '{' instruccion* '}'
    {
      calls.put($ID.text, 0);
      TSL.clear();
      // Al salir del método, limpiamos también la información sobre variables estáticas locales
      // Las variables globales se mantienen entre métodos
    }
    ;

instruccion
    : asignacion
    | declaracion_local
    | llamada SEMICOLON
    ;

// a1 = 4;
asignacion
    : ID '=' expr SEMICOLON
    {
      String exp = $ID.text + " = " + $expr.text;
      checkTypes(findId($ID.text), $expr.t, exp);
      
      // BTA: Guardamos si la expresión es estática (1) o dinámica (0)
      staticVars.put($ID.text, $expr.isStatic);
      
      // BTA: Informamos si la asignación es reducible o no
      if ($expr.isStatic == 1) {
        System.out.println("REDUCIBLE EXPRESSION: " + exp + " (Type: " + $expr.t + ", Static: " + $expr.isStatic + ")");
        textOutput += "REDUCIBLE EXPRESSION: " + exp + " (Type: " + $expr.t + ", Static: " + $expr.isStatic + ")\n";
      } else {
        System.out.println("DYNAMIC EXPRESSION: " + exp + " (Type: " + $expr.t + ", Static: " + $expr.isStatic + ")");
        textOutput += "DYNAMIC EXPRESSION: " + exp + " (Type: " + $expr.t + ", Static: " + $expr.isStatic + ")\n";
      }
    }
    ;

// int x, y, z;
declaracion_local
    : tipo id1=ID
    {
      pushTSL($id1.text, $tipo.text);
    }
    (',' id2=ID
      {
        pushTSL($id2.text, $tipo.text);
      }
    )* SEMICOLON
    ;

// int x, int y, int z;
declaracion_args
    : tipo1=tipo id1=ID
    {
      pushTSL($id1.text, $tipo1.text);
      // Los argumentos de función son dinámicos por defecto
      staticVars.put($id1.text, 0);
    }
    (',' tipo2=tipo id2=ID
      {
        pushTSL($id2.text, $tipo2.text);
        // Los argumentos de función son dinámicos por defecto
        staticVars.put($id2.text, 0);
      }
    )*
    ;

llamada returns [int t, int isStatic=0]
    : ID '(' ')' /*SEMICOLON*/
    {
      pushCall($ID.text);
      $t = findId($ID.text);
      // Las llamadas a funciones siempre son dinámicas
      $isStatic = 0;
    }
    ;

expr returns[int t, int isStatic]
    : m1=multExpr
    { 
      $t=$m1.t; 
      $isStatic=$m1.isStatic;
    }
    (op=('+'|'-') m2=multExpr
      {
        $t = checkTypes($t, $m2.t);
        // Si alguno de los operandos es dinámico, toda la expresión es dinámica
        $isStatic = ($isStatic == 1 && $m2.isStatic == 1) ? 1 : 0;
      }
    )*
    ;

multExpr returns[int t, int isStatic]
    : a1=atom
    {
      $t=$a1.t;
      $isStatic=$a1.isStatic;
    }
    ('*' a2=atom
      {
        $t = checkTypes($t, $a2.t);
        // Si alguno de los operandos es dinámico, toda la expresión es dinámica
        $isStatic = ($isStatic == 1 && $a2.isStatic == 1) ? 1 : 0;
      }
    )*
    ;

atom returns[int t, int isStatic]
    : CINT 
    { 
      $t=1; 
      $isStatic=1; // Las constantes son siempre estáticas
    }
    | CFLOAT 
    { 
      $t=3; 
      $isStatic=1; // Las constantes son siempre estáticas
    }
    | CDOUBLE 
    { 
      $t=2; 
      $isStatic=1; // Las constantes son siempre estáticas
    }
    | ID 
    { 
      $t = findId($ID.text);
      // Una variable es estática solo si ya ha sido determinada como tal
      $isStatic = isStatic($ID.text);
      
      // Si la variable no fue inicializada explícitamente, es dinámica
      if (!isInitialized($ID.text)) {
        System.out.println("WARNING: Variable " + $ID.text + " not explicitly initialized, treating as dynamic");
        textOutput += "WARNING: Variable " + $ID.text + " not explicitly initialized, treating as dynamic\n";
        $isStatic = 0;
      }
    }
    | '(' e=expr
    {
      $t=$e.t;
      $isStatic=$e.isStatic;
    }
    ')'
    | llamada
    {
        $t = $llamada.t;
        $isStatic = 0;
    }
    ;

metodo_principal
    : 'public' 'void' 'main' '(' ')' '{' instruccion* '}'
    {
      TSG.clear();
      TSL.clear();
      staticVars.clear();
    }
    ;

modificAcceso
    : PUBLIC
    | PRIVATE
    | PROTECTED
    ;

tipo
    : INT
    | DOUBLE
    | FLOAT
    | VOID
    ;

CONTAR   : 'contar';
INT      : 'int'      ;
DOUBLE   : 'double'   ;
FLOAT    : 'float'    ;
VOID     : 'void'     ;
PUBLIC   : 'public'   ;
PRIVATE  : 'private'  ;
PROTECTED: 'protected';
SEMICOLON: ';';
DOT: '.';

ID: ('a' ..'z' | 'A' ..'Z' | '_')  ('a' ..'z' | 'A' ..'Z' | '_' | '0' ..'9' )*;
CFLOAT: CINT DOT CINT 'f';
CDOUBLE: CINT DOT CINT;
CINT: ('0'..'9')+;

WS :  (' ' | '\n' | '\t' | '\r' )+  {$channel=HIDDEN;} ;