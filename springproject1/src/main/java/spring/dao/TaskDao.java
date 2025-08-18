package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.entity.Task;

import java.util.List;

@Repository
public class TaskDao {
    /*
      MENTOR COMMENT
      For JPA compatibility, instead of injecting the SessionFactory directly
      it is recommended to use the following instead:
      @PersistenceContext
      private EntityManager entityManager;
      The EntityManager has methods similar to the ones in the Session, but JPA standard
      The corresponding Hibernate session will be used for those operations in the JPA entity.
     */
    private final SessionFactory sessionFactory;
    public TaskDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    /*
      MENTOR COMMENT
      I like this approach of marking these DAO methods as transactional with propagation = REQUIRED
      Even though transactional boundaries should be defined at the service layer,
      doing this here will make the DAO method join the service scoped transaction,
      while making sure that a transaction exists if for whatever reason the DAO is invoked directly
      by a controller method. If anything outside of this DAO method goes wrong
      within the transaction created at the service layer the operation of this DAO method will be
      also rolled back.
      The readOnly = true is a nice touch for read operations, as it helps the persistence provider
      optimize the transaction.

      An alternative to this - only for read operations - is the use of Propagation.SUPPORTS.
      Doing so:
      * Uses an existing transaction if one exists
      * Executes non-transactionally if none exists
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Task> getAllTasks(int offset, int limit) {
        Query<Task> query = getSession().createQuery("select t from Task t", Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getAllCount() {
        Query<Long> query = getSession().createQuery("select count(t) from Task t", Long.class);
        return Math.toIntExact(query.getSingleResult());
    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Task getTaskById(int id) {
        Query<Task> query = getSession().createQuery("select t from Task t where t.id=:ID", Task.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Task task) {
        getSession().persist(task);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Task task){
        getSession().remove(task);
    }
}
