package com.devel.skeleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Libro {

    String title = null;
    String category = null;
    int pages = 0;

    Libro(String title, String category, int pages) {
        this.title = title;
        this.category = category;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}

public class EvalPredicate {

    public static void main(String[] args) {

        List<String> lulu = new ArrayList<>();

        Libro l = new Libro("El señor de los anillos", "fantasia", 1100);
        Libro l2 = new Libro("El Juego de Ender", "ciencia ficcion", 500);
        Libro l3 = new Libro("La fundacion", "ciencia ficcion", 500);
        Libro l4 = new Libro("Los pilares de la tierra", "historica", 1200);

        List<Libro> books = Arrays.asList(l, l2, l3, l4);

        books.stream()
                .filter(libro -> libro.getPages() > 1000)
                .map(libro -> libro.getTitle())
                .forEach(System.out::println);
    }
}
