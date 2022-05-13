package com.company;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryOpenAddressingTest {

    private DictionaryOpenAddressing<Integer, Integer> dict;

    @BeforeEach
    void setUp() {
        dict = new DictionaryOpenAddressing<>(10);
    }

    @org.junit.jupiter.api.Test
    void put() throws DuplicateKeyException {
        dict.put(3,2);
        assertThrows(DuplicateKeyException.class, () -> dict.put(3,4));
        assertEquals(2, dict.get(3));
    }

    @org.junit.jupiter.api.Test
    void get() throws DuplicateKeyException {
        dict.put(12,2);
        assertEquals(2, dict.get(12));
        assertNull(dict.get(9));
    }

    @org.junit.jupiter.api.Test
    void remove() throws DuplicateKeyException {
        dict.put(5,111);
        assertEquals(111, dict.get(5));
        assertEquals(111, dict.remove(5));
        assertNull(dict.get(5));
    }

    @org.junit.jupiter.api.Test
    void contains() throws DuplicateKeyException {
        dict.put(11,111);
        dict.put(9,111);
        dict.put(13,111);
        assertTrue(dict.contains(11));
        assertTrue(dict.contains(13));
        assertFalse(dict.contains(8));
    }

    @org.junit.jupiter.api.Test
    void size() throws DuplicateKeyException {
        dict.put(11,111);
        dict.put(10,111);
        dict.put(1,111);
        assertEquals(3, dict.size());
        dict.remove(10);
        assertEquals(2, dict.size());
    }
}