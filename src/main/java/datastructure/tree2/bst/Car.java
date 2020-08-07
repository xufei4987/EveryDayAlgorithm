package datastructure.tree2.bst;

public class Car {
    private int fee;

    public Car(int fee) {
        this.fee = fee;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Car{" +
                "fee=" + fee +
                '}';
    }
}
