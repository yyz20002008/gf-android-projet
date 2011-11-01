package com.lifetrack.core.validator;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lifetrack.utils.LogUtils;



@Component
public class BeanValidator {
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	private static final Logger logger = LoggerFactory.getLogger(BeanValidator.class);

	public static <T> boolean validate(T bean) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean);
		if (constraintViolations.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("[" + bean.getClass() + ":]{");
			for (Iterator<ConstraintViolation<T>> i = constraintViolations.iterator(); i.hasNext();) {
				ConstraintViolation<T> v = i.next();
				sb.append(String.format("<Property \"%1$s\" has invalidValue(%2$s), errorMsg:\"%3$s\">",
						v.getPropertyPath(), v.getInvalidValue(), v.getMessage()));
			}
			sb.append("}");
			LogUtils.warn(logger, sb.toString());
			return false;
		}
		return true;
	}

	public static <T> boolean validateProperty(T bean, String property) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(bean, property);
		if (constraintViolations.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("[" + bean.getClass() + ":]{");
			for (Iterator<ConstraintViolation<T>> i = constraintViolations.iterator(); i.hasNext();) {
				ConstraintViolation<T> v = i.next();
				sb.append(String.format("<Property \"%1$s\" has invalidValue(%2$s), errorMsg:\"%3$s\">",
						v.getPropertyPath(), v.getInvalidValue(), v.getMessage()));
			}
			sb.append("}");
			LogUtils.warn(logger, sb.toString());
			return false;
		}
		return true;
	}
}
