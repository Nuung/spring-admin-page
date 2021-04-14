# Shopping Mall Simple Admin Page with Spring (boot)

> Spring(boot) practice. Simple admin page for shopping mall!!

## Tech Stack
- RESTful-API
- mysql
- Spring Boot
    - Lombok
    - JPA 
- git (with github)


## 개발 순서

### 1. admin project 구상

- 요구 사항 명세서
- 기능 명세서 
    - View 단위로 세부 기능 다시 명세
    - 프론트는 러프하게 짜놓고 백엔드에 힘을 주자
    
### 2. Entity 및 Repository 설정
1. ERD 설계 (with mysql)
2. 실제 Table 생성
3. Entity 생성
4. Repository 생성
 

### 3. 코드 정상 작동 -> 타겟 테이블 단위로 유닛테스트 실시 
1. Repository 테스트 (Test)
2. 연관관게 설정
3. 필요한 Query Method 생성 


### 4. 실제 API 구현 
- CRUD