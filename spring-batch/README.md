Spring Batch
- 프로그램을 실행하면 등록된 모든 Job 이 실행
- 특정 job 만 실행을 원하면 edit configuration 에서 설정
  - Program arguments: --spring.batch.job.names=jobName
  - application.yml batch: job: names: ${job.name:NONE}
    - job.name 파라미터로 job 을 실행
    - Program arguments: --job.name=jobName
    - default NONE 설정으로 지정하지 않으면 배치 실행 안됨 

@EnableBatchProcessing
- SpringBootApplication main method 에 추가 
- Batch 를 실행하겠다는 설정

Job
- spring batch 의 실행 단위
- JobBuilderFactory 를 통해 생성
  - spring batch 에서 제공하는 클래스
  - 이미 빈에 등록되어 있음
- 하나의 job 은 여러개의 step 을 가질 수 있음  
  
Job method
- .get : job 의 name 을 설정    
  - name 은 스프링 배치를 실행할 수 있는 키 이기도 함 
- .incrementer : job 의 실행단위를 구분
  - RunIdIncrementer : job 이 실행될때마다 파라미터 아이디를 자동으로 생성
- .start : job 실행 시 최초로 실행될 step 설정

Step
- job 의 실행 단위
- StepBuilderFactory 를 통해 생성
- Chunk 기반 : 하나의 큰 덩어리를 n개씩 나눠서 실행
  - Chunk 기반 step 은 ItemReader, ItemProcessor, ItemWriter 가 있음
- Task 기반 : 하나의 작업 기반으로 실행

Step - Chunk 기반
- 대량 처리를 하는 경우 Tasklet 보다 비교적 쉽게 구혀 
- 예를 들어 10,000개의 데이터 중 1,000개씩 10개의 덩어리로 수행  
- Item 은 배치 처리 대상 객체를 의미
- ItemReader 는 배치 처리 대상 객체를 읽어 ItemProcessor 또는 ItemWriter 에게 전달
  - 예시로, 파일 또는 DB 에서 데이처를 읽는 작업
- ItemProcessor 는 input 객체를 output 객체로 filtering 또는 processing 하여 ItemWriter 에게 전달
  - 예시로, ItemReader 에서 읽은 데이터를 수정 또는 ItemWriter 대상인지 filtering
  - ItemProcessor 는 optional 하며, ItemReader 또는 ItemWriter 가 역활을 대신할 수 있음
- ItemWriter 는 배치 처리 대상 객체를 처리
  - 예시로, DB update 를 하거나, 처리 대상 사용자에게 알림을 보낸다.
- chunk(chunkSize) chunkSize 개씩 데이터를 분할  

Step - Task 기반
- 배치 처리 과정이 비교적 쉬운 경우 쉽게 사용
- 대량 처리를 하는 경우 더 복잡
- 하나의 큰 덩어리를 여러 덩어리로 나누어 처리하기 부적합
- 예를 들어 10,000개의 데이터를 한번에 처리하거나, 소동으로 1,000개씩 분할해야함

Step method
- .get : job 의 get 과 동일
- .tasklet : step 의 실행단위

job 과 step 은 Bean 으로 등록되어야 한다.

Execution Context
- JobExecution Context 는 job 내에서만 공유
- StepExecution Context 는 step 내에서만 공유

배치 실행을 위한 메타 데이터가 저장되는 테이블
- BATCH_JOB_INSTANCE
  - Job 이 실행되며 생성되는 최상위 계층의 테이블
  - job_name 과 job_key 를 기준으로 하나의 row 가 생성되며, 같은 job_name 과 job_key 가 저장될 수 없다.
  - job_key 는 BATCH_JOB_EXECUTION_PARAMS 에 저장되는 Parameter 를 나열해 암호화해 저장한다.
- BATCH_JOB_EXECUTION
  - Job 이 실행되는 동안 시작/종료 시간, job 상태 등을 관리 
- BATCH_JOB_EXECUTION_PARAMS
  - Job 을 실행하기 위해 주입된 parameter 정보 저장
- BATCH_JOB_EXECUTION_CONTEXT
  - Job 이 실행되며 공유해야할 데이터를  직렬화해 저장
- BATCH_STEP_EXECUTION
  - Step 이 실행되는 동안 필요한 데이터 또는 실행된 결과 저장
- BATCH_STEP_EXECUTION_CONTEXT
  - Step 이 실행되며 공유해야할 데이터를 직렬화해 저장

메타 테이블 script 는 spring-batch-core/org.springframework/batch/core/* 에 위치
- External Libraries 에서 검색하여 찾을 수 있음
- MySQL 용은 schema-mysql.sql 파일 
- schema.sql 설정
  - schema-**.sql 의 실행 구분은
  - DB 종류별로 script 가 구분
  - spring.batch.initialize-schema config 로 구분한다.
  - ALWAYS, EMBEDDED, NEVER 로 구분한다.
    - ALWAYS : 항상 실행
    - EMBEDDED : 내장 DB일 때만 실행
    - NEVER : 항상 실행 안함
  - 기본 값은 EMBEDDED 

