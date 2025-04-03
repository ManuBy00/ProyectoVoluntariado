package Model;

public interface CRUD <T> {

        void add(T elemento); //metodo para añadir un elemento a la lista (por ejemplo añadir activdades a iniciativa)

         void update(T elemento);

         void remove(T elemento); //borrar actividad

         void mostrar(T elemento); //devuelve la cadena con los datos de una actividad. Puede simplemente devolver el toString
}

