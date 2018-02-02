//: annotations/database/DBTable.java
package com.sst.javaFeature.annotation.dbFunction;
import java.lang.annotation.*;

@Target(ElementType.TYPE) // Applies to classes only
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
  public String name() default "";
} ///:~
