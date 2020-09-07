#include<stdio.h>
#include<string.h>

int main(){
    char cadena1 [30];
    char cadena2 [30];
    
    printf("Escribe la primera cadena: ");
    scanf("%s", cadena1);
    printf("Escribe la segunda cadena: ");
    scanf("%s", cadena2);
	int longitud1 = strlen(cadena1);
	int longitud2 = strlen(cadena2); 
	printf("La cadena de mayor longitud es: ");
	if(longitud1>longitud2){
		printf("%s teniendo %i caracteres. \n", cadena1,longitud1);
	}else{
		if(longitud1<longitud2)
			printf("%s teniendo %i caracteres. \n", cadena2,longitud2);
		else
			printf("No hay cadena mas grande tienen la misma longitud\n");
	}
    return 0;
}
