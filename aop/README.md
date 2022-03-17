	implementation 'org.springframework.boot:spring-boot-starter-aop'
AOP를 사용하기 위해 필요한 설정. (Spring JPA 등에는 포함되어 있음)  
설정 확인: External Libraries / Gradle: org.aspectj:aspectjweaver:1.9.7

    testCompileOnly 'org.projectlombok:lombok'
    testAnnotationProcessor 'org.projectlombok:lombok'
테스트 코드에서 lombok을 사용하기 위해 필요한 설정.

스프링 AOP는 AspectJ의 문법을 차용하고, 프록시 방식의 AOP를 제공. (AspectJ를 직접 사용하는 것이 아님)  
@Aspect 어노테이션은 AspectJ가 제공하는 어노테이션

