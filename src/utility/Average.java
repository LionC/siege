package utility;

public class Average {
    double sum = 0;
    int count = 0;

    public Average() {}

    public void add(Number aNumber) {
        this.sum += aNumber.doubleValue();
        this.count++;
    }

    public void reset() {
        this.sum = 0;
        this.count = 0;
    }

    public double get() {
        return count > 0 ? sum/count : 0;
    }

    public int getCount() {
        return this.count;
    }

    public double getSum() {
        return this.sum;
    }
}
