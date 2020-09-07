#include<stdio.h>

void bSort(int numeros[], int tam){
  int i;
  int j;
  int aux;
  for (i = (tam - 1); i > 0; i--){
    for (j = 1; j <= i; j++){
      if (numeros[j-1] > numeros[j]){
        aux = numeros[j-1];
        numeros[j-1] = numeros[j];
        numeros[j] = aux;
      }
    }
  }
}

int main(){
  int numeros[10];
  int x;
  int i;
  printf("Ingresa 10 numeros enteros: \n");
  for(i=0;i<10;i++){
    scanf("%i",&x);
    numeros[i]=x;
  }
  bSort(numeros,10);
  int k;
  for(i=0;i<10;i++)
    printf("Elemento %d: %d\n",i,numeros[i]);
  return 0;
}
