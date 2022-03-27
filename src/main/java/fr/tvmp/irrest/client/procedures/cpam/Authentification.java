package fr.tvmp.irrest.client.procedures.cpam;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.Procedure;
import fr.tvmp.irrest.client.procedures.choice.ChoiceList;
import fr.tvmp.irrest.client.procedures.choice.ChoiceProxy;
import fr.tvmp.irrest.client.service.StringFormaterUtil;
import fr.tvmp.irrest.common.dto.auth.CredentialsDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserDTO;
import fr.tvmp.irrest.common.dto.utilisateur.UserPublicDTO;

public class Authentification extends Procedure {
    @Override
    protected void execute() {
        Procedure procedure = new ChoiceList(
                ChoiceProxy.buildFromDTOList(
                        Context.getCpamService().getAllUsers(),
                        StringFormaterUtil::userPublic
                )
        ).run();

        UserPublicDTO userPublicDTO = procedure.getResult(ChoiceProxy.class)
                .map(c -> c.getUnderlyingObject(UserPublicDTO.class))
                .orElse(null);

        if(userPublicDTO == null)
            return;

        display(StringFormaterUtil.userPublic(userPublicDTO));

        display("Mot de passe ?");
        String password = waitString();

        display(password);

        UserDTO user = Context.getCpamService().authUser(new CredentialsDTO(userPublicDTO.getId(), password));

        display(user.toString());
    }

    @Override
    public String getPickerName(){
        return "Authentification";
    }
}