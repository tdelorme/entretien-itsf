package tech.nakama.tde.itsf;

import tech.nakama.tde.itsf.sales.domain.CategoryItem;
import tech.nakama.tde.itsf.sales.domain.ImportedItem;
import tech.nakama.tde.itsf.sales.domain.Item;
import tech.nakama.tde.itsf.sales.domain.Sales;

import java.util.List;

class Main {

	public static void main(String[] args) {
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

		System.out.println("Output 1");
		salesInput1.items().forEach(System.out::println);
		System.out.println("Sales Taxes: "+salesInput1.displayTotalTaxAmount()+" Total: "+salesInput1.displayTotalPrice());

		System.out.println("Output 2");
		salesInput2.items().forEach(System.out::println);
		System.out.println("Sales Taxes: "+salesInput2.displayTotalTaxAmount()+" Total: "+salesInput2.displayTotalPrice());

		System.out.println("Output 3");
		salesInput3.items().forEach(System.out::println);
		System.out.println("Sales Taxes: "+ salesInput3.displayTotalTaxAmount()+" Total: "+salesInput3.displayTotalPrice());
	}

}
