package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.client.service.StringFormaterUtil;
import fr.tvmp.irrest.common.dto.remboursement.RemboursementDTO;
import fr.tvmp.irrest.common.dto.utilisateur.PatientDTO;

public class VoirRemboursements extends Procedure {
    @Override
    protected void execute() {
        display("NSS du patient ?");
        String nss = waitString();

        PatientDTO patient = Context.getCpamService().getPatient(nss);
        display(StringFormaterUtil.patient(patient));
        
        RemboursementDTO[] rembs = Context.getCpamService().getRemboursementsFromPatient(patient.getId());

        for(RemboursementDTO r : rembs){
            display("- "+ StringFormaterUtil.remboursement(r));
        }
    }

    @Override
    public String getPickerName() {
        return "Voir les remboursements d'un patient";
    }
}
