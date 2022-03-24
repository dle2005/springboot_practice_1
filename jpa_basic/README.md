```
jpa.basic
  ㄴ member : JPA 메커니즘의 이해
```

JPA 에서 데이터를 변경하는 모든 동작은 transaction 안에서 이루어져야 한다.

JPA 를 사용해서 Entity 를 가져오면 JPA 가 관리흘 해준다.  
수정시에 transaction commit 시점에 변경 사항을 확인하여 JPA 가 update query 를 만들어 실행한다.

EntityManagerFactory 는 웹 서버가 동작시 하나가 생성되고 애플리케이션 전체에서 공유한다.

EntityManager 는 요청이 발생시 생성되고 종료되며, 쓰레드간에 공유를 하면 안된다.

JPA 는 SQL 을 추상화한 JPQL 이라는 객체 지향 쿼리 언어를 제공한다.  
JPQL 은 엔티티 객체를 대상으로 하며, SQL 은 데이터베이스 테이블을 대상으로 한다.
