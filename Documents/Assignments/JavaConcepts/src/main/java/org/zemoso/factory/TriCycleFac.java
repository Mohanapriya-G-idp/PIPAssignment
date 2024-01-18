package org.zemoso.factory;

public class TriCycleFac implements CycleFactory {
    @Override
    public Cycle newCycle() {
        return new TriCycle();
    }
}
