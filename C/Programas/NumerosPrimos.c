#include<stdio.h>

int primo(int n){
	int i,condicion;      
	if(n!=1 && n!=0){
		for(i=2;i<=n;i++)
		if(n%i==0){
			if(n==i){
				condicion=1;
			}else{
				condicion=0;
				break;
			}
        }else {
			condicion=0;
	   }
   }
   return condicion;
}

int main(){
	 int i;
	 for(i=0;i<=1000;i++)
		 if(primo(i)==1) 
		 printf("%i \n",i);
	              
	return 0;
}
