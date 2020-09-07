#include<stdio.h>
#include<math.h>

void coordenadaP(float x,float y){	
	printf("Las coordenadas del punto son ");
	printf("x: %f \t",x);
	printf("y: %f ",y);
	return;
}

float distancia(float x1,float x2, float y1, float y2){
	float a=pow((x1-x2),2);
	float b=pow((y1-y2),2);
	float res=a+b;
	return sqrt(res);
}
int main(){
	printf("Ingresa 4 cantidades, x1,y1,x2,y2:\n");
	float x1;
	float x2;
	float y1;
	float y2;
	scanf("%f",&x1);
	scanf("%f",&y1);
	scanf("%f",&x2);
	scanf("%f",&y2);
	coordenadaP(x1,y1);
	printf("\n");
	coordenadaP(x2,y2);
	float res=distancia(x1,x2,y1,y2);
	printf("\n");
	printf("La distancia es: %f \n",res);
	return 0;
}
