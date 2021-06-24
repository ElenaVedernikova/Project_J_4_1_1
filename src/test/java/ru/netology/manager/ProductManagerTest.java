package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Book coreJava = new Book(1, "CoreJava", 100, "Cay S.Horstmann");
    private Book java = new Book(2, "Java", 150, "Cay S.Horstmann");
    private Book testDriven = new Book(3, "Test Driven", 200, "Lasse Koskela");
    private Book driven = new Book(4, "Test Driven", 250, "Kousen");
    private Smartphone samsungA41 = new Smartphone(5, "Galaxy A41", 2000, "Samsung");
    private Smartphone samsungM31 = new Smartphone(6, "Galaxy M31", 2300, "Samsung");
    private Smartphone sony = new Smartphone(7, "Sony", 1000, "SONY");
    private Smartphone sonyAz = new Smartphone(8, "Sony", 6000, "SONY AZ");
    private Smartphone nokia = new Smartphone(9, "3310", 5000, "Nokia");
    private Smartphone nokiaAz = new Smartphone(9, "3310", 5000, "Nokia");
    private Product apple = new Product (10, "repl", 4000);

    @Test
    public void shouldFindByNameBook() {
        manager.add(coreJava);
        manager.add(java);
        manager.add(testDriven);
        manager.add(driven);
        manager.add(samsungA41);
        manager.add(samsungM31);
        manager.add(sony);
        manager.add(sonyAz);

        Product[] expected = new Product[]{testDriven, driven};
        Product[] actual = manager.searchBy("Test");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByNameSmartphone() {
        manager.add(coreJava);
        manager.add(java);
        manager.add(testDriven);
        manager.add(driven);
        manager.add(samsungA41);
        manager.add(samsungM31);
        manager.add(sony);
        manager.add(sonyAz);

        Product[] expected = new Product[]{sony, sonyAz};
        Product[] actual = manager.searchBy("Sony");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByAuthorBook() {
        manager.add(coreJava);
        manager.add(java);
        manager.add(testDriven);
        manager.add(driven);
        manager.add(samsungA41);
        manager.add(samsungM31);
        manager.add(sony);
        manager.add(sonyAz);

        Product[] expected = new Product[]{coreJava, java};
        Product[] actual = manager.searchBy("Cay S.Horstmann");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByManufacturerSmartphone() {
        manager.add(coreJava);
        manager.add(java);
        manager.add(testDriven);
        manager.add(driven);
        manager.add(samsungA41);
        manager.add(samsungM31);
        manager.add(sony);
        manager.add(sonyAz);

        Product[] expected = new Product[]{samsungA41, samsungM31};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBySameManufacturerAndNameSmartphone() {
        manager.add(coreJava);
        manager.add(java);
        manager.add(testDriven);
        manager.add(driven);
        manager.add(samsungA41);
        manager.add(samsungM31);
        manager.add(sony);
        manager.add(sonyAz);
        manager.add(nokia);
        manager.add(nokiaAz);

        Product[] expected = new Product[]{nokia, nokiaAz};
        Product[] actual = manager.searchBy("Nokia");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByManufacturer() {
        manager.add(coreJava);
        manager.add(java);
        manager.add(testDriven);
        manager.add(driven);
        manager.add(samsungA41);
        manager.add(samsungM31);
        manager.add(sony);
        manager.add(sonyAz);
        manager.add(nokia);
        manager.add(nokiaAz);

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Nokia AF");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByProduct() {
        manager.add(coreJava);
        manager.add(java);
        manager.add(testDriven);
        manager.add(driven);
        manager.add(samsungA41);
        manager.add(samsungM31);
        manager.add(sony);
        manager.add(sonyAz);
        manager.add(nokia);
        manager.add(nokiaAz);
        manager.add(apple);

        Product[] expected = new Product[] {};
        Product[] actual = manager.searchBy("repl");
        assertArrayEquals(expected, actual);
    }
}
