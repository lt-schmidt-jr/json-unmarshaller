package com.elasticpath.rest.sdk.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Repeatable(Zooms.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface Zoom {

	public String[] value();
}
