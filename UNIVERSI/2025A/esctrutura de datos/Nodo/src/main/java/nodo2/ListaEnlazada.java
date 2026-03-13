
package nodo2;

class Nodo {
    int info;
    Nodo liga;

    Nodo(int info) {
        this.info = info;
        this.liga = null;
    }

    Nodo(int info, Nodo liga) {
        this.info = info;
        this.liga = liga;
    }
}

public class ListaEnlazada {
    private Nodo p;

    void insertarFinal(int dato) {
        if (p == null) p = new Nodo(dato);
        else {
            Nodo q = p;
            while (q.liga != null) q = q.liga;
            q.liga = new Nodo(dato);
        }
    }

    void insertarAntesDeX(int dato, int x) {
        if (p == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        if (p.info == x) {
            p = new Nodo(dato, p);
            return;
        }
        Nodo q = p;
        while (q.liga != null && q.liga.info != x) q = q.liga;
        if (q.liga == null) System.out.println("El nodo " + x + " no esta en la lista");
        else q.liga = new Nodo(dato, q.liga);
    }

    void mostrarLista() {
        for (Nodo q = p; q != null; q = q.liga) System.out.print(q.info + " -> ");
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarFinal(10);
        lista.insertarFinal(20);
        lista.insertarFinal(30);
        lista.insertarFinal(40);
        lista.mostrarLista();

        System.out.println("Insertando 25 antes de 30:");
        lista.insertarAntesDeX(25, 30);
        lista.mostrarLista();

        System.out.println("Intentando insertar 15 antes de 50:");
        lista.insertarAntesDeX(15, 50);
    }
}

