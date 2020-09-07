# -*- coding: utf-8 -*-

class Neuron(object):
	ALPHA = 0.3
	pesos=[]
	x1=0
	x2=0
	x3=0
	
	salida=0
	
	def calcula_error(self,salida, salida_deseada):
		return salida_deseable-salida

	def calculo_peso_nuevo(self, w,x,e):
		return w+self.ALPHA* x*e

	def funcion_activacion(self,entradas):
		suma=0
		for x in xrange(0,3):
			suma=suma + entradas[x]*self.pesos[x]		
		
		if suma > 0 :return 1
		else : return 0

	def entrenar(self,conj_entrenamiento):
		entrenada=0
		while(entrenada < len(conj_entrenamiento)-1):
			entrenada=0
			for x in xrange(0, len(conj_entrenamiento)):
				entradas=conj_entrenamiento[x]['entradas']
				salida_deseada=conj_entrenamiento[x]['salida_deseada']
				res=self.funcion_activacion(entradas)
				print res

				if res == salida_deseada:
					return 'chido'
					entrenada=entrenada+1
				else : 					
					err= self.calcula_error(res,salida_deseada)
					for p in xrange(0,3):
						self.peso= self.calculo_peso_nuevo(pesos[p],entradas[p],err)		

	def hacer_consulta(self,entradas):
		return self.funcion_activacion(entradas)		

	"""docstring for Neuron"""
	def __init__(self):				
		self.pesos = [0.1,0.4,0.2]

neurona=Neuron()

entrenamiento=[
	{
		'entradas':[0,0,0],
		'salida_deseada':0
	},
	{
		'entradas':[0,0,1],
		'salida_deseada':0
	},
	{
		'entradas':[0,1,0],
		'salida_deseada':0
	},
	{
		'entradas':[0,1,1],
		'salida_deseada':0
	},	
	{
		'entradas':[1,0,0],
		'salida_deseada':0
	},	
	{
		'entradas':[1,1,0],
		'salida_deseada':0
	},
	{
		'entradas':[1,0,1],
		'salida_deseada':0
	},	
	{
		'entradas':[1,1,1],
		'salida_deseada':0
	},
]


# entrenamiento= [
#     {
#         'entradas': [0,0,0],
#         'salida_deseada': 0
#     },
#     {
#         'entradas': [0,0,1],
#         'salida_deseada': 1
#     },
#     {
#         'entradas': [0,1,0],
#         'salida_deseada': 1
#     },
#     {
#         'entradas': [1,0,0],
#         'salida_deseada': 1
#     },
#     {
#         'entradas': [1,0,1],
#         'salida_deseada': 1
#     },
#     {
#         'entradas': [1,1,0],
#         'salida_deseada': 1
#     }
# ]

# entrenamiento = [
#     {
#         'entradas': [0,0,0],
#         'salida_deseada': 0
#     },
#     {
#         'entradas': [1,1,1],
#         'salida_deseada': 1
#     }
# ]

# entrenamiento = [    
#     {
#         'entradas': [0,0,1],
#         'salida_deseada': 1
#     },
#     {
#         'entradas': [1,0,0],
#         'salida_deseada': 1
#     },
#     {
#         'entradas': [1,0,1],
#         'salida_deseada': 1
#     },
# ]
		
neurona.entrenar(entrenamiento)