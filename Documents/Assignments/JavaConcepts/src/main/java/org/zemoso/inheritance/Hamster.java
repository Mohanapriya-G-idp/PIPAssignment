package org.zemoso.inheritance;

public class Hamster extends Rodent{

    public Hamster() {
        System.err.println("Hamster class");
    }

    @Override
    void eatingHabit() {
        System.out.println("Hamester eats grass");
    }
    @Override
    public void communication(){
        System.out.println("Hamster way of communication:  vocals ");
    }
}
