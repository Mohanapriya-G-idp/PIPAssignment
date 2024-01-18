package org.zemoso.inheritance;

public abstract class Rodent {
    public Rodent() {
        System.err.println("Rodent class");
    }

    abstract void eatingHabit();
    abstract void communication();
}
