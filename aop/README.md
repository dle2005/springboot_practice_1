	implementation 'org.springframework.boot:spring-boot-starter-aop'
AOP를 사용하기 위해 필요한 설정. (Spring JPA 등에는 포함되어 있음)  
설정 확인: External Libraries / Gradle: org.aspectj:aspectjweaver:1.9.7

    testCompileOnly 'org.projectlombok:lombok'
    testAnnotationProcessor 'org.projectlombok:lombok'
테스트 코드에서 lombok을 사용하기 위해 필요한 설정.

