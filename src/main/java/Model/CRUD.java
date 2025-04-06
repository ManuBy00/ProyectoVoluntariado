package Model;

public interface CRUD <T> {

        void add(T elemento);

         void update(T elemento);

         void remove(T elemento);

         void mostrar(T elemento);
}

