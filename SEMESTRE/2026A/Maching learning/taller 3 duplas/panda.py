
#TALLER 3 OBJETIVO Desarrollar habilidades prácticas en el manejo, análisis y transformación de datos utilizando las librerías 
# NumPy y Pandas, así como la lectura y escritura de archivos CSV, con el fin de fortalecer la comprensión de conceptos de programación 
# aplicada al análisis de información.

#ejercico 1 duplas
import numpy as np

A = np.array([1,2,3,4,5,6,7,8,9,10])

suma_total = A.sum()        # Mejor que usar sum()
promedio = A.mean()         # Correcto
cuadrados = A**2            # Mejor que usar lista por comprensión

print("Suma:", suma_total)
print("Promedio:", promedio)
print("Cuadrados:", cuadrados)

#ejercicio 2

a = np.array([2, 4, 6, 8])
b = np.array([1, 3, 5, 7])

# a) Suma, resta y multiplicación
print("Suma:", a + b)
print("Resta:", a - b)
print("Multiplicación:", a * b)

# b) Divide a entre b
print("División:", a / b)

# c) a elevado al cubo
print("a^3:", a**3)

# d) b elevado al cubo
print("b^3:", b**3)

#ejercicio 3
m = np.arange(1,10).reshape(3,3)

print("Matriz:\n", m)
print("Suma total:", m.sum())
print("Suma por filas:", m.sum(axis=1))
print("Suma por columnas:", m.sum(axis=0))
print("Valor máximo:", m.max())

#ejercicio 4
A = np.array([[1],[2],[3]])
B = np.array([[10,20,30]])

print("Suma A+B:\n", A + B)

#ejercicio 5
A = np.array([1,4,9,16,25])
print("Raíz cuadrada:", np.sqrt(arr))
print("Logaritmo natural:", np.log(arr))
print("Exponencial:", np.exp(arr))

#ejercicio 6
rand = np.random.randint(0,51,100)

print("Media:", rand.mean())
print("Desviación estándar:", rand.std())
print("Percentil 90:", np.percentile(rand,90))
print("Varianza:", rand.var())

#ejercicio 7
u = np.array([5,12,7,18,3,21])
print("Mayores que 10:", u[u>10])
u[u<10] = 10
print("Reemplazado:", u)

#ejercicio 8
A = np.array([[4,2],
              [1,3]])
print("Determinante:", np.linalg.det(A))
print("Inversa:\n", np.linalg.inv(A))
print("Valores propios:", np.linalg.eigvals(A))

#ejercicio 9
A = np.array([[2,1],
              [1,3]])

b = np.array([5,6])
x = np.linalg.solve(A,b)
print("Solución:", x)

#ejercicio 10
A = np.array([[1,2],
              [3,4]])

B = np.array([[5,6],
              [7,8]])

# a) Elemento a elemento
print("Elemento a elemento:\n", A * B)
# b) Multiplicación matricial
print("Matricial:\n", A @ B)


