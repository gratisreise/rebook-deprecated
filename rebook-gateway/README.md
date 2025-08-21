# Rebook Gateway

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.13-brightgreen)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.5-blue)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Cloud Gateway](https://img.shields.io/badge/Spring%20Cloud%20Gateway-2023.0.5-6DB33F)
![Eureka](https://img.shields.io/badge/Eureka-2023.0.5-FF6600)
![Gradle](https://img.shields.io/badge/Gradle-8.14.2-02303A)

**Rebook 마이크로서비스 아키텍처의 API Gateway 서비스**

모든 클라이언트 요청의 단일 진입점으로 인증, 라우팅, 서비스 디스커버리를 담당하는 핵심 인프라 서비스

</div>

---

## 1. 개요

**Rebook Gateway**는 중고 도서 거래 플랫폼 Rebook의 API Gateway 서비스로, 모든 마이크로서비스에 대한 통합 진입점을 제공합니다. Spring Cloud Gateway 기반으로 구현된 본 서비스는 **중앙화된 인증**, **동적 라우팅**, **서비스 디스커버리**를 통한 확장 가능한 구조를 제공합니다.


### 서비스 역할

본 서비스는 Rebook 플랫폼 내에서 다음과 같은 역할을 담당합니다:

- **JWT 인증**: HMAC-SHA 기반 JWT 토큰 검증 및 사용자 컨텍스트 전파
- **동적 라우팅**: Eureka 기반 서비스 디스커버리 및 클라이언트 사이드 로드 밸런싱
- **프로토콜 지원**: HTTP/REST, WebSocket, Server-Sent Events (SSE) 연결 처리
- **CORS 관리**: 환경별 교차 출처 리소스 공유 정책 적용
- **모니터링**: Prometheus 메트릭 노출 및 Sentry 에러 트래킹

---

## 2. 목차

- [주요 기능](#3-주요-기능)
- [기술 스택](#4-기술-스택)
- [아키텍처](#5-아키텍처)
- [프로젝트 구조](#6-프로젝트-구조)

---

## 3. 주요 기능

### 3.1 인증 및 보안

#### JWT 토큰 검증
- ✅ Authorization 헤더에서 Bearer 토큰 추출 및 검증
- ✅ WebSocket/SSE 연결을 위한 쿼리 파라미터 토큰 지원
- ✅ HMAC-SHA 서명 검증 및 만료 시간 확인
- ✅ 사용자 ID 추출 및 `X-User-Id` 헤더 주입

#### 보안 설정
- ✅ 화이트리스트 기반 공개 엔드포인트 관리
- ✅ CSRF 비활성화 (Stateless JWT 아키텍처)
- ✅ 반응형 Spring Security 설정 (WebFlux 기반)
- ✅ 인증 우회 경로 설정 (`/api/auths/**`)

#### 사용자 컨텍스트 전파
- ✅ JWT Subject에서 사용자 ID 추출
- ✅ 다운스트림 서비스로 `X-User-Id` 헤더 전달
- ✅ 서비스 간 안전한 사용자 식별 정보 공유

### 3.2 라우팅 및 서비스 디스커버리

#### 동적 서비스 라우팅
- ✅ Eureka 기반 서비스 디스커버리
- ✅ 클라이언트 사이드 로드 밸런싱 (`lb://SERVICE-NAME`)
- ✅ 경로 기반 라우팅 (PathPattern Matching)
- ✅ 서비스 헬스체크 및 자동 장애 감지

#### 프로토콜 지원
- ✅ HTTP/REST API 라우팅
- ✅ WebSocket 연결 업그레이드 및 라우팅 (`lb:ws://`)
- ✅ Server-Sent Events (SSE) 스트리밍 지원
- ✅ 프로토콜별 토큰 인증 전략 적용

#### CORS 설정
- ✅ 환경별 허용 Origin 관리 (Dev/Prod)
- ✅ 허용 HTTP 메서드: GET, POST, PUT, DELETE, OPTIONS, PATCH
- ✅ 자격 증명 포함 요청 지원 (Credentials: true)
- ✅ 모든 헤더 허용 (`*`)

### 3.3 모니터링 및 관찰성

- ✅ Spring Actuator 헬스체크 엔드포인트
- ✅ Prometheus 메트릭 노출 (`/actuator/prometheus`)
- ✅ Sentry 실시간 에러 트래킹 및 알림
- ✅ 환경별 Actuator 엔드포인트 접근 제어

---

## 4. 기술 스택

### 4.1 백엔드 프레임워크

| 기술 | 버전 | 용도 |
|------|------|------|
| **Spring Boot** | 3.3.13 | 애플리케이션 프레임워크 |
| **Java** | 17 | 프로그래밍 언어 |
| **Spring WebFlux** | - | 반응형 웹 프레임워크 |
| **Lombok** | - | 보일러플레이트 코드 제거 |

### 4.2 마이크로서비스 인프라 (Spring Cloud)

| 기술 | 버전 | 용도 |
|------|------|------|
| **Spring Cloud Gateway** | 2023.0.5 | API Gateway 및 라우팅 엔진 |
| **Eureka Client** | 2023.0.5 | 서비스 디스커버리 및 등록 |
| **Spring Cloud Config** | 2023.0.5 | 중앙화된 설정 관리 (외부 Config Server) |

### 4.3 보안

| 기술 | 버전 | 용도 |
|------|------|------|
| **Spring Security (Reactive)** | - | 반응형 보안 설정 |
| **JWT (jjwt)** | 0.12.6 | JWT 토큰 파싱 및 검증 |

### 4.4 모니터링 & 로깅

| 기술 | 버전 | 용도 |
|------|------|------|
| **Spring Actuator** | - | 헬스체크 및 메트릭 엔드포인트 |
| **Prometheus** | - | 메트릭 수집 및 모니터링 |
| **Sentry** | 8.13.2 | 실시간 에러 트래킹 및 알림 |

### 4.5 빌드 & 배포

| 기술 | 버전 | 용도 |
|------|------|------|
| **Gradle** | 8.14.2 | 빌드 자동화 도구 |
| **Docker** | - | 컨테이너화 및 배포 |
| **JUnit 5** | - | 단위 테스트 프레임워크 |

---

## 5. 아키텍처

### 5.1 마이크로서비스 아키텍처

```
┌─────────────────┐
│   Client        │ ← Frontend (React, Mobile App)
└────────┬────────┘
         │
    ┌────▼────────────────────────────────┐
    │      API Gateway (port 8080)        │
    │  ┌────────────────────────────────┐ │
    │  │  CustomFilter (Order: -10)     │ │
    │  │  - JWT 토큰 추출 및 검증      │ │
    │  │  - X-User-Id 헤더 주입        │ │
    │  └────────────────────────────────┘ │
    │  ┌────────────────────────────────┐ │
    │  │  SecurityConfig                │ │
    │  │  - 화이트리스트 관리           │ │
    │  │  - CORS 정책 적용              │ │
    │  └────────────────────────────────┘ │
    └────────┬────────────────────────────┘
             │
    ┌────────▼─────────┐
    │  Eureka Server   │ ← 서비스 디스커버리 (port 8761)
    │  (Discovery)     │
    └────────┬─────────┘
             │
   ┌─────────┼─────────┬──────────┬──────────┬──────────┐
   │         │         │          │          │          │
┌──▼───┐ ┌──▼───┐ ┌───▼────┐ ┌───▼────┐ ┌──▼────┐ ┌───▼────┐
│ User │ │ Book │ │Trading │ │  Chat  │ │ Noti  │ │ Other  │
│Service│ │Service│ │Service │ │Service │ │Service│ │Services│
└──────┘ └──────┘ └────────┘ └────────┘ └───────┘ └────────┘

    ┌─────────────────────────────────────────────┐
    │        Infrastructure Services              │
    ├─────────────────────────────────────────────┤
    │  Eureka  │  Config Server  │  PostgreSQL   │
    │  Redis   │  RabbitMQ       │  Prometheus   │
    └─────────────────────────────────────────────┘
```

### 5.2 요청 처리 흐름

#### 1. 인증 및 라우팅 플로우
```
Client Request
    ↓
[1] Gateway 수신 (port 8080)
    ↓
[2] CustomFilter 실행 (Order: -10)
    ├─ JWT 토큰 추출
    │  ├─ Authorization: Bearer <token> (REST API)
    │  └─ ?token=<token> (WebSocket/SSE)
    ├─ 화이트리스트 체크 (/api/auths/** → 인증 우회)
    ├─ JwtUtil 토큰 검증
    │  ├─ HMAC-SHA 서명 검증
    │  ├─ 만료 시간 확인
    │  └─ Subject(userId) 추출
    └─ Request Mutation
       └─ X-User-Id 헤더 추가
    ↓
[3] SecurityConfig 적용
    ├─ CORS 정책 적용
    └─ 화이트리스트 엔드포인트 허용
    ↓
[4] Eureka 서비스 디스커버리
    ├─ 서비스 이름으로 인스턴스 조회
    └─ 헬스체크 기반 인스턴스 선택
    ↓
[5] 로드 밸런싱 (Round Robin)
    └─ 정상 인스턴스로 요청 전달
    ↓
[6] Downstream Service 처리
    └─ X-User-Id 헤더 기반 사용자 인증
    ↓
[7] 응답 반환
    └─ Client에게 응답 전달
```

#### 2. WebSocket 연결 플로우
```
// WebSocket 라우팅 특수 처리
1. Client WebSocket Handshake Request
   - URL: /api/ws-chat/connect?token=<jwt-token>

2. CustomFilter 검증
   - Query parameter에서 token 추출
   - JWT 검증 후 X-User-Id 헤더 추가

3. Gateway 라우팅
   - lb:ws://CHAT-SERVICE로 프로토콜 업그레이드
   - WebSocket 연결 유지

4. Chat Service 처리
   - X-User-Id 기반 사용자 식별
   - 양방향 메시지 스트리밍
```

---

## 6. 프로젝트 구조

### 주요 디렉토리 설명

| 디렉토리/파일 | 역할 | 주요 기능 |
|---------|------|----------|
| **RebookGatewayApplication.java** | 메인 애플리케이션 | Spring Boot 애플리케이션 진입점 |
| **CustomFilter.java** | 글로벌 인증 필터 | JWT 검증 및 사용자 컨텍스트 주입 (Order: -10) |
| **JwtUtil.java** | JWT 유틸리티 | 토큰 파싱, 서명 검증, 사용자 ID 추출 |
| **SecurityConfig.java** | 보안 설정 | 화이트리스트 관리, CORS 정책, CSRF 비활성화 |


```
rebook-gateway/
├── src/main/java/com/example/rebookgateway/
│   ├── RebookGatewayApplication.java    # 메인 애플리케이션
│   ├── CustomFilter.java                # JWT 검증 및 X-User-Id 헤더 주입
│   ├── JwtUtil.java                     # JWT 토큰 파싱 및 검증
│   └── SecurityConfig.java              # 화이트리스트, CORS 설정
│
├── src/main/resources/
│   ├── application.yaml                 # Spring Cloud Config 연동
│   ├── application-dev.yaml             # 개발 환경 설정
│   └── application-prod.yaml            # 운영 환경 설정
│
├── build.gradle
├── Dockerfile
└── README.md
```