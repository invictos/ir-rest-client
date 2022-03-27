package fr.tvmp.irrest.client;

import fr.tvmp.irrest.client.procedures.MainMenu;

public class CPAMApplication {
    //private static final String DEFAULT_API_URL = "http://127.0.0.1:8080/tvmp/api";
    private static final String DEFAULT_API_URL = "http://ipv4.ovh:8080/tvmp/api";
    public static void main(String[] args) {
        loadContext(args);

        new MainMenu().run();

    }


    private static void loadContext(String[] args) {
        Context.create(args.length > 1 ? args[0] : DEFAULT_API_URL);
    }

}
