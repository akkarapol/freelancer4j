## Deploy the Freelancer4J Project-Service Microservices to OCP
          
  1. In a terminal, change directory to the root of the project service project   
         
     ```          
     $ cd ~/freelancer4j/project-service          
     ```
          
  2. Deploy an instance of Mongo on OCP using the project-service-mongodb-persistent.yaml template in the project-service/ocp directory in the directory:
         
     ``` 
     $ oc process -f ocp/project-service-mongodb-persistent.yaml \
     -p PROJECT_DB_USERNAME=mongo \
     -p PROJECT_DB_PASSWORD=mongo | oc create -f - -n $FREELANCER4J_PRJ
     ```
     
  3. Create a ConfigMap with the configuration for the project service application: 
  
     ```
     $ oc create configmap project-service --from-file=etc/app-config.yml -n $FREELANCER4J_PRJ
     ```
  
  4. Deploy the project service application on OpenShift using the Fabric8 Maven plug-in:
  
     ```
     $ mvn clean fabric8:deploy -Popenshift -Dfabric8.namespace=$FREELANCER4J_PRJ
     ```
  5. Monitor the deployment of the project-service:
     
     ```
     $ oc get pods -n $FREELANCER4J_PRJ -w
     ```
  6. Test the project service.
  
     a. Retrieve the URL of the project service application:
     
     ```
     $ export PROJECT_URL=http://$(oc get route project-service -n $FREELANCER4J_PRJ -o template --template='{{.spec.host}}')
     ```
     
     b.Retrieve all projects:
     
     ```
     $ curl -X GET "$PROJECT_URL/projects"
     ```
     
     b.Retrieve a project:
     
     ```
     $ curl -X GET "$PROJECT_URL/projects/1"
     ```
     
     c.Retrieve project status:
     
     ```
     $ curl -X GET "$PROJECT_URL/projects/status/open"
     ```
     
