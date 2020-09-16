package datastructure.union;

public class Test {

    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 10);
        Student s2 = new Student("lisi", 11);
        Student s3 = new Student("wangwu", 12);
        Student s4 = new Student("zhouliu", 13);

        GenericUnionFind<Student> genericUnionFind = new GenericUnionFind<>();
        genericUnionFind.makeSet(s1);
        genericUnionFind.makeSet(s2);
        genericUnionFind.makeSet(s3);
        genericUnionFind.makeSet(s4);

        genericUnionFind.union(s1,s2);
        genericUnionFind.union(s3,s4);

        System.out.println(genericUnionFind.isSame(s1,s2));//true
        System.out.println(genericUnionFind.isSame(s2,s3));//false
        System.out.println(genericUnionFind.isSame(s3,s4));//true
        System.out.println(genericUnionFind.isSame(s1,s4));//false
        System.out.println("----------after union----------");
        genericUnionFind.union(s1,s3);
        System.out.println(genericUnionFind.isSame(s1,s2));//true
        System.out.println(genericUnionFind.isSame(s2,s3));//true
        System.out.println(genericUnionFind.isSame(s3,s4));//true
        System.out.println(genericUnionFind.isSame(s1,s4));//true
    }

    private static class Student{
        String name;
        int age;
        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
