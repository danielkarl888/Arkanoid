package gamecomponents;

/**
 * Counter is a simple class that is used for counting things.
 */
public class Counter {
    private int value;

    /**
     * constructor.
     * @param value is the number of the count to set.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * add number to current count.
     * @param number to add the current count
     */
    public void increase(int number) {
        this.value = this.value + number;
    }

    /**
     * subtract number from current count.
     * @param number to subtract the current count.
     */
    public void decrease(int number) {
        this.value = this.value - number;
    }

    /**
     * get current count.
     * @return the current count.
     */
    public int getValue() {
        return this.value;
    }
}
