package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.*;


public class CollectionTests {

    @Test
    void arrayTests() {
        //var array = new String[3];
        //array[0]="a";

        var array = new String[]{"a", "b", "c"};
        Assertions.assertEquals("a", array[0]);
        Assertions.assertEquals(3, array.length);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }
    @Test
    void listTests() {
        var list = new ArrayList<>(List.of("a","b","c"));
        Assertions.assertEquals(3,list.size());
        Assertions.assertEquals("a",list.get(0));

        list.set(0,"d");
        Assertions.assertEquals("d",list.get(0));
    }
    @Test
    void setTests(){
        var set = new HashSet<>(List.of("a","b","c","a"));
        Assertions.assertEquals(3,set.size());
        set.stream().findFirst().get();

        set.add("a");
        Assertions.assertEquals(3,set.size());
    }
    @Test
    void testMap(){
        var digits = new HashMap<Character, String>();
        digits.put('1',"one");
        digits.put('2',"Two");
        digits.put('3',"Three");

        Assertions.assertEquals("one",digits.get('1'));
        digits.put('1',"один");
        Assertions.assertEquals("один",digits.get('1'));
    }
}