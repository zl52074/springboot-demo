//Jenkinsfile (Declarative Pipeline)
pipeline {
  agent any

  environment {
      DOCKER_USERNAME = 'zl52074'
      PROJECT_NAME = 'springboot-demo'
      GITHUB_URL = 'git@github.com:zl52074/springboot-demo.git' //ssh地址配置公钥免密登录
      GITHUB_USERNAME = 'zl52074'
      GITHUB_EMAIL = '737218582@qq.com'
      GITHUB_CREDENTIAL_ID = 'git'
      DOCKER_CREDENTIAL_ID = 'docker'
  }

  parameters{
    string(name: 'PROJECT_VERSION', defaultValue: '0.1', description: '输入项目版本号')
  }

  stages {

    stage('拉取源码') {
      steps {
        echo 'git拉取源码'
        checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: "$GITHUB_CREDENTIAL_ID", url: "$GITHUB_URL"]])
      }
    }

    stage('构建项目') {
      steps {
        echo 'maven构建项目'
        //在maven中运行以下命令
        sh 'mvn -v'
        sh 'ls -al'
        sh 'mvn -Dmaven.test.skip=true -s "settings.xml" clean package' //跳过测试
        sh 'ls -al target'
      }
    }

    stage ('打包、推送镜像') {
      steps {
        echo 'docker打包、推送镜像'
        sh 'docker build -f Dockerfile -t $DOCKER_USERNAME/$PROJECT_NAME:$PROJECT_VERSION .'
        sh 'docker images | grep $PROJECT_NAME'
        withCredentials([usernamePassword(credentialsId: "$DOCKER_CREDENTIAL_ID", passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
          sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
          sh 'docker push $DOCKER_USERNAME/$PROJECT_NAME:$PROJECT_VERSION'
        }
      }
    }

    stage('部署') {
      steps {
        echo 'k8s部署'
        input( message: '部署到k8s ?')
        sh "sed -i 's,{IMAGE},$DOCKER_USERNAME/$PROJECT_NAME:$PROJECT_VERSION,' deploy/deployment.yaml" //替换占位符
        sh "cat deploy/deployment.yaml"
        sh "kubectl apply -f deploy/deployment.yaml"
      }
    }

    stage('发布版本'){
      when{
        expression{
          return params.PROJECT_VERSION =~ /v.*/
        }
      }
      steps {
        echo 'git发布版本'
        input(message: 'git发布版本'+params.PROJECT_VERSION+' ?')
        withCredentials([usernamePassword(credentialsId: "$GITHUB_CREDENTIAL_ID", passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
          sh 'git config --global user.email "$GITHUB_EMAIL" '
          sh 'git config --global user.name "$GITHUB_USERNAME" '
          sh 'git tag -a $PROJECT_VERSION -m "$PROJECT_VERSION" '
          sh 'git push $GITHUB_URL --tags --ipv4'
        }
      }
    }


  }
}
