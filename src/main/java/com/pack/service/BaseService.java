package com.pack.service;

import com.pack.repository.BaseRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * 标准service层查询接口
 * 中间直接用类型的查询，需要类型T的属性不用基础类型，都用包装类型，这样才能够进行正确的查询
 * @author Administrator
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID extends Serializable> {
	
	BaseRepository<T, ID> getRepository();
	
	<S extends T> S save(S entity);

	T findById(ID id);

	<S extends T> Iterable<S> save(Iterable<S> entities);
	
	void delete(ID id);
	
	void delete(T entity);
	
	void deleteInBatch(Iterable<T> entities);
	
	void deleteAllInBatch();
	
	T getOne(ID id);
	
	boolean exists(ID id);
	
	/**
	 * 将T对象中的不为null的属性按与进行默认查询
	 * @param example
	 * @return
	 */
	boolean exists(T example);
	
	boolean exists(Example<T> example);
	
	long count();
	
	/**
	 * 将T对象中的不为null的属性按与进行默认查询
	 * @param example
	 * @return
	 */
	long count(T example);
	
	<S extends T> long count(Example<S> example);
	
	List<T> findAll();
	
	List<T> findAll(Sort sort);
	
	List<T> findAll(Iterable<ID> ids);
	
	List<T> findAll(T example);
	
	<S extends T> List<S> findAll(Example<S> example);
	
	<S extends T> List<S> findAll(S example, Sort sort);
	
	<S extends T> List<S> findAll(Example<S> example, Sort sort);
	
	Page<T> findAll(Pageable pageable);
	
	<S extends T> Page<S> findAll(S example, Pageable pageable);
	
	<S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

}
