def merge(a, b):
	"""
	Dado dos arreglos de numeros enteros ordenados A y B 
	devolver un arreglo C con los elemento de A y B ordenados, 
	sin emplear algun algoritmo de ordenamiento

	   Parametros:
	   a -- un arreglo de numeros enteros ordenados 
	   b -- un arreglo de numeros enteros ordenados

	"""
	c = []
	arreglo1 = 0
	arreglo2 = 0
	i=0	
	while( (arreglo1 < len(a)) & (arreglo2 < len(b)) ):
		if(a[arreglo1] < b[arreglo2]):						
			c.append(a[arreglo1])
			arreglo1+=1
			i+=1
		else:
			c.append(b[arreglo2])
			arreglo2+=1
			i+=1		

	#cuando un arreglo ya termino y faltan elementos de otro
	while (arreglo1 < len(a)):
		c.append(a[arreglo1])
		arreglo1+=1;

	while (arreglo2 < len(b)):
		c.append(b[arreglo2])	
		arreglo2+=1;		
	print(c)	

merge([1,3,5],[2,4,6])

merge([3, 4, 9, 9, 17, 20], [8, 9, 40, 41])
#returns [3, 4, 8, 9, 9, 9, 17, 20, 40, 41]

merge([5, 6, 20], [2, 3, 4, 5])
#returns [2, 3, 4, 5, 5, 6, 20]

merge([1, 3, 5, 7, 9], [2, 4, 6, 8])
#returns [1, 2, 3, 4, 5, 6, 7, 8, 9]

merge([14, 17, 18, 21, 22, 26], [3, 15, 30, 31])
#returns [3, 14, 15, 17, 18, 21, 22, 26, 30, 31]

merge([3, 4, 6, 9, 11, 16, 17], [8, 9, 9, 40])
#returns [3, 4, 6, 8, 9, 9, 9, 11, 16, 17, 40]

			
