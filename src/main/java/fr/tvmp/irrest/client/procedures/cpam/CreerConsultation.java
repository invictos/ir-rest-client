package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;

public class CreerConsultation extends Procedure {
    @Override
    protected void execute() {
        display("Téléverser une consultation");
        display("Patient (NSS) à rembourser ?");
        String nss = waitString();

        Context.getCpamService().createConsultation(
                Context.getCpamService().getPatientFromSSN(nss).getId()
        );

        display("Téléversement enregistré");
    }

    @Override
    public String getPickerName() {
        return "Téléverser une consultation";
    }
}
