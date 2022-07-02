package com.griddynamics.ppetrenko.helpers;

public class ConvertationHelper {
    public static double convertMoneyAmountToDouble(String amountText) {
        return Double.parseDouble(amountText.substring(1));
    }
}
