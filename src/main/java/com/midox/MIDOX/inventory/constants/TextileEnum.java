package com.midox.MIDOX.inventory.constants;

public class TextileEnum {
//TODO enums are not supported with Master entity framework. Need to work on it so that values in dropdown can be fetched from DB
    public enum EmployeeStatus{
        EMP_ACTIVE("EMP_ACTIVE"),
        EMP_INACTIVE("EMP_INACTIVE");

        private final String status;
        private EmployeeStatus(String status){
            this.status = status;
        }

        public String getStatus(){
            return status;
        }
    }

    public enum Status{
        ACTIVE,
        INACTIVE
    }

    public enum ProcStatus{
        // These entries are static and should be present in DB
        PROC_STAT_TBS,
        PROC_STAT_INP,
        PROC_STAT_HOLD,
        PROC_STAT_FIN
    }

    public enum Designation{
        WORKER,
        SUPERVISOR,
        MANAGER,
        ADMIN
    }

    public enum Gender{
        MALE,
        FEMALE,
        OTHER
    }
}
