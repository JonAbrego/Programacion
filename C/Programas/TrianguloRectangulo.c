#include<stdio.h>
#include<math.h>

int main(){
	float a;
	float b;
	float c;
	printf("Introduce el valor de a: ");
	scanf("%f",&a);
	printf("Introduce el valor de b: ");
	scanf("%f",&b);
	printf("Introduce el valor de c: ");
	scanf("%f",&c);
	if(sqrt(pow(a,2)+pow(b,2))==c){
	  printf("Puede construirse un triangulo rectangulo \n");
	}else{
	  if(sqrt(pow(c,2)-pow(a,2))==b){
	    printf("Puede construirse un triangulo rectangulo \n");
	  }else{
	    if(sqrt(pow(c,2)-pow(b,2))==a){
	      printf("Puede construirse un triangulo rectangulo \n");
	    }else{
	      printf("NO puede construirse un triangulo rectangulo\n");
	    }
	  }
	}
	return 0;
}
