package belog.dao.common;


import belog.pojo.PageModel;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Beldon on
 */
public interface CommonDao<T> {

    /**
     * 保存一个实体
     *
     * @param entity
     */
    void saveEntity(T entity);

    /**
     * 保存所有实体
     *
     * @param entityList
     */
    void saveBatch(Collection<T> entityList);

    /**
     * 保存所有实体
     *
     * @param entities
     */
    void saveBatch(T[] entities);

    /**
     * 新增或更新一个实体
     *
     * @param entity
     */
    void saveOrUpdate(T entity);

    /**
     * 新增或更新一批实体
     *
     * @param entities
     */
    void saveOrUpdate(Collection<T> entities);

    /**
     * 更新一个实体
     *
     * @param entity
     */
    void updateEntity(T entity);

    /**
     * 更新一批实体
     *
     * @param entityList
     */
    void updateBatch(Collection<T> entityList);

    /**
     * 更新一批实体
     *
     * @param entities
     */
    void updateBatch(T[] entities);

    /**
     * 删除一个实体
     *
     * @param entity
     */
    void deleteEntity(T entity);


    /**
     * 根据ID删除
     *
     * @param id
     */
    void delete(Serializable id);

    void delete(String id);

    void delete(Integer id);


    /**
     * 删除一批实体
     *
     * @param entityList
     */
    void deleteBatch(Collection<T> entityList);

    /**
     * 删除一批实体
     *
     * @param entities
     */
    void deleteBatch(T[] entities);

    /**
     * 根据ID查找实体
     *
     * @param id
     * @param t
     * @return
     */
    T findById(Serializable id, T t);


    /**
     * 根据ID查找实体
     *
     * @param id
     * @return
     */
    T findById(Serializable id);


    T findById(Serializable id, Class<T> clazz);

    /**
     * 根据字符串ID查找实体
     *
     * @param id
     * @param t
     * @return
     */
    T findByStringId(String id, T t);

    /**
     * 根据字符串ID查找实体
     *
     * @param id
     * @return
     */
    T findByStringId(String id);

    /**
     * 根据整型ID查找实体
     *
     * @param id
     * @param t
     * @return
     */
    T findByIntId(Integer id, T t);

    /**
     * 根据整型ID查找实体
     *
     * @param id
     * @return
     */
    T findByIntId(Integer id);


    /**
     * 根据hql查找一个实体
     *
     * @param hql
     * @return
     */
    T findOneByHql(String hql);

    /**
     * 根据hql查找实体
     *
     * @param
     * @param hql
     * @return
     */
    List<T> findByHql(String hql);

    /**
     * 查找所有
     *
     * @return
     */
    List<T> findAll(Class<T> entityClass);

    /**
     * 根据排序，查找所有
     *
     * @param entityClass
     * @param order
     * @return
     * @see Order
     */
    List<T> findAll(Class<T> entityClass, Order order);


    /**
     * 查找所有
     *
     * @return
     */
    List<T> findAll();


    /**
     * 排序查找所有
     *
     * @param order
     * @return
     */
    List<T> findAll(Order order);

    /**
     * 获取分页内容
     *
     * @param hql
     * @param pm
     * @return
     */
    List<T> getPageResult(final String hql, final PageModel pm);

    /**
     * 获取分页内容
     *
     * @param entityClass 实体
     * @param pm
     * @return
     */
    List<T> getPageResult(final Class<T> entityClass, final PageModel pm);

    /**
     * 获取分页内容，有排序
     *
     * @param entityClass
     * @param pm
     * @return
     */
    List<T> getPageResult(final Class<T> entityClass, final PageModel pm, Order order);

    /**
     * 获取分页内容
     *
     * @param pm
     * @return
     */
    List<T> getPageResult(final PageModel pm);


    /**
     * 获取分页内容 有排序
     *
     * @param pm
     * @param order
     * @return
     */
    List<T> getPageResult(final PageModel pm, Order order);


    /**
     * 获取分页内容
     *
     * @param instance 实体类
     * @param pm
     * @return
     */
    List<T> getPageResult(final T instance, final PageModel pm);


    /**
     * 查找分页内
     *
     * @param instance
     * @param pm
     * @param order
     * @return
     */
    List<T> getPageResult(final T instance, final PageModel pm, Order order);

    /**
     * 获取要查询的内容总数
     *
     * @param hql
     * @return
     */
    int getAllRowCount(String hql);

    /**
     * 获取要查询的内容总数
     *
     * @param entityClass 实体类
     * @return
     */
    int getAllRowCount(Class<T> entityClass);

    /**
     * 获取要查询的内容总数
     *
     * @return
     */
    int getAllRowCount();

    /**
     * 根据HQL获取分页内容
     *
     * @param hql
     * @param pm
     * @return
     */
    PageModel findPageByHql(String hql, PageModel pm);

    /**
     * 根据实体获取分页内容，该分页按自然排序，无过滤条件
     *
     * @param entityClass 实体类
     * @param pm
     * @return
     */
    PageModel findPageByClass(Class<T> entityClass, PageModel pm);

    /**
     * 根据实体获取分页内容，该分页按自然排序，按照order排序
     *
     * @param entityClass
     * @param pm
     * @param order
     * @return
     */
    PageModel findPageByClass(Class<T> entityClass, PageModel pm, Order order);

    /**
     * 获取分页内容，该分页按自然排序，无过滤条件
     *
     * @param pm
     * @return
     */
    PageModel findPage(PageModel pm);

    /**
     * 获取分页内容，该分页按自然排序，根据排序
     *
     * @param pm
     * @param order
     * @return
     */
    PageModel findPage(PageModel pm, Order order);


    /**
     * 根据example获取分页,无排序方式
     *
     * @param entity
     * @param pm
     * @return
     */
    PageModel findPageByExample(T entity, PageModel pm);

    /**
     * 根据example获取分页,排序
     *
     * @param entity
     * @param pm
     * @param order
     * @return
     */
    PageModel findPageByExample(T entity, PageModel pm, Order order);

    /**
     * 通过例子查找列表
     *
     * @param exampleEntity
     * @return
     */
    List<T> findByExample(T exampleEntity);


    /**
     * 通过例子查找列表,有排序
     *
     * @param exampleEntity
     * @param order
     * @return
     */
    List<T> findByExample(T exampleEntity, Order order);

    /**
     * 通过例子查找一个数据
     *
     * @param exampleEntity 例子实体
     * @return
     */
    T findOneByExample(T exampleEntity);

}
