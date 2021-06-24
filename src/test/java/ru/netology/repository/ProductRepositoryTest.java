package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductRepositoryTest {

    private ProductRepository repository = new ProductRepository();

    private Book coreJava = new Book(1, "CoreJava", 100, "Cay S.Horstmann");
    private Book java = new Book(2, "Java", 150, "Cay S.Horstmann");
    private Book testDriven = new Book(3, "Test Driven", 200, "Lasse Koskela");
    private Book driven = new Book(4, "Test Driven", 250, "Kousen");

    @Test
    public void shouldFindById() {
        repository.save(coreJava);
        repository.save(java);
        repository.save(testDriven);
        repository.save(driven);

        repository.removeById(3);

        Product[] expected = new Product[]{coreJava, java, driven};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindById() {
        repository.save(coreJava);
        repository.save(java);
        repository.save(testDriven);
        repository.save(driven);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(5);
        });
    }
}


