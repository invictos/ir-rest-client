package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.common.Banque;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserDTO;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class MAJBanque extends Procedure {
    PatientDTO patient;

    @Override
    protected void execute() {
        display("L'IBAN du patient est actuellement: "+ patient.getBanque().getIban());
        display("Nouveau IBAN ?");
        String iban = waitString();

        Banque newBanque = Context.getCpamService().putPatientBanque(patient.getId(), new Banque(iban));
        patient.setBanque(newBanque);

        display("Nouveau IBAN: "+ newBanque.getIban());
    }

    @Override
    public String getPickerName() {
        return "Mise a jour de l'IBAN";
    }
}
