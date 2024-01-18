package org.zemoso.inheritance;

public class RodentArray {
    public static void main(String[] args) {
        Rodent[] r = new Rodent[3];
        r[0] = new Mouse();
        r[1] = new Gerbil();
        r[2] =new Hamster();
        for (Rodent rodent:r){
            rodent.communication();
            rodent.eatingHabit();
            System.out.println();
        }
    }
}
