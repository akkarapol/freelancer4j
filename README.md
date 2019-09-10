# Freelance4J Inc. Cloud Native Development

## 1. Clone Source Code

   1. Open a terminal window and change to the lab folder in the home directory:  

      ```
      $ cd ~/ 
      ```

   2. Clone the Git repositories containing the source code: 

      ```
      $ git clone https://github.com/akkarapol/freelancer4j.git
      ```

## 2. Create the OpenShift projects to OCP

   1. log in to OpenShift Container Platform with the oc client:
      
      ```
      $ export OCP_SERVER_URL=<<insert server URL provided by instructor>>

      $ oc login --insecure-skip-tls-verify --server=${OCP_SERVER_URL}
      ```
      
   2. Create the OpenShift projects to which the application is to be deployed:
  
      ```
      $ export FREELANCER4J_PRJ=<<insert your project name>>

      $ oc new-project $FREELANCER4J_PRJ
      ```
   3. Add the view role to the default service account:
   
      ```
      $ oc policy add-role-to-user view -z default -n $FREELANCER4J_PRJ
      ```
      
 ## 3. Deploy the Freelancer4J Microservices to OCP
 
  ### 3.1 Freelancer-Service
          
  1. In a terminal, change directory to the root of the freelancer service project   
         
     ```          
     $ cd ~/freelancer4j/freelancer-service          
     ```
          
  2. Deploy an instance of Postgresql on OCP using the freelancer-service-postgresql-persistent.yaml template in the freelancer-service/ocp directory in the directory:
         
     ``` 
     $ oc process -f ocp/freelancer-service-postgresql-persistent.yaml \
     -p FREELANCER_DB_USERNAME=freelancer \
     -p FREELANCER_DB_PASSWORD=freelancer \
     -p FREELANCER_DB_NAME=freelancerdb | oc create -f - -n $FREELANCER4J_PRJ
     ```

  3. Deploy the freelancer service application on OpenShift using the Fabric8 Maven plug-in:
  
     ```
     $ mvn clean fabric8:deploy -Popenshift -Dfabric8.namespace=$FREELANCER4J_PRJ
      ```
  4. Monitor the deployment of the freelancer-service:
     
     ```
     $ oc get pods -n $FREELANCER4J_PRJ -w
     ```
  6. Test the freelancer service.
  
     a. Retrieve the URL of the freelancer service application:
     
     ```
     $ export FREELANCER_URL=http://$(oc get route freelancer-service -n $FREELANCER4J_PRJ -o template --template='{{.spec.host}}')
     ```
     
     b.Retrieve all freelancers:
     
     ```
     $ curl -X GET "$FREELANCER_URL/freelancers"
     ```
     
     b.Retrieve a freelancer:
     
     ```
     $ curl -X GET "$FREELANCER_URL/freelancers/1"
     ```
     
  ### 3.2 Project-Service
          
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
     
     
  ### 3.3 API-Gateway
          
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
     $ curl -X GET "$API_GATEWAY//gateway/freelancers"
     ```
     
     i. Retrieves information for the given freelancer
     
     ```
     $ curl -X GET "$API_GATEWAY/gateway/freelancers/1"
     ```
     
