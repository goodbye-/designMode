package com.sst.javaFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Student{
	private Integer id;
	private String name ;
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
		return "id是" + id + "的学生的名字是" + name ;
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
	
	
}


public class ArrayFeature {
	public static void main(String[] args) {
		Student[] studentArray = new Student[5];
		for (int i = 0; i < studentArray.length; i++) {
			studentArray[i] = new Student(i,"学生" + i);
		}
		List<Student> studentList = new ArrayList<Student>();
		for (int i = 0; i < studentArray.length; i++) {
			studentList.add( new Student(i,"学生" + i));
		}
		System.out.println(studentArray);//输出不正确，因为使出的是array类的toString()方法
		System.out.println(studentList);//正常,AbstractCollection父类重写了toString方法
		System.out.println(Arrays.toString(studentArray));//正常，Arrays重写了toString方法
		
		List<Student> studnetList1 = new ArrayList<Student>(Arrays.asList(studentArray));
		studnetList1.add(new Student(5, "学生5"));
		System.out.println("studnetList1:" + studnetList1);
		
		List<Student> asList = Arrays.asList(studentArray);//这个是arrays中的内部类，不是那个arrayList
		asList.add(new Student(5, "学生5"));
		System.out.println(asList);//运行报错，不允许添加元素，内部是一个数组
		
		
	}
}
