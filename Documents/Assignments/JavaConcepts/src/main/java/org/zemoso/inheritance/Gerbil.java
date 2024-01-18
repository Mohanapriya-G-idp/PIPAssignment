package org.zemoso.inheritance;

public class Gerbil extends Rodent{

    public Gerbil() {
        System.err.println("Gerbil class");
    }

    @Override
    void eatingHabit() {
        System.out.println("Gerbil eats vegetables");
    }
    @Override
    public void communication(){
        System.out.println("Gerbil way of communication:  scent marking ");
    }
}
