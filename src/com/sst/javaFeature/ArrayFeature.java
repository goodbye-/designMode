package com.sst.javaFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Student implements Comparable<Student>{
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id是" + id + "的学生的名字是" + name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public Student(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return id - o.getId();
    }
}

class Teacher<T> {
    T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return t.toString();
    }
}

class ClassParameter<T> {
    public T[] f(T[] arg) {
        return arg;
    }
}

class MethodParameter {
    public static <T> T[] f(T[] arg) {
        return arg;
    }
}

public class ArrayFeature {
    @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
    public static void main(String[] args) {
        System.out.println(ArrayFeature.class.getPackage().getName());
        Student[] studentArray = new Student[5];
        for (int i = 0; i < studentArray.length; i++) {
            studentArray[i] = new Student(i, "学生" + i);
        }
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < studentArray.length; i++) {
            studentList.add(new Student(i, "学生" + i));
        }
        System.out.println(studentArray);// 输出不正确，因为使出的是array类的toString()方法
        System.out.println(studentList);// 正常,AbstractCollection父类重写了toString方法
        System.out.println(Arrays.toString(studentArray));// 正常，Arrays重写了toString方法

        List<Student> studnetList1 = new ArrayList<Student>(Arrays.asList(studentArray));
        studnetList1.add(new Student(5, "学生5"));
        System.out.println("studnetList1:" + studnetList1);

        List<Student> asList = Arrays.asList(studentArray);// 这个是arrays中的内部类，不是那个arrayList
        // asList.add(new Student(5, "学生5"));
        System.out.println(asList);// 运行报错，不允许添加元素，内部是一个数组，没有实现add(E)方法，
        // 默认的父类abstract类的默认实现是抛出不支持操作的异常,而arrayList就实现了这个方法，大致就是新建一个数组，将新的元素复制到新书组中去

        // 数组的初始化方法
        Student[] students1 = null;// 不初始化怎么输出啊？
        int i = 5;
        Student[] students2 = new Student[i];// 引用类型初始化为null，基本类型按照基本法初始化，数字为0；
        System.out.println(students1 + " " + Arrays.toString(students2));
        int[] intArray = { 1, 23, 4, 5, 6, };
        int[] intArray2 = new int[] { 1, 23, 4, 56, 7, 8, 9 };
        intArray = intArray2;// 输出结果是intArray2
        System.out.println(Arrays.toString(intArray) + Arrays.toString(intArray2));//之后如果intArray变了，2也跟着变
        int[][] multiArray = { { 1, 2 }, { 1, 2, 3 }, { 1, 2, 3, 4, } };// 这个被成为粗糙数组
        System.out.println(Arrays.toString(multiArray));// 输出结果：[[I@4e25154f,
                                                        // [I@70dea4e,
                                                        // [I@5c647e05]，可以看出这和c语言本质是一样的
        System.out.println(Arrays.deepToString(multiArray));

        // 数组与泛型
        // Teacher<T>[] teachers = new
        // Teacher<T>[5];//编译错误，不能实例化有参数化类型的数组，数组必须知道他所持有的具体类型，而泛型会擦除
        // Teacher<Student>[] teachers = new Teacher<Student>[5];//依然不行
        Teacher<Student>[] teachers;//虽然不能实例化但是可以创建这样的引用
        // 但是你可以参数化数组本身
        Integer[] ints = { 1, 2, 3, 4, 5 };
        Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Integer[] ints2 =
          new ClassParameter<Integer>().f(ints);
        Double[] doubles2 =
          new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles2 = MethodParameter.f(doubles);
        System.out.println(Arrays.toString(ints2) + Arrays.toString(doubles2));
        
        //尽管不能创建实际的持有泛型的数组对象，但是可以创建非泛型的数组对象，然后转型
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[])la; // "Unchecked" warning
        ls[0] = new ArrayList<String>();
        // Compile-time checking produces an error:
        //! ls[1] = new ArrayList<Integer>();

        // The problem: List<String> is a subtype of Object
        Object[] objects = ls; // So assignment is OK
        // Compiles and runs without complaint:
        objects[1] = new ArrayList<Integer>();

        // However, if your needs are straightforward it is
        // possible to create an array of generics, albeit
        // with an "unchecked" warning:
        List<Student>[] spheres =
          (List<Student>[])new List[10]; //unchecked warning
        for(int i1 = 0; i1 < spheres.length; i1++)
          spheres[i1] = new ArrayList<Student>();

        
        //创建测试数据，arrays的方法
        Arrays.fill(intArray, 100);
        System.out.println(Arrays.toString(intArray));
        Arrays.fill(studentArray, new Student(100, "name100"));
        studentArray[1].setName("name101");
        studentArray[1].setId(101);
        System.out.println(Arrays.toString(studentArray));//证明是同一个引用
        
        //System.arraycopy(src, srcPos, dest, destPos, length);
        Arrays.equals(studentArray,null) ;
        
        Arrays.sort(intArray2);
        System.out.println(Arrays.toString(intArray2));
        Arrays.sort(doubles, Collections.reverseOrder());//Comparator的作用就在于如果给你一个comparable的对象，你可以改写它的排序方法，自己写一个实现类，重写comparaTo方法即可，基本类型数组不可以使用这种方式
        System.out.println(Arrays.toString(doubles));
        //前提是数组已经排好序了
        Arrays.sort(doubles);
        System.out.println(Arrays.binarySearch(doubles, 2.2));//这个是有第三个参数comparator的，因为比如对字符串排序，你之前的顺序是caseInsense的，那么如果不传，对于默认的排序自然排序是错误的，会引发不可预知的事情，所以要保持排序的一致
        System.out.println(Arrays.toString(doubles));
    }
}

