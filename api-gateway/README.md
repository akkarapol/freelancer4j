## Deploy the Freelancer4J API-Gateway Microservices to OCP

1. In a terminal, change directory to the root of the api gateway project   
         
     ```          
     $ cd ~/freelancer4j/api-gateway          
     ```       
  
  2. Retrieve the URL of the freelancer and project service:
     
     ```          
     $ export FREELANCER_URL=http://$(oc get route freelancer-service -n $FREELANCER4J_PRJ -o template --template='{{.spec.host}}')
     
     $ export PROJECT_URL=http://$(oc get route project-service -n $FREELANCER4J_PRJ -o template --template='{{.spec.host}}') 
     ```
  
  2. Display the URLs of the services:
     
     ```   
     $ echo $FREELANCER_URL
     
     $ echo $PROJECT_URL
     ```   
 
  3. Edit the file etc/project-defaults.yml.

  4. Copy this entry to the end of the file, and update the URL for the freelancer-service and project-service that you retrieved in the previous step:
     
     ```   
     freelancer:
       service:
         url: << update URL for $FREELANCER_URL >>
     project4j:
       service:
         url: << update URL for $PROJECT_URL >>
     ```   
  
  4. Create the ConfigMap with the configuration for the api-gateway service application: 
  
     ```
     $ oc create configmap api-gateway --from-file=etc/project-defaults.yml -n $FREELANCER4J_PRJ
     ```
  
  5. Deploy the application on OpenShift Container Platform with the Fabric8 Maven plug-in:
  
     ```
     $ mvn clean fabric8:deploy -Popenshift -Dfabric8.namespace=$FREELANCER4J_PRJ
     ```  
  
  6. Monitor the deployment of the inventory-service:    
  
     ```
     $ oc get pods -n $COOLSTORE_PRJ -w -n $FREELANCER4J_PRJ
     ``` 
  7. Test the project service.
  
     a. Retrieve the URL of the API Gateway:
     
     ```
     $ export API_GATEWAY=http://$(oc get route api-gateway -n $FREELANCER4J_PRJ -o template --template='{{.spec.host}}')
     ```  

     b. Retrieves a list of all projects
     
     ```
     $ curl -X GET "$API_GATEWAY/gateway/projects"
     ```
     
     c. Retrieves information for the given projectId
     
     ```
     $ curl -X GET "$API_GATEWAY/gateway/projects/1"
     ```
     
     d. Retrieves a list of projects with the given status (open, in_progress, completed, cancelled)
     
     ```
     $ curl -X GET "$API_GATEWAY/gateway/projects/status/cancelled"
     ```
     
     f. Retrieves a list of all freelancers
     
     ```
     $ curl -X GET "$API_GATEWAY/gateway/freelancers"
     ```
     
     i. Retrieves information for the given freelancer
     
     ```
     $ curl -X GET "$API_GATEWAY/gateway/freelancers/1"
     ```
     
