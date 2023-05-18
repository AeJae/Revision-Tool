package com.ajsf;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static Boolean inArray(String input, String[] array) {
        for (String s : array) {
            if (s.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}