# 글쑤시개 Back-End

## 1. 환경

- springboot 2.5.0
- h2, mysql

## 2. 실행 방법

```shell
# 1. war 생성
> ./gradlew bootjar
BUILD SUCCESSFUL in 9s

> ls build/libs
gssg-be-0.0.1-SNAPSHOT.jar

# 2. dev profile 로 jar 실행
> java -jar -Dspring.profiles.active=dev build/libs/gssg-be-0.0.1-SNAPSHOT.jar

2021-06-06 22:27:24.611  INFO 17114 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-06-06 22:27:24.777  INFO 17114 --- [           main] com.gssg.gssgbe.GssgBeApplication        : Started GssgBeApplication in 4.857 seconds (JVM running for 5.4)

# 3. http://localhost:8080/swagger-ui/index.html
```

- `prod` 로 실행하려면 mysql 세팅이 필요합니다.

