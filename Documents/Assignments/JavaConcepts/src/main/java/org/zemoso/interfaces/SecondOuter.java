package org.zemoso.interfaces;

import static org.zemoso.interfaces.FirstOuter.Inner.i;

public class SecondOuter extends FirstOuter{
    static class SecondInner extends FirstOuter.Inner{
static String s ;
        public SecondInner(int i,String s) {
            super(i);
            this.s = s;
        }
        public void display(){
            System.out.println("values of 2nd class:"+ i+s);
        }
    }


}
