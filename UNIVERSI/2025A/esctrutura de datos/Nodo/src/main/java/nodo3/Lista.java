
package nodo3;

class Nodo {
    int info;
    Nodo liga;

    Nodo(int info) {
        this.info = info;
        this.liga = null;
    }
}

public class Lista {
    Nodo p;

    void insertarDespuesDeX(int dato, int x) {
        Nodo q = p;
        while (q != null && q.info != x) q = q.liga;
        if (q == null) 
          System.out.println("El nodo registrado como referencia no se encuentra en la lista");
        else {
            Nodo nuevo = new Nodo(dato);
            nuevo.liga = q.liga;
            q.liga = nuevo;
        }
    }

    void insertarFinal(int dato) {
        if (p == null) p = new Nodo(dato);
        else {
            Nodo q = p;
            while (q.liga != null) q = q.liga;
            q.liga = new Nodo(dato);
        }
    }

    void mostrarLista() {
        for (Nodo q = p; q != null; q = q.liga) 
         System.out.print(q.info + " -> ");
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertarFinal(10);
        lista.insertarFinal(20);
        lista.insertarFinal(30);

        System.out.println("Lista original:");
        lista.mostrarLista();

        System.out.println("Insertando 25 despues de 20:");
        lista.insertarDespuesDeX(25, 20);
        lista.mostrarLista();
    }
}
