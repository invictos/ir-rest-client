package fr.tvmp.irrest.client.procedures;

import fr.tvmp.irrest.client.Context;
import fr.tvmp.irrest.client.procedures.choice.Choice;
import fr.tvmp.irrest.client.procedures.choice.ChoiceList;
import fr.tvmp.irrest.client.procedures.cpam.Authentification;
import fr.tvmp.irrest.client.service.AllowedProcedureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainMenu extends Procedure{

    @Override
    protected void execute() {

        Optional<Procedure> procedure;
        do{
            display("Bienvenue sur le serveur CPAM!");

            Procedure choiceProcedure = new ChoiceList(
                AllowedProcedureService.allowedProcedure(Context.getCpamService().getCurrentRole())
            ).run();

            procedure = choiceProcedure.getResult(Procedure.class);

            try{
                procedure.ifPresent(Procedure::run);
            }catch(Exception err){
                System.out.println(err.toString());
            }

        }while(procedure.isPresent()); //We stop only when no procedure has been found (ie we quit)

    }
}
