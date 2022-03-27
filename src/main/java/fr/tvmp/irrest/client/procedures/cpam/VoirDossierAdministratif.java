package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.client.procedures.choice.ChoiceList;
import fr.tvmp.irrest.common.dto.utilisateur.AdministratifDTO;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserDTO;

import java.util.List;

public class VoirDossierAdministratif extends Procedure {
    @Override
    protected void execute() {
        display("NSS du patient ?");
        String nss = waitString();

        PatientDTO patient = Context.getCpamService().getPatient(nss);

        display(patient.toString());

        Procedure procedure = new ChoiceList(List.of(new Procedure[]{
                new MAJBanque(patient),
                new MAJAdresse(patient)
        })).run().getResult(Procedure.class).orElse(null);

        if(procedure==null)
            return;

        procedure.run();
    }

    @Override
    public String getPickerName() {
        return "Voir le dossier administratif d'un patient";
    }
}
