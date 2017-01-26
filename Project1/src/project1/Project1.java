/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Eddie
 */
public class Project1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner inputS = new Scanner(System.in);
        File file = new File("");
        char[][] aud;
        int row;
        int seat;
        int quan;
        boolean running = true;
        while(running){
        System.out.println("Which auditoium would you like to book? 1, 2, 3 or exit(4)");
        int input = inputS.nextInt();
        if(input == 1 || input ==2 || input == 3){
            if(input == 1){
                file = new File("A1.txt");
            }
            if(input == 2){
                file = new File("A2.txt");
            }
            if(input == 3){
                file = new File("A3.txt");
            }
            aud = readAud(file);
            display(aud);
            System.out.println("Which Row would you like to book for");
            row = inputS.nextInt() - 1;
            System.out.println("Which Seat would you like your booking to start at");
            seat = inputS.nextInt() - 1;
            System.out.println("How many seats would you like");
            quan = inputS.nextInt();
            if(available(aud,row,seat,quan)){
                reserve(aud,row,seat,quan,file);
                System.out.println("Seat reserved");
            }
            else{
                if(available(aud,row,findBest(aud,row,quan),quan)){
                    System.out.println("I can get you seat " + (findBest(aud,row,quan) + 1) + " Would you like that seat instead Y/N");
                    char YN = (char) System.in.read();
                    if(YN == 'Y'){
                        reserve(aud,row,findBest(aud,row,quan),quan,file);
                        System.out.println("Seat reserved");
                    }
                    
                }
                else{
                    System.out.println("There are no seats that fit you request on that row");
                }
            }
            
            
        }
        else if(input == 4){
            //Exit
            running = false;
        }
        else{
            
        }
        }
        
        print();
        
    }
    
    public static char[][] readAud(File fileraw) throws IOException {
        Scanner tmp = new Scanner(fileraw);
        String line = "";
        //Scanner tmp = new Scanner(null);
        int j = 0;
        while(tmp.hasNextLine()){
            line = tmp.nextLine();
            j++;
        }
        char[][] aud  = new char[j][line.length()];
        Scanner file = new Scanner(fileraw);
        j = 0;
        while(file.hasNextLine()){
            line  = file.nextLine();
            for(int i = 0; i < line.length();i++){
                aud[j][i] = line.charAt(i);
            }
            j++;
        }
        return aud;
    }
    
    //Prints formated auditorium
    public static void display(char[][] aud){
        //Prints colum headers as 1 digit
        System.out.print(" ");
        for(int i = 1; i <= aud[0].length;i++){
            System.out.print(i%10);
        }
        //Loops through 2d array and prints seats
        System.out.print("\n");
        for(int i = 0; i < aud.length;i++){
            System.out.print(i+1);
            for(int j = 0; j < aud[0].length;j++){
                System.out.print("" + aud[i][j]);
            }
            System.out.print("\n");
        }
        
    }
    
    //Changes the requested seats to reserved and then writes to file
    public static void reserve(char[][] aud, int row, int seat, int quantity,File file)throws IOException{
        for(int i = 0; i < quantity;i++){
            aud[row][seat+i] = '.';
        }
        writeF(aud,file);
    }
    
    //Check is the requested seats are avalible
    public static boolean available(char[][] aud, int row, int seat, int quantity){
        //Checks if best seat didn't find a seat
        if(seat == 999){
            return false;
        }
        //Checks if requested seats would go out of bounds
        if(seat + quantity > aud[0].length)
            return false;
        //Checks if seats are avalible
        for(int i = 0; i < quantity;i++){
            if(aud[row][seat+i] == '.'){
                return false;
            }
        }
        return true;
    }
    
    //Counts reserved seat and open seats and puts them in array passed in
    public static void count(int[] seats, File file) throws IOException{
    char[][] aud = readAud(file);
        for(int i = 0;i < aud.length;i++){
            for(int j = 0; j < aud[0].length;j++){
                if(aud[i][j] == '#'){
                    seats[0]++;
                }
                else{
                    seats[1]++;
                }
            }
        }
    }
    
    //Opens and output stream and writes 2d array to file
    public static void writeF(char[][] aud, File file)throws IOException{
        OutputStream f = new FileOutputStream(file);
        for(int i = 0; i < aud.length;i++){
            for(int j = 0; j < aud[0].length;j++){
                f.write(aud[i][j]);
            }
            f.write('\r');
            f.write('\n');
            
        }  
    }
    /*
    public static int Best(char[][] aud, int row, int quan){
        int middle = aud[row].length / 2;
        int left = 999;
        int right = 999;
        for(int i = middle;i >= 0 + quan;i--){
            if(aud[row][i] == '#'){
                boolean avial = true;
                for(int j = 1; j <= quan;j++){
                    if(aud[row][i+j] == '#'){
                        avial = true;
                    }
                    else{
                        avial = false;
                    }
                        
                }
                if(avial){
                    left = i;
                    break;
                }
            }
        }
        for(int i = middle; i < aud[row].length - quan;i++){
            if(aud[row][i] == '#'){
                boolean avial = true;
                for(int j = 1; j <= quan;j++){
                    if(aud[row][i+j] == '#'){
                        avial = true;
                    }
                    else{
                        avial = false;
                    }
                }
                if(avial){
                    right = i;
                    break;
                }
            }
        }
        if(left == 999)
            return 999;
        if(Math.abs(middle - left ) < Math.abs(middle - right)){
            return left + quan + 1;
        }
        else
        {
            return right;
        }
        //return Math.min(right,left - quan);
    }
    */
    //looks for seat closets to the middle
    public static int findBest(char[][] aud, int row, int quan){
        int middle = (aud[0].length -1) / 2;
        for(int i = 0; i <= middle;i++){
            //goes back from middle
            if(available(aud,row, middle - i,quan)){
                return middle - i;
            }
            //goes forward from middle
            else if(available(aud,row, middle + i + 1,quan)){
                return middle + i+ 1;
            }
        }
        //returned when no seats are found on the row
        return 999;
    }
    
    //Used to foramt data as a table
    public static void print() throws IOException{
        //open, reserved
        int[] tmp = new int[2];
        int totalR = 0;
        int totalO = 0;
        int totalS = 0;
        
        //Prints headers
        System.out.print("\nAuditorium: ");
        System.out.print(" Reserved ");
        System.out.print("Open ");
        System.out.print(" Sales");
        
        //First row with data
        System.out.print("\nAuditorium 1 ");
        count(tmp,new File("A1.txt"));
        System.out.print(tmp[1] + "       ");
        System.out.print(tmp[0] + "    ");
        System.out.print("$" + (tmp[1] * 7) + "   ");
        totalR += tmp[1];
        totalO += tmp[0];
        tmp[0] = 0;
        tmp[1] = 0;
        
        //Seconds row
        System.out.print("\nAuditorium 2 ");
        count(tmp,new File("A2.txt"));
        System.out.print(tmp[1] + "       ");
        System.out.print(tmp[0] + "    ");
        System.out.print("$" + (tmp[1] * 7) + "   ");
        totalR += tmp[1];
        totalO += tmp[0];
        tmp[0] = 0;
        tmp[1] = 0;
        
        //third row
        System.out.print("\nAuditorium 3 ");
        count(tmp,new File("A3.txt"));
        System.out.print(tmp[1] + "       ");
        System.out.print(tmp[0] + "    ");
        System.out.print("$" + (tmp[1] * 7) + "   ");
        totalR += tmp[1];
        totalO += tmp[0];
        tmp[0] = 0;
        tmp[1] = 0;
        
        totalS = totalR * 7;
        
        //prints totals
        System.out.print("\nTotal        ");
        System.out.print(totalR + "      ");
        System.out.print(totalO + "   $");
        System.out.print(totalS + "   ");
        
        
        
    }
}
