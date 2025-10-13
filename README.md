# Rebook - 중고책 거래 플랫폼

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.13-6DB33F?style=flat&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.5-6DB33F?style=flat&logo=spring)](https://spring.io/projects/spring-cloud)
[![Gradle](https://img.shields.io/badge/Gradle-8.14.2-02303A?style=flat&logo=gradle)](https://gradle.org/)

Rebook은 Spring Cloud 기반 마이크로서비스 아키텍처로 구축된 중고책 거래 플랫폼입니다. 도서 검색, 리뷰, 거래, 실시간 채팅 등의 기능을 제공합니다.

## 📚 목차

- [시스템 아키텍처](#시스템-아키텍처)
- [마이크로서비스 구성](#마이크로서비스-구성)
- [기술 스택](#기술-스택)
- [시작하기](#시작하기)

## 🏗 시스템 아키텍처
### 소프트웨어 아키텍쳐
![소프트웨어아키텍쳐](https://rebook-bucket.s3.ap-northeast-2.amazonaws.com/rebook/software_architecture.jpg)

## 🎯 마이크로서비스 구성

### 인프라 서비스

| 서비스 | 포트 | 설명 | 상세 문서 |
|--------|------|------|-----------|
| **API Gateway** | 8080 | JWT 인증, 라우팅, CORS 관리를 담당하는 API 게이트웨이 | [📖 상세보기](./rebook-gateway/README.md) |
| **Eureka Server** | 8761 | 서비스 디스커버리 및 등록을 담당하는 중앙 레지스트리 | [📖 상세보기](./rebook-eureka/README.md) |
| **Config Server** | 8888 | Git 기반 중앙 집중식 설정 관리 서버 | [📖 상세보기](./rebook-config/README.md) |

### 비즈니스 서비스

| 서비스 | 포트 | 주요 기능 | 상세 문서 |
|--------|------|-----------|-----------|
| **User Service** | 9000 | • 사용자 인증 및 프로필 관리<br>• Keycloak 통합 인증<br>• AWS S3 프로필 이미지 관리<br>• 관심 카테고리 설정 | [📖 상세보기](./rebook-user/README.md) |
| **Book Service** | 9001 | • 도서 검색 및 등록<br>• Naver Books API 연동<br>• Gemini AI 카테고리 자동 분류<br>• 도서 리뷰 및 평점<br>• 북마크 기능 | [📖 상세보기](./rebook-book/README.md) |
| **Trading Service** | 9002 | • 중고책 거래 등록 및 관리<br>• 거래 상태 추적<br>• 판매자-구매자 매칭<br>• 거래 알림 발송 | [📖 상세보기](./rebook-trading/README.md) |
| **Chat Service** | 9003 | • 실시간 1:1 채팅<br>• WebSocket 지원<br>• MongoDB 메시지 저장<br>• 읽음 상태 관리 | [📖 상세보기](./rebook-chat/README.md) |
| **Notification Service** | 9004 | • 실시간 알림 (SSE)<br>• RabbitMQ 메시지 수신<br>• 알림 설정 관리<br>• 카테고리별 알림 필터링 | [📖 상세보기](./rebook-notification/README.md) |

### 설정 저장소

| 저장소 | 설명 | 상세 문서 |
|--------|------|-----------|
| **Config Repository** | Spring Cloud Config 서버가 사용하는 중앙 집중식 설정 파일 저장소 | [📖 상세보기](./rebook-yamls/README.md) |

## 🛠 기술 스택

### Backend Framework
- **Java 17** - 최신 LTS 버전
- **Spring Boot 3.3.13** - 애플리케이션 프레임워크
- **Spring Cloud 2023.0.5** - 마이크로서비스 패턴
- **Gradle 8.14.2** - 빌드 자동화

### 마이크로서비스 기술
- **Netflix Eureka** - 서비스 디스커버리
- **Spring Cloud Gateway** - API 게이트웨이
- **Spring Cloud Config** - 중앙 설정 관리
- **OpenFeign** - 선언적 HTTP 클라이언트

### 데이터베이스
- **PostgreSQL** - 관계형 데이터베이스 (서비스별 독립 DB)
- **MongoDB** - NoSQL 데이터베이스 (채팅 메시지)
- **Redis** - 캐싱 및 세션 관리

### 메시징 & 통합
- **RabbitMQ** - 비동기 메시지 큐
- **Server-Sent Events (SSE)** - 실시간 알림
- **WebSocket** - 실시간 채팅

### 외부 API
- **Naver Books API** - 도서 검색
- **Google Gemini AI** - 자동 카테고리 분류
- **AWS S3** - 이미지 스토리지
- **Keycloak** - 인증 서버

### 모니터링 & 관찰성
- **Spring Actuator** - 헬스 체크 및 메트릭
- **Prometheus** - 메트릭 수집
- **Sentry** - 에러 추적 및 로깅

## 🚀 시작하기

### 필수 요구사항

| 항목 | 버전 | 설명 |
|------|------|------|
| Java | 17+ | OpenJDK 또는 Oracle JDK |
| Gradle | 7.0+ | 빌드 도구 |
| PostgreSQL | 13+ | 메인 데이터베이스 |
| MongoDB | 5.0+ | 채팅 메시지 저장 |
| Redis | 6+ | 캐싱 레이어 |
| RabbitMQ | 3.9+ | 메시징 브로커 |
| Docker | 20.10+ | 컨테이너화 (선택사항) |

### 로컬 개발 환경 설정

#### 1. 인프라 서비스 시작 (순서 중요)

```bash
# 1. Eureka 서버 시작
cd rebook-eureka
./gradlew bootRun

# 2. Config 서버 시작 (Eureka 등록 필요)
cd ../rebook-config
./gradlew bootRun

# 3. Gateway 시작 (Eureka에서 서비스 발견)
cd ../rebook-gateway
./gradlew bootRun
```

#### 2. 비즈니스 서비스 시작

```bash
# User 서비스
cd rebook-user
./gradlew bootRun

# Book 서비스
cd ../rebook-book
./gradlew bootRun

# Trading 서비스
cd ../rebook-trading
./gradlew bootRun

# Chat 서비스
cd ../rebook-chat
./gradlew bootRun

# Notification 서비스
cd ../rebook-notification
./gradlew bootRun
```

#### 3. 서비스 확인

- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **개별 서비스**: 각 서비스의 포트로 직접 접근 가능

### Docker Compose로 시작하기

```bash
# 전체 시스템 시작
docker-compose up -d

# 로그 확인
docker-compose logs -f

# 전체 시스템 종료
docker-compose down
```

## 💻 개발 가이드

### 프로젝트 구조

```
rebook/
├── rebook-gateway/          # API Gateway
├── rebook-eureka/           # Service Discovery
├── rebook-config/           # Config Server
├── rebook-user/             # User Service
├── rebook-book/             # Book Service
├── rebook-trading/          # Trading Service
├── rebook-chat/             # Chat Service
├── rebook-notification/     # Notification Service
├── rebook-yamls/            # Config Repository
├── compose.yaml             # 도커 컴포즈 파일
├── compose.yaml             # 프로메테우스 메트릭 정보
└── README.md                # 이 파일
```


### API 문서

각 서비스는 Swagger UI를 통해 API 문서를 제공합니다:

**로컬 개발 환경**:
- **User Service**: http://localhost:9000/swagger-ui.html
- **Book Service**: http://localhost:9001/swagger-ui.html
- **Trading Service**: http://localhost:9002/swagger-ui.html
- **Chat Service**: http://localhost:9003/swagger-ui.html
- **Notification Service**: http://localhost:9004/swagger-ui.html

**운영 환경**:
- **통합 API 문서**: https://api.rebookcloak.click/swagger-ui/index.html

> **참고**: Swagger UI는 개발 환경에서만 활성화됩니다.

### 환경 변수 설정

각 서비스는 다음 환경 변수가 필요합니다:

```bash
# Gateway
export JWT_SECRET=your-jwt-secret
export SENTRY_DSN=your-sentry-dsn

# Config Server
export ENCRYPT_KEY=your-encryption-key

# User Service
export AWS_ACCESS_KEY=your-aws-access-key
export AWS_SECRET_KEY=your-aws-secret-key

# Book Service
export NAVER_CLIENT_ID=your-naver-client-id
export NAVER_CLIENT_SECRET=your-naver-client-secret
export GEMINI_API_KEY=your-gemini-api-key
```

### 개발 프로파일

- **dev**: 개발 환경 (로컬 Docker 컨테이너 사용)
- **prod**: 운영 환경 (외부 DB 및 암호화된 설정)

```bash
# 개발 프로파일로 실행
./gradlew bootRun --args='--spring.profiles.active=dev'

# 운영 프로파일로 실행
./gradlew bootRun --args='--spring.profiles.active=prod'
```

## 🔗 추가 리소스

- **아키텍처 상세 문서**: [CLAUDE.md](./CLAUDE.md)
- **설정 관리 가이드**: [rebook-yamls/README.md](./rebook-yamls/README.md)
- **API 게이트웨이 가이드**: [rebook-gateway/README.md](./rebook-gateway/README.md)

## 📊 모니터링

### 헬스 체크

**로컬 개발 환경**:
```bash
# 전체 서비스 상태 확인
curl http://localhost:8761  # Eureka Dashboard

# 개별 서비스 헬스 체크
curl http://localhost:8080/actuator/health  # Gateway
curl http://localhost:9000/actuator/health  # User Service
curl http://localhost:9001/actuator/health  # Book Service
```

**운영 환경**:
- **헬스 체크**: https://api.rebookcloak.click/metrics/query

### 메트릭 수집 및 시각화

**로컬 개발 환경**:
```bash
# Prometheus 메트릭
curl http://localhost:9000/actuator/prometheus
```

**운영 환경**:
- **Grafana 모니터링 대시보드**: https://api.rebookcloak.click/grafana/login

## 🛡️ 보안

- JWT 기반 인증 (Gateway에서 검증)
- 운영 환경 설정 암호화 (`{cipher}` 프리픽스)
- CORS 설정 및 관리
- Actuator 엔드포인트 접근 제한

자세한 내용은 [CLAUDE.md - Security Analysis](./CLAUDE.md#-security-analysis)를 참조하세요.

