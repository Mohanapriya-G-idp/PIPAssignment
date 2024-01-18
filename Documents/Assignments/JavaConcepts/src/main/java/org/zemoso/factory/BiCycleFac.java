package org.zemoso.factory;

public class BiCycleFac implements CycleFactory{
    @Override
    public Cycle newCycle() {
        return new BiCycle();
    }
}
