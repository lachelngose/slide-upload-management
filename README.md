# slide-upload-management

슬라이드 업로드 및 후속 처리를 다루는 Kotlin/Spring Boot 백엔드 프로젝트입니다.

## Tech Stack

현재 빌드 설정 기준으로 다음 기술을 사용합니다.

- Kotlin
- Java 17
- Spring Boot 3.1
- Spring WebFlux / Reactor
- Kotlin Coroutines
- Spring Data JPA
- PostgreSQL
- QueryDSL
- Spring Kafka
- AWS S3 SDK / Spring Cloud AWS
- Spring Validation
- Spring Actuator
- Kotest / MockK

## 프로젝트 성격

비동기·이벤트 기반 처리와 외부 스토리지 연계를 포함하는 백엔드 구현 예제입니다.  
구체적인 실행 환경과 기능 흐름은 코드와 설정에 따라 확인할 수 있습니다.

## 로컬 개발 시 참고

- JDK 17 필요
- PostgreSQL 연결 설정 필요
- S3 또는 AWS 관련 설정이 필요한 코드 경로가 존재할 수 있음
- Kafka를 사용하는 기능은 별도 브로커 설정이 필요할 수 있음

> 현재 README는 저장소의 빌드 구성에 기반해 작성했습니다. 실행에 필요한 실제 환경 변수나 기능별 사용법은 코드 확인 후 보완하는 것이 좋습니다.
