/*Ejercicio 1*/

/*Definimos nuestros hechos*/
word(astante, a,s,t,a,n,t,e).
word(astoria, a,s,t,o,r,i,a).
word(baratto, b,a,r,a,t,t,o).
word(cobalto, c,o,b,a,l,t,o).
word(pistola, p,i,s,t,o,l,a).
word(statale, s,t,a,t,a,l,e).

/*Verificamos si dos palabras coinciden en la segunda letra*/
igual_22(P1,P2):-(word(P1,_,W1,_,_,_,_,_),W1=X),(word(P2,_,W2,_,_,_,_,_),W2=Y),X==Y.
/*Verificamos si la segunda letra de una palabra coincide con la 4ta letra de otra*/
igual_24(P1,P2):-(word(P1,_,W1,_,_,_,_,_),W1=X),(word(P2,_,_,_,W2,_,_,_),W2=Y),X==Y.
/*Verificamos si la segunda letra de una palabra coincide con la 6ta letra de otra*/
igual_26(P1,P2):-(word(P1,_,W1,_,_,_,_,_),W1=X),(word(P2,_,_,_,_,_,W2,_),W2=Y),X==Y.
/*Verificamos si la 4ta letra de una palabra coincide con la 2nda de letra otra*/
igual_42(P1,P2):-(word(P1,_,_,_,W1,_,_,_),W1=X),(word(P2,_,W2,_,_,_,_,_),W2=Y),X==Y.
/*Verificamos si la 4ta letra de una palabra coincide con la 4ta letra de otra */
igual_44(P1,P2):-(word(P1,_,_,_,W1,_,_,_),W1=X),(word(P2,_,_,_,W2,_,_,_),W2=Y),X==Y.
/*Verificamos si la 4ta letra de una palabra coincide con la 6ta letra de otra */
igual_46(P1,P2):-(word(P1,_,_,_,W1,_,_,_),W1=X),(word(P2,_,_,_,_,_,W2,_),W2=Y),X==Y.
/*Verificamos que la 6ta letra  de una palabra coincide con la 2nda letra de otra*/
igual_62(P1,P2):-(word(P1,_,_,_,_,_,W1,_),W1=X),(word(P2,_,W2,_,_,_,_,_),W2=Y),X==Y.
/*Verificamos que la 6ta letra  de una palabra coincide con la 4ta letra de otra*/
igual_64(P1,P2):-(word(P1,_,_,_,_,_,W1,_),W1=X),(word(P2,_,_,_,W2,_,_,_),W2=Y),X==Y.
/*Verificamos que la 6ta letra  de una palabra coincide con la 6ta letra de otra*/
igual_66(P1,P2):-(word(P1,_,_,_,_,_,W1,_),W1=X),(word(P2,_,_,_,_,_,W2,_),W2=Y),X==Y.

/*Verificamos que las letras coicidan segun el crucigrama que se dio */
/*Si se cumplen cada condicion de las reglas entonces sera verdad*/
devuelveV1(V1):-(igual_22(astante,astoria),igual_24(cobalto,astoria),igual_26(pistola,astoria),V1=astoria).
devuelveV2(V2):-(igual_42(astante,baratto),igual_44(cobalto,baratto),igual_46(pistola,baratto),V2=baratto).
devuelveV3(V3):-(igual_62(astante,statale),igual_64(cobalto,statale),igual_66(pistola,statale),V3=statale).

devuelveH1(H1):-(igual_22(astante,astoria),igual_42(astante,baratto),igual_62(astante,statale),H1=astante).
devuelveH2(H2):-(igual_24(cobalto,astoria),igual_44(cobalto,baratto),igual_64(cobalto,statale),H2=cobalto).
devuelveH3(H3):-(igual_26(pistola,astoria),igual_46(pistola,baratto),igual_66(pistola,statale),H3=pistola).

crossword(V1,V2,V3,H1,H2,H3):-devuelveV1(V1),devuelveV2(V2),devuelveV3(V3),devuelveH1(H1),devuelveH2(H2),devuelveH3(H3).

/*Ejercicio 2*/

/*Hacemos una busqueda respecto con las diferentes combinaciones que pueden haber*/
busca(C,[H|Tail],V) :- V is C+H; busca(+(C,H),Tail,V); busca(C, Tail, V). 

/*Llamamos a nuestro metodo auxiliar y nos determinara si se puede o no */
cantex(L,V) :- busca(0,L,V).

/*Ejercicio 3*/

/*Definimos nuestros hechos*/
color1(rojo).
color2(azul).
color3(amarrillo).
color4(verde).

/*Definimos que X y Y son vecinos si son distintos*/
vecinos(X,Y):-X\=Y.

/*Determinaremos que regiones son vecinas en base al esquema dado */
reg1(Reg1):-vecinos(color1,color2),vecinos(color1,color2),vecinos(color1,color4),vecinos(color1,color3),Reg1=rojo.
reg2(Reg2):-vecinos(color2,color1),vecinos(color2,color4),Reg2=azul.
reg3(Reg3):-vecinos(color4,color1),vecinos(color4,color2),Reg3=verde.
reg4(Reg4):-vecinos(color3,color1),vecinos(color3,color4),vecinos(color3,color2),Reg4=amarrillo.
reg5(Reg5):-vecinos(color2,color3),vecinos(color2,color4),Reg5=azul.
reg6(Reg6):-vecinos(color4,color3),Reg6=verde.

colorea(Reg1,Reg2,Reg3,Reg4,Reg5,Reg6):-reg1(Reg1),reg2(Reg2),reg3(Reg3),reg4(Reg4),reg5(Reg5),reg6(Reg6).