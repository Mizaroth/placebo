package com.placebo.sababot.repository.api;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional(propagation=Propagation.REQUIRED)
public abstract class AbstractGenericDAO<T> implements CRUDOperations<T> {
  @Autowired
  private SessionFactory sessionFactory;
  
  private Class<T> clazz;
  
  @Override
  public T findOne(final long id) {
    return getCurrentSession().get(clazz, id);
  }

  @Override
  public List<T> findAll() {
    return getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
  }

  @Override
  public void create(final T entity) {
    if(entity != null)
      getCurrentSession().saveOrUpdate(entity);
  }

  @Override
  public T update(final T entity) {
    if(entity != null)
      return (T) getCurrentSession().merge(entity);
    return null;
  }

  @Override
  public void delete(final T entity) {
    if(entity != null)
      getCurrentSession().delete(entity);
  }

  @Override
  public void deleteById(final long entityId) {
    final T entity = findOne(entityId);
    if(entity != null)
      delete(entity);
  }

  protected Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  public void setClazz(Class<T> clazz) {
    this.clazz = clazz;
  }
}
