# Rebook Trading Service

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.13-brightgreen)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.5-blue)
![Java](https://img.shields.io/badge/Java-17-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14+-336791)
![Redis](https://img.shields.io/badge/Redis-6+-DC382D)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.x-FF6600)
![AWS S3](https://img.shields.io/badge/AWS-S3-569A31)
![Gradle](https://img.shields.io/badge/Gradle-8.14.2-02303A)

**Rebook 마이크로서비스 아키텍처의 거래 관리 서비스**

중고 도서 거래 등록, 관리, 찜하기, 알림 발행을 담당하는 핵심 백엔드 서비스

</div>

---

## 1. 개요

**Rebook Trading Service**는 중고 도서 거래 플랫폼 Rebook의 핵심 백엔드 마이크로서비스로, 사용자 간 도서 거래를 관리하고 조율합니다. Spring Boot 기반으로 구현된 본 서비스는 **서비스 디스커버리**, **중앙화된 설정 관리**, **비동기 메시징**을 통한 확장 가능한 구조를 제공합니다.


### 서비스 역할

본 서비스는 Rebook 플랫폼 내에서 다음과 같은 역할을 담당합니다:

- **거래 관리**: 중고 도서 거래 등록, 수정, 삭제 및 상태 관리
- **찜하기 시스템**: 사용자별 관심 거래 북마킹 기능
- **추천 시스템**: Book Service 연동을 통한 개인화 거래 추천
- **알림 발행**: RabbitMQ를 통한 실시간 거래 알림 전송
- **이미지 관리**: AWS S3 기반 거래 이미지 업로드 및 관리

---

## 2. 목차

- [1. 개요](#1-개요)
- [2. 목차](#2-목차)
- [3. 주요 기능](#3-주요-기능)
- [4. 기술 스택](#4-기술-스택)
- [5. 아키텍처](#5-아키텍처)
- [6. API 문서](#6-api-문서)
- [7. 프로젝트 구조](#7-프로젝트-구조)


---

## 3. 주요 기능

### 3.1 거래 관리

#### 거래 등록 및 수정
- ✅ 중고 도서 거래 등록 (이미지 업로드 포함)
- ✅ 거래 정보 수정 (이미지 교체 가능)
- ✅ 거래 삭제 (소유자 권한 검증)
- ✅ AWS S3 기반 이미지 저장 및 URL 관리

#### 거래 조회
- ✅ 거래 상세 정보 조회 (찜하기 상태 포함)
- ✅ 내 거래 목록 조회 (페이지네이션 지원)
- ✅ 특정 도서의 모든 거래 목록 조회
- ✅ 타 사용자의 거래 목록 조회
- ✅ 사용자 맞춤 거래 추천 (Book Service 연동)

#### 거래 상태 관리
- ✅ 거래 상태 변경 (판매중 → 예약중 → 판매완료)
- ✅ 소유자 권한 검증을 통한 안전한 상태 관리
- ✅ 상태별 거래 필터링 및 조회

### 3.2 찜하기 (북마크) 시스템

- ✅ 거래 찜하기/찜 해제 토글 기능
- ✅ 찜한 거래 목록 조회 (페이지네이션 지원)
- ✅ 거래 조회 시 현재 사용자의 찜하기 상태 표시
- ✅ 복합키(Composite Key) 기반 효율적인 찜하기 관리

### 3.3 추천 시스템

- ✅ 사용자 맞춤 도서 거래 추천
- ✅ Book Service와 OpenFeign 연동을 통한 개인화 추천
- ✅ 추천 도서에 해당하는 거래 목록 자동 필터링

### 3.4 알림 시스템 (Event-Driven)

- ✅ 새로운 거래 등록 시 해당 도서를 찜한 사용자에게 알림 발행
- ✅ 거래 가격 변동 시 찜한 사용자에게 알림 발행
- ✅ RabbitMQ 기반 비동기 메시징 (Notification Service 연동)
- ✅ JSON 메시지 직렬화를 통한 안정적 메시지 전송


---

## 4. 기술 스택

### 4.1 백엔드 프레임워크

| 기술 | 버전 | 용도 |
|------|------|------|
| **Spring Boot** | 3.3.13 | 애플리케이션 프레임워크 |
| **Java** | 17 | 프로그래밍 언어 |
| **Spring Data JPA** | - | ORM 및 데이터 접근 계층 |
| **Spring Validation** | - | 요청 데이터 유효성 검증 |
| **Lombok** | - | 보일러플레이트 코드 제거 |

### 4.2 데이터베이스 & 캐싱

| 기술 | 버전 | 용도 |
|------|------|------|
| **PostgreSQL** | 14+ | 관계형 데이터베이스 (메인 데이터 저장소) |
| **Redis** | 6+ | 분산 캐싱 및 세션 관리 |

### 4.3 마이크로서비스 인프라 (Spring Cloud)

| 기술 | 버전 | 용도 |
|------|------|------|
| **Eureka Client** | 2023.0.5 | 서비스 디스커버리 및 등록 |
| **Spring Cloud Config** | 2023.0.5 | 중앙화된 설정 관리 (외부 Config Server) |
| **OpenFeign** | 2023.0.5 | 선언적 HTTP 클라이언트 (Book Service 연동) |

### 4.4 메시징 & 이벤트

| 기술 | 버전 | 용도 |
|------|------|------|
| **RabbitMQ (AMQP)** | 3.x | 비동기 메시징 및 이벤트 발행 |
| **Spring AMQP** | - | RabbitMQ 통합 및 메시지 컨버터 |

### 4.5 클라우드 & 스토리지

| 기술 | 버전 | 용도 |
|------|------|------|
| **AWS S3** | SDK 2.27.21 | 거래 이미지 파일 저장소 |

### 4.6 모니터링 & 로깅

| 기술 | 버전 | 용도 |
|------|------|------|
| **Spring Actuator** | - | 헬스체크 및 메트릭 엔드포인트 |
| **Prometheus** | - | 메트릭 수집 및 모니터링 |
| **Sentry** | 8.13.2 | 실시간 에러 트래킹 및 알림 |
| **SLF4J & Logback** | - | 애플리케이션 로깅 |

### 4.7 API 문서화

| 기술 | 버전 | 용도 |
|------|------|------|
| **SpringDoc OpenAPI 3** | 2.6.0 | Swagger UI 기반 REST API 문서 자동 생성 |

### 4.8 빌드 & 배포

| 기술 | 버전 | 용도 |
|------|------|------|
| **Gradle** | 8.14.2 | 빌드 자동화 도구 |
| **Docker** | - | 컨테이너화 및 배포 |
| **Jacoco** | - | 테스트 커버리지 분석 |
| **JUnit 5** | - | 단위 테스트 프레임워크 |

---

## 5. 아키텍처

### 5.1 마이크로서비스 아키텍처

```
┌─────────────────┐
│  API Gateway    │ ← 인증 및 라우팅 (X-User-Id 헤더 주입)
└────────┬────────┘
         │
         ├──────────────┬──────────────┬──────────────┐
         │              │              │              │
    ┌────▼────┐    ┌────▼────┐   ┌────▼────┐    ┌────▼────┐
    │ Trading │    │  Book   │   │  User   │    │ Notif.  │
    │ Service │◄───┤ Service │   │ Service │    │ Service │
    └────┬────┘    └─────────┘   └─────────┘    └────▲────┘
         │         (OpenFeign)                        │
         │                                            │
         │         ┌──────────────┐                   │
         └────────►│  RabbitMQ    │───────────────────┘
                   │  (AMQP)      │  (Notification Events)
                   └──────────────┘

    ┌─────────────────────────────────────────────┐
    │        Infrastructure Services              │
    ├─────────────────────────────────────────────┤
    │  Eureka  │  Config Server  │  PostgreSQL   │
    │  Redis   │  AWS S3         │  Prometheus   │
    └─────────────────────────────────────────────┘
```

### 5.3 엔티티 관계도 (ERD)

```
┌─────────────────────────────┐
│        Trading              │
├─────────────────────────────┤
│ PK  id (Long)               │
│     userId (String)         │
│     bookId (Long)           │
│     state (State enum)      │
│     price (Integer)         │
│     imageUrl (String)       │
│     createdAt (LocalDateTime)│
│     updatedAt (LocalDateTime)│
└─────────────────────────────┘
              │
              │ 1:N
              │
              ▼
┌─────────────────────────────┐
│      TradingUser            │  (찜하기 Join Table)
├─────────────────────────────┤
│ PK  tradingId (Long)        │ ← Composite Key
│ PK  userId (String)         │ ← Composite Key
│     createdAt (LocalDateTime)│
└─────────────────────────────┘
```

**엔티티 설계 패턴**:
- **JPA Auditing**: `@EntityListeners(AuditingEntityListener.class)`로 생성/수정 시간 자동 관리
- **복합키 (Composite Key)**: `TradingUserId` 임베디드 클래스로 사용자-거래 다대다 관계 표현
- **상태 관리**: `State` enum (판매중, 예약중, 판매완료)을 STRING으로 DB 저장


## 6. API 문서

### 6.1 Swagger UI 접근

애플리케이션 실행 후 아래 URL에서 대화형 API 문서를 확인할 수 있습니다:

```
https://api.rebookcloak.click/webjars/swagger-ui/index.html?urls.primaryName=rebook-trading
```

### 6.2 API 엔드포인트 상세

#### 6.2.1 거래 관리 API (`TradingController`)

| Method | Endpoint | Summary |
|--------|----------|---------|
| **POST** | `/api/tradings` | 거래 등록 |
| **GET** | `/api/tradings/{tradingId}` | 거래 상세 조회 |
| **PATCH** | `/api/tradings/{tradingId}` | 거래 상태 수정 |
| **PUT** | `/api/tradings/{tradingId}` | 거래 정보 수정 |
| **DELETE** | `/api/tradings/{tradingId}` | 거래 삭제 |
| **GET** | `/api/tradings/me` | 내 거래 목록 조회 |
| **GET** | `/api/tradings/books/{bookId}` | 특정 도서 거래 목록 |
| **GET** | `/api/tradings/recommendations` | 추천 거래 목록 |
| **GET** | `/api/tradings/others/{userId}` | 타인의 거래 목록 |

#### 6.2.2 찜하기 API (`TradingUserController`)

| Method | Endpoint | Summary |
|--------|----------|---------|
| **POST** | `/api/tradings/{tradingId}/marks` | 거래 찜하기/취소 (토글) |
| **GET** | `/api/tradings/marks` | 찜한 거래 목록 조회 |


## 7. 프로젝트 구조

### 주요 디렉토리 설명

| 디렉토리 | 역할 | 주요 기능 |
|---------|------|----------|
| **advice/** | 전역 예외 처리 | `@RestControllerAdvice`로 모든 컨트롤러 예외 통합 핸들링 |
| **common/** | 응답 표준화 | 통일된 API 응답 구조 제공 (`CommonResult`, `SingleResult`, `PageResponse`) |
| **config/** | 인프라 설정 | RabbitMQ, S3, JPA Auditing 등 외부 서비스 연동 설정 |
| **controller/** | REST API | 엔드포인트 정의 및 Swagger 문서화 (`@Tag`, `@Operation`) |
| **enums/** | 상태 관리 | 거래 상태 enum (판매중, 예약중, 판매완료) |
| **exception/** | 커스텀 예외 | 도메인별 예외 클래스 (404, 409, 403, 400) |
| **feigns/** | 서비스 간 통신 | OpenFeign을 통한 Book Service 동기 호출 |
| **model/entity/** | 도메인 모델 | JPA 엔티티 및 복합키 정의 |
| **model/** (DTO) | 데이터 전송 | 요청/응답 DTO 및 메시징 메시지 |
| **repository/** | 데이터 접근 | Spring Data JPA 리포지토리 인터페이스 |
| **service/** | 비즈니스 로직 | 트랜잭션 관리, 권한 검증, 외부 서비스 연동 |

---

## 8. 코드 분석 및 품질 지표

### 8.1 아키텍처 패턴 분석

#### 계층형 아키텍처 (Layered Architecture)
본 프로젝트는 **3계층 아키텍처**를 엄격하게 준수하여 관심사 분리와 유지보수성을 확보했습니다:

**Controller Layer (표현 계층)**
- 2개의 REST 컨트롤러 (`TradingController`, `TradingUserController`)
- Swagger/OpenAPI 문서화 완비 (`@Tag`, `@Operation` 애노테이션)
- 헤더 기반 사용자 인증 (`X-User-Id` 헤더)
- 통일된 응답 래핑 (`ResponseService` 팩토리 패턴)

**Service Layer (비즈니스 계층)**
- CQRS 패턴 적용: `TradingService` (Command), `TradingReader` (Query)
- 트랜잭션 경계 명확화: 6개의 `@Transactional` 메서드
- 도메인별 책임 분리: 거래 관리, 찜하기, S3 업로드, 알림 발행
- 읽기 전용 최적화: `@Transactional(readOnly = true)` 적용

**Repository Layer (데이터 접근 계층)**
- Spring Data JPA 메서드 네이밍 컨벤션 활용
- 페이지네이션 지원 (`Pageable`)
- 복합키 기반 효율적 조회 (`TradingUserId`)

#### CQRS 패턴 (Command Query Responsibility Segregation)
```java
// Command (쓰기 작업)
TradingService.postTrading()    // 거래 생성
TradingService.updateTrading()  // 거래 수정
TradingService.deleteTrading()  // 거래 삭제

// Query (읽기 작업)
TradingReader.findById()        // 단건 조회
TradingReader.readTradings()    // 목록 조회
TradingReader.getRecommendations() // 추천 조회
```

**CQRS 적용 효과**:
- 읽기/쓰기 작업의 명확한 분리
- 읽기 성능 최적화 가능 (읽기 전용 트랜잭션, 캐싱 적용 용이)
- 코드 가독성 및 유지보수성 향상

### 8.2 코드 품질 지표

#### 구조적 품질
- **총 Java 파일 수**: 30개
- **Spring Bean 구성**: 11개 (`@Service`, `@Repository`, `@Controller`, `@Component`)
- **트랜잭션 메서드**: 6개 (`@Transactional` 적용)
- **테스트 커버리지**: Jacoco 설정 완료 (HTML 리포트 생성)
- **기술 부채**: TODO/FIXME 주석 없음 (코드 정리 완료)

#### 코딩 규칙 준수
✅ **Spring Boot 모범 사례 적용**
- 생성자 주입 방식 (`@RequiredArgsConstructor` + `final` 필드)
- 불변 객체 패턴 (`@Getter`, `@Setter` 선택적 사용)
- 응답 DTO 분리로 엔티티 노출 방지

✅ **예외 처리 전략**
- 계층별 커스텀 예외 정의 (`CMissingDataException`, `CUnauthorizedException`)
- 전역 예외 핸들러 (`@RestControllerAdvice`)로 일관된 오류 응답
- 적절한 HTTP 상태 코드 매핑 (404, 403, 409, 500)

✅ **트랜잭션 관리**
- 읽기 전용 트랜잭션 최적화 (`TradingReader` 클래스)
- 명시적 트랜잭션 경계 설정 (쓰기 작업에만 `@Transactional` 적용)
- 트랜잭션 내 외부 API 호출 분리 (Book Service Feign 호출)

### 8.3 보안 분석

#### 인증 및 권한 관리
✅ **헤더 기반 사용자 인증**
- API Gateway에서 인증 완료 후 `X-User-Id` 헤더 주입
- 서비스 레벨에서 소유권 검증 (TradingService:61, 72, 107)

✅ **권한 검증 패턴**
```java
// 거래 수정/삭제 시 소유자 확인
if(!trading.getUserId().equals(userId)) {
    throw new CUnauthorizedException("Unauthorized user Access");
}
```

⚠️ **보안 권장사항**
1. **입력 유효성 검증 강화**
   - `@Valid` 애노테이션 추가 권장 (현재 `@RequestPart`에 미적용)
   - DTO 필드에 `@NotNull`, `@Size`, `@Min` 등 제약조건 명시

2. **S3 URL 보안 강화**
   - 현재: 하드코딩된 리전 정보 (S3Service:30)
   - 권장: CloudFront 서명된 URL 또는 Pre-signed URL 사용

3. **민감 정보 관리**
   - 현재: Spring Cloud Config Server에서 설정 관리 (✅ 올바른 방식)
   - 권장: AWS Secrets Manager 또는 Vault 통합 고려

### 8.4 성능 최적화 분석

#### 현재 적용된 최적화
✅ **데이터베이스 최적화**
- JPA Auditing으로 생성/수정 시간 자동 관리
- 페이지네이션 지원으로 대량 데이터 조회 시 메모리 절약
- 읽기 전용 트랜잭션 (`@Transactional(readOnly=true)`)으로 성능 향상

✅ **비동기 처리**
- RabbitMQ 기반 알림 발행으로 응답 속도 개선
- 메시지 발행 후 즉시 응답 (Fire and Forget 패턴)

#### 성능 개선 권장사항
⚠️ **캐싱 전략 부재**
- Redis 의존성은 추가되어 있으나 실제 사용 코드 없음
- **권장 적용**:
  ```java
  @Cacheable(value = "tradings", key = "#tradingId")
  public TradingResponse getTrading(String userId, Long tradingId)

  @CacheEvict(value = "tradings", key = "#tradingId")
  public void updateTrading(...)
  ```

⚠️ **N+1 쿼리 잠재적 문제**
- `findByUserId`, `findByBookId` 메서드는 단순 조회만 수행
- 찜하기 상태 확인 시 각 거래마다 개별 조회 발생 가능 (TradingService:138)
- **권장**: Batch 조회 또는 `@EntityGraph`로 일괄 로드

⚠️ **데이터베이스 인덱스 최적화**
- 현재 엔티티에 인덱스 명시 없음
- **권장 인덱스**:
  ```java
  @Table(indexes = {
      @Index(name = "idx_user_id", columnList = "userId"),
      @Index(name = "idx_book_id", columnList = "bookId"),
      @Index(name = "idx_state", columnList = "state")
  })
  ```

### 8.5 테스트 전략

#### 현재 테스트 상태
- **단위 테스트**: 0개 (src/test 디렉토리 비어있음)
- **테스트 인프라**: JUnit 5, Spring Boot Test, RabbitMQ Test 설정 완료
- **커버리지 도구**: Jacoco 설정 완료 (HTML 리포트 자동 생성)

⚠️ **테스트 권장사항**
1. **컨트롤러 테스트** (`@WebMvcTest`)
   - 각 엔드포인트별 정상/예외 케이스 검증
   - MockMvc 기반 통합 테스트

2. **서비스 레이어 테스트** (`@ExtendWith(MockitoExtension.class)`)
   - 비즈니스 로직 단위 테스트
   - 권한 검증 로직 테스트 (소유자 확인)

3. **리포지토리 테스트** (`@DataJpaTest`)
   - 커스텀 쿼리 메서드 검증
   - 페이지네이션 동작 확인

4. **통합 테스트** (`@SpringBootTest`)
   - RabbitMQ 메시지 발행 확인 (TestRabbitTemplate)
   - S3 업로드 Mock 테스트 (Testcontainers 또는 LocalStack)

### 8.6 모니터링 및 관찰성

#### 현재 적용된 모니터링
✅ **Actuator Endpoints**
- 헬스체크 및 메트릭 엔드포인트 활성화
- Prometheus 메트릭 수집 설정 완료

✅ **에러 트래킹**
- Sentry 통합 (버전 8.13.2)
- 실시간 에러 알림 및 트래킹

✅ **구조화된 로깅**
- SLF4J + Logback 사용
- 주요 비즈니스 로직에 로그 출력 (S3 업로드, 알림 발행)

#### 관찰성 개선 권장사항
⚠️ **분산 추적 (Distributed Tracing)**
- Spring Cloud Sleuth + Zipkin 통합 권장
- 마이크로서비스 간 요청 추적 가능

⚠️ **비즈니스 메트릭 추가**
```java
@Timed("trading.create")  // 거래 생성 소요 시간
@Counted("trading.state.change")  // 상태 변경 횟수
```

### 8.7 개선 우선순위

| 우선순위 | 항목 | 예상 효과 | 구현 난이도 |
|---------|------|----------|------------|
| **P0 (긴급)** | 단위 테스트 작성 | 버그 예방, 리팩토링 안전성 | 중간 |
| **P0 (긴급)** | 입력 유효성 검증 강화 | 보안 취약점 제거 | 낮음 |
| **P1 (높음)** | Redis 캐싱 적용 | 응답 속도 30-50% 개선 | 낮음 |
| **P1 (높음)** | 데이터베이스 인덱스 추가 | 조회 성능 10배 이상 개선 | 낮음 |
| **P2 (중간)** | N+1 쿼리 해결 | DB 부하 감소 | 중간 |
| **P2 (중간)** | S3 Pre-signed URL 적용 | 보안 강화, CDN 연동 | 중간 |
| **P3 (낮음)** | 분산 추적 도입 | 디버깅 효율성 향상 | 높음 |

---

## 9. 개발 및 배포 가이드

### 9.1 로컬 개발 환경 설정

#### 사전 요구사항
- Java 17 이상
- Gradle 8.14.2
- Docker & Docker Compose (인프라 서비스 실행용)

#### 외부 서비스 실행
```bash
# PostgreSQL, Redis, RabbitMQ 로컬 실행
docker-compose up -d postgres redis rabbitmq

# Eureka, Config Server 실행 (별도 리포지토리)
# 각 서비스는 독립적으로 실행되어야 함
```

#### 애플리케이션 실행
```bash
# 빌드
./gradlew build

# 개발 환경 실행
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### 9.2 Docker 빌드 및 배포

#### 멀티 플랫폼 빌드 (ARM64 → AMD64)
```bash
# Docker Buildx를 이용한 크로스 플랫폼 빌드
docker buildx build --platform=linux/amd64 \
  -t nooaahh/rebook-trading-service:latest \
  --push .
```

#### 로컬 빌드 및 실행
```bash
# 이미지 빌드
docker build -t rebook-trading-service:latest .

# 컨테이너 실행
docker run -d -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  rebook-trading-service:latest
```

### 9.3 CI/CD 파이프라인

#### Gradle 최적화 기법
본 프로젝트의 Dockerfile은 **멀티 스테이지 빌드**와 **레이어 캐싱**을 활용하여 빌드 속도를 최적화합니다:

```dockerfile
# 1단계: 의존성 레이어 분리 (변경 빈도 낮음)
COPY gradlew gradle build.gradle settings.gradle .
RUN ./gradlew dependencies --no-daemon

# 2단계: 소스 코드 빌드 (변경 빈도 높음)
COPY src src
RUN ./gradlew bootJar --no-daemon
```

**효과**:
- 의존성이 변경되지 않은 경우 Docker 캐시 활용
- 빌드 시간 50-70% 단축 (의존성 다운로드 생략)

### 9.4 프로덕션 체크리스트

#### 배포 전 필수 확인 사항
- [ ] 테스트 커버리지 80% 이상 달성
- [ ] Jacoco 리포트 검토 (`./gradlew test jacocoTestReport`)
- [ ] Sentry 에러 트래킹 설정 확인
- [ ] Spring Cloud Config Server 연결 검증
- [ ] Eureka 서비스 등록 확인
- [ ] RabbitMQ Exchange 및 Queue 생성 확인
- [ ] AWS S3 버킷 권한 및 CORS 설정 검증
- [ ] PostgreSQL 데이터베이스 인덱스 생성 확인
- [ ] Actuator 엔드포인트 보안 설정 (프로덕션 환경에서 `/actuator` 접근 제한)

---

## 10. 문제 해결 (Troubleshooting)

### 10.1 공통 문제

#### Q1. Eureka 서비스 등록 실패
```
com.netflix.discovery.shared.transport.TransportException:
Cannot execute request on any known server
```

**원인**: Eureka Server가 실행되지 않았거나 네트워크 연결 불가

**해결**:
1. Eureka Server 상태 확인: `http://localhost:8761`
2. `application.yaml`에서 Eureka 설정 검증
3. 네트워크 방화벽 확인

#### Q2. RabbitMQ 연결 실패
```
org.springframework.amqp.AmqpConnectException:
java.net.ConnectException: Connection refused
```

**원인**: RabbitMQ 서버 미실행 또는 잘못된 포트/호스트 설정

**해결**:
1. RabbitMQ 실행 확인: `docker ps | grep rabbitmq`
2. RabbitMQ Management UI 접속: `http://localhost:15672`
3. `application.yaml`에서 host, port, username, password 검증

#### Q3. S3 업로드 권한 오류
```
software.amazon.awssdk.services.s3.model.S3Exception:
Access Denied (Service: S3, Status Code: 403)
```

**원인**: AWS IAM 권한 부족 또는 잘못된 자격증명

**해결**:
1. IAM 사용자에 S3 PutObject 권한 부여
2. AWS Credentials 설정 확인 (환경변수 또는 Config Server)
3. S3 버킷 정책 검토

### 10.2 성능 문제

#### Q4. 느린 거래 목록 조회
**증상**: `/api/tradings/me` 엔드포인트 응답 시간 3초 이상

**원인**:
- 데이터베이스 인덱스 부재
- N+1 쿼리 (찜하기 상태 확인)

**해결**:
```sql
-- userId 인덱스 추가
CREATE INDEX idx_trading_user_id ON trading(user_id);

-- state 인덱스 추가 (상태별 필터링 시)
CREATE INDEX idx_trading_state ON trading(state);
```

#### Q5. 메모리 부족 (OOM) 오류
**증상**: 대량 데이터 조회 시 `OutOfMemoryError` 발생

**해결**:
- 페이지네이션 파라미터 검증 추가 (최대 size 제한)
```java
@PageableDefault(size = 20, page = 0)
@Max(value = 100, message = "Page size must not exceed 100")
Pageable pageable
```

---

## 11. 라이선스

본 프로젝트는 교육 목적으로 개발되었습니다.


```
rebook-trading-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/rebooktradingservice/
│   │   │   ├── advice/                        # 전역 예외 처리
│   │   │   │   └── GlobalExceptionHandler.java  (RestControllerAdvice)
│   │   │   │
│   │   │   ├── common/                        # 공통 응답 모델
│   │   │   │   ├── CommonResult.java           (기본 성공 응답)
│   │   │   │   ├── SingleResult.java           (단일 데이터 응답)
│   │   │   │   ├── ListResult.java             (리스트 응답)
│   │   │   │   ├── PageResponse.java           (페이지네이션 응답)
│   │   │   │   ├── ResponseService.java        (응답 래핑 팩토리)
│   │   │   │   └── ResultCode.java             (응답 코드 상수)
│   │   │   │
│   │   │   ├── config/                        # 설정 클래스
│   │   │   │   ├── RabbitConfig.java           (RabbitMQ 설정)
│   │   │   │   └── S3Config.java               (AWS S3 클라이언트 설정)
│   │   │   │
│   │   │   ├── controller/                    # REST 컨트롤러
│   │   │   │   ├── TradingController.java      (거래 CRUD 및 조회)
│   │   │   │   └── TradingUserController.java  (찜하기 관리)
│   │   │   │
│   │   │   ├── enums/                         # 열거형
│   │   │   │   └── State.java                  (거래 상태: SELLING, RESERVED, SOLD)
│   │   │   │
│   │   │   ├── exception/                     # 커스텀 예외
│   │   │   │   ├── CMissingDataException.java  (404 데이터 미존재)
│   │   │   │   ├── CDuplicatedDataException.java (409 중복 데이터)
│   │   │   │   ├── CUnauthorizedException.java (403 권한 없음)
│   │   │   │   └── CInvalidDataException.java  (400 유효하지 않은 입력)
│   │   │   │
│   │   │   ├── feigns/                        # Feign 클라이언트
│   │   │   │   └── BookClient.java             (Book Service 연동)
│   │   │   │
│   │   │   ├── model/                         # DTO 및 엔티티
│   │   │   │   ├── entity/                    # JPA 엔티티
│   │   │   │   │   ├── Trading.java           (거래 메인 엔티티)
│   │   │   │   │   ├── TradingUser.java       (찜하기 조인 테이블)
│   │   │   │   │   └── compositekey/
│   │   │   │   │       └── TradingUserId.java (복합키 클래스)
│   │   │   │   ├── message/                   # 메시징 DTO
│   │   │   │   │   └── NotificationTradeMessage.java (알림 메시지)
│   │   │   │   ├── TradingRequest.java        (거래 등록/수정 요청 DTO)
│   │   │   │   └── TradingResponse.java       (거래 응답 DTO)
│   │   │   │
│   │   │   ├── repository/                    # JPA 리포지토리
│   │   │   │   ├── TradingRepository.java     (거래 데이터 접근)
│   │   │   │   └── TradingUserRepository.java (찜하기 데이터 접근)
│   │   │   │
│   │   │   ├── service/                       # 비즈니스 로직
│   │   │   │   ├── TradingService.java        (거래 생성/수정/삭제)
│   │   │   │   ├── TradingReader.java         (거래 조회 전용)
│   │   │   │   ├── TradingUserService.java    (찜하기 관리)
│   │   │   │   ├── S3Service.java             (AWS S3 파일 업로드)
│   │   │   │   └── NotificationPublisher.java (RabbitMQ 메시지 발행)
│   │   │   │
│   │   │   └── RebookTradingServiceApplication.java (메인 애플리케이션)
│   │   │
│   │   └── resources/
│   │       ├── application.yaml               (Spring Cloud Config 연동)
│   │       ├── application-dev.yaml           (개발 환경 설정)
│   │       └── application-prod.yaml          (운영 환경 설정)
│   │
│   └── test/
│       └── java/com/example/rebooktradingservice/
│           └── (테스트 클래스들)
│
├── build.gradle                               # Gradle 빌드 설정
├── Dockerfile                                 # Docker 이미지 빌드 설정
├── CLAUDE.md                                  # Claude Code 가이드
└── README.md                                  # 프로젝트 문서 (본 파일)
```

