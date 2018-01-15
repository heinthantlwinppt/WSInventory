package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/15/2018.
 */

public class Manufacturing_Smith_Jobgold {
    public static final String TABLE_MANUFACTURING_SMITH_JOBGOLD = "manufacturing_smith";
    public static final String COLUMN_SMITH_JOBORDER_ID = "smith_joborder_id";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_GOLDSAVING = "goldsaving";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_ROW_NO = "row_no";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_GOLD_ID = "gold_id";
    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_SMITH_JOBORDER_ID,
            COLUMN_WEIGHT,
            COLUMN_QTY,
            COLUMN_GOLDSAVING,
            COLUMN_REMARKS,
            COLUMN_ROW_NO,
            COLUMN_IS_DELETE,
            COLUMN_GOLD_ID

    };

    private int smith_joborder_id;
    private String weight;
    private String qty;
    private String goldsaving;
    private String remarks;
    private String is_delete;
    private String gold_id;
    private int uom_id;
}
