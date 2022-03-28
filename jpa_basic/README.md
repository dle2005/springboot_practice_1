```
jpa.basic
  ㄴ jpamain : JPA 메커니즘의 이해
    ㄴ 영속성 컨텍스트
  ㄴ domain : 상품 주문 예제
    ㄴ 엔티티 매핑
    ㄴ 연관관계 매핑 
    ㄴ 상속관계 매핑
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
  ㄴ 상품 주문 예제
  ㄴ 연관관계 매핑
    ㄴ 연관관계의 주인 (양방향 매칭 큐칙)  
  ㄴ 상속관계 매핑
    ㄴ 조인 전략
    ㄴ 단일 테이블 전략
    ㄴ 구현 클래스마다 테이블 전략
    ㄴ MappedSuperclass  
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
  
연관관계의 주인 (양방향 매칭 규칙)
- 객체의 두 관계중 하나를 연관관계의 주인으로 지정
- 연관관계의 주인만이 외래 키를 관리(등록, 수정)
- 주인이 아닌쪽은 읽기만 가능
- 주인은 mappedBy 속성 사용 X
- 주인이 아니면 mappedBy 속성으로 주인 지정 
- 외래키가 있는 곳을 주인으로 정하는것이 좋음

상속관계 매핑
- 관계형 데이터베이스는 상속 관계가 없음
- 슈퍼타입 서프타입 관계라는 모델링 기법이 객체 상속과 유사
- 상속관계 매핑: 객체의 상속과 구조와 DB의 슈퍼타입 서브타입 관계를 매핑
  - 각각 테이블로 변환 -> 조인 전략
  - 통합 테이블로 변환 -> 단일 테이블 전략
  - 서브타입 테이블로 변환 -> 구현 클래스마다 테이블 전략 

조인 전략
- 각 엔티티마다 @Inheritance(strategy = InheritanceType.JOINED) 추가
- DTYPE 추가는 ITEM 엔티티에 @DiscriminatorColumn 추가
  - DTYPE 에는 하위 엔티티명이 표시 
- 장점
  - 테이블 정규화
  - 외래 키 참조 무결성 제약조건 활용가능
  - 저장 공간 효율화
- 단점
  - 조회시 조인을 많이 사용, 성능 저하
  - 조회 쿼리가 복잡함
  - 데이터 저장시 INSERT SQL 2번 호출 

```
ITEM_TB
ITEM_ID (PK), NAME, PRICE, DTYPE

ALBUM_TB
ITEM_ID (PK, FK), ARTIST

MOVIE_TB
ITEM_ID (PK, FK), DIRECTOR, ACTOR

BOOK_TB
ITEM_ID (PK, FK) AUTHOR, ISBN
```
  
단일 테이블 전략 
- 별도의 설정 없을시 default 로 단일 테이블 전략 사용 
- 부모 엔티티에 @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
- Insert 가 한번에 이루어지고 JOIN 이 필요없어 성능은 가장 좋음   
- DTYPE 이 필수 @DiscriminatorColumn 없어도 생성됨 
- 장점
  - 조인이 필요 없으므로 일반적으로 조회 성능이 빠름
  - 조회 쿼리가 단순함
- 단점
  - 자식 엔티티가 매핑한 컬럼은 모두 null 허용
  - 단일 테이블에 모든 것을 저장하므로 테이블이 커질 수 있으며 상황에 따라서 조회 성능이 오히려 느려질 수 있다.
  
```
ITEM_TB
ITEM_ID (PK), NAME, PRICE, ARTIST, DIRECTOR, ACTOR, AUTHOR, ISBN, DTYPE
```

구현 클래스마다 테이블 전략 
- 부모 엔티티에 @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
- 이 경우 부모 엔티티를 추상 클래스로 만드는것이 설계상 맞음
- 이 전략은 DBA, ORM 전문가 둘 다 추천 x
- 장점
  - 서브 타입을 명확하게 구분해서 처리할 때 효과적
  - not null 제약조건 사용 가능
- 단점
  - 여러 자식 테이블을 함께 조회할 때 성능이 느림 (UNION SQL 사용)
  - 자식 테이블을 통합해서 쿼리하기 어려움 
```
ALBUM_TB
ITEM_ID (PK), NAME, PRICE, ARTIST

MOVIE_TB
ITEM_ID (PK), NAME, PRICE, DIRECTOR, ACTOR

BOOK_TB
ITEM_ID (PK), NAME, PRICE, AUTHOR, ISBN
```

MappedSuperclass 매핑 정보 상속 
- 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
  - 부모 클래스에 @MappedSuperclass 어노테이션 추가
  - 자식 클래스는 부모를 상속 받아 사용
- 상속관계 매핑 x, 엔티티 x, 테이블과 매핑 x
- 직접 생성해서 사용할 일이 없으므로 추상 클래스 사용 권장
- 조회, 검색 불가
- 테이블과 관계 없고, 단순히 엔티티가 공통으로 사용하는 매핑 정보를 모으는 역활
- @Entity 클래스는 엔티티나 @MappedSuperclass 로 지정한 클래스만 상속 가능 