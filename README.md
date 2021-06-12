# 글쑤시개 Back-End

[![codecov](https://codecov.io/gh/Dogcument/gssg-be/branch/master/graph/badge.svg?token=R1MG8FC6BQ)](https://codecov.io/gh/Dogcument/gssg-be)

## 1. 환경

- springboot 2.5.0
- java 11
- h2, mysql
- CI/CD & 코드 품질
    - GitHub Actions
    - codecov

[구현한 기술](https://github.com/Dogcument/gssg-be/wiki/%EA%B5%AC%ED%98%84%ED%95%9C-%EA%B8%B0%EC%88%A0)

## 2. Local 실행 방법

```shell
# 1. jar 생성
> ./gradlew bootjar
BUILD SUCCESSFUL in 9s

> ls build/libs
gssg-be-0.0.1-SNAPSHOT.jar

# 2. local profile 로 jar 실행
> java -jar -Dspring.profiles.active=local build/libs/gssg-be-0.0.1-SNAPSHOT.jar

2021-06-06 22:27:24.611  INFO 17114 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-06-06 22:27:24.777  INFO 17114 --- [           main] com.gssg.gssgbe.GssgBeApplication        : Started GssgBeApplication in 4.857 seconds (JVM running for 5.4)

# 3. http://localhost:8080/swagger-ui.html
```

- `prod` 로 실행하려면 mysql 세팅이 필요합니다.

