package org.zemoso.factory;

import org.zemoso.factory.Cycle;

public class TriCycle implements Cycle {
    @Override
    public void countOfWheels() {
        System.out.println("Tri-cycle has 3 wheels");
    }
}
