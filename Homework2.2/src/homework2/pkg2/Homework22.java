package homework2.pkg2;
import java.util.Scanner;
/**
 *
 * @author Eddie
 */
public class Homework22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Enter a credit card number as a long integer:");
        Scanner in = new Scanner(System.in);
        long num;
        num = in.nextLong();
        String number = Long.toString(num);
        if(isValid(number)){
            System.out.println(number + " is valid");
        }
        else{
            System.out.println(number + " is invalid");
        }
        
        
        
        
    }
    public static boolean isValid(String number){
        if(!prefixMatched(number))
            return false;
        if( 16 < getSize(number)  || getSize(number) < 13)
            return false;
        return (sumOfDoubleEvenPlace(number) + sumOfOddPlace(number))%10 == 0;
    }
    public static int sumOfDoubleEvenPlace(String number){
        int sum = 0;
        System.out.println("double");
        for(int i =0;i<number.length();i+=2){
            //System.out.println("char =" + ((number.charAt(i)) * 2) + "\n");
            System.out.println( "Dig = " + getDigit(Integer.parseInt(number.charAt(i) + "") * 2) + "\n");
            sum += getDigit(Integer.parseInt(number.charAt(i) + "") * 2);
        }
        System.out.println( "Sum = " + sum);
        return sum;
        
    }
    public static int getDigit(int number){
        if(number > 9){
            return number/10 + (number%10);
        }
        return number;
    }
    
    public static int sumOfOddPlace(String number){
        int sum = 0;
        System.out.println("add");
        for(int i =1;i<number.length();i+=2){
            System.out.println(getDigit(Integer.parseInt(number.charAt(i) +"")) + "\n");
            sum += getDigit(Integer.parseInt(number.charAt(i) + ""));
        }
        System.out.println( "Sum = " + sum);
        return sum;
    }
    public static boolean prefixMatched(String number){
        if(number.charAt(0) == '4' || number.charAt(0) == '5' || number.charAt(0) == '6'){
            return true;
        }
        else if(number.charAt(0) == '3' && number.charAt(1) == '7'){
        return true;
        }
        return false;
        
    }
    public static int getSize(String d){
        return d.length();
        
    }
    public static long getPrefix(String number, int k){
        return number.charAt(0);
    }
}
