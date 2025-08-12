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
    private final SessionFactory sessionFactory;
    public TaskDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
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
