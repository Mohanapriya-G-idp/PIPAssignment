package org.zemoso.interfaces;

public class FIrstSecond {
    public static void main(String[] args) {
        SecondOuter.SecondInner si = new SecondOuter.SecondInner(20,"Priya");
        si.display();
    }
}
