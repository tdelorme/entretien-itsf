package tech.nakama.tde.itsf.sales.domain;

import tech.nakama.tde.itsf.utils.DoubleUtils;

public record Item(int quantity, String name, double price, CategoryItem categoryItem, ImportedItem importedItem) {

    public double priceIncludingTax() {
        double priceIncludingTax = quantity * price;
        double taxAmount = taxAmount();

        if (taxAmount != 0) {
            priceIncludingTax = quantity * (price + taxAmount);
        }

        return DoubleUtils.formatDouble(priceIncludingTax);
    }

    public double taxAmount() {
        double taxPercent = CategoryItem.getTaxPercent(categoryItem);
        taxPercent = ImportedItem.IMPORTED.equals(importedItem) ? taxPercent + 5 : taxPercent;

        double taxAmount = (taxPercent * price) / 100;

        return roundUpToNearestFiveCents(taxAmount);
    }

    private double roundUpToNearestFiveCents(double amount) {
        return Math.ceil(amount / 0.05) * 0.05;
    }

    public String displayPriceIncludingTax() {
        return DoubleUtils.displayDouble(priceIncludingTax());
    }

    @Override
    public String toString() {
        return quantity + " " + name + " " + displayPriceIncludingTax();
    }
}
