package fr.tvmp.irrest.client.procedures;

import fr.tvmp.irrest.common.dto.utilisateur.UserRole;

import java.util.List;

public class Tuto extends Procedure{
    @Override
    protected void execute() {
        display("Tutoriel");
        display("Authentifiez vous & laissez vous guider !");

        waitEnter();
    }
}
