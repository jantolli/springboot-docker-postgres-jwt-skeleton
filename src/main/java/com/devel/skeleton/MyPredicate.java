package com.devel.skeleton;

import java.util.function.Predicate;

public class MyPredicate {

    Predicate<Libro> eval(String title) {
        return (Libro l) -> {
            return l.getTitle().contains(title);
        };
    }

}
