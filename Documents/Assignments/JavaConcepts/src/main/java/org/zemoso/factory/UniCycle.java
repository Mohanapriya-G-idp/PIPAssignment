package org.zemoso.factory;

import org.zemoso.factory.Cycle;

public class UniCycle implements Cycle {
    @Override
    public void countOfWheels() {
        System.out.println("Unicycle has one wheel");
    }
}
