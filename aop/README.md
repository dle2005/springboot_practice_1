	implementation 'org.springframework.boot:spring-boot-starter-aop'
AOP를 사용하기 위해 필요한 설정. (Spring JPA 등에는 포함되어 있음)  
설정 확인: External Libraries / Gradle: org.aspectj:aspectjweaver:1.9.7

    testCompileOnly 'org.projectlombok:lombok'
    testAnnotationProcessor 'org.projectlombok:lombok'
테스트 코드에서 lombok을 사용하기 위해 필요한 설정.

스프링 AOP는 AspectJ의 문법을 차용하고, 프록시 방식의 AOP를 제공. (AspectJ를 직접 사용하는 것이 아님)  
@Aspect 어노테이션은 AspectJ가 제공하는 어노테이션

@Pointcut 에 포인트컷 표현식을 사용  
메서드 이름과 파라미터를 합쳐 포인트컷 시그니처라 한다.  
매서드의 반환 타입은 void이여야 하며, 코드 내용은 비워둔다.

포인트 컷을 별도의 파일로 관리해서 호출하는 경우 클래스 경로를 포함시켜야 한다.  

어드바이스는 기본적으로 순서를 보장하지 않음.  
순서리 지정하고 싶으면 @Aspect 적용 단위로 @Order 어노테이션을 적용.  
어드바이스 단위가 아니라 클래스 단위로 적용해야 한다.