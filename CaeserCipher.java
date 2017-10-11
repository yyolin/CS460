/*
 * 
 */
package cs460_hw1;

import java.util.Scanner;

public class CaeserCipher {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String res = "";
        while (!res.equals("Exit")) {
            System.out.print("Enter Encrypt/ Decrypt/ Exit: ");
            res = input.nextLine();
            if(res.length() > 1) {
                res = res.substring(0, 1).toUpperCase() + res.substring(1);
            }
            int n = 0;
            switch(res) {
                case "Encrypt":
                    System.out.print("Enter plaintext to encrypt: ");
                    String plaintext = input.nextLine();
                    System.out.print("Enter number of letter to jump: ");
                    n = input.nextInt();
                    input.nextLine();
                    System.out.println("ciphertext: " + 
                            encrypt(plaintext, (n % 26)));
                    break;
                case "Decrypt":
                    System.out.print("Enter ciphertext to decrypt: ");
                    String ciphertext = input.nextLine();
                    System.out.print("Enter number of letter jumped: ");
                    n = input.nextInt();
                    input.nextLine();
                    System.out.println("plaintext: " + 
                            decrypt(ciphertext, (n % 26)));
                    break;
                case "Exit":
                    break;
                default:
                    System.out.println("Invalid Input.");
            }
        }
    }
    
    //This method encrypt the plaintext
    private static String encrypt(String line, int n){
        String result = "";
        char[] re2 = line.toCharArray();
        for(int i = 0; i < re2.length; i++) {
            if(re2[i] == (char)32) {
                //do nothing because it is just space
            } else {
                re2[i] = (char) (re2[i] + n);
            }
            
            //loop back when it is bigger than Z or z
            if((re2[i] > 90 && re2[i] < 97) || (re2[i] > 122)) {
                re2[i] = (char) (re2[i] - n);
            }
            
            result = result + re2[i];
        }
        return result;
    }
    
    //This method decrypt the ciphertext
    private static String decrypt(String line, int n){
        String result = "";
        char[] re2 = line.toCharArray();
        for(int i = 0; i < re2.length; i++) {
            if(re2[i] == (char)32) {
                //do nothing because it is just space
            } else {
                re2[i] = (char) (re2[i] - n);
            }
            
            //loop back when it is less than A or a
            if((re2[i] < 65) || (re2[i] > 90 && re2[i] < 97)) {
                re2[i] = (char) (re2[i] + 26);
            }
            
            result = result + re2[i];
        }
        return result;
    }
}
