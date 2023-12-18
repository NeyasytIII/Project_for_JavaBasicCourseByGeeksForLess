package com.GeeksForLess.task.JDBCLogicTest;

import com.GeeksForLess.task.JDBCLogic.ByRootGetter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;


public class ByRootGetterTest {


    // Перевіримо роботу пошуку за коренем.
    @Test
    public void testGetByRootInDB_Valid() {
        try {
            ByRootGetter.getByRootInDB(2.0);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }
    // Перевіримо роботу пошуку за набором коренів.
    @Test
    public void testGetFromSetOfRoots_Valid() {
        try {
            ByRootGetter.getFromSetOfRoots(2.0,4,2,6,2,23);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }
    // Перевіримо роботу пошуку за всіма коренями.
    @Test
    public void testGetByRoots_Valid() {
        try {
            ByRootGetter.getByRoots(2.0,3,4,1,2);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }
}
