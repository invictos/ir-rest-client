package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.client.service.StringFormaterUtil;
import fr.tvmp.irrest.common.dto.traitement.TraitementDTO;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;

import java.util.List;

public class VoirMonDossierMedical extends Procedure {
    @Override
    protected void execute() {
        PatientDTO patient = (PatientDTO) Context.getCpamService().getUser();

        List<TraitementDTO> traitements = Context.getCpamService().getTraitementsFromPatient(patient.getId());

        display("Liste des traitements de "+patient.getNom());
        for(TraitementDTO traitement: traitements){
            display("- "+StringFormaterUtil.traitement(traitement));
        }
    }

    @Override
    public String getPickerName() {
        return "Voir mon dossier médical";
    }
}
