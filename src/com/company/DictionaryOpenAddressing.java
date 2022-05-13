package com.company;

public class DictionaryOpenAddressing<TKey, TValue> {

    class HashNode<T1, T2> {

        public T1 key;
        public T2 value;

        public HashNode(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    private HashNode<TKey, TValue>[] array;
    private int CAPACITY;
    private int elemAmount;

    public DictionaryOpenAddressing(int CAPACITY) {
        this.CAPACITY = CAPACITY;
        array = new HashNode[CAPACITY];
        elemAmount = 0;
    }

    public void put(TKey key, TValue value) throws DuplicateKeyException {
        HashNode<TKey, TValue> node = new HashNode<>(key, value);

        if(contains(key))
            throw new DuplicateKeyException();

        array[openAddressing(key)] = node;
        elemAmount++;
        tryToResize();
    }

    public TValue get(TKey key) {
        int idx = hash(key) % CAPACITY;

        for(int i = 0; i < CAPACITY; i++) {
            if(array[idx] == null) return null;
            if(array[idx].key.equals(key)) return array[idx].value;

            idx = (hash(key) + i) % CAPACITY;
        }

        return null;
    }

    public TValue remove(TKey key) {
        int idx = hash(key) % CAPACITY;

        for(int i = 0; i < CAPACITY; i++) {
            if(array[idx] == null) return null;
            if(array[idx].key.equals(key)) {
                TValue temp = array[idx].value;
                array[idx] = null;
                --elemAmount;
                return temp;
            }

            idx = (hash(key) + i) % CAPACITY;
        }

        return null;
    }

    public boolean contains(TKey key) {
        int idx = hash(key) % CAPACITY;

        for(int i = 0; i < CAPACITY; i++) {
            if(array[idx] == null) return false;
            if(array[idx].key.equals(key)) return true;

            idx = (hash(key) + i) % CAPACITY;
        }
        return false;
    }

    public int size() {
        return elemAmount;
    }

    private int openAddressing(TKey key) {
        int h = hash(key) % CAPACITY;

        if(array[h] == null) {
            return h;
        }

        for(int i = 0; i < CAPACITY; i++) {
            if(array[h] != null)
                h = (hash(key) + i) % CAPACITY;
            else
                break;
        }

        return h;
    }

    private int hash(TKey key) {
        if(key instanceof Integer)
            return 13 * (int) key ^ ((int) key >> 31);
        return 13 * key.hashCode() ^ (key.hashCode() >> 31);
    }

    private void tryToResize() {
        if(elemAmount / (double) CAPACITY >= 0.75) {
            CAPACITY *= 2;

            HashNode[] newArray = new HashNode[CAPACITY];

            System.arraycopy(array, 0, newArray, 0, array.length);

            array = newArray;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(HashNode n : array) {
            if(n != null) {
                sb.append(n);
                sb.append(", ");
            }
        }
        sb.replace( sb.length()-2, sb.length()-1, "]");
        return sb.toString();
    }

    public String toStringWithNulls() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(HashNode n : array) {
            sb.append(n);
            sb.append(", ");
        }
        sb.replace( sb.length()-2, sb.length()-1, "]");
        return sb.toString();
    }
}
