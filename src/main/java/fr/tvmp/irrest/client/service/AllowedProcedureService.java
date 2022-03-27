package fr.tvmp.irrest.client.service;

import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.client.procedures.Tuto;
import fr.tvmp.irrest.client.procedures.choice.Choice;
import fr.tvmp.irrest.client.procedures.cpam.*;
import fr.tvmp.irrest.common.dto.utilisateur.UserRole;

import java.util.ArrayList;
import java.util.List;

public class AllowedProcedureService {
    static public List<Choice> allowedProcedure(UserRole role){
        List<Choice> possibleChoices = new ArrayList<>(List.of(new Procedure[]{
                new Authentification(),
                new Tuto()
        }));


        if(role == null){
            return possibleChoices;
        }

        possibleChoices.add(new Deconnexion());

        switch(role) {
            case PATIENT:
                possibleChoices.addAll(List.of(new Procedure[]{
                        new VoirMesRemboursements(),
                        new VoirMonDossierAdministratif(),
                        new VoirMonDossierMedical()
                }));
                break;
            case MEDECIN:
                possibleChoices.addAll(List.of(new Procedure[]{
                        new CreerConsultation(),
                        new VoirDossierMedical(),
                        new CreerTraitement()
                }));
                break;
            case ADMINISTRATIF:
                possibleChoices.addAll(List.of(new Procedure[]{
                        new AjouterUnPatient(),
                        new VoirDossierAdministratif(),
                        new VoirRemboursements()
                }));
                break;
            default:
                throw new RuntimeException("Role not implemented");
        }

        return possibleChoices;
    }
}
