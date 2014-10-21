package com.managment.data;

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
    // Map to store categories and their estimated budget amounts parsed from the budgetFile that they are stored in
    // Map<Category, estimatedAmount> estimatedAmount = new HashMap<Category, estimatedAmount>();

    private Budget(){

    }

    public Budget(String budgetFileName) {

    }

    public BigDecimal getEstimatedCategoryAmounts(String category){
       // If there is no category listed for the given category then the estimated amount for that category is 0
       BigDecimal estimatedCategoryAmount = BigDecimal.ONE;

        // If the category is contained in the estimated values then retrieve the estimated amount
        if(estimatedAmount.containsKey(category)) {
            estimatedCategoryAmount = estimatedAmount.get(category);
        }

       return estimatedCategoryAmount;
    }

    private void getBudgetData() {

    }
}
