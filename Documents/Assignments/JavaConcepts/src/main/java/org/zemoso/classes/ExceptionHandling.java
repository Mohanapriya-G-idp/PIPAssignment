package org.zemoso.classes;

public class ExceptionHandling {
    static int a = 100;
    static String s = null;

    public static void expection() {

       // System.out.println(a / 0);
        if(s!=null) {
            System.out.println(s.length());
        }else {
            System.err.println("String is null");
        }
        int b = Integer.parseInt(s);
        System.out.println(b);

    }

    public static void main(String[] args) {

        System.out.println("Program started");
        try {

            expection();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Exception Handled");
        }
    }
}
