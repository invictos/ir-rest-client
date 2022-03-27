package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.client.procedures.choice.ChoiceList;
import fr.tvmp.irrest.common.dto.traitement.TraitementDTO;
import fr.tvmp.irrest.common.dto.traitement.TraitementNewDTO;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserPublicDTO;

import java.util.List;
import java.util.UUID;

public class CreerTraitement extends Procedure {
    @Override
    protected void execute() {
        display("Quel patient ? (NSS)");

        UserPublicDTO user = Context.getCpamService().getPatientFromSSN(waitString());

        TraitementDTO traitement = Context.getCpamService().postTraitement(
                new TraitementNewDTO(Context.getCpamService().getUser().getId(), user.getId(), List.of())
        );

        display("Nouveau traitement crée !");

        Procedure procedure = new ChoiceList(List.of(new Procedure[]{
                new VoirDossierMedical.AjouterUneDonnee(traitement),
                new VoirDossierMedical.SupprimerUneDonnee(traitement)
        })).run().getResult(Procedure.class).orElse(null);

        if(procedure==null)
            return;

        procedure.run();
    }

    @Override
    public String getPickerName() {
        return "Créer un traitement";
    }
}
