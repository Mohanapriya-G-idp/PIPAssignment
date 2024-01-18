package org.zemoso.classes;

import org.zemoso.inheritance.TriCycle;
import org.zemoso.inheritance.UniCycle;

public class CycleArray
{
    public static void main(String[] args) {
        Cycle[] c =new Cycle[3];
        c[0] = new BiCycle();
        c[1] =new UniCycle();
        c[2] = new TriCycle();
        System.out.println("Upcasting");
        for (Cycle cycle:c){
            cycle.ride();
        }
        System.out.println("Downcasting");
        BiCycle b = (BiCycle) c[0];
        b.balance();
        b.ride();
        UniCycle u = (UniCycle) c[1];
        u.balance();
        u.ride();
        TriCycle t = (TriCycle) c[2];
        t.ride();

    }
}
