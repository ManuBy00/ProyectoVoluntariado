package Model;

public interface CRUD <T, ID> {

        public boolean add(T elemento);

        public boolean  update(T elemento);

        public boolean remove(T elemento);

        public String mostrar(T elemento);

        public void  mostrarConjunto();

        public T encontrarElemento(ID id);
}

