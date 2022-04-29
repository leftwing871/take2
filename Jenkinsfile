pipeline {
  agent any

  parameters {
    booleanParam(name : 'BUILD_DOCKER_IMAGE', defaultValue : true, description : 'BUILD_DOCKER_IMAGE')
    booleanParam(name : 'RUN_TEST', defaultValue : true, description : 'RUN_TEST')
    booleanParam(name : 'PUSH_DOCKER_IMAGE', defaultValue : true, description : 'PUSH_DOCKER_IMAGE')
    booleanParam(name : 'PROMPT_FOR_DEPLOY', defaultValue : false, description : 'PROMPT_FOR_DEPLOY')
    booleanParam(name : 'DEPLOY_WORKLOAD', defaultValue : true, description : 'DEPLOY_WORKLOAD')

    // CI
    string(name : 'AWS_ACCOUNT_ID', defaultValue : '<YOUR_AWS_ACCOUNT_ID>', description : 'AWS_ACCOUNT_ID')
    string(name : 'DOCKER_IMAGE_NAME', defaultValue : 'take2', description : 'DOCKER_IMAGE_NAME')
    string(name : 'DOCKER_TAG', defaultValue : '1.0.0', description : 'DOCKER_TAG')

    // CD
    // string(name : 'TARGET_SVR_USER', defaultValue : 'ec2-user', description : 'TARGET_SVR_USER')
    // string(name : 'TARGET_SVR_PATH', defaultValue : '/home/ec2-user/', description : 'TARGET_SVR_PATH')
    // string(name : 'TARGET_SVR', defaultValue : '10.0.3.61', description : 'TARGET_SVR')
  }

  environment {
    REGION = "ap-northeast-2"
    ECR_REPOSITORY = "${params.AWS_ACCOUNT_ID}.dkr.ecr.ap-northeast-2.amazonaws.com"
    ECR_DOCKER_IMAGE = "${ECR_REPOSITORY}/${params.DOCKER_IMAGE_NAME}"
    ECR_DOCKER_TAG = "${params.DOCKER_TAG}"
    ECR_DOCKER_IMAG_NAME = "${params.DOCKER_IMAGE_NAME}"
  }

  stages {
      stage('============ Build Jar ============') {
        when { expression { return params.BUILD_DOCKER_IMAGE } }



        steps {
            dir("${env.WORKSPACE}") {
                sh 'ls -al'
                sh 'chmod +x ./gradlew'
                sh './gradlew bootJar'
            }
        }

        post {
            always {
                echo "Docker build success!"
            }
        }
        // steps {
        //         echo '============ Build Docker Image ============'
        //     }
    }
    stage('============ Build Docker Image ============') {
        when { expression { return params.BUILD_DOCKER_IMAGE } }
        // agent { label 'build' }
        steps {
            //dir("${env.WORKSPACE}") {
                sh 'whoami'
                sh 'aws ecr get-login-password --region ${REGION} | docker login --username AWS --password-stdin ${ECR_REPOSITORY}'
                sh 'docker build -t ${ECR_DOCKER_IMAG_NAME} .'
                sh 'docker tag ${ECR_DOCKER_IMAG_NAME}:latest ${ECR_DOCKER_IMAGE}:${BUILD_ID} '
            //}
        }

        post {
            always {
                echo "Docker build success!"
            }
        }
        // steps {
        //         echo '============ Build Docker Image ============'
        //     }
    }
    // stage('============ Run test code ============') {
    //     when { expression { return params.RUN_TEST } }
    //     // agent { label 'build' }
    //     steps {
    //         sh'''
    //             aws ecr get-login-password --region ${REGION} | docker login --username AWS --password-stdin ${ECR_REPOSITORY}
    //             docker run --rm ${ECR_DOCKER_IMAGE}:${ECR_DOCKER_TAG} /root/.local/bin/pytest -v
    //         '''
    //     }
    //     steps {
    //             echo '============ Run test code ============'
    //         }
    //}
    stage('============ Push Docker Image ============') {
        when { expression { return params.PUSH_DOCKER_IMAGE } }
        // agent { label 'build' }
        steps {
            echo "Push Docker Image to ECR"
            //aws ecr get-login-password --region ${REGION} | docker login --username AWS --password-stdin ${ECR_REPOSITORY}
            sh'''

                docker push ${ECR_DOCKER_IMAGE}:${BUILD_ID}
            '''
        }
    }
    stage('Prompt for deploy') {
        // when { expression { return params.PROMPT_FOR_DEPLOY } }
        // agent { label 'deploy' }
        // steps {
        //     script {
        //         env.APPROAL_NUM = input message: 'Please enter the approval number',
        //                           parameters: [string(defaultValue: '',
        //                                        description: '',
        //                                        name: 'APPROVAL_NUM')]
        //     }

        //     echo "${env.APPROAL_NUM}"
        // }
        steps {
                echo '============ Prompt for deploy ============'
            }
    }
    stage('============ Deploy workload ============') {
        // when { expression { return params.DEPLOY_WORKLOAD } }
        // agent { label 'deploy' }
        // steps {
        //     sshagent (credentials: ['aws_ec2_user_ssh']) {
        //         sh """#!/bin/bash
        //             scp -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no \
        //                 deploy/docker-compose.yml \
        //                 ${params.TARGET_SVR_USER}@${params.TARGET_SVR}:${params.TARGET_SVR_PATH};
        //             ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no \
        //                 ${params.TARGET_SVR_USER}@${params.TARGET_SVR} \
        //                 'aws ecr get-login-password --region ${REGION} | docker login --username AWS --password-stdin ${ECR_REPOSITORY}; \
        //                  export IMAGE=${ECR_DOCKER_IMAGE}; \
        //                  export TAG=${ECR_DOCKER_TAG}; \
        //                  docker-compose -f docker-compose.yml down;
        //                  docker-compose -f docker-compose.yml up -d';
        //         """
        //     }
        // }
        steps {
                echo '============ Deploy workload ============'
            }
    }
  }

//   post {
//     failure {
//       slackSend(
//         channel: "#jenkins_test",
//         color: "danger",
//         message: "[Failed] Job:${env.JOB_NAME}, Build num:#${env.BUILD_NUMBER} @channel (<${env.RUN_DISPLAY_URL}|open job detail>)"
//       )
//     }
//   }
}
