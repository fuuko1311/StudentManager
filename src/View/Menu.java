package View;

import Model.Library;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> choices;
    Library week3 = new Library();

    public Menu() {
        choices = new ArrayList<>();
    }

    public Menu(String td, String[] mc){
        title = td;
        choices = new ArrayList<>();
        for(String s : mc) choices.add((T) s);
    }

    //-------------------------------------------
    public void display(){
        System.out.println(title);
        System.out.println("-------------------");
        for(int i = 0; i< choices.size(); i++){
            System.out.println((i+1)+". "+ choices.get(i));
        }
        System.out.println("-------------------");
    }
    //----------------------------------------------------
    public int getSelected(){
        display();
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }
//-------------------------------------------
    public abstract void execute(int n);
//-------------------------------------------
    public void run() {
        while (true) {
            int n = getSelected();
            execute(n);
            if (n > choices.size()) {
                break;
            }
        }
    }
//-------------------------------------------
}