package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.client.procedures.choice.Choice;
import fr.tvmp.irrest.client.procedures.choice.ChoiceList;
import fr.tvmp.irrest.client.procedures.choice.ChoiceProxy;
import fr.tvmp.irrest.client.service.StringFormaterUtil;
import fr.tvmp.irrest.common.dto.traitement.*;
import fr.tvmp.irrest.common.dto.utilisateur.MedecinDTO;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserPublicDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VoirDossierMedical extends Procedure {
    @Override
    protected void execute() {
        display("NSS du patient ?");
        String nss = waitString();

        UserPublicDTO patient = Context.getCpamService().getPatientFromSSN(nss);

        List<TraitementDTO> traitements = Context.getCpamService().getTraitementsFromPatient(patient.getId());

        display("Liste des traitements de "+patient.getNom());
        for(TraitementDTO traitement: traitements){
            display("- "+ StringFormaterUtil.traitement(traitement));
        }

        ChoiceProxy choice = new ChoiceList(
                ChoiceProxy.buildFromDTOList(traitements,t -> t.getId().toString())
        ).run().getResult(ChoiceProxy.class).orElse(null);

        //Le médecin ne souhaite pas faire de modifications
        if(choice==null)
            return;

        TraitementDTO traitement = choice.getUnderlyingObject(TraitementDTO.class);

        Procedure procedure = new ChoiceList(List.of(new Procedure[]{
                new AjouterUneDonnee(traitement),
                new SupprimerUneDonnee(traitement)
        })).run().getResult(Procedure.class).orElse(null);

        if(procedure==null)
            return;

        procedure.run();
    }

    @Override
    public String getPickerName() {
        return "Voir le dossier médical d'un patient";
    }

    public static class AjouterUneDonnee extends Procedure {
        TraitementDTO traitement;

        public AjouterUneDonnee(TraitementDTO traitement) {
            this.traitement = traitement;
        }

        @Override
        protected void execute() {
            display("Type de donnée ? (1: NOTE/2: ORDONNANCE)");
            Integer choix = waitInt();

            display("Quel texte ajouter ?");
            String text = waitString();

            //Quick hack
            DonneeMedicaleType type = choix == 2 ? DonneeMedicaleType.ORDONANCE : DonneeMedicaleType.NOTE;

            Context.getCpamService().postDonneeMedicale(traitement.getId(), new DonneeMedicaleNewDTO(type, text));
        }

        @Override
        public String getPickerName() {
            return "Ajouter une donnée au traitement";
        }
    }

    public static class SupprimerUneDonnee extends Procedure {
        TraitementDTO traitement;

        public SupprimerUneDonnee(TraitementDTO traitement) {
            this.traitement = traitement;
        }

        @Override
        protected void execute() {
            ChoiceProxy choice = new ChoiceList(
                    ChoiceProxy.buildFromDTOList(traitement.getDonnees(), StringFormaterUtil::donnee)
            ).run().getResult(ChoiceProxy.class).orElse(null);

            if(choice==null)
                return;

            DonneeMedicaleDTO donnee = choice.getUnderlyingObject(DonneeMedicaleDTO.class);

            Context.getCpamService().deleteDonneeMedicale(traitement.getId(), donnee.getId());

            display("Donnée supprimée !");

            waitEnter();
        }

        @Override
        public String getPickerName() {
            return "Supprimer une donnée du traitement";
        }
    }
}
