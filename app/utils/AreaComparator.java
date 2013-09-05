package utils;

import java.util.Comparator;

public class AreaComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        int i = o2.compareTo(o1);
        return i == 0 ? 1 : i;
    }
}

