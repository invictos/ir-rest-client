package fr.tvmp.irrest.client.procedures.choice;

import fr.tvmp.irrest.client.procedures.Procedure;

import java.util.ArrayList;
import java.util.List;

/**
 * Display a list of choice & returns the selected one
 */
public class ChoiceList extends Procedure {

    List<Choice> list;
    boolean clearBeforeChoice;

    public ChoiceList(List<Choice> list) {
        this(list, true);
    }

    public ChoiceList(Choice... list) {
        this(List.of(list), true);
    }

    public ChoiceList(List<Choice> choiceList, boolean clearBeforeChoices) {
        this.list = new ArrayList<>(choiceList);
        this.clearBeforeChoice = clearBeforeChoices;

        this.list.add(new Quitter());
    }

    @Override
    protected void execute() {
        Choice choice;

        do {
            if(clearBeforeChoice)
                clear();

            choice = askChoice();

        }while (choice == null);

        setResult(choice);
    }

    /**
     * Finds what to do next
     * @return the procedure selected by the user, null otherwise
     */
    public Choice askChoice(){
        displayProcedureList();

        int choix;

        try{
            choix = waitInt();
        }catch (RuntimeException exception){
            return null;
        }

        try{
            return list.get(choix-1);
        }catch (IndexOutOfBoundsException exception){
            return null;
        }
    }

    /**
     * Shows the procedure list, returns the inputted number
     * @return Integer
     */
    private void displayProcedureList(){
        display("Choix ?");

        int size = list.size();
        for (int i = 0; i < size; i++) {
            display(String.format("- [%d] %s", i+1, list.get(i).getPickerName()));
        }
    }

    /**
     * Choice used as a leave option
     */
    private static class Quitter implements Choice{
        @Override
        public String getPickerName() {
            return "Quitter";
        }

        @Override
        public boolean isQuitter() {
            return true;
        }
    }
}