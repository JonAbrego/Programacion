from decimal import Decimal, getcontext

def mismaDiferencia(arreglo):
	"""
	Verifica si una serie de numeros consecutivos
	tienen una misma magnitud entre si

	   Parametros:
	   arreglo -- un arreglo de numeros para representar la serie 
	              de numero 

	"""	
	if(len(arreglo) <= 1):
		print("Requiere al menos dos elementos")
	elif len(arreglo)==2:
		print(True);
	else:		
		getcontext().prec = 2
		#la diferencia entre los dos pimeros elementos para obetenr la magnitud
		diff= float(Decimal(arreglo[0])-Decimal(arreglo[1]))	
		#auxiliar para mantener la magnitud
		mag=diff				
		#elemnto actual
		x = arreglo[0]
		#iniciamos i en 1 para comenzar en el elemnto siguiente de X
		i=1						
		while i < len(arreglo):	
			print("valor i", i,"actual",x,"siguiente",arreglo[i],"diferencia",diff)							
			#comparamos si el elemento actual con la magnitud es igual al siguiente elemento						
			if(arreglo[i] == float(Decimal(x) - Decimal(mag))):			
				#actualizamos el elemento actual con el que esta posicionado i
				x=arreglo[i]								
				i+=1
				if(i < len(arreglo)):
					#calculamos la diferencia del elemento actual con el siguiente
					diff = float(Decimal(x) - Decimal(arreglo[i]))								
			#si diferencia es distinto de mag, la magnitud cambio			
			elif diff != mag:		
				#print("valor i", i,"actual",x,"siguiente",arreglo[i],"diferencia",diff)				
				print(False)
				break			
	print(i,len(arreglo))
	if i == (len(arreglo)):
			print(True)	

#a=[1,3,5]
#a=[194,54,23,7,3,6,8]
#a=[44,37,30,23]
#a=[1,8]
#a=[-2.3,-1.1,0.1,1.3,2.5,3.7,4.8]
a=[10,11,12,13,16]

mismaDiferencia(a)				

#help(mismaDiferencia)