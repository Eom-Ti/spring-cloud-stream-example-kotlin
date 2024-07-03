# Spring Cloud Stream Example Project
이 repository는 Spring Cloud Strea을 활용해 코인 거래소의 Candle 현황을 조회하여 Proudce, Consume 하는 간단한 프로젝트 입니다.
Srping Cloud Stream의 기능을 실험해 볼 수 있도록 구성한 간단한 예제 프로젝트입니다. 해당 샘플에 사용되는 메시징 프레임 워크는 `Kafka` 와 `RabbitMQ`를 사용합니다.
각 메시징 프레임워크에 따라 Docker Compose File을 별도 추가하였으며, 초기 프로젝트 실행시 이미지 Pull 작업 및 Container 생성 작업으로 조금의 시간이 소요될 수 있습니다.

해당 프로젝트는 `Kafka`를 사용하여 초기 구축하였으며, 이후 `RabbitMQ`를 사용하여 메시징 프레임워크를 교체하더라도 `Business Logic(Code)` 수정없이 메시징 프레임워크를 교체할 수 있도록 구성되어 있습니다.
다만 `application.yml` 및 `build.gradle`의 일부 의존성 및 설정은 변경될 수 있습니다.

해당 프로젝트는 Kotlin으로 작성되어 있으며 [Kotlin 코드](https://github.com/Eom-Ti/spring-cloud-stream-example-kotlin)의 경우 현재 작업중입니다.

# Dictionary
- Candle : 코인 시세의 변동을 보여주는 봉 차트입니다.(해당 프로젝트에선 분봉단위(1분)로 사용합니다.)

# Project
## coin-functions
consumer, transformer-server, producer 에서 사용할 수 있는 함수, pojo 형태의 객체를 가지고있는 모듈입니다.
## consumer
Spring Boot Application으로 Candle 데이터 객체를 수신하고, 기존의 데이터는 캐시를 통해 관리하며, 상승/하락 의 정보를 기록하는 역할을 수행합니다.

![image](https://github.com/Eom-Ti/spring-cloud-stream-example/assets/71249347/d9d98229-b38f-48bf-8367-066f581fbd44)

해당 Application에선 캐시 데이터 관리를 위해 Redis를 사용중이며, `org.springframework.boot:spring-boot-docker-compose`를 활용하여 docker container를 관리하고 있습니다. 자세한 설정은 아래의 transformer-server를 참조 부탁드립니다.
```text
redis-compose.yml
```

## producer
Spring Boot Application으로 코인의 현재 거래 현황(Candle)을 거래소로 부터 조회하여 String 데이터를 지정된 대상(`Kafka` or `RabbigMq`)으로 전송하는 역할을 수행합니다.
## transformer-server
Srping Boot Application으로 String 데이터를 수신하여 코인 Candle 데이터 객체로 변환하여 메시지를 발행하는 역할을 수행합니다.

해당 Application에서는 `org.springframework.boot:spring-boot-docker-compose`를 활용하여 docker compose를 프로젝트 시작과 종료시 자동으로 실행할 수 있게 설정되어 있습니다.
초기 시작시 docker container 구성 작업에 5분정도의 시간이 소요될 수 있습니다.(docer image pull, conatiner config)
```yml
  docker:
    compose:
      file: './transformer-server/rabbitmq-compose.yml'
      enabled: true
      lifecycle-management: start_and_stop
      stop:
        command: down
        timeout: 10
```
```text
kafka-compse.yml
rabbitmq-compse.yml
```

# PoC
![image](https://github.com/Eom-Ti/spring-cloud-stream-example/assets/71249347/7adf22e8-1d3c-44f9-b775-5c5191ccf78d)

## Project Flow
### data Polling - Produce Server
지정된 거래소로 부터 일정시간(1분) 동안 각 코인의 거래 Canlde 을 조회합니다.
### Publish String Data - Produce Server
메시징 프레임워크를 통해 String Type의 데이터를 발행합니다.(이때 Kafka, RabbitMQ를 사용가능합니다.)
### Transform Data - Transformer Server
String 타입의 메시지를 Candle 객체에 맞게 변환하여 메시지를 발행합니다.
### Consume Data - Consumer Server
Consumer Server에서 각 서비스에 맞게 해당 메시지를 수신하여 처리합니다.
