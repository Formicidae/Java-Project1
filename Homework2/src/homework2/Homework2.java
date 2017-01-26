/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework2;

/**
 *
 * @author Eddie
 */
import java.util.Scanner;
public class Homework2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           stan();
       
        
    }
    
    public static void stan(){
         System.out.println("Enter ten numbers:");
        Scanner in = new Scanner(System.in);
        double[] nums = new double[10];
        for(int i = 0; i < 10;i++){
            nums[i] = in.nextDouble();
        }
        double mean = 0;
        for(double i : nums){
            mean += i;
        }
        mean = mean/10;
        double stan;
        double xi2 = 0;
        double xi = 0;
        
        for(double i : nums){
            xi2 += Math.pow(i,2.0);
        }
        for(double i : nums){
            xi += i;
        }
        xi = Math.pow(xi,2.0)/10;
        
        stan = Math.sqrt((xi2 - xi)/9);

        System.out.print("\nThe Mean is ");
        System.out.print(mean);
        System.out.print("\nThe standard deviation is ");
        System.out.print(stan);
    }
    
}
