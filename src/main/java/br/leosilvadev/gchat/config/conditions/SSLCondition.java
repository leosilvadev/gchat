package br.leosilvadev.gchat.config.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SSLCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String property = context.getEnvironment().getProperty("gchat.ssl.enabled");
		return Boolean.parseBoolean(property);
	}

}
