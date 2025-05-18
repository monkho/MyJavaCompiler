grammar sintax;

@header {
  package compiler.antlr;
  import java.util.List;
  import java.util.ArrayList;
  import java.util.HashMap;
}

@members {
  HashMap TSG = new HashMap();
  HashMap TSL = new HashMap();
  List<String> metodos = new ArrayList<String>();

  void pushMetodo (String ID) {
    if (metodos.contains(ID)) {
      System.out.println("Method: " + ID + " already declared.");
    } else {
      metodos.add(ID);
    }
  }

  void pushTSG (String ID, String tipo) {
    Integer existe = (Integer) TSG.get(ID);
    if (existe == null) {
      if (tipo.compareTo("int")==0) TSG.put(ID, 1);
      else if (tipo.compareTo("double")==0) TSG.put(ID, 2);
      else if (tipo.compareTo("float")==0) TSG.put(ID, 3);
      else if (tipo.compareTo("class")==0) TSG.put(ID, 4);
    } else {
      System.out.println("ID: " + ID + " already in TSG.");
    }
  }

  void pushTSL (String ID, String tipo) {
    Integer existe = (Integer) TSL.get(ID);
    if (existe == null) {
      if (tipo.compareTo("int")==0) TSL.put(ID, 1);
      else if (tipo.compareTo("double")==0) TSL.put(ID, 2);
      else if (tipo.compareTo("float")==0) TSG.put(ID, 3);
      else if (tipo.compareTo("class")==0) TSL.put(ID, 4);
    } else {
      System.out.println("ID: " + ID + " already in TSL.");
    }
  }

  Integer findId(String ID) {
    Integer existe = (Integer) TSL.get(ID);
    if (existe == null) {
      existe = (Integer) TSG.get(ID);
      if (existe == null) {
        System.out.println("ID: " + ID + " not declared.");
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
      System.out.println("Error! mismatch types");
      return 11;
    }
    return t2;
  }
}

program  : clase+;

clase: modificAcceso? 'class' ID {pushTSG($ID.text, "class");} '{' 
        miembro* 
      '}';

miembro: metodo | propiedad;

propiedad:
    modificAcceso? tipo ID (',' ID)* SEMICOLON;

metodo:
    modificAcceso? tipo ID {pushMetodo($ID.text);} '(' declaracion_args? ')' '{' instruccion* '}' {TSL.clear();};

instruccion  : asignacion | declaracion_local;
asignacion   : ID '=' expr SEMICOLON 
                { System.out.println("Expr: " + $expr.text + " Type: " + $expr.t); };

// int x, y, z;
declaracion_local:
  tipo id1 = ID {pushTSL($id1.text, $tipo.text);} (
    ',' id2 = ID {pushTSL($id2.text, $tipo.text);}
  )* SEMICOLON;

// int x, int y, int z;
declaracion_args: tipo1=tipo id1=ID{pushTSL($id1.text, $tipo1.text);} 
                    (',' tipo2=tipo id2=ID{pushTSL($id2.text, $tipo2.text);})*;

expr returns[int t]    : m1=multExpr { $t=$m1.t; }
                          (('+'|'-' ) m2=multExpr { $t=$m2.t; })*;
multExpr returns[int t]: a1=atom { $t=$a1.t; } 
                          ('*' a2=atom { $t = checkTypes($t, $a2.t); } )*;
atom  returns[int t]   : CINT { $t=1; } |
                        CFLOAT { $t=3; } |
                        CDOUBLE { $t=2; } |
                        ID { /* search ID in TSL, if found, return its type 
                                if not found in TSL, search in TSG, if not found
                                print "Not declared variable", and return 12, because
                                it can be embeded in another expresion*/
                              $t = findId($ID.text);
                            } |
                        '(' expr { $t=$expr.t; } ')';

modificAcceso: PUBLIC | PRIVATE | PROTECTED ;
tipo         : INT | DOUBLE | FLOAT | VOID;

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
 
WS : [ \t\n\r]+ -> skip ;