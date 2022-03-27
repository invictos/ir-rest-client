package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.client.procedures.choice.ChoiceList;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;

import java.util.List;

public class VoirMonDossierAdministratif extends Procedure {
    @Override
    protected void execute() {
        PatientDTO patient = (PatientDTO) Context.getCpamService().getUser();

        display(patient.toString());

        Procedure procedure = new ChoiceList(List.of(new Procedure[]{
                new MAJAdresse(patient),
                new MAJMaBanque()
        })).run().getResult(Procedure.class).orElse(null);

        if(procedure==null)
            return;

        procedure.run();
    }

    @Override
    public String getPickerName() {
        return "Voir mon dossier administratif";
    }

    static class MAJMaBanque extends Procedure{

        @Override
        protected void execute() {
            display("Pour changer votre IBAN, veuillez contacter votre CPAM.");
            waitEnter();
        }

        @Override
        public String getPickerName() {
            return "Changer mon IBAN";
        }
    }
}
