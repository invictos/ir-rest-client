# IRRest Client

Ceci est le client pour le projet **fr.tvmp.irrest**


## Package vers un .jar
```bash
mvn clean compile assembly:single
```

## Execution
```bash
#BasePath=http://127.0.0.1:8080/tvmp/api
java -jar target/ir-rest-client-1.0-SNAPSHOT-jar-with-dependencies.jar

#On peut passer le basePath de l'API en argument
java -jar target/ir-rest-client-1.0-SNAPSHOT-jar-with-dependencies.jar http://ipv4.ovh:2803/tvmp/api
```