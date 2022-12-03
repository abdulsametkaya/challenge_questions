package com.backend_challenge.questions;

public class Q4 {

    //4- Bir for döngüsü ile aşağıdaki çıktıyı yazar mısınız.
    //*
    //**
    //****
    //******
    //********
    //**********

    public static void main(String[] args) {

        countStars(6);
    }

    public static void countStars(int num) {

        System.out.print("*");
        for (int i = 0; i < num; i++) {
            for (int j = 1; j <= i * 2; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }


}
