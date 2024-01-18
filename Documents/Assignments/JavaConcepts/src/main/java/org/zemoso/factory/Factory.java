package org.zemoso.factory;

public class Factory
{
    public static void createCycle(CycleFactory factory){
        Cycle c = factory.newCycle();
        c.countOfWheels();
    }

    public static void main(String[] args) {
        createCycle(new UniCycleFac());
        createCycle(new BiCycleFac());
        createCycle(new TriCycleFac());
    }
}
