grammar newSintax;

@lexer::header {
package application;
}

@header {
package application;
import java.util.HashMap;
}

@members {
    HashMap<String, Integer> TSG = new HashMap<>();
    HashMap<String, Integer> TSL = new HashMap<>();
    HashMap<String, Integer> TSGMetodo = new HashMap<>();

    void pushTSG(String ID, String tipo) {
        Integer exist = TSG.get(ID);
        if (exist == null) {
            if (tipo.compareTo("int") == 0) TSG.put(ID, 1);
            else if (tipo.compareTo("double") == 0) TSG.put(ID, 2);
            else if (tipo.compareTo("class") == 0) TSG.put(ID, 3);
            else System.out.println("ID " + ID + " Tipo incorrecto");
        } else {
            System.out.println("ID " + ID + " ya declarado");
        }
    }

    void pushTSL(String ID, String tipo) {
        Integer exist = TSL.get(ID);
        if (exist == null) {
            if (tipo.compareTo("int") == 0) TSL.put(ID, 1);
            else if (tipo.compareTo("double") == 0) TSL.put(ID, 2);
            else if (tipo.compareTo("class") == 0) TSL.put(ID, 3);
        } else {
            System.out.println("ID " + ID + " ya declarado");
        }
    }

    void pushTSGMetodo(String ID, String tipo) {
        Integer exist = TSGMetodo.get(ID);
        if (exist == null) {
            if (tipo.compareTo("int") == 0) TSGMetodo.put(ID, 1);
            else if (tipo.compareTo("double") == 0) TSGMetodo.put(ID, 2);
        } else {
            System.out.println("ID " + ID + " ya declarado");
        }
    }
}

program  : clase+ ;

clase    : modificAcceso 'class' ID {
    pushTSG($ID.text, "class");
} 
'{'
    miembro*
'}';

miembro  : metodo | propiedad ;

propiedad : modificAcceso tipo ID (',' ID )* SEMICOLON ;

metodo   : modificAcceso tipo ID {
    pushTSGMetodo($ID.text, $tipo.text);
} 
'(' dec_args? ')'
'{'
    instruccion*
'}' 
{ TSL.clear(); };

instruccion : asignacion | declaracion_local;

asignacion  : ID EQUAL expr {
    System.out.println("expr: " + $expr.text + " tipo : " + $expr.tipo);
} SEMICOLON;

dec_args    : tipo ID (',' tipo ID)* ;

declaracion_local : tipo id1=ID {
    pushTSL($id1.text, $tipo.text);
} (',' id2=ID { pushTSL($id2.text, $tipo.text); })* SEMICOLON;

expr returns [int tipo] : m1=multExpr {
    $tipo = $m1.tipo;
} (('+'|'-') m2=multExpr)*;

multExpr returns [int tipo] : a1=atom {
    $tipo = $a1.tipo;
} ('*' a2=atom {
    if ($tipo != $a2.tipo) {
        $tipo = 3;
        System.out.println("error de tipo");
    }
})*;

atom returns [int tipo] : CINT {
    $tipo = 1;
} 
| CDOUBLE {
    $tipo = 2;
} 
| ID {
    // Aquí deberías agregar la lógica para ID si es necesario.
} 
| '(' expr {
    $tipo = $expr.tipo;
} ')';

modificAcceso : PUBLIC | PRIVATE | PROTECTED ;

tipo : INT | DOUBLE ;

INT     : 'int' ;
DOUBLE  : 'double' ;
STRING  : 'string' ;
CHAR    : 'char' ;
BOOLEAN : 'boolean' ;
PUBLIC  : 'public' ;
PRIVATE : 'private' ;
PROTECTED : 'protected' ;
SEMICOLON : ';' ;
EQUAL   : '=' ;
DOT     : '.' ;

CDOUBLE : CINT DOT CINT ;
CINT    : [0-9]+ ;
ID      : [a-zA-Z_][a-zA-Z0-9_]* ;
WS      : [ \t\r\n]+ -> skip ;