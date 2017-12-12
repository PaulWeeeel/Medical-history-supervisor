# Medical-history-supervisor
## Before build
Add local dependency: 

cmd at Medical-history-supervisor\src\libs

mvn install:install-file -Dfile= Msc.jar -DgroupId=iflytek -DartifactId=iflytek-voice -Dversion=1.0 -Dpackaging=jar

mvn install:install-file -Dfile= FaceppSDK.jar -DgroupId=facepp -DartifactId=facepp_face -Dversion=1.0 -Dpackaging=jar


## Base

### 可运行

- /toRegister
- /register
- /toLogin
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

