package com.lifetrack.core.dao;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.lifetrack.core.exception.IllegalEntityExcpetion;
import com.lifetrack.core.validator.BeanValidator;

/**
 * hibernate拦截器，这里只是在向数据库插入数据时填充创建时间字段
 * 
 * @author Charles Feng
 * @since 1.0.0
 */
public class HibernateInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 4426550787363797218L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		for (int i = 0; i < propertyNames.length; i++) {
			if ("createTime".equals(propertyNames[i])) {
				state[i] = new Date();
				break;
			}
		}

		if (!BeanValidator.validate(entity)) {
			throw new IllegalEntityExcpetion();
		}

		return true;

	}
}
