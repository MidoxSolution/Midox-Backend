package com.midox.MIDOX.inventory.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
//Nice
public class ValidationUtil {


    public static boolean isNotNull(Integer integer) {
        if (integer != null) {
            return true;
        }
        return false;
    }

    public static <T> boolean isNotNull(Optional<T> optional) {
        if (optional != null && optional.isPresent()) {
            return true;
        }
        return false;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> boolean isNotEmpty(List<T> list) {
        return list.size() > 0;
    }

    public static boolean isNotNull(String string) {
        if (string != null) {
            return true;
        }
        return false;
    }

    public static <T> boolean isNotNull(List<T> optionsList) {
        if (optionsList.size() > 0) {
            return true;
        }
        return false;
    }
}
