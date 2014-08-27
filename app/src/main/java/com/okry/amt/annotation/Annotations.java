package com.okry.amt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by apple on 14-6-25.
 */
public class Annotations {


    public @interface ashow{
        public String value();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface atest{

    }

    public @interface UseCase {
        public int id();
        public String description() default "no description";
    }

}
