grammar zfd;
@header{
package com.bigzhao.antlr;
}

code : element* ;

element : (configDef | typeDef | fieldDef | structDef )';'?;

fieldDef: NAME (NAME|structBody) expression* ('[' (expression|INFINITE) ']')?;

typeDef : '@type' NAME className;

structDef: '@type' NAME structBody;

structBody: '{' code '}';

configDef
:'@config' configDefLine                # configDefSingleLine
|'@config' '{' configDefLine* '}'      # configDefBlock
;
configDefLine:  NAME ('='|':') (STRINGLIT|WORD)';';


className: NAME(('.'|'$')NAME)*;

value: NUMBER | ref | STRINGLIT;
ref : '$'refSeg | ref'.'refSeg;

refSeg: NAME('['NUMBER']')?;

WS:[\r\n\t ]+ {skip();};
NAME: [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER: [0-9]+('.'[0-9]*)? | '0x'[0-9A-Fa-f]+;
WORD: [a-zA-Z0-9_]+;
STRINGLIT: '"'(~'"'|'\\"')*'"';
INFINITE: '*';


expression
 :value															# ValueExpression
 |'+' expression												# UnaryPlusExpression
 | '-' expression												# UnaryMinusExpression
 | '~' expression										        # BitNotExpression
 | '!' expression										        # LogicalNotExpression
 | expression ( '*' | '/' | '%' ) expression				# MultiplicativeExpression
 | expression ( '+' | '-' ) expression						# AdditiveExpression
 | expression ( '<<' | '>>' ) expression					# BitShiftExpression
 | expression ( '<' | '>' | '<=' | '>=' ) expression		# RelationalExpression
 | expression ( '==' | '!=' ) expression	                # EqualityExpression
 | expression '&' expression									# BitAndExpression
 | expression '^' expression									# BitXOrExpression
 | expression '|' expression									# BitOrExpression
 | expression '&&' expression								# LogicalAndExpression
 | expression '||' expression								# LogicalOrExpression
 | expression '?' expression ':' expression				# TernaryExpression
 | '(' expression ')'											# ParenthesizedExpression

 ;
