#!/usr/bin/env python
from graphviz import Digraph
import sys

# Metodo usados para calcular nuestra secuencia
def calcula(m,n):        
    #determinamos nuestro alfabeto en base a nuestra m 
    alfabeto = list(map(str, range(m)))
    #auxiliares para ir almacenando nuestra secuencia calculada
    a = [0] * m * n
    secuencia = []
    def bruijn(t, p):
        if t > n:
            if n % p == 0: #si se cumple el modulo agregamos
                secuencia.extend(a[1:p + 1]) #usamos extends que en lugar de append, ya que nos evitamos guardar una lista completa
                                             #y solo guardamos y concatenamos lo que nos sera de utilidad
        else:
            a[t] = a[t - p] 
            bruijn(t + 1, p)
            for j in range(a[t - p] + 1, m):                
                a[t] = j
                bruijn(t + 1, t)#hacemos una llamada recursiva 
    bruijn(1, 1)
    for j in range(0, n - 1):
        a[j] = 0 
    #
    secuencia.extend(a[0:n - 1])
    #para la secuencia dada la unimos a una cadena vacia para
    #imprimirlo de una manera mas util de manejar
    return "".join(alfabeto[i] for i in secuencia)

#Metodo para generar nuestra lista de vertices 
def lista_vertices(c, m , n):
    v=[]
    for i in range(0,m**n):
        if c[i:i+(n-1)] not in v and len(c[i:i+(n-1)])==(n-1): #Solo nos interesan los vertices que cumplan las condiciones 
        #senaladas que son que v no este ya en la lista y que ademas cumplan el largo de nuestras etiquetas que queremos                                                              
            v.insert(i,c[i:i+(n-1)])
    return v            

#def lista_aristas(c, m, n):
#    e=[]#lista para guardar las aristas 
#    for i in range(0,m**n): 
#        if c[i:i+n] not in e and len(c[i:i+n])==n:
#            e.insert(i,c[i:i+n])               
#    return e

#Metodo usado para generar la grafica solicitada 
def grafica(c,m,n):
    dot = Digraph(name='Proyecto',format='pdf') #indicamos que tipo de grafica sera, ademas de el formato de salida y nombre del archivo
    ver=lista_vertices(c, m , n) #listas de vertices
    for i in range(0,len(ver)):
        dot.node(ver[i],ver[i])#creamos una lista de nodos en base a la lista de nodos calculada en el metodo lista_vertices          
    for i in range(0,len(ver)):
        elemento = ver[i] #auxiliar que se comparara con todos los j elemetnos
        for j in range(0,len(ver)):
            elemento2 = ver[j] #auxiliar para que vayamos recorriendo los nodos y poder comparar todos con el elemnto i
            arista=elemento[0:1]+elemento2[0:(n-1)] #Creamos una arista de tamano n            
            if arista[0:(n-1)]==elemento and arista[1:n]==elemento2: #verificamos que segun la union se pueda crear los nodos que estamos empatando
                dot.edge(elemento,elemento2,label=arista)#creamos una lista de vertice en base a la lista de nodos calculada en el metodo lista_vertices          
    b ="" #Cadena para formar la secuencia de brujin
    for i in range(0,len(c)):        
        if len(c[i:n+i]) == n: #Verificamos que nuestraque tomamos cadenas de tamano n
            aux=c[i:n+i] #Asignamos a un variable aux           
            b+=aux[0:1] #A nuestra cadena vacia b le concatenamos el primer elemento de la subcadena aux tomada para generar la secuencia
    dot.body.append(r'label ='+b) #Para imprimir la secuencia en el documento
    dot.body.append('fontsize=12') #definimos el tamano de la letras
    dot.render(view=True)         

def main():
    #verifica que le pasen los parametros requeridos
    try: 
        m = sys.argv[1]
        n = sys.argv[2]
    except:
        print "Brujin necesita dos enteros un m y n positivos "        
        print "python "+ sys.argv[0] + " m:entero n:entero"
        print "Por ejmeplo:\npython "+ sys.argv[0] + " 2 3"
        quit()        
    #llamada de metodo para calcular la secuencia de G_{m,n}        
    grafica(calcula(int(m),int(n)),int(m),int(n))        

if __name__ == '__main__':
    main()