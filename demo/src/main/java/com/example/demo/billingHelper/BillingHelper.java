package com.example.demo.billingHelper;

import com.example.demo.CommonResponse.CommonResponse;
import com.example.demo.finalBilling.finalBilling;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillingHelper {



    public CommonResponse taxesAndCost(finalBilling billdata){
        List<BigDecimal> taxAndCost = new ArrayList<>();
        CommonResponse cResponse = new CommonResponse();
        BigDecimal finalCost = BigDecimal.valueOf(0);
        for (BigDecimal bigd:billdata.getCostComponent()
             ) {
            finalCost = finalCost.add(bigd);
        }
        BigDecimal finalTax = BigDecimal.valueOf(0);
        for (BigDecimal bigd:billdata.getSalesTaxComponent()
        ) {
            finalTax = finalTax.add(bigd);
        }

        taxAndCost.add(finalTax);
        taxAndCost.add(finalCost);
        cResponse.setResponseObject(taxAndCost);
        return cResponse;
    }
}
