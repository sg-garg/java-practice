package com.sg.practice.collections;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by shara on 23-07-2017.
 */
public class EnumMapTest {
    public static void main(String[] args) {
        EnumMap<EnumKey, String> enumMapTest = new EnumMap<EnumKey, String>(EnumKey.class);
        enumMapTest.put(EnumKey.One, "One");
        enumMapTest.put(EnumKey.Two, "Two");
        enumMapTest.put(EnumKey.Two, "Three");

        System.out.println("One Way to print value");

       Iterator<EnumKey> keys= enumMapTest.keySet().iterator();
       while (keys.hasNext()){
           EnumKey key = keys.next();
           System.out.println(key+" - "+enumMapTest.get(key));
       }

        System.out.println("Second Way to print value");
        Set<EnumKey> keys1= enumMapTest.keySet();
        for (EnumKey key: keys1) {
            System.out.println(enumMapTest.get(key));
        }

        System.out.println("Third Way to print value");
        Set<Map.Entry<EnumKey,String>> aaa =  enumMapTest.entrySet();
        for (Map.Entry<EnumKey, String> a: aaa ) {
            System.out.println(a.getKey() +":"+ a.getValue());

        }
    }
}


