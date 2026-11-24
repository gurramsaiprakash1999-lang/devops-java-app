pipeline {
    agent any
    tools {
        maven 'Default'
    }
    stages {
        stage('1. Checkout Code') {
            steps {
                checkout scm
            }
        }
        stage('2. Maven Build & Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('3. SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=admin -Dsonar.password=admin'
            }
        }
        stage('4. Build Docker Image') {
            steps {
                sh 'docker build --no-cache -t java-app:latest .'
            }
        }
        stage('5. Deploy to Kubernetes Cluster') {
            steps {
                sh '''
                    export PATH=$PATH:/usr/local/bin
                    export MINIKUBE_HOME=/home/ubuntu
                    export KUBECONFIG=/var/lib/jenkins/.kube/config
                    
                    # Load Docker image into Minikube engine
                    minikube image load java-app:latest
                    
                    # Apply Manifests & perform rolling restart
                    kubectl apply -f k8s-deployment.yaml
                    kubectl rollout restart deployment/java-app-deployment
                    kubectl rollout status deployment/java-app-deployment --timeout=60s
                '''
            }
        }
    }
}
