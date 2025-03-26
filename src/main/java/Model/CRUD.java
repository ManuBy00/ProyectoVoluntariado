package Model;

public interface CRUD <T, ID> {

        public boolean add(T elemento); //metodo para añadir un elemento a la lista (por ejemplo añadir activdades a iniciativa)

        public boolean  update(T elemento); //actualizar los datos de una actividad

        public boolean remove(T elemento); //borrar actividad

        public String mostrar(T elemento); //devuelve la cadena con los datos de una actividad. Puede simplemente devolver el toString

        public void  mostrarConjunto(); // imprime la lista completa de actividades

        public T encontrarElemento(ID id); //devolver una actividad concreta buscada por nombre o algo así
}

