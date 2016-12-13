package andrii.schedule;

import java.util.Comparator;

/**
 * Created by andrii on 07.12.16.
 */
public class Job implements Comparable<Job> {
    private int weight;
    private int lenght;

    public Job(int weight, int lenght) {
        this.weight = weight;
        this.lenght = lenght;
    }

    public int getWeight() {
        return weight;
    }

    public int getLenght() {
        return lenght;
    }

    @Override
    public int compareTo(Job o2) {
        return this.lenght - o2.getLenght();
    }

    @Override
    public String toString(){
        return new StringBuffer("priority: ").append(weight/lenght)
                .append(";\tlength: ").append(lenght)
                .append(";\tweight: ").append(weight)
                .toString();
    }
}