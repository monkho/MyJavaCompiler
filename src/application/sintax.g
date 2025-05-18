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
  
  int checkTypes(int t1, int t2) {
    if(t1!=t2) {
      textOutput += "WARNING! mismatch types\n";
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
    }
    ;

instruccion
    : asignacion
    | declaracion_local
    | llamada
    ;

// a1 = 4;
asignacion
    : ID '=' expr SEMICOLON
    {
      String exp = $ID.text + '=' + $expr.text;
      checkTypes(findId($ID.text), $expr.t, exp);
      
      checkAsignacion($ID.text, $expr.t);
      //System.out.println("\tExpression: " + $expr.text + " of type: " + $expr.t);
      // textOutput += "\tExpression: " + $expr.text + " of type: " + $expr.t + "\n";
    }
    | ID '=' llamada
    {
      String exp = $ID.text + '=' + $llamada.text;
      checkTypes(findId($ID.text), $llamada.t, exp);
      
      checkAsignacion($ID.text, $llamada.t);
      //System.out.println("\tExpression: " + $llamada.text + " of type: " + $llamada.t);
      // textOutput += "\tExpression: " + $llamada.text + " of type: " + $llamada.t + "\n";
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
    }
    (',' tipo2=tipo id2=ID
      {
        pushTSL($id2.text, $tipo2.text);
      }
    )*
    ;

llamada returns [int t]
    : ID '(' ')' SEMICOLON
    {
      pushCall($ID.text);
      $t = findId($ID.text);
    }
    ;

expr returns[int t]
    : m1=multExpr
    { $t=$m1.t; }
    (('+'|'-' ) m2=multExpr
      {
        $t = checkTypes($t, $m2.t);
      }
    )*
    ;

multExpr returns[int t]
    : a1=atom
    {
      $t=$a1.t;
    }
    ('*' a2=atom
      {
        $t = checkTypes($t, $a2.t);
      }
    )*
    ;

atom  returns[int t]
    : CINT { $t=1; }
    | CFLOAT { $t=3; }
    | CDOUBLE { $t=2; }
    | ID 
    { /* search ID in TSL, if found, return its type 
        if not found in TSL, search in TSG, if not found
        print "Not declared variable", and return 11, because
        it can be embeded in another expresion
      */
      $t = findId($ID.text);
    }
    | '(' expr
    {
      $t=$expr.t;
    }
    ')'
    ;

metodo_principal
    : 'public' 'void' 'main' '(' ')' '{' instruccion* '}'
    {
      TSG.clear();
      TSL.clear();
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
// WS : [ \t\n\r]+ -> skip ;