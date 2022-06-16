package com.example.demo.itemController;


import com.example.demo.CommonResponse.CommonResponse;
import com.example.demo.Enums.Categories;
import com.example.demo.Item.Items;
import com.example.demo.billingHelper.BillingHelper;
import com.example.demo.finalBilling.finalBilling;
import com.example.demo.itemHelper.ItemHelper;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class itemController {

    @PostMapping("/add")
    public CommonResponse addItem(@RequestBody Items item){

        CommonResponse commonResponse = new CommonResponse();
        ItemHelper itemHelper = new ItemHelper();
        ArrayList<String> error =  itemHelper.validateFields(item);
        if(error.size() > 0){
            commonResponse.setResponseObject(error);
            commonResponse.setResponseCode(400);
            commonResponse.setProcessingSuccess(false);
            return commonResponse;
        }

       Map<String, List<BigDecimal>> finalMap = itemHelper.getAmountAndTaxes(item);
        commonResponse.setResponseObject(finalMap);
        return commonResponse;

    }


    @GetMapping("/getEnums")
    public CommonResponse getEnums(){
        CommonResponse commonResponse = new CommonResponse();
        ArrayList<String> enumList = new ArrayList<>();
        enumList.add(Categories.BOOKS.name());
        enumList.add(Categories.MEDICAL_SUPPLIES.name());
        enumList.add(Categories.FOOD.name());
        enumList.add(Categories.OTHERS.name());
        commonResponse.setResponseObject(enumList);
        return commonResponse;
    }

    @PostMapping("/finalize")
    public CommonResponse getFinalBill(@RequestBody finalBilling fBill){
        return new BillingHelper().taxesAndCost(fBill);
    }




}
