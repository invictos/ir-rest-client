package fr.tvmp.irrest.client.service;

import fr.tvmp.irrest.common.dto.CPAMDto;
import fr.tvmp.irrest.common.dto.remboursement.ARemboursableDTO;
import fr.tvmp.irrest.common.dto.remboursement.RemboursementDTO;
import fr.tvmp.irrest.common.dto.traitement.DonneeMedicaleDTO;
import fr.tvmp.irrest.common.dto.traitement.TraitementDTO;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserPublicDTO;

public class StringFormaterUtil {
    static public String user(UserDTO user){
        return String.format("%s %s: %s", user.getPrenom(), user.getNom(), user.getId());
    }

    static public String patient(PatientDTO user){
        return String.format("Patient: %s %s, NSS n°%s", user.getPrenom(), user.getNom(), user.getNss());
    }

    static public String userPublic(UserPublicDTO user){
        return String.format("%s %s: %s", user.getPrenom(), user.getNom(), user.getId());
    }

    static public String remboursement(RemboursementDTO remb){
        Float prixTotal = remb.getRemboursables().stream()
                .map(ARemboursableDTO::getPrix)
                .reduce(0F, Float::sum);

        Float rembourse = remb.getRemboursables().stream()
                .map(ARemboursableDTO::getRemboursement)
                .reduce(0F, Float::sum);

        return String.format("Remboursement n°%s, total: %.2f€, base CPAM: %.2f€ ", remb.getId(), prixTotal, rembourse);
    }

    public static String traitement(TraitementDTO traitement) {
        StringBuilder sb = new StringBuilder(String.format("[%s] Préscrit par: %s\n",traitement.getId(), traitement.getMedecin()));

        for(DonneeMedicaleDTO donnee: traitement.getDonnees()){
            sb.append(String.format("    * %s\n",donnee(donnee)));
        }

        return sb.toString();
    }

    public static String donnee(DonneeMedicaleDTO donneeMedicale) {
        return String.format("[%s] %s", donneeMedicale.getType(), donneeMedicale.getValue());
    }
}
