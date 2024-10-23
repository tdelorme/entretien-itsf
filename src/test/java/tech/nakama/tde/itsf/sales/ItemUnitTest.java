package tech.nakama.tde.itsf.sales;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tech.nakama.tde.itsf.sales.domain.CategoryItem;
import tech.nakama.tde.itsf.sales.domain.ImportedItem;
import tech.nakama.tde.itsf.sales.domain.Item;

import java.util.stream.Stream;

public class ItemUnitTest {

    @ParameterizedTest
    @MethodSource("provideDataOfItemObject")
    void shouldReturnNewItemInstance_whenIsInstanciate(int quantity, String name, double price, CategoryItem categoryItem, ImportedItem importedItem) {
        Item item = new Item(quantity, name, price, categoryItem, importedItem);

        Assertions.assertEquals(quantity, item.quantity());
        Assertions.assertEquals(name, item.name());
        Assertions.assertEquals(price, item.price());
        Assertions.assertEquals(categoryItem, item.categoryItem());
        Assertions.assertEquals(importedItem, item.importedItem());
    }

    @ParameterizedTest
    @MethodSource("provideDataOfItemObject")
    void shouldReturnTaxedPrice_whenIsInstanciate(int quantity, String name, double price, CategoryItem categoryItem, ImportedItem importedItem, double totalPrice) {
        Item item = new Item(quantity, name, price, categoryItem, importedItem);

        Assertions.assertEquals(totalPrice, item.priceIncludingTax());
    }

    private static Stream<Arguments> provideDataOfItemObject() {
        return Stream.of(
                Arguments.of(1, "book", 12.49, CategoryItem.BOOK, ImportedItem.NOT_IMPORTED, 12.49),
                Arguments.of(1, "music CD", 14.99, CategoryItem.MUSICAL, ImportedItem.NOT_IMPORTED, 16.49),
                Arguments.of(1, "chocolate bar", 0.85, CategoryItem.FOOD, ImportedItem.NOT_IMPORTED, 0.85),
                Arguments.of(1, "imported box of chocolates", 10.00, CategoryItem.FOOD, ImportedItem.IMPORTED, 10.50),
                Arguments.of(1, "imported bottle of perfume", 47.50, CategoryItem.COSMETIC, ImportedItem.IMPORTED, 54.65),
                Arguments.of(1, "imported bottle of perfume", 27.99, CategoryItem.COSMETIC, ImportedItem.IMPORTED, 32.19),
                Arguments.of(1, "bottle of perfume", 18.99, CategoryItem.COSMETIC, ImportedItem.NOT_IMPORTED, 20.89),
                Arguments.of(1, "packet of headache pills", 9.75, CategoryItem.MEDICAL, ImportedItem.NOT_IMPORTED, 9.75),
                Arguments.of(1, "box of imported chocolates", 11.25, CategoryItem.FOOD, ImportedItem.IMPORTED, 11.85)
        );
    }

}
