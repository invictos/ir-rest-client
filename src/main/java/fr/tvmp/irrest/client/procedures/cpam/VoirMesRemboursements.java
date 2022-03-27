package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.client.service.StringFormaterUtil;
import fr.tvmp.irrest.common.dto.remboursement.RemboursementDTO;

public class VoirMesRemboursements extends Procedure {
    @Override
    protected void execute() {

       RemboursementDTO[] rembs = Context.getCpamService().getRemboursementsFromPatient(Context.getCpamService().getUser().getId());

       for(RemboursementDTO r : rembs){
           display("- "+ StringFormaterUtil.remboursement(r));
       }
    }

    @Override
    public String getPickerName() {
        return "Voir mes remboursements";
    }
}
