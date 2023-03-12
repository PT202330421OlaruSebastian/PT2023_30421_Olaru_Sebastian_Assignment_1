package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer,Integer> polinom = new HashMap<>();

        polinom.put(1,12);
        polinom.put(2,15);
        polinom.put(3,17);

        System.out.println(polinom);
        MainForm main = new MainForm(null);
    }
}