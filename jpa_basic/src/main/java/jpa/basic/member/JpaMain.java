package jpa.basic.member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        try {
            /* 생성
                Member member = new Member();
                member.setId(1L);
                member.setName("hello");

                entityManager.persist(member);
             */

            /* 수정
                Member member = entityManager.find(Member.class, 1L);
                member.setName("hello2");

                // JPA를 통해서 Entity를 가져오면 JPA가 관리해준다.
                // transaction commit 시에 업데이트 내용을 확인해서 변경사항이 있으면 update query를 만들어 실행한다.
             */

            /* JPQL 조회
                entityManager.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5) // pagination
                    .setMaxResults(10)
                    .getResultList();
             */


            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
