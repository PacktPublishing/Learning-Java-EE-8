package com.sebastian_daschner.learning_java_ee.control;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD, TYPE})
@Documented
public @interface Tracked {

    @Nonbinding
    ProcessTracker.Category value();

}
