# Aws-lambda
WIP

### Pre-requisite
* Basic knowledge of AWS/S3/Api/Gateway/Spring boot/Git
* Follow below steps to set sam client on mac machine then verify sam version
```
brew tap aws/tap
brew install aws-sam-cli
```
```
root$ sam --version
SAM CLI, version 0.43.0
```

### Steps to build , deploy and test are :

* Checkout demo code
```
git clone 
cd lambda-spring-boot2-rest-api
```

* maven build code on local
```
mvn clean package
```

* Aws S3 
```
aws s3api create-bucket --bucket my-bucket122 --region us-east-1
//aws s3 cp target/lambda-spring-boot2-rest-api.jar s3://spring-boot-lambda-rest/
```

```
aws cloudformation package --template-file sam.yaml --output-template-file target/output-sam.yaml --s3-bucket spring-boot-lambda-0403
aws cloudformation deploy --template-file target/output-sam.yaml --stack-name spring-boot-lambda --capabilities CAPABILITY_IAM
```



* Let's build via SAM
```
root$ sam build
Building resource 'HelloWorldFunction'
Running JavaMavenWorkflow:CopySource
Running JavaMavenWorkflow:MavenBuild
Running JavaMavenWorkflow:MavenCopyDependency
Running JavaMavenWorkflow:MavenCopyArtifacts

Build Succeeded

Built Artifacts  : .aws-sam/build
Built Template   : .aws-sam/build/template.yaml

Commands you can use next
=========================
[*] Invoke Function: sam local invoke
[*] Deploy: sam deploy --guided
    
root$ sam local invoke
Invoking com.aws.demo.handler.LambdaMethodHandler::handleRequest (java8)

Fetching lambci/lambda:java8 Docker container image............................................................................................................................................................................................................................................
Mounting /...../lambda-spring-boot2-rest-api/.aws-sam/build/HelloWorldFunction as /var/task:ro,delegated inside runtime container
START RequestId: 3c05587e-3261-1b8e-4dc8-b0718814b5a8 Version: $LATEST
END RequestId: 3c05587e-3261-1b8e-4dc8-b0718814b5a8
REPORT RequestId: 3c05587e-3261-1b8e-4dc8-b0718814b5a8  Init Duration: 1216.88 ms       Duration: 1705.90 ms    Billed Duration: 1800 ms        Memory Size: 512 MB     Max Memory Used: 75 MB  

{"body":"{ \"message\": \"hello world\", \"location\": \"14.140.116.156\" }","headers":{"X-Custom-Header":"application/json","Content-Type":"application/json"},"statusCode":200}
root$ 
```




