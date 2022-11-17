package com.scheduler.user.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {
    INACTIVE(0),
    ACTIVE(1),
    SUSPENDED(9);

    private int value;

    UserStatus(int value){
        this.value = value;
    }
    private static final Map<Integer, UserStatus> BY_VALUE = new HashMap<>();

    static {
        for (UserStatus userStatus: values()) {
            BY_VALUE.put(userStatus.value, userStatus);
        }
    }

   public static UserStatus fromInteger(Integer i){
       return BY_VALUE.get(i);
   }
}
