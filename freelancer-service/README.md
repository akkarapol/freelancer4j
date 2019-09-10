# Freelance4J Inc. Cloud Native Development

 ## 3. Deploy the Freelancer4J Freelancer-Service Microservices to OCP
         
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
     
