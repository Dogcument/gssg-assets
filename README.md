# 글쑤시개 Back-End
> 현재 문서가 현행화되어있지 않습니다.

## 1. 환경

[![codecov](https://codecov.io/gh/Dogcument/gssg-assets/branch/master/graph/badge.svg?token=Y3ZHZKHU2Z)](https://codecov.io/gh/Dogcument/gssg-assets)

![](https://img.shields.io/badge/spring%20boot-2.5.0-green) ![](https://img.shields.io/badge/java-11-green)

![](https://img.shields.io/badge/h2-1.4.200-blue) ![](https://img.shields.io/badge/mysql-8.0.25-blue) ![](https://img.shields.io/badge/jpa-%20-blue) ![](https://img.shields.io/badge/querydsl-%20-blue)

![](https://img.shields.io/badge/git%20action-%20-yellow)

- [구현한 기술](https://github.com/Dogcument/gssg-assets/wiki/%EA%B5%AC%ED%98%84%ED%95%9C-%EA%B8%B0%EC%88%A0-&-%EA%B8%B0%EB%8A%A5)
- [ERD](https://github.com/Dogcument/gssg-assets/wiki/ERD)

## 2. Local 실행 방법

```shell
# 1. Application 실행 (기본으로 local profile 셋업)
> ./gradlew :gssg-interface:bootRun -x test

2022-05-09 04:00:13.133 - INFO 39863 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : [] Tomcat started on port(s): 8080 (http) with context path ''
2022-05-09 04:00:13.154 - INFO 39863 --- [  restartedMain] com.gssg.gssgbe.GssgBeApplication        : [] Started GssgBeApplication in 9.104 seconds (JVM running for 9.668)

# 3. http://localhost:8080/swagger-ui.html
```

- `local` 을 제외한 PHASE 로 실행은 지원하지 않습니다.
