/*
**************************************************************************************************
Author: Sriram Yadavalli
Date: 9/15/2021
Course: CMSC 350 6380
Assignment: Project 2
**************************************************************************************************
Description:
This class defines an unchecked exception that contains a constructor that allows a message to be supplied.
**************************************************************************************************
*/

import java.util.Comparator;
import java.util.List;

public class OrderedList {
    public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list) {
        return checkSorted(list, (lhs, rhs) -> lhs.compareTo(rhs));
    }

    public static <T> boolean checkSorted(List<T> list, Comparator<? super T> comparator) {
        if(list != null && !list.isEmpty() && comparator != null){
            T prev = null;
            for (T curr : list) {
                if(prev == null){
                    prev = curr;
                }
                else{
                    if(comparator.compare(prev, curr) >= 0){
                        return false;
                    }
                }
            }
            return true;
        }
        else{
            throw new RuntimeException("List and comparator cannot be null or empty");
        }
    }
}
