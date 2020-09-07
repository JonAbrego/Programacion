#include<stdio.h>

int main(){
	char numero[5];
	printf("Ingresa un numero de 5 digitos: ");
	scanf("%s",numero);
	if(numero[0]== numero[4]){
		if(numero[1]==numero[3])
		printf("El numero es palindormo\n");
	}else
		printf("El numero no es palindormo\n");
return 0;
}
