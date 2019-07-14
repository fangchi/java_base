package com.fc.lombok;

public class BookTest {

    public static void main(String[] args) {
        Book book = Book.builder().serialNo("1").build();
        System.out.println(book);
    }

}
