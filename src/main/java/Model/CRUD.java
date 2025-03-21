package Model;

public interface CRUD <T, ID> {

        public void add(T elemento);

        public void update(T elemento);

        public void remove(T elemento);

        public String mostrar(T elemento);

        public void  mostrarConjunto(T elemento);

        public T encontrarElemento(ID id);

}

