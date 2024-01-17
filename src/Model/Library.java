package Model;

import java.util.Scanner;

public class Library {

    protected Scanner sc;

    public Library() {
        sc = new Scanner(System.in);
    }

    public String getString(String mes) {
        System.out.print(mes);
        return sc.nextLine();
    }

    public int getInt(String prompt, int m, int n) {
        int a;
        while (true) {
            try {
                System.out.print(prompt);
                String s = sc.nextLine();
                a = Integer.parseInt(s);
                if (a >= m && a <= n) {
                    break;
                } else {
                    System.out.println("Please enter a number between " + m + " and " + n);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return a;
    }
}
