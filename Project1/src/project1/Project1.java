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
        File fileT = new File("");
        char[][] aud;
        boolean running = true;
        while(running){
        System.out.println("Which auditoium would you like to book? 1, 2, 3 or exit(4)");
        int input = inputS.nextInt();
        if(input == 1 || input ==2 || input == 3){
            if(input == 1){
                file = new File("A1.txt");
                fileT = new File("A1.txt");
                System.out.println("File 1 opened");
            }
            if(input == 2){
                file = new File("A2.txt");
                fileT = new File("A2.txt");
                System.out.println("File 2 opened");
            }
            if(input == 3){
                file = new File("A3.txt");
                fileT = new File("A3.txt");
            }

            //System.out.println(fileIn.next(),);
            aud = readAud(file);
            display(aud);
            System.out.println("" + aud[2][3]);
            
            
        }
        else if(input == 4){
            //Exit
            running = false;
        }
        else{
            
        }
        }
        
        
        
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
        System.out.println(j);
        char[][] aud  = new char[j][line.length()];
        System.out.println(line.length());
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
    
    public static void display(char[][] aud){
        System.out.print(" ");
        for(int i = 1; i < aud[0].length;i++){
            System.out.print(i%10);
        }
        System.out.print("\n");
        for(int i = 0; i < aud.length;i++){
            System.out.print(i+1);
            for(int j = 0; j < aud[0].length;j++){
                System.out.print("" + aud[i][j]);
            }
            System.out.print("\n");
        }
        
    }
    
}
