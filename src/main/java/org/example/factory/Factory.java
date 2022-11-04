package org.example.factory;

@FunctionalInterface
public interface Factory<T> {

    T create();
}
