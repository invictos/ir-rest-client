package fr.tvmp.irrest.client.procedures;

import fr.tvmp.irrest.client.procedures.choice.Choice;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public abstract class Procedure implements Choice {
    Object result;

    abstract protected void execute();

    public Procedure run(){
        clear();
        execute();
        return this;
    }

    public <T> Optional<T> getResult(Class<T> type) {
        T res = null;
        try{
            res = type.cast(result);
        }catch (ClassCastException ignored) {
        }
        return Optional.ofNullable(res);
    }

    protected void setResult(Object obj){
        result = obj;
    }

    static Scanner scanner = new Scanner(System.in);

    @Override
    public String toString(){
        return getClass().getSimpleName();
    }

    static protected void display(String string){
        System.out.println(string);
    }

    static protected void waitEnter(){
        waitEnter("Appuyez sur entrez pour continuer");
    }

    static protected void waitEnter(String text){
        if(text != null)
            display(text);
        scanner.nextLine();
    }

    static protected Integer waitInt(){
        Integer res = scanner.nextInt();
        scanner.nextLine();
        return res;
    }

    static protected String waitString(){
        return scanner.nextLine();
    }

    static protected void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {}
    }

    @Override
    public String getPickerName(){
        return toString();
    }
}