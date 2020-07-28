package com.jfast.core.task;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.jfast.util.PropertiesUtil;

public class ScanTaskCondition implements Condition {
	
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return "true".equalsIgnoreCase(PropertiesUtil.getPropValue("global.properties", "task.active"));
    }
    
}
