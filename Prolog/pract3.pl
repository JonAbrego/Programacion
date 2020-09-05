:- dynamic on/2.
:- dynamic onTop/1.
      
/*Mundo de los bloques.*/
on(d,piso).
on(c,piso).
on(b,c).
on(a,b).
on(f,piso).
on(e,f).
on(i,piso).
on(h,i).
on(g,h).

blocked(B):- on(_,B).

onTop(B):- \+ blocked(B).

movePiso(B):- onTop(B),retract(on(B,_)), assertz(on(B,piso)).

move(X,Y):- onTop(X),retract(on(X,_)), assertz(on(X,Y)).

bottom(X,X):- on(X,piso).
bottom(X,Y):- on(X,W), bottom(W,Y).

move_ordered(X,W):- bottom(X,Y), move_reversed(X,piso), move_reversed(Y,W).

move_reversed(X,W):- on(X,piso), move(X,W).
move_reversed(X,W):- on(X,Y), move(X,W), move_reversed(Y,X).

/* Computologos: la competecia*/

computologos([lourdes,susana,francisco,jose]).

competencia:- solucion(S,B),write('Reto de programación = '),write(S),nl,
	write('Reto de demostración = '),write(B),nl.

solucion(Prog,Dem):- computologos(C), permutacion(Prog,C), permutacion(Dem,C),
	ultimo(Prog,UltProg),ultimo(Dem,UltDem), francisco\==UltDem, francisco\==UltProg, jose\==UltProg,
	primer(Prog,PriProg),jose\==PriProg, 
	primer(Dem,PriDem),jose\==PriDem,
	tercer(Dem,TerDem), TerDem==PriProg,
	primer(Dem,PriDem), lourdes==PriDem, 
	mejor(francisco,jose,Dem),
	mejor(jose,francisco,Prog),
	mejor(susana,lourdes, Prog).
	
		
mejor(X,_,[X,_,_,_]).
mejor(_,Y,[_,_,_,Y]).
mejor(X,Y,[_,X,Y,_]).


permutacion([],[]).
permutacion([H|T],R):- member(H,R),select(H,R,Rez),permutacion(T,Rez).

primer([X,_,_,_],X).
segundo([_,X,_,_],X).
tercer([_,_,X,_],X).
ultimo([_,_,_,X],X).
