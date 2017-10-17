/*
 * 
 */
package cs460_hw1;

import java.util.ArrayList;
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
                    plaintext = plaintext.toUpperCase();
                    System.out.println("ciphertext: " + 
                            encrypt(plaintext, (n % 26)));
                    break;
                case 2:
                    System.out.print("Enter ciphertext to decrypt: ");
                    String ciphertext = input.nextLine();
                    System.out.print("Enter number of letter jumped: ");
                    n = input.nextInt();
                    input.nextLine();
                    ciphertext = ciphertext.toUpperCase();
                    System.out.println("plaintext: " + 
                            decrypt(ciphertext, (n % 26)));
                    break;
                case 3:
                    System.out.print("Enter ciphertext: ");
                    String text = input.nextLine();
                    System.out.print("Number of possible plaintext to show: ");
                    int possible = input.nextInt();
                    input.nextLine();
                    double[] freqList = new double[26];
                    String[] textList = new String[26];
                    for (int i = 0; i < 26; i++) {
                        String decodedText = decrypt(text, i);
                        double ent = freq(decodedText);
                        freqList[i] = ent;
                        textList[i] = decodedText;
                    }
                    if(possible > 26) {
                        possible = 26;
                    }
                    sort(freqList, textList);
                    System.out.println("Results: ");
                    for (int i = 0; i < possible; i++) {
                        System.out.println(textList[i]);
                    }
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
                if(re2[i] > 'Z') {
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
                if(re2[i] < 'A') {
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
    
    private static void sort(double[] f, String[] t) {
        for(int i = f.length - 1; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (f[j - 1] > f[j]) {
                    double tempF = f[j - 1];
                    f[j - 1] = f[j];
                    f[j] = tempF;
                    
                    String tempT = t[j - 1];
                    t[j - 1] = t[j];
                    t[j] = tempT;
                }
            }
        }
    }
}
