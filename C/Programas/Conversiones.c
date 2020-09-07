#include<stdio.h>

float celsius(int grados){
	float grd;
	grd=((grados*9)/5)+32;
	return grd;
}

float farenheit(float grados){
	float grd;
	grd=((grados-32)*5)/9;
	return grd;
}

int main(){
	printf("°C \t °F \n");
	int i;
	for(i=0;i<=100;i++){
		printf("%i \t",i);	
		printf("%f \n",celsius(i));
	}
	printf("\n");
	printf("°F \t °C \n");
	for(i=32;i<=212;i++){
		printf("%i \t",i);	
		printf("%f \n",farenheit(i));
	}
	return 0;
}
