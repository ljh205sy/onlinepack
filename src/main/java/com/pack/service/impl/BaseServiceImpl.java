package com.pack.service.impl;

import com.pack.service.BaseService;
import com.pack.service.StringMatcherType;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    private static final Map<Class<?>, ExampleMatcher> modelDefaultMatcher = new HashMap<>();

    @Override
    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        getRepository().deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        getRepository().deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        Optional<T> findById = getRepository().findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            return null;
        }
    }

    @Override
    public T findById(ID id) {
        Optional<T> findById = getRepository().findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            return null;
        }
    }

    @Override
    public boolean exists(ID id) {
        return getRepository().existsById(id);
    }

    @Override
    public boolean exists(T example) {
        ExampleMatcher match = getDefaultMatch(example.getClass());
        Example<T> of = Example.of(example, match);
        return exists(of);
    }

    private ExampleMatcher getDefaultMatch(Class<? extends Object> clazz) {
        if (!modelDefaultMatcher.containsKey(clazz)) {
            modelDefaultMatcher.put(clazz, genDefaultMatcher(clazz));
        }
        ExampleMatcher match = modelDefaultMatcher.get(clazz);
        return match;
    }

    private ExampleMatcher genDefaultMatcher(Class<? extends Object> class1) {
        Field[] declaredFields = class1.getDeclaredFields();
        ExampleMatcher match = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
        for (Field field : declaredFields) {
            ManyToOne many2one = field.getAnnotation(ManyToOne.class);
            OneToOne one2one = field.getAnnotation(OneToOne.class);
            if (many2one != null || one2one != null) {
                // 需要递归
                // fieldName.cFieldName
                match = genPathsDefaultMatcher(match, field.getName(), field.getType(), 0);
            } else {
                StringMatcherType annotation = field.getAnnotation(StringMatcherType.class);
                if (annotation != null) {
                    match = match.withMatcher(field.getName(), GenericPropertyMatcher.of(annotation.value()));
                }
            }
        }

        return match;
    }

    /**
     * 递归处理复杂对象属性的匹配器
     *
     * @param match     递归匹配器
     * @param fieldPath 已有路径
     * @param clazz     当前字段类型
     * @param depth     深度。可能存在循环引用。默认深度不能超过3。从0开始。避免死循环堆栈溢出
     * @return
     */
    private ExampleMatcher genPathsDefaultMatcher(ExampleMatcher match, String fieldPath, Class<?> clazz, int depth) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            ManyToOne many2one = field.getAnnotation(ManyToOne.class);
            OneToOne one2one = field.getAnnotation(OneToOne.class);
            if (many2one != null || one2one != null) {
                // fieldName.cFieldName
                // 如何避免循环引用？
                if (depth <= 3) {
                    match = genPathsDefaultMatcher(match, fieldPath + "." + field.getName(), field.getType(), ++depth);
                }
            } else {
                StringMatcherType annotation = field.getAnnotation(StringMatcherType.class);
                if (annotation != null) {
                    match = match.withMatcher(fieldPath + "." + field.getName(), GenericPropertyMatcher.of(annotation.value()));
                }
            }
        }
        return match;
    }

    @Override
    public boolean exists(Example<T> example) {
        return getRepository().exists(example);
    }

    @Override
    public long count() {
        return getRepository().count();
    }

    @Override
    public long count(T example) {
        ExampleMatcher match = getDefaultMatch(example.getClass());
        Example<T> of = Example.of(example, match);
        return count(of);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return getRepository().count(example);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) {
        return getRepository().findAllById(ids);
    }

    @Override
    public List<T> findAll(T example) {
        ExampleMatcher match = getDefaultMatch(example.getClass());
        Example<T> of = Example.of(example, match);
        return findAll(of);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return getRepository().findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(S example, Sort sort) {
        ExampleMatcher match = getDefaultMatch(example.getClass());
        Example<S> of = Example.of(example, match);
        return findAll(of, sort);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getRepository().findAll(example, sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public <S extends T> Page<S> findAll(S example, Pageable pageable) {
        ExampleMatcher match = getDefaultMatch(example.getClass());
        Example<S> of = Example.of(example, match);
        return findAll(of, pageable);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getRepository().findAll(example, pageable);
    }
}