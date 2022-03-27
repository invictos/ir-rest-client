package fr.tvmp.irrest.client.service.cpam;

import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.common.Banque;
import fr.tvmp.irrest.common.dto.auth.CredentialsDTO;
import fr.tvmp.irrest.common.dto.auth.TokenDTO;
import fr.tvmp.irrest.common.dto.remboursement.RemboursementDTO;
import fr.tvmp.irrest.common.dto.traitement.DonneeMedicaleDTO;
import fr.tvmp.irrest.common.dto.traitement.DonneeMedicaleNewDTO;
import fr.tvmp.irrest.common.dto.traitement.TraitementDTO;
import fr.tvmp.irrest.common.dto.traitement.TraitementNewDTO;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;
import fr.tvmp.irrest.common.dto.utilisateur.PatientNewDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserPublicDTO;

import java.util.List;
import java.util.UUID;

public interface CPAMService {
    UserDTO getUser(UUID id);
    List<UserPublicDTO> getAllUsers();

    PatientDTO postPatient(PatientNewDTO patient);
    UserPublicDTO getPatientFromSSN(String ssn);
    Banque getPatientBanque(UUID patient);
    Banque putPatientBanque(UUID patient, Banque banque);    
    Adresse getPatientAdresse(UUID patient);
    Adresse putPatientAdresse(UUID patient, Adresse adresse);

    RemboursementDTO[] getRemboursementsFromPatient(UUID patient);
    RemboursementDTO getRemboursement(UUID remboursement);
    RemboursementDTO createConsultation(UUID patient);

    List<TraitementDTO> getTraitementsFromPatient(UUID patient);
    TraitementDTO postTraitement(TraitementNewDTO traitement);
    TraitementDTO getTraitement(UUID traitement);
    DonneeMedicaleDTO getDonneeMedicale(UUID traitement, UUID donnee);
    DonneeMedicaleDTO postDonneeMedicale(UUID traitement, DonneeMedicaleNewDTO donnee);
    void deleteDonneeMedicale(UUID traitement, UUID donnee);

    TokenDTO auth(CredentialsDTO credentials);
    void disconnect();
}
