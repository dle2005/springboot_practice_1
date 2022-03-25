```
jpa.basic
  ㄴ jpamain : JPA 메커니즘의 이해
  ㄴ domain : 상품 주문 예제
```

```
목차
  ㄴ JPA메커니즘의 이해
    ㄴ EntityManagerFactory
    ㄴ EntityManager
    ㄴ EntityManager Transaction
  ㄴ 영속성 컨텍스트 (EntityManager)
    ㄴ 1차 캐시
    ㄴ 쓰기 지연
    ㄴ 변경 감지
  ㄴ 플러시 (flush)
  ㄴ 엔티티 매핑
    ㄴ 객체와 테이블 매핑
    ㄴ 데이터베이스 스키마 자동 생성   
    ㄴ 필드와 컬럼 매핑
    ㄴ 기본 키 매핑
```

### **JPA 매커니즘의 이해**

JPA 에서 데이터를 변경하는 모든 동작은 transaction 안에서 이루어져야 한다.

JPA 를 사용해서 Entity 를 가져오면 JPA 가 관리흘 해준다.  
수정시에 transaction commit 시점에 변경 사항을 확인하여 JPA 가 update query 를 만들어 실행한다.

EntityManagerFactory 는 웹 서버가 동작시 하나가 생성되고 애플리케이션 전체에서 공유한다.

EntityManager 는 요청이 발생시 생성되고 종료되며, 쓰레드간에 공유를 하면 안된다.

JPA 는 SQL 을 추상화한 JPQL 이라는 객체 지향 쿼리 언어를 제공한다.  
JPQL 은 엔티티 객체를 대상으로 하며, SQL 은 데이터베이스 테이블을 대상으로 한다.

### **영속성 컨텍스트**

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

### **플러시**

영속성 컨텍스트의 변경내용을 데이터베이스에 반영  

트랜젝션이 commit 되면 flush 가 자동으로 실행  
변경 감지 - 수정 엔티티 쓰기 지연 SQL 저장소에 등록 - 쿼리를 데이터베이스에 전송  

JPQL query 실행시 플러시를 먼저 자동으로 호출된다.

### **엔티티 매핑**

객체와 테이블 매핑: @Entity, @Table  
필드와 컬럼 매핑: @Column  
기본 키 매핑: @Id  
연관관계 매핑: @ManyToOne, @JoinColumn

@Entity  
- @Entity 어노테이션이 붙은 클래스는 JPA 가 관리한다.  
- JPA 를 사용해서 테이블과 매핑할 클래스는 @Entity 가 필수이다. 
- 기본 생성자 필수(파라미터가 없는 public 또는 protected 생성자)
- final class, enum, interface, inner class 사용 불가
- 저장할 필드에 final 사용 불가

@Table
- 에티티와 매핑할 테이블 지정
- ex. @Table(name = "MBR")

데이터베이스 스키마 자동 생성
- DDL 을 애플리케이션 실행 시점에 자동 생성
- 데이터베이스 방언을 활요해서 데이터베이스에 맞는 적절한 DDL 생성
- 설정 hibernate.hbm2ddl.auto (create, create-drop, update, validate, none)
  - create: 기존 테이블 삭제 후 다시 생성 (DROP + CREATE)
  - create-drop: create 와 같으나 종료시점에 테이블 DROP
  - update: 변경분만 반영 (추가만 가능)
  - validate: 엔티티와 테이블이 정상 매핑되었는지만 확인
- 제약조건 추가 (alter)
  - @Column(unique = true, nullable = false, length = 10)
  - @Table(uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"NAME", "AGE"})})  
    
필드와 컬럼 매핑
- @Column(name = "DB_Column_name") db column name 과 매핑 
- @Enumerated(EnumType.STRING) Enum type 필드로 사용하고 싶은 경우 사용. db 에는 enum 이 존재 하지 않음.
- @Temporal(TemporalType.TIMESTAMP) 자바의 DATE type 은 날짜와 시간 모두 가지고 있지만, db 에는 DATE, TIME, TIMESTAMP 가 존재.
- @Lob varchar 를 넘어가는 범위를 가지고 싶을때. (blob, clob)
- @Transient db 에는 없고 메모리에서만 사용할 경우 

기본 키 매핑
- 직접 할당: @Id
- 자동 생성: GeneratedValue  
  - AUTO: 기본 설정, 데이터베이스 방언에 맞춰 생성 (아래 3개 중)
  - IDENTITY: 데이터베이스에 위임, MySQL
    - persist 하는 순간 DB에 바로 insert query 실행
  - SEQUENCE: 데이터베이스 시퀸스 오브젝트 사용, ORACLE
    - @SequenceGenerator 사용
  - TABLE: 키 생성용 테이블 사용, 모든 DB
    - @TableGenerator 필요
  
상품 주문 예제
- 회원 기능
  - 회원 등록
  - 회원 조회
- 상품 기능
  - 상품 등록
  - 상품 수정
  - 상품 조회
- 주문 기능
  - 상품 주문
  - 주문 내역 조회
  - 주문 취소