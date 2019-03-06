package com.example.brianbaek;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestSort {

    List<String> stringList;
    String[] strings = new String[]{"ㄱ", "ㄴ", "ㄷ", "x","y","z","a","b","c","d","e","ㅅ","ㅇ","ㅈ"};


    @Before
    public void setUp(){
        stringList = new ArrayList<>(Arrays.asList(strings));

    }

    public <T> void sortList(List<T> list){
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if(o1 instanceof String && o2 instanceof String)
                    return ((String) o1).compareToIgnoreCase((String)o2) * -1;
                if(o1 instanceof Comparable && o2 instanceof Comparable){
                    Comparable c1 = (Comparable) o1;
                    Comparable c2 = (Comparable) o2;
                    return  c1.compareTo(c2) * -1;
                }
                return -1;
            }
        });
    }

    @Test
    public void testSort(){

        String[] korStrings = {"ㄱ,ㄴ,ㄷ,ㄹ,ㅁ,ㅂ,ㅅ"};
        //stringList.addAll(Arrays.asList(korStrings));
        Collections.sort(stringList);
        //Arrays.sort(strings);
        for(String s : stringList){
            System.out.print(s);
        }
        System.out.println();

        sortList(stringList);
        for(String s : stringList){
            System.out.print(s);
        }
    }
}
