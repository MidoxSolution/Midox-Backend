package com.midox.MIDOX.inventory.constants;

public class TextileEnum {

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

    public enum Designation{
        WORKER,
        SUPERVISOR,
        MANAGER,
        ADMIN
    }
}
