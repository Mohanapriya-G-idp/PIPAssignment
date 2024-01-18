package org.zemoso.interfaces;

public class FirstOuter {
    static class Inner{
        static int i;
        public Inner(int i) {
            this.i = i;
        }
       public void display(){
            System.out.println("value of  1st class:"+ i);
        }
    }
}
