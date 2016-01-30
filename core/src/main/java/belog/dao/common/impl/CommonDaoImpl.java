package belog.dao.common.impl;

import belog.dao.common.CommonDao;
import belog.pojo.PageModel;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import static org.hibernate.criterion.Example.create;

/**
 * @author Beldon
 */
public abstract class CommonDaoImpl<T> implements CommonDao<T> {

    private final Class<T> entityClass;

    @Autowired
    private HibernateTemplate template;

    public CommonDaoImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void saveEntity(T entity) {
        template.save(entity);
    }

    public void saveBatch(Collection<T> entityList) {
        for (T t : entityList) {
            saveEntity(t);
        }
    }

    public void saveBatch(T[] entities) {
        for (T t : entities) {
            saveEntity(t);
        }
    }

    public void saveOrUpdate(T entity) {
        template.saveOrUpdate(entity);
    }

    public void saveOrUpdate(Collection<T> entities) {
        for (T t : entities) {
            saveEntity(t);
        }
    }

    public void updateEntity(T entity) {
        template.update(entity);
    }

    public void updateBatch(Collection<T> entityList) {
        for (T t : entityList) {
            updateEntity(t);
        }
    }

    public void updateBatch(T[] entities) {
        for (T t : entities) {
            updateEntity(t);
        }
    }

    public void deleteEntity(T entity) {
        template.delete(entity);
    }

    public void delete(Serializable id) {
        template.delete(findById(id));
    }

    public void delete(String id) {
        template.delete(findById(id));
    }

    public void delete(Integer id) {
        template.delete(findById(id));
    }

    public void deleteBatch(Collection<T> entityList) {
        for (T t : entityList) {
            deleteEntity(t);
        }
    }

    public void deleteBatch(T[] entities) {
        for (T t : entities) {
            deleteEntity(t);
        }
    }

    public T findById(Serializable id, T t) {
        return (T) template.get(t.getClass(), id);
    }

    public T findById(Serializable id) {
        return template.get(entityClass, id);
    }

    public T findById(Serializable id, Class<T> clazz) {
        return template.get(clazz, id);
    }

    public T findByStringId(String id, T t) {
        return findById(id, t);
    }

    public T findByStringId(String id) {
        return template.get(entityClass, id);
    }

    public T findByIntId(Integer id, T t) {
        return findById(id, t);
    }

    public T findByIntId(Integer id) {
        return template.get(entityClass, id);
    }

    public T findOneByHql(String hql) {
        List list = template.find(hql);
        return list != null && list.size() != 0 ? (T) list.get(0) : null;
    }

    public List<T> findByHql(String hql) {
        return (List<T>) template.find(hql);
    }

    public List<T> findAll(Class<T> entityClass) {
        return this.template.loadAll(entityClass);
    }


    public List<T> findAll(Class<T> entityClass, Order order) {
        return template.getSessionFactory().getCurrentSession().createCriteria(entityClass).addOrder(order).list();
    }

    public List<T> findAll() {
        return this.template.loadAll(entityClass);
    }

    public List<T> findAll(Order order) {
        return findAll(entityClass, order);
    }

    public List<T> getPageResult(String hql, PageModel pm) {
        return template.getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult(pm.getBeginIndex())
                .setMaxResults(pm.getPageSize()).list();
    }

    public List<T> getPageResult(Class<T> entityClass, PageModel pm) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        return (List<T>) template.findByCriteria(criteria, pm.getBeginIndex(), pm.getPageSize());
    }

    public List<T> getPageResult(final Class<T> entityClass, final PageModel pm, Order order) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass).addOrder(order);
        return (List<T>) template.findByCriteria(criteria, pm.getBeginIndex(), pm.getPageSize());
    }

    public List<T> getPageResult(PageModel pm) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        return (List<T>) template.findByCriteria(criteria, pm.getBeginIndex(), pm.getPageSize());
    }

    public List<T> getPageResult(final PageModel pm, Order order) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass).addOrder(order);
        return (List<T>) template.findByCriteria(criteria, pm.getBeginIndex(), pm.getPageSize());
    }

    public List<T> getPageResult(final T instance, final PageModel pm) {
        return template.getSessionFactory().getCurrentSession()
                .createCriteria(instance.getClass()).setFirstResult(pm.getBeginIndex()).setMaxResults(pm.getPageSize()).add(create(instance)).list();
    }

    public List<T> getPageResult(final T instance, final PageModel pm, Order order) {
        return template.getSessionFactory().getCurrentSession()
                .createCriteria(instance.getClass()).setFirstResult(pm.getBeginIndex()).setMaxResults(pm.getPageSize())
                .add(create(instance)).addOrder(order).list();
    }


    public int getAllRowCount(String hql) {
        return findByHql(hql).size();
    }

    public int getAllRowCount(Class<T> entityClass) {
        Query query = template.getSessionFactory().getCurrentSession().createQuery("SELECT COUNT(*) FROM " + entityClass.getName());
        return ((Number) query.uniqueResult()).intValue();
    }

    public int getAllRowCount() {
        Query query = template.getSessionFactory().getCurrentSession().createQuery("SELECT COUNT(*) FROM " + entityClass.getName());
        return ((Number) query.uniqueResult()).intValue();
    }

    public PageModel findPageByHql(String hql, PageModel pm) {
        if (pm.getTotalCount() == 0) {
            pm.setTotalCount(this.getAllRowCount(hql));
        }
        if (pm.getTotalCount() > 0) {
            List<T> list = this.getPageResult(hql, pm);
            if (list.isEmpty()) {
                int currentPage = pm.getCurrentPage() - 1;
                if (currentPage > pm.getTotalCount()) {
                    currentPage = pm.getTotalPage();
                }
                pm.setCurrentPage(currentPage);
                list = this.getPageResult(hql, pm);
            }
            pm.setList(list);
        } else {
            pm.setCurrentPage(0);
        }
        return pm;
    }

    public PageModel findPageByClass(Class<T> entityClass, PageModel pm) {
        if (pm.getTotalCount() == 0) {
            pm.setTotalCount(this.getAllRowCount(entityClass));
        }
        if (pm.getTotalCount() > 0) {
            List list = this.getPageResult(entityClass, pm);
            if (isEmpty(list, pm)) {
                list = this.getPageResult(entityClass, pm);
            }
            pm.setList(list);
        } else {
            pm.setCurrentPage(0);
        }
        return pm;
    }

    public PageModel findPageByClass(Class<T> entityClass, PageModel pm, Order order) {
        if (pm.getTotalCount() == 0) {
            pm.setTotalCount(this.getAllRowCount(entityClass));
        }
        if (pm.getTotalCount() > 0) {
            List list = this.getPageResult(entityClass, pm, order);
            if (isEmpty(list, pm)) {
                list = this.getPageResult(entityClass, pm, order);
            }
            pm.setList(list);
        } else {
            pm.setCurrentPage(0);
        }
        return pm;
    }


    public PageModel findPage(PageModel pm) {
        return findPageByClass(entityClass, pm);
    }

    public PageModel findPage(PageModel pm, Order order) {
        return findPageByClass(entityClass, pm, order);
    }

    public PageModel findPageByExample(T entity, PageModel pm) {
        if (pm.getTotalCount() == 0) {
            pm.setTotalCount(this.findByExample(entity).size());
        }
        if (pm.getTotalCount() > 0) {
            List<T> results = getPageResult(entity, pm);
            if (isEmpty(results, pm)) {
                results = this.getPageResult(entity, pm);
            }
            pm.setList(results);
        } else {
            pm.setCurrentPage(0);
        }
        return pm;
    }

    public PageModel findPageByExample(T entity, PageModel pm, Order order) {
        if (pm.getTotalCount() == 0) {
            pm.setTotalCount(this.findByExample(entity).size());
        }
        if (pm.getTotalCount() > 0) {
            List<T> results = getPageResult(entity, pm, order);
            if (isEmpty(results, pm)) {
                results = this.getPageResult(entity, pm, order);
            }
            pm.setList(results);
        } else {
            pm.setCurrentPage(0);
        }
        return pm;
    }

    public List<T> findByExample(T exampleEntity) {
        return template.findByExample(exampleEntity);
    }

    public List<T> findByExample(T exampleEntity, Order order) {
        return (List<T>) template.findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(order));
    }

    public T findOneByExample(T exampleEntity) {
        List<T> list = this.findByExample(exampleEntity);
        return list.size() > 0 ? list.get(0) : null;
    }

    private boolean isEmpty(List<T> results, PageModel pm) {
        if (results.isEmpty()) {
            int currentPage = pm.getCurrentPage() - 1;
            if (currentPage > pm.getTotalPage()) {
                currentPage = pm.getTotalPage();
            }
            pm.setCurrentPage(currentPage);
            return true;
        } else {
            return false;
        }
    }
}
