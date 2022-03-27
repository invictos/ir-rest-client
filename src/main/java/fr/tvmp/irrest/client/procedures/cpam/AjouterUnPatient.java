package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import fr.tvmp.irrest.common.dto.utilisateur.PatientNewDTO;

public class AjouterUnPatient extends Procedure {
    @Override
    protected void execute() {
        display("Procédure d'ajout d'un nouveau patient");

        display("Veuillez saisir le prénom");
        String prenom = waitString();

        display("Veuillez saisir le nom");
        String nom = waitString();

        display("Veuillez saisir le mdp");
        String password = waitString();

        display("Veuillez saisir la rue");
        String rue = waitString();

        display("Veuillez saisir la ville");
        String ville = waitString();

        display("Veuillez saisir l'IBAN");
        String iban = waitString();

        display("Veuillez saisir le NSS");
        String nss = waitString();

        Context.getCpamService().postPatient(new PatientNewDTO(prenom, nom, password, new Adresse(rue, ville), nss, new Banque(iban)));

        display("Patient ajouté");
    }

    @Override
    public String getPickerName() {
        return "Ajouter un patient";
    }
}