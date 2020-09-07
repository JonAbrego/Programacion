#include<stdio.h>
#include<math.h>

float factorial(int n)
{
	if (n == 0) 
		return 1;
	else
		return n * factorial (n - 1);
}

float calc(int n, int x){
	float e=1;
	int i;
	float s=0;
	for(i=1;i<=n;i++){
	  e+=pow(x,i);
	  e/=factorial(i);
	  s+=e;
	  e=0;
	}
	return s;
}
int main(void){
	int x;
	int n;
	printf("Introduce un valor para el número de iteraciones: ");
	scanf("%i",&n);
	printf("Introduce un valor para x:");
	scanf("%i",&x);
	  float s=calc(n,x);
	printf("%f\n",s);	
  return 0;
}
