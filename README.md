# 1. CowAPI

![CowAPI](https://user-images.githubusercontent.com/22286954/176443623-a4f0dd26-0603-4bf2-8a00-2562cd633653.jpg)

<br>

## 프로젝트 기획 배경
- 다양한 기능들을 시도해보고 부족한 부분을 지속적으로 학습하기 위해 springboot framework 개인 프로젝트 진행합니다.
- 블로그 정리를 하며 개발 과정들을 문서화합니다. 
- 직접 개발 시작부터 배포까지 혼자 진행해 보는 것을 도전합니다.

## 프로젝트 정의 및 목표
- 정의 : AI 기능을 서비스 하는 플랫폼
- 목표
    - 프로젝트 시작부터 배포까지 홀로 백엔드 담당
    - AI의 기능보다 효율적이고 정확하게 서비스 하는 플랫폼 개발에 중점
    - TDD 기반의 기능 구현
    - 보다 정확하고 효율적인 코드를 작성해보기
    - React를 통한 프론트엔드와에 데이터 통신 학습

## 개발 환경
- Java 11 + SpringBoot
- Gradle
- Intellij

<br>

# 2. 요구사항

## 사용자
- JWT 토큰을 통한 사용자 인증
- OAuth2.0을 사용한 로그인 서비스
- 관리자와 일반 사용자 권한

## 대시보드 (HOME)
- SSE (Server Sent Event)를 통한 실시간 처리

## 공지
- 사용자의 권한 (관리자, 일반 사용자)에 따른 접근 제어

## QnA
- 새로운 게시글이 올라올 경우 Slack 봇을 이용한 알림 기능

## AI
- WebClient를 사용하여 Spring 서버와 AI 서버 통신

## 기타
- Swagger를 통한 API 문서 자동화
- TDD 기반 기능 구현

# 3. 프론트 엔드
- 프론트엔드와의 소통을 원할하게 하기 위해 프론트 엔드 개발 시도합니다.
- 백엔드 학습이 주요 목표이므로 디자인 보다는 기능 중심의 개발을 합니다.
- React + java script
- Github : [여기](https://github.com/Oh-JunYoung/CowAPI-FrontEnd)