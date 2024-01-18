package org.zemoso.classes;

import java.util.Scanner;

public class AddMatrices {


    public static void main(String[] args) {
        System.err.println("Program for Adding Metrices");
        Scanner s = new Scanner(System.in);
        double a[][],b[][],sum[][];
        System.err.println("Enter the width of matrix a");
        int width = s.nextInt();
        System.err.println("Enter the height of matrix a");
        int height = s.nextInt();
        a = new double[width][height];
        System.err.println("Enter the elements for array a");
        readData(a,s);
        b = new double[width][height];
        System.err.println("Enter the elements for array b");
        readData(b,s);
        sum = new double[width][height];
        add(a,b,sum);
        printValues(sum);

    }
    public static void readData(double[][] arr,Scanner scan){
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){
                System.err.print("Enter the values for each position ("+ (i+1)+","+(j+1)+" ) = ");
                arr[i][j]= scan.nextDouble();
            }
        }

    }
    public static void add(double[][] arr1,double[][] arr2,double[][] result){
        for (int i=0;i<arr1.length;i++){
            for (int j=0;j<arr1[0].length;j++){
                result[i][j] = arr1[i][j]+arr2[i][j];
            }
        }
    }
public static void printValues(double[][] arr){
    for (double[] r:arr){
        for (double c:r){
            System.out.print(c +" ");
        }
        System.out.println();
    }
}

}