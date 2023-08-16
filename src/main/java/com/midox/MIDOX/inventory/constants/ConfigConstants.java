package com.midox.MIDOX.inventory.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigConstants {

    public static final class Numbers {
        public static final int ZERO = 0;
        public static final String COLOR_FABRIC_CODE = "colorFabricCode";
        public static final String MATERIAL = "material";
        public static final String BRAND = "brand";
        public static final String PRODUCT = "product";
        public static final String DESIGN = "design";
    }

    public static final class DropDownTypes {
        public static final String SUB_CATEGORY = "subCategory";
        public static final String COLOR_FABRIC_CODE = "colorFabricCode";
        public static final String MATERIAL = "material";
        public static final String BRAND = "brand";
        public static final String PRODUCT = "product";
        public static final String DESIGN = "design";
    }

    public static final class Messages {
        public static final String STATUS = "MIDOX IS WORKING FINE.";
        public static final String GROUP_ENTITY_ADDED = "GROUP ENTITY ADDED SUCCESSFULLY.";
        public static final String GROUP_ENTITY_ADDED_FAILED = "GROUP ENTITY ADD FAILED.";
        public static final String GROUP_MASTER_ADDED = "GROUP MASTER ADDED SUCCESSFULLY.";
        public static final String GROUP_MASTER_ADDED_FAILED = "GROUP MASTER ADD FAILED.";
        public static final String GROUP_ENTITY_EDITED = "GROUP ENTITY EDITED SUCCESSFULLY.";
        public static final String GROUP_ENTITY_EDITED_FAILED = "GROUP ENTITY EDIT FAILED.";
        public static final String GROUP_ENTITY_DELETED = "GROUP ENTITY DELETED SUCCESSFULLY.";
        public static final String GROUP_ENTITY_DELETED_FAILED = "GROUP ENTITY DELETE FAILED.";
        public static final String STOCK_ADDED = "STOCK ADDED SUCCESSFULLY.";
        public static final String STOCK_ADD_OPERATION_FAILED = "STOCK ADD OPERATION FAILED.";
        public static final String STOCK_HISTORY_NOT_FOUND = "STOCK HISTORY NOT FOUND.";
        public static final String EMPLOYEE_ADDED_SUCCESSFULLY = "EMPLOYEE ADDED SUCCESSFULLY.";
        public static final String EMPLOYEE_ADD_OPERATION_FAILED = "EMPLOYEE ADD OPERATION FAILED.";
        public static final String EMPLOYEE_NOT_FOUND = "EMPLOYEE NOT FOUND.";
        public static final String BRAND_ADDED = "BRAND ADDED SUCCESSFULLY.";
        public static final String BRAND_ADD_OPERATION_FAILED = "BRAND ADD OPERATION FAILED.";
        public static final String SUPPLIER_ADDED = "SUPPLIER ADDED SUCCESSFULLY.";
        public static final String SUPPLIER_ADD_OPERATION_FAILED = "SUPPLIER ADD OPERATION FAILED.";
    }

    public static final class ErrorMessages {
        public static final String Error = "Error";
        public static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR.";


    }

    public static final class DatabaseConstants {
        public static final String SCHEMA_NAME = "public";

    }

    public static final class FUNCTION_UPDATE_STOCK {
        public static final String FUNCTION_UPDATE_STOCK_NAME = "update_stock";
        public static final String STOCK_HISTORY_ID = "stockHistoryId";
        public static final String STOCK = "stock";
        public static final String AMOUNT = "amount";
        public static final String INSERT_QUANTITY = "insQuantity";
        public static final String UPDATE_QUANTITY = "updateQuantity";
        public static final String PACKING_SLIP_NUMBER = "packingSlipNo";
        public static final String MATERIAL_ID = "materialId";
        public static final String USER_ID = "userId";

    }

}
