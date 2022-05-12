package com.company;

public class Main {

    public static void main(String[] args) throws DuplicateKeyException {
        DictionaryOpenAddressing<Integer, Integer> dict = new DictionaryOpenAddressing<>(10);

        dict.put(1, 11);
        dict.put(2, 11);
        dict.put(3, 11);
        dict.put(4, 11);
        dict.put(5, 11);
        dict.put(6, 11);
        dict.put(7, 11);
        System.out.println(dict.toStringWithNulls());
        dict.put(8, 11);
        System.out.println(dict.toStringWithNulls());
        dict.put(9, 11);
        dict.put(10, 11212);
        System.out.println(dict.get(10));


        System.out.println(dict.toStringWithNulls());
    }
}
