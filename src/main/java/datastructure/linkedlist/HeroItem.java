package datastructure.linkedlist;

public class HeroItem implements Comparable<HeroItem>{
    private String name;
    private String nickName;
    private int no;

    public HeroItem(String name, String nickName, int no) {
        this.name = name;
        this.nickName = nickName;
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "HeroItem{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", no=" + no +
                '}';
    }

    @Override
    public int compareTo(HeroItem heroItem) {
        return this.no > heroItem.no ? 1 : this.no == heroItem.no ? 0 : -1;
    }
}
