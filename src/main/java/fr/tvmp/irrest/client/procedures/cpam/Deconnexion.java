package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;

public class Deconnexion extends Procedure {
    @Override
    protected void execute() {
        display("Déconnexion en cours");
        Context.getCpamService().disconnect();
        waitEnter();
    }

    @Override
    public String getPickerName() {
        return "Déconnexion";
    }
}
