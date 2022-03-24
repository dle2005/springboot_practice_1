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

영속성 컨텍스트란 엔티티를 영구 저장하는 환경이다.  
ex) EntityManager.persist(entity); DB 저장이 아닌 영속화, 영속성 컨텍스트에 저장하는 것이다.    
엔티티 매니저를 통해서 영속성 컨텍스트에 접근한다.  

영속되면 바로 DB 에 쿼리를 날리는 것이 아닌 영속 컨텍스트(entityManager)에 1차 캐시된다.
1차 캐시는 요청의 트랜잭션 내에서만 존재하여 사실상 큰 이점을 기대하기는 어렵다.  

영속 컨텍스트는 내부에 쓰기 지연 SQL 저장소를 갖고 영속되때마다 해당 저장소에 query 를 추가한다. 
추가된 query 들은 transaction 이 commit 되는 순간 실행된다. 
jdbc.batch_size 를 설정하여 한번에 여러 query 를 실행시킬 수 있다.

1차 캐시에 객체를 읽어온 시점을 스냅샷에 저장하고 커밋 시점에 스냅샷과 엔티티를 비교하여 변경감지를 한다.

엔티티의 생명주기
- 비영속(new/transient) : 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태.
- 영속(managed) : 영속성 컨텍스트에 관리되는 상태
- 준영속(detached) : 영속성 컨텍스트에 저장되었다가 분리된 상태
- 삭제(removed) : 삭제된 상태


