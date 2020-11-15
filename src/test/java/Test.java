import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        String s = "a";
        System.out.println(s.hashCode());
        Person p1 = new Person(10, 1.67f, "zhangsan");
        Person p2 = new Person(10, 1.67f, "zhangsan");
        HashMap<Person, Integer> map = new HashMap<>();
        map.put(p1,111);
        map.put(p2,222);
        System.out.println(map.get(p1));
        System.out.println(map.get(p2));
    }
    public static class Person{
        int age;
        float height;
        String name;

        public Person(int age, float height, String name) {
            this.age = age;
            this.height = height;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            if (age != person.age) return false;
            if (Float.compare(person.height, height) != 0) return false;
            return name != null ? name.equals(person.name) : person.name == null;
        }

        @Override
        public int hashCode() {
            int result = age;
            result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }
}
