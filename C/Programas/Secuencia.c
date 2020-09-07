#include<stdio.h>

int main(){
  int sec[15];
  int i;
  int n;
  int aux1=1;
  int aux2=1;
  printf("Ingresa un valor: ");
  scanf("%i",&n);
  sec[0]=n;
  int aux3=n;
  for(i=1;i<=15;i++){
    if((i%2)>0){
      n+=aux1;
      aux1++;
    }
    if((i%2)==0){
      n-=aux2;
      aux2++;
    }
    sec[i]=n;
    n=aux3;
  }
  for(i=0;i<15;i++){
      printf("%d",sec[i]);
      if(i<14)
	printf("=>");
  }
  printf("\n");
  return 0;
}
