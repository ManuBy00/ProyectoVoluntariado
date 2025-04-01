package Model;

public interface CRUD <T, ID> {

        public void add(T elemento); //metodo para añadir un elemento a la lista (por ejemplo añadir activdades a iniciativa)

        public void update(T elemento);

        public void remove(T elemento); //borrar actividad

        public void mostrar(T elemento); //devuelve la cadena con los datos de una actividad. Puede simplemente devolver el toString
}

