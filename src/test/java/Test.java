public class Test {
    public static void main(String[] args) {
        String phone = "18566622667";
        String regex = "(^\\d{3})\\d.*(\\d{4})";
        phone = phone.replaceAll(regex, "$1****$2");
        System.out.println(phone);
    }
}
