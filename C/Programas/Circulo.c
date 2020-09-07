#include<stdio.h>
#include<math.h>

#define PI 3.14159265358979323846
int main(){
  float radio;
  printf("Introduce un numero(que sera el radio de un circulo): ");
  scanf("%f",&radio);
  printf("El radio del circulo es:%f \n",radio);
  printf("El diametro es : %f \n",radio*2);
  printf("El area del circulo es %f \n", PI*pow(radio,2));
  return 0;
}
