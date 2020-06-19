# CMS1.0-Backend
대덕소프트웨어 전공동아리 관리 서비스 

**Club Management Service** - CMS 1.0 Backend



### GIT Rule

* `Git Flow` 전략을 Git Branch 전략으로 채택.
* branch naming rule : `[domain name]-[feature info]`
* commit message rule : `CMS-이슈번호 [Method] content`



### Package Structure

DDD기반 도메인형 패키지 구조를 구성했습니다.

```yaml
└── src
├── main
│   ├── java
│   │   └── com
│   │       └── cms
│   │           └── api
│   │               ├── ApiApplication.java
│   │               ├── domain
│   │               │   ├── student
│   │               │   │   ├── controller
│   │               │   │   ├── service
│   │               │   │   ├── dao
│   │               │   │   ├── domain
│   │               │   │   ├── dto
│   │               │   │   └── exception
│   │               │   ├── admin
│   │               │   │   ├── controller
│   │               │   │   ├── service
│   │               │   │   ├── dao
│   │               │   │   ├── domain
│   │               │   │   ├── dto
│   │               │   │   └── exception
│   │               │   └── model
│   │               │       ├── Comment.java
│   │               ├── global
│   │               │   ├── common
│   │               │   │   ├── request
│   │               │   │   └── response
│   │               │   ├── config
│   │               │   │   ├── SwaggerConfig.java
│   │               │   │   ├── properties
│   │               │   │   ├── resttemplate
│   │               │   │   └── security
│   │               │   ├── error
│   │               │   │   ├── ErrorResponse.java
│   │               │   │   ├── GlobalExceptionHandler.java
│   │               │   │   └── exception
│   │               │   └── util
│   │               └── infra
│   │                   ├── email
│   │                   └── sms
│   │                       ├── AmazonSmsClient.java
│   │                       ├── SmsClient.java
│   │                       └── dto
│   │                           └── SmsRequest.java
│   └── resources
│       ├── application-dev.yml
│       ├── application-local.yml
│       ├── application-prod.yml
│       └── application.yml
```

