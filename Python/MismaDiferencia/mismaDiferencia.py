def mismaDiferencia(arreglo):
	"""
	Verifica si una serie de numeros consecutivos
	tienen una misma magnitud siempre

	   Parametros:
	   arreglo -- un arreglo de numeros para representar la serie 
	              de numero 

	"""
	if(len(arreglo) <= 1):
		print("requiere al menos dos elementos")
	elif len(arreglo)==2:
		print(True);
	else:
		x = arreglo[0]
		diff= arreglo[0]-arreglo[1]	
		aux=diff
		print(aux)
		i=1				
		while i <len(arreglo)-1:				
			if(arreglo[i] == x + diff)|(arreglo[i] == x - diff):			
				x=arreglo[i]
				diff= x - arreglo[i+1]						
			if diff != aux:			
				print(diff)
				print(False)
				break;
			i+=1
			if i == (len(arreglo)-1):		
				print(True)
				break
a=[1,5,9,13]
mismaDiferencia(a)				

help(mismaDiferencia)