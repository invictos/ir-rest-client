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
import fr.tvmp.irrest.common.dto.utilisateur.*;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import java.util.List;
import java.util.UUID;

public class JerseyCPAMService implements CPAMService {

    private final boolean LOG_REQUESTS = false;

    private final WebTarget baseTarget;
    private final Client client;

    private String token;

    @Getter
    private UserDTO user;

    public JerseyCPAMService(String baseURL){
        this.client = createClient();
        this.baseTarget = client.target(baseURL);
    }

    public UserRole getCurrentRole(){
        return user != null ? user.getRole() : null;
    }

    public Client createClient(){
        return ClientBuilder.newBuilder()
            .register((ClientRequestFilter) requestContext -> {
                //Hook for request
                if(LOG_REQUESTS)
                    System.out.printf("[%s] %s%n", requestContext.getMethod(), requestContext.getUri());

                //we add the token to every outbound request
                if(token != null){
                    requestContext.getHeaders().add("Authorization", "Bearer "+token);
                }
            })
            .register((ClientResponseFilter) (clientRequestContext, clientResponseContext) -> {
                //Hook for response
            })
            .register(JacksonJsonProvider.class)
            .build();
    }

    /**
     * Shorthand for Invocation creation: sets path & uses APPLICATION_JSON_TYPE
     * @param endpoint path to API endpoint
     * @return InvocationBuilder
     */
    public Invocation.Builder endpoint(String endpoint){
        return baseTarget.path(endpoint).request(MediaType.APPLICATION_JSON_TYPE);
    }

    /**
     * Shorthand for the object serializer
     * @param object object to serialize
     * @return Entity the Entity representing the APPLICATION_JSON_TYPE's serialized object
     */
    public <T> Entity<T> toEntity(T object){
        return Entity.entity(object, MediaType.APPLICATION_JSON_TYPE);
    }

    public PatientDTO getPatient(String nss){
        return (PatientDTO) getUser(getPatientFromSSN(nss).getId());
    }

    @Override
    public UserDTO getUser(UUID id) {
        return endpoint("/user/"+id).get(new GenericType<>(){});
    }

    @Override
    public List<UserPublicDTO> getAllUsers() {
        return endpoint("user/all").get(new GenericType<>(){});
    }

    @Override
    public PatientDTO postPatient(PatientNewDTO patient) {
        return endpoint("patients").post(toEntity(patient), PatientDTO.class);
    }

    @Override
    public UserPublicDTO getPatientFromSSN(String ssn) {
        return endpoint("patients/"+ssn).get(new GenericType<>(){});
    }

    @Override
    public Banque getPatientBanque(UUID patient) {
        return endpoint("patients/"+patient+"/banque").get(new GenericType<>(){});
    }

    @Override
    public Banque putPatientBanque(UUID patient, Banque banque) {
        return endpoint("patients/"+patient+"/banque").put(toEntity(banque), Banque.class);
    }

    @Override
    public Adresse getPatientAdresse(UUID patient) {
        return endpoint("patients/"+patient+"/adresse").get(new GenericType<>(){});
    }

    @Override
    public Adresse putPatientAdresse(UUID patient, Adresse adresse) {
        return endpoint("patients/"+patient+"/adresse").put(toEntity(adresse), Adresse.class);
    }

    @Override
    public RemboursementDTO[] getRemboursementsFromPatient(UUID patient) {
        return endpoint("remboursements/patient/"+patient).get(new GenericType<>(){});
    }

    @Override
    public RemboursementDTO getRemboursement(UUID remboursement) {
        return endpoint("remboursements/"+remboursement).get(new GenericType<>(){});
    }

    @Override
    public RemboursementDTO createConsultation(UUID patient) {
        return endpoint("/remboursements/patient/"+patient+"/consultation").post(null, RemboursementDTO.class);
    }

    @Override
    public List<TraitementDTO> getTraitementsFromPatient(UUID patient) {
        return endpoint("traitements/patient/"+patient).get(new GenericType<>(){});
    }

    @Override
    public TraitementDTO postTraitement(TraitementNewDTO traitement) {
        return endpoint("/traitements").post(toEntity(traitement), TraitementDTO.class);
    }

    @Override
    public TraitementDTO getTraitement(UUID traitement) {
        return endpoint("traitements/"+traitement).get(new GenericType<>(){});
    }

    @Override
    public DonneeMedicaleDTO getDonneeMedicale(UUID traitement, UUID donnee) {
        return endpoint("traitements/"+traitement+"/donnees/"+donnee).get(new GenericType<>(){});
    }

    @Override
    public DonneeMedicaleDTO postDonneeMedicale(UUID traitement, DonneeMedicaleNewDTO donnee) {
        return endpoint("traitements/"+traitement+"/donnees").post(toEntity(donnee), DonneeMedicaleDTO.class);
    }

    @Override
    public void deleteDonneeMedicale(UUID traitement, UUID donnee) {
        endpoint("traitements/"+traitement+"/donnees/"+donnee).delete();
    }


    @Override
    public TokenDTO auth(CredentialsDTO credentials) {
        return endpoint("auth").post(toEntity(credentials), TokenDTO.class);
    }

    @Override
    public void disconnect() {
        endpoint("auth/disconnect").get();
        token = null;
        user = null;
    }

    public UserDTO authUser(CredentialsDTO credentials){
        token = auth(credentials).getToken();

        user = getUser(credentials.getUuid());

        return user;
    }
}
