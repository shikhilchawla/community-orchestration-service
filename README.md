# community-orchestration-service
Service to orchestrate different flows in my-community app

Below are the steps to build and deploy the service along with the envoy side-car proxy:

Build and compile:
- mvn clean package

Build, tag and push image for side-car:
- cd k8s/envoy
- docker build -t orch-envoy-sidecar-proxy .
- docker tag orch-envoy-sidecar-proxy localhost:5050/orch-envoy-sidecar-proxy
- docker push localhost:5050/orch-envoy-sidecar-proxy

Build, tag and push image for orchestration service:
- cd ../.. 
- docker build -t community-orch-svc .
- docker tag community-orch-svc localhost:5050/community-orch-svc
- docker push localhost:5050/community-orch-svc

To deploy the service on Kubernetes cluster (e.g local K8 cluster), run the following command:
- kubectl apply -f k8s/deployment.yml

Once the service is deployed you can check if the service is healthy by calling the below end-point:
- http://localhost:32000/my-community/health