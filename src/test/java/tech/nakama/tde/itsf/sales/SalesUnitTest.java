package tech.nakama.tde.itsf.sales;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tech.nakama.tde.itsf.sales.domain.CategoryItem;
import tech.nakama.tde.itsf.sales.domain.ImportedItem;
import tech.nakama.tde.itsf.sales.domain.Item;
import tech.nakama.tde.itsf.sales.domain.Sales;

import java.util.List;
import java.util.stream.Stream;

public class SalesUnitTest {

    @ParameterizedTest
    @MethodSource("provideDataOfSalesObject")
    void shouldReturnTotalPriceWithTaxOfSalesInstance_whenGettingTotalPrice(Sales sales, double totalExpected, double totalTaxAmountExpected) {
        Assertions.assertEquals(totalExpected, sales.totalPrice());
        Assertions.assertEquals(totalTaxAmountExpected, sales.totalTaxAmount());
    }

    @Test
    void shouldReturnFormattedTotalPrice_WhenDecimalPriceEndWith0() {
        Sales sales = new Sales(List.of(new Item(1, "test", 100, CategoryItem.BOOK, ImportedItem.NOT_IMPORTED)));

        Assertions.assertEquals("100.00", sales.displayTotalPrice());
    }

    @Test
    void shouldReturnFormattedTotalTaxAmount_WhenDecimalPriceEndWith0() {
        Sales sales = new Sales(List.of(new Item(1, "test", 100, CategoryItem.COSMETIC, ImportedItem.IMPORTED)));

        Assertions.assertEquals("15.00", sales.displayTotalTaxAmount());
    }

    private static Stream<Arguments> provideDataOfSalesObject() {
        Item bookItem = new Item(1, "book", 12.49, CategoryItem.BOOK, ImportedItem.NOT_IMPORTED);
        Item itemMusicCD = new Item(1, "music CD", 14.99, CategoryItem.MUSICAL, ImportedItem.NOT_IMPORTED);
        Item chocolateBarItem = new Item(1, "chocolate bar", 0.85, CategoryItem.FOOD, ImportedItem.NOT_IMPORTED);
        Item importedBoxOfChocolatesItem = new Item(1, "imported box of chocolates", 10.00, CategoryItem.FOOD, ImportedItem.IMPORTED);
        Item importedBottleOfPerfumeItem = new Item(1, "imported bottle of perfume", 47.50, CategoryItem.COSMETIC, ImportedItem.IMPORTED);
        Item importedBottleOfPerfumeItem2 = new Item(1, "imported bottle of perfume", 27.99, CategoryItem.COSMETIC, ImportedItem.IMPORTED);
        Item bottleOfPerfumeItem = new Item(1, "bottle of perfume", 18.99, CategoryItem.COSMETIC, ImportedItem.NOT_IMPORTED);
        Item packetOfHeadachePills = new Item(1, "packet of headache pills", 9.75, CategoryItem.MEDICAL, ImportedItem.NOT_IMPORTED);
        Item boxOfImportedChocolatesItem = new Item(1, "box of imported chocolates", 11.25, CategoryItem.FOOD, ImportedItem.IMPORTED);

        Sales salesInput1 = new Sales(List.of(bookItem, itemMusicCD, chocolateBarItem));
        Sales salesInput2 = new Sales(List.of(importedBoxOfChocolatesItem, importedBottleOfPerfumeItem));
        Sales salesInput3 = new Sales(List.of(importedBottleOfPerfumeItem2, bottleOfPerfumeItem, packetOfHeadachePills, boxOfImportedChocolatesItem));
        return Stream.of(
                Arguments.of(salesInput1, 29.83, 1.50),
                Arguments.of(salesInput2, 65.15, 7.65),
                Arguments.of(salesInput3, 74.68, 6.70)
        );
    }
}
