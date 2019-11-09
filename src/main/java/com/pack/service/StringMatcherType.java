package com.pack.service;

import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringMatcherType {
	/**
	 * 匹配方式
	 * @return
	 */
	StringMatcher value();
}
