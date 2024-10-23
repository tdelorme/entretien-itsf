package tech.nakama.tde.itsf.sales.domain;

public enum CategoryItem {
    BOOK,
    FOOD,
    MEDICAL,
    MUSICAL,
    COSMETIC;

    public static double getTaxPercent(CategoryItem categoryItem) {
        return switch (categoryItem){
            case BOOK,
                 FOOD,
                 MEDICAL ->  0d;
            default -> 10d;
        };
    }
}
