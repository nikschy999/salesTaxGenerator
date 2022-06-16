package com.example.demo.itemHelper;



import com.example.demo.Enums.Categories;
import com.example.demo.Enums.Imported;
import com.example.demo.Item.Items;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ItemHelper {


    public ArrayList<String> validateFields(Items item) {

        ArrayList<String> listOfError = new ArrayList<>();

        if (ObjectUtils.isEmpty(item.getPricePerUnit())) {
            listOfError.add("Invalid Price Per Unit");
        }
        if (ObjectUtils.isEmpty(item.getQuantity())) {
            listOfError.add("Invalid Quantity");
        }
        if (ObjectUtils.isEmpty(item.getCategory())) {
            listOfError.add("Invalid Category");
        }
        if (ObjectUtils.isEmpty(item.getIsItemImported())) {
            listOfError.add("Invalid Item Imported choice");
        }
        if (ObjectUtils.isEmpty(item.getItemName())) {
            listOfError.add("Invalid Item Name");
        }

        return listOfError;
    }


    public Map<String,List<BigDecimal>> getAmountAndTaxes(Items item){
        List<BigDecimal> amount = new ArrayList<>();
        Map<String,List<BigDecimal>> table = new LinkedHashMap<>();

        if(Objects.equals(item.getCategory(), Categories.OTHERS.name())){
            if(item.getIsItemImported().equals(Imported.IMPORTED.name())){
                BigDecimal mrp = item.getPricePerUnit().multiply(BigDecimal.valueOf(item.getQuantity()));
                amount.add(mrp);
                amount.add(taxCalculator(mrp,0.15));
                amount.add(mrp.add(taxCalculator(mrp,0.15)));
                table.put("item", amount);


            }else{
                BigDecimal mrp = item.getPricePerUnit().multiply(BigDecimal.valueOf(item.getQuantity()));
                amount.add(mrp);
                amount.add(taxCalculator(mrp,0.10));
                amount.add(mrp.add(taxCalculator(mrp,0.10)));
                table.put("item", amount);
            }


        }else{
            if(item.getIsItemImported().equals(Imported.IMPORTED.name())){
                BigDecimal mrp = item.getPricePerUnit().multiply(BigDecimal.valueOf(item.getQuantity()));
                amount.add(mrp);
                amount.add(taxCalculator(mrp,0.05));
                amount.add(mrp.add(taxCalculator(mrp,0.05)));
                table.put("item", amount);
            }else{
                BigDecimal mrp = item.getPricePerUnit().multiply(BigDecimal.valueOf(item.getQuantity()));
                amount.add(mrp);
                amount.add(taxCalculator(mrp,0.0));
                amount.add(mrp.add(taxCalculator(mrp,0.0)));
                table.put("item", amount);
            }
        }

        return table;

    }

    public static BigDecimal taxCalculator(BigDecimal value,double taxCalc){
        BigDecimal bigd = BigDecimal.valueOf(taxCalc);
        BigDecimal nonSimplifiedTax = value.multiply(bigd);
        nonSimplifiedTax = nonSimplifiedTax.setScale(2, RoundingMode.HALF_UP);

        BigDecimal simplifiedTax = BigDecimal.valueOf(20).multiply(nonSimplifiedTax);
        simplifiedTax = (simplifiedTax.setScale(0,RoundingMode.CEILING)).divide(BigDecimal.valueOf(20));
        simplifiedTax = simplifiedTax.setScale(2,RoundingMode.HALF_UP);



        return  simplifiedTax;


    }
}





