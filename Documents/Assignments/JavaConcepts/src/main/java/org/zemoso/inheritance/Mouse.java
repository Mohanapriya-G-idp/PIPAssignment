package org.zemoso.inheritance;

public class Mouse extends Rodent {
    public Mouse() {
        System.err.println("Mouse class");
    }

    @Override
    void eatingHabit() {
        System.out.println("Mouse eats meat");

    }

    @Override
    public void communication() {
        System.out.println("Mouse way of communication: sound");
    }
}
