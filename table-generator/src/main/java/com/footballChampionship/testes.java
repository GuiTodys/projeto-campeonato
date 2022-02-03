package com.footballChampionship;

public class testes {
    public static String stringInteira = "aaa;bbb;ccc;eeee";
    public static String[] stringArray = stringInteira.split(";");

    public static void main(String[] args) {
        for (int i = 0; i < stringArray.length; i++) {
            System.out.println(stringArray[i]);
        }
    }
}
