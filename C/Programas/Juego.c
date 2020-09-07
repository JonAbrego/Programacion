#include<stdio.h>
#include<stdlib.h>
#include<time.h> 
void tirada(int i){
	switch(i){
		case 1: 
			printf("Piedra");
			break;
		case 2: 
			printf("Papel");
			break;
		case 3: 
			printf("Tijera");
			break;
	}
	return;
}

void resultado(int t1,int t2){
	if(t1==t2) printf("Empate");
	if((t1==1)&(t2==3))	printf("Gana el jugador J1");
	if((t1==2)&(t2==1)) printf("Gana el jugador J1");
	if((t1==3)&(t2==2)) printf("Gana el jugador J1");
	if((t2==1)&(t1==3))	printf("Gana el jugador J2");
	if((t2==2)&(t1==1)) printf("Gana el jugador J2");
	if((t2==3)&(t1==2)) printf("Gana el jugador J2");
	return;
}

int main(){
	int hora = time(NULL);
	srand(hora);  
	int t1=1+(rand()%3);
	int t2=(1+(rand()%3));
	printf("Jugador1: ");
	tirada(t1);
	printf("\n");
	printf("Jugador2: ");
	tirada(t2);
	printf("\n");
	resultado(t1,t2);
	printf("\n");
	return 0;	
}
