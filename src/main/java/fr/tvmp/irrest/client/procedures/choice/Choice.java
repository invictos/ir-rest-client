package fr.tvmp.irrest.client.procedures.choice;

public interface Choice {
    String getPickerName();

    default boolean isQuitter(){
        return false;
    }
}