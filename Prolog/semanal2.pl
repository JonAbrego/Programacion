% Definición lógica de los números naturales.
nat(cero).
nat(suc(N)) :- nat(N).


% suma(N,M,K)
% Predicado que es cierto cuando K = N+M.
suma(cero,N,N).
suma(suc(M),N,suc(NM)) :- suma(M,N,NM).


% prod(N,M,K)
% Predicado que es cierto cuando K = N*M.
prod(cero,_,cero).
prod(suc(N),M,K) :- suma(NM,M,K),prod(N,M,NM).


% factorial(N,M)
% Predicado que es cierto si M es el factorial de N.
factorial(cero,suc(cero)).
factorial(suc(N),M) :- prod(suc(N),K,M), factorial(N,K).


% potencia(X,N,R)
% Predicado que es cierto si XN=R
potencia(_,cero,suc(cero)).
potencia(X,suc(M),R) :- potencia(X,M,K), prod(K,X,R).


% menor(N,M)
% Predicado que es cierto si N<M.
menor(cero,suc(_)).
menor(suc(N),M) :- menor(N,M).


% igual(N,M)
% Predicado que es cierto si N=M.
igual(cero,cero).
igual(suc(N),suc(M)) :- igual(N,M).


% elem(X,L)
% Predicado que es cierto si X es elemento de la lista L.
elem(X,[X|_]).
elem(X,[_|XS]) :- elem(X,XS).


% reversa(L,R)
% Predicado que es cierto si la lista R es la reversa de la lista L.
reversa([],[]).
reversa([X|XS],R) :- reversa(XS,R1), conct(R1,[X],R).


% conct(L,M,N)
% Predicado que es cierto si N es el resultado de concatenar L con M (auxiliar para reversa)
conct([],L,L).
conct([X|XS],L,[X|YS]) :- conct(XS,L,YS).


% palindroma(L)
% Predicado que es cierto si la lista L es pal´ındroma.
palindroma([]).
palindroma(L) :- reversa(L,L).


% ultimo(L,X)
% Predicado que es cierto si X es el ´ultimo elemento de la lista L.
ultimo([A],A).
ultimo([_|XS],A) :- ultimo(XS,A).


% long(L,N)
% Predicado que es cierto si N es la longitud de la lista L.
long([],cero).
long([_|XS],suc(N)) :- long(XS,N).  


% elimina(X,L1,L2)
% Predicado que es cierto si la lista L2 es la lista que resulta de eliminar todas las apariciones de X en la lista L1.
elimina(X,[X|XS],XS).
elimina(X,[Y|YS],[Y|ZS]) :- elimina(X,YS,ZS).
