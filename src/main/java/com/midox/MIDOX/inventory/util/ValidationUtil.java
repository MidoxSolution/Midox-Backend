package com.midox.MIDOX.inventory.util;

import java.util.List;
import java.util.Optional;

public class ValidationUtil {


    public static boolean isNotNull(Integer integer) {
        if (integer != null) {
            return true;
        }
        return false;
    }

    public static <T> boolean isNotNull(Optional<T> optional) {
        if (optional != null) {
            return true;
        }
        return false;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

}
