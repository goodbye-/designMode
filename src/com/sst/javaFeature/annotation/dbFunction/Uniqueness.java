//: annotations/database/Uniqueness.java
// Sample of nested annotations
package com.sst.javaFeature.annotation.dbFunction;

public @interface Uniqueness {
  Constraints constraints()
    default @Constraints(unique=true);
} ///:~
