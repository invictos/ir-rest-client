package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MAJAdresse extends Procedure {
    PatientDTO patient;

    @Override
    protected void execute() {
        display("L'adresse est actuellement: "+ patient.getAdresse());

        display("Rue ?");
        String rue = waitString();

        display("Ville ?");
        String ville = waitString();

        Adresse newAdresse = Context.getCpamService().putPatientAdresse(patient.getId(), new Adresse(rue, ville));
        patient.setAdresse(newAdresse);

        display("Nouvelle Adresse: "+ newAdresse);
    }

    @Override
    public String getPickerName() {
        return "Mise a jour de l'Adresse";
    }
}
