# Freelance4J Inc.

# 1. Clone Source Code

   1. Open a terminal window and change to the lab folder in the home directory:  

      ```
      $ cd ~/lab 
      ```

   2. Clone the Git repositories containing the source code: 

      ```
      $ git clone https://github.com/akkarapol/freelancer4j.git
      ```

#2. Deploy the Freelancer4J Microservices to OpenShift Container Platform

   1. log in to OpenShift Container Platform with the oc client:
      
      ```
      $ export OCP_SERVER_URL=<<insert server URL provided by instructor>>

      $ oc login --insecure-skip-tls-verify --server=${OCP_SERVER_URL}
      ```
      
  2. Create the OpenShift projects to which the application is to be deployed:
  
     ```
     $ export FREELANCER4J_PRJ=<<insert your project name>>

     $ oc new-project ${FREELANCER4J_PRJ}
     ```
