package fr.tvmp.irrest.client;

import fr.tvmp.irrest.client.service.cpam.JerseyCPAMService;
import lombok.Getter;
import lombok.Setter;

public class Context {
    @Getter
    @Setter
    static private JerseyCPAMService cpamService;

    static public void create(String baseURL){
        setCpamService(new JerseyCPAMService(baseURL));
    }
}
