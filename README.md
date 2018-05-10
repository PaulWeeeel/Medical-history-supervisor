# Medical-history-supervisor
## Before build
Add local dependency: 

remove the dependencies of jpa first

no need to cmd at ..\Medical-history-supervisor
ps:no blank space should be involved in the path
  for windows cmd it should be / rather than \

mvn install:install-file -Dfile=[localdir]src\libs\Msc.jar -DgroupId=iflytek -DartifactId=iflytek-voice -Dversion=1.0 -Dpackaging=jar

mvn install:install-file -Dfile=[localdir]src\libs\FaceppSDK.jar -DgroupId=facepp -DartifactId=facepp_face -Dversion=1.0 -Dpackaging=jar


add back the dependencies of jpa finally

## Base

### 可运行
- index
- /register
- /login

## Partient

### 可运行

- /
- /list
- /home
- /toAdd
- /add
- /toEdit?id=
- /edit
- /delete?id=

### 未检测

- /home/{id}

### 未解决

- /list?name="小兰"

原因：url中出现中文字符



## CaseHistory

### 可运行





## Medicine

### 可运行

- /list
- /add
- /edit
- /delete



## Stock

### 可运行

- /list
- /add
- /edit
- /delete



## Payment(W)

### 可运行

- /
- /list
- /add
- /toEdit
- /edit
- /delete

