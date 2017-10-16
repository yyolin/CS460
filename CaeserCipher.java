/*
 * 
 */
package cs460_hw1;

import java.util.Scanner;

public class CaeserCipher {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int res = 0;
        while (res != 4) {
            System.out.print("Options: \n1. Encrypt\n2. Decrypt\n" +
                    "3. Frequency Attack\n4. Exit\nEnter: ");
            res = input.nextInt();
            input.nextLine();
            System.out.println("");
            int n = 0;
            switch(res) {
                case 1:
                    System.out.print("Enter plaintext to encrypt: ");
                    String plaintext = input.nextLine();
                    System.out.print("Enter number of letter to jump: ");
                    n = input.nextInt();
                    input.nextLine();
                    System.out.println("ciphertext: " + 
                            encrypt(plaintext, (n % 26)));
                    break;
                case 2:
                    System.out.print("Enter ciphertext to decrypt: ");
                    String ciphertext = input.nextLine();
                    System.out.print("Enter number of letter jumped: ");
                    n = input.nextInt();
                    input.nextLine();
                    System.out.println("plaintext: " + 
                            decrypt(ciphertext, (n % 26)));
                    break;
                case 3:
                    System.out.print("Enter ciphertext: ");
                    String text = input.nextLine();
                    double lowest = Double.MAX_VALUE;
                    String lowestText = "";
                    for (int i = 0; i < 26; i++) {
                        String decoded = decrypt(text, i);
                        double ent = freq(decoded);
                        if(ent < lowest) {
                            lowest = ent;
                            lowestText = decoded;
                        }
                    }
                    System.out.print("Decoded text: " + lowestText);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input.");
            }
            System.out.println("");
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
                
                //loop back when it is bigger than Z or z
                if((re2[i] > 'Z' && re2[i] < 'a') || (re2[i] > 'z')) {
                    re2[i] = (char) (re2[i] - 26);
                }
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
                
                //loop back when it is less than A or a
                if((re2[i] < 'A') || (re2[i] > 'Z' && re2[i] < 'a')) {
                    re2[i] = (char) (re2[i] + 26);
                }
            }

            result = result + re2[i];
        }
        return result;
    }
    
    private static double freq(String text) {
        double[] f = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 
            0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 
            0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 
            0.02758, 0.00978, 0.02360, 0.00150, 
            0.01974, 0.00074};
        
        double res = 0;
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if (c >= 'a' && c <= 'z') {
                res += -Math.log(f[c - 'a']);
            } else if (c >= 'A' && c <= 'Z') {
                res += -Math.log(f[c - 'A']);
            }
        }
        return res;
    }
}
