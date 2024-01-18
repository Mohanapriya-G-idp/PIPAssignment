package org.zemoso.factory;

public class UniCycleFac implements CycleFactory {
    @Override
    public Cycle newCycle() {
        return new UniCycle();
    }
}
