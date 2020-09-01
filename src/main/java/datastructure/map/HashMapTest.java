package datastructure.map;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Object, Integer> hashMap = new HashMap<>();
        hashMap.put(new Person("youxu",28,1.80f), 1);
        hashMap.put(new Person("youxu",28,1.80f), 2);
        hashMap.put(new Person("lisi",26,1.85f), 3);
        hashMap.put("aaa", 4);
        hashMap.put(null, 5);
//        System.out.println(hashMap.size());
//        System.out.println(hashMap.get(new Person("youxu",28,1.80f)));
//        System.out.println(hashMap.get(new Person("lisi",26,1.85f)));
//        System.out.println(hashMap.get("aaa"));
//        System.out.println(hashMap.get(null));
//        hashMap.remove(null);
//        hashMap.remove(new Person("youxu",28,1.80f));
//        hashMap.remove("aaa");
//        System.out.println(hashMap.size());
        hashMap.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            boolean visit(Object key, Integer value) {
                System.out.println(key + "---" + value.toString());
                return false;
            }
        });
    }

    private static class Person{
        String name;
        Integer age;
        Float height;

        public Person(String name, Integer age, Float height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (name != null ? !name.equals(person.name) : person.name != null) return false;
            if (age != null ? !age.equals(person.age) : person.age != null) return false;
            return height != null ? height.equals(person.height) : person.height == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (age != null ? age.hashCode() : 0);
            result = 31 * result + (height != null ? height.hashCode() : 0);
            return result;
        }
    }
}
