import java.util.ArrayList;
import java.util.Collections;

public class TablaHash {

    // Tamaño de la tabla hash
    private static final int TABLE_SIZE = 50;

    // Tabla hash
    private Integer[] hashTable;

    public TablaHash() {
        hashTable = new Integer[TABLE_SIZE];
    }

    // Función hash: CLAVE MOD 50
    private int hashFunction(int key) {
        return key % TABLE_SIZE;
    }

    // Método de resolución de colisiones: (CLAVE + 1) MOD 50
    private int resolveCollision(int key, int index) {
        return (key + 1) % TABLE_SIZE;
    }

    // Insertar un valor en la tabla hash
    public void insert(int key) {
        int index = hashFunction(key);

        // Resolver colisiones
        while (hashTable[index] != null) {
            index = resolveCollision(key, index);
        }

        hashTable[index] = key;
    }

    // Búsqueda lineal en la tabla hash
    public int linearSearch(int key) {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (hashTable[i] != null && hashTable[i] == key) {
                return i; // Retorna la posición donde se encontró la clave
            }
        }
        return -1; // Retorna -1 si no se encuentra la clave
    }

    // Búsqueda binaria en una lista ordenada
    public int binarySearch(ArrayList<Integer> sortedList, int key) {
        int left = 0;
        int right = sortedList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (sortedList.get(mid) == key) {
                return mid; // Retorna la posición donde se encontró la clave
            }

            if (sortedList.get(mid) < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Retorna -1 si no se encuentra la clave
    }

    // Método para obtener una lista ordenada de los valores en la tabla hash
    public ArrayList<Integer> getSortedValues() {
        ArrayList<Integer> values = new ArrayList<>();
        for (Integer value : hashTable) {
            if (value != null) {
                values.add(value);
            }
        }
        Collections.sort(values);
        return values;
    }

    public static void main(String[] args) {
        TablaHash hashTable = new TablaHash();

        // Valores a insertar
        int[] values = {40, 50, 62, 103, 204, 304, 328};

        // Insertar valores en la tabla hash
        for (int value : values) {
            hashTable.insert(value);
        }

        // Búsqueda lineal
        int keyToSearch = 103;
        int linearSearchResult = hashTable.linearSearch(keyToSearch);
        if (linearSearchResult != -1) {
            System.out.println("Búsqueda lineal: El valor " + keyToSearch + " se encuentra en la posición " + linearSearchResult);
        } else {
            System.out.println("Búsqueda lineal: El valor " + keyToSearch + " no se encuentra en la tabla hash");
        }

        // Búsqueda binaria
        ArrayList<Integer> sortedValues = hashTable.getSortedValues();
        int binarySearchResult = hashTable.binarySearch(sortedValues, keyToSearch);
        if (binarySearchResult != -1) {
            System.out.println("Búsqueda binaria: El valor " + keyToSearch + " se encuentra en la posición " + binarySearchResult + " de la lista ordenada");
        } else {
            System.out.println("Búsqueda binaria: El valor " + keyToSearch + " no se encuentra en la lista ordenada");
        }
    }
}