package tech.nakama.tde.itsf.sales.domain;

import tech.nakama.tde.itsf.utils.DoubleUtils;

import java.util.List;

public record Sales(List<Item> items) {

    public double totalPrice() {
        double totalPrice = items.stream()
                .map(Item::priceIncludingTax)
                .reduce(0d, Double::sum);

        return DoubleUtils.formatDouble(totalPrice);
    }

    public double totalTaxAmount() {
        double totalTax = items.stream()
                .map(Item::taxAmount)
                .reduce(0d, Double::sum);
        return DoubleUtils.formatDouble(totalTax);
    }

    public String displayTotalPrice() {
        return DoubleUtils.displayDouble(totalPrice());
    }

    public String displayTotalTaxAmount() {
        return DoubleUtils.displayDouble(totalTaxAmount());
    }



}
