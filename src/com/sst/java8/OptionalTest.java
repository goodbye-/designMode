package com.sst.java8;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        // of:为非 null 的值创建一个 Optional。
        // 调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("Sanaulla");
        // 传入参数为null，抛出NullPointerException.
        // Optional<String> someNull = Optional.of(null);

        // ofNullable:为指定的值创建一个 Optional，如果指定的值为 null，则返回一个空的 Optional。
        Optional empty = Optional.ofNullable(null);

        // isPresent:如果值存在返回 true，否则返回 false。
        // get:如果 Optional 有值则将其返回，否则抛出 NoSuchElementException。
        // isPresent方法用来检查Optional实例中是否包含值
        if (name.isPresent()) {
            // 在Optional实例内调用get()返回已存在的值
            System.out.println(name.get());// 输出Sanaulla
        }
        // ifPresent:如果 Optional 实例有值则为其调用 consumer，否则不做处理

        /*
         * 要理解 ifPresent 方法，首先需要了解 Consumer 类。简答地说，Consumer
         * 类包含一个抽象方法。该抽象方法对传入的值进行处理，但没有返回值。Java8 支持不用接口直接通过 lambda 表达式传入参数。
         */
        // ifPresent方法接受lambda表达式作为参数。
        // lambda表达式对Optional的值调用consumer进行处理。
        name.ifPresent((value) -> {
            System.out.println("The length of the value is: " + value.length());
        });

        // orElse:如果有值则将其返回，否则返回指定的其它值。
        System.out.println(empty.orElse("There is no value present!"));
        System.out.println(name.orElse("There is some value!"));
        // orElseGet:orElseGet 与 orElse 方法类似，区别在于得到的默认值。
        // orElse方法将传入的字符串作为默认值，orElseGet 方法可以接受 Supplier 接口的实现用来生成默认值。
        System.out.println(empty.orElseGet(() -> "Default Value"));
        // orElseThrow:如果有值则将其返回，否则抛出 supplier 接口创建的异常。
        try {
            // orElseThrow与orElse方法类似。与返回默认值不同，
            // orElseThrow会抛出lambda表达式或方法生成的异常
            empty.orElseThrow(ValueAbsentException::new);
        } catch (Throwable ex) {
            // 输出: No value present in the Optional instance
            System.out.println(ex.getMessage());
        }

        // map:map 方法文档说明如下：

        /*
         * 如果有值，则对其执行调用 mapping 函数得到返回值。如果返回值不为 null，则创建包含 mapping 返回值的 Optional
         * 作为 map 方法返回值，否则返回空 Optional。 map 方法用来对 Optional 实例的值执行一系列操作。通过一组实现了
         * Function 接口的 lambda 表达式传入操作。
         */
        // map方法执行传入的lambda表达式参数对Optional实例的值进行修改。
        // 为lambda表达式的返回值创建新的Optional实例作为map方法的返回值。
        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));

        // flatMap:
        /*
         * 如果有值，为其执行 mapping 函数返回 Optional 类型返回值，否则返回空 Optional。flatMap 与
         * map（Funtion）方法类似，区别在于 flatMap 中的 mapper 返回值必须是 Optional。调用结束时，flatMap
         * 不会对结果用 Optional 封装。
         * 
         * flatMap 方法与 map 方法类似，区别在于 mapping 函数的返回值不同。map 方法的 mapping
         * 函数返回值可以是任何类型 T，而 flatMap 方法的 mapping 函数必须是 Optional。
         */
        // flatMap与map（Function）非常类似，区别在于传入方法的lambda表达式的返回类型。
        // map方法中的lambda表达式返回值可以是任意类型，在map函数返回之前会包装为Optional。
        // 但flatMap方法中的lambda表达式返回值必须是Optionl实例。
        upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(upperName.orElse("No value found"));// 输出SANAULLA

        // filter:
        /*
         * filter 个方法通过传入限定条件对 Optional 实例的值进行过滤。文档描述如下：
         * 
         * 如果有值并且满足断言条件返回包含该值的 Optional，否则返回空 Optional。
         * 
         * 读到这里，可能你已经知道如何为 filter 方法传入一段代码。是的，这里可以传入一个 lambda 表达式。对于 filter
         * 函数我们应该传入实现了 Predicate 接口的 lambda 表达式。
         */

        // filter方法检查给定的Option值是否满足某些条件。
        // 如果满足则返回同一个Option实例，否则返回空Optional。
        Optional<String> longName = name.filter((value) -> value.length() > 6);
        System.out.println(longName.orElse("The name is less than 6 characters"));// 输出Sanaulla

    }

}

class ValueAbsentException extends Throwable {

    private static final long serialVersionUID = 1L;

    public ValueAbsentException() {
        super();
    }

    public ValueAbsentException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "No value present in the Optional instance";
    }
}
