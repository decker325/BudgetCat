package com.managment.finance.budgetcat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Mychelle.
 */

public class Budget {

    private int budgetID;
    private String budgetName;
    private String budgetFileName;
    Map<String, BigDecimal> estimatedAmount = new HashMap<String, BigDecimal>();

    private Budget(){

    }

    public Budget(String budgetFileName) {

    }

    public Map<String, BigDecimal> getEstimatedCategoryAmounts(){
       return estimatedAmount;
    }

    private void getBudgetData() {

    }
}
