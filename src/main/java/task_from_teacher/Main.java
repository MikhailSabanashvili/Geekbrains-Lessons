package task_from_teacher;

import task_from_teacher.market.Market;
import task_from_teacher.person.customer.Customer;
import task_from_teacher.person.seller.Seller;
import task_from_teacher.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    //1) Seller, может добавлять, убирать продукты, выставлять стоимость
    //2) Customer может покупать товар, может что-то желать
    //3) Когда seller продает продукты, у него они отнимаются, но добавляются у customer
    // кэш отнимается у customer, добавляется к seller
    //4) Поиск товара про продавцу и продукту, либо по продукту

    public static void main(String[] args) {
        Market market = new Market();

        Seller firstSeller = createFirstSeller();
        Seller secondSeller = createSecondSeller();

        market.addSeller(firstSeller);
        market.addSeller(secondSeller);

        Customer customer = createFirstCustomer();
        //customer.findProductOnMarket(market);
        //ищем продавца и покупаем у него всё что можем
        customer.findProductOnSeller("Виталий", "Еремин", market);
        customer.findProductOnSeller("Алексей", "Ушаков", market);
        //customer.findProductOnSeller("Какой то", "Левый чел", market); //проверка выбросится ли исключение
        customer.info();
    }

    private static Customer createFirstCustomer() {
        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setQuantity(3);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setQuantity(2);

        return new Customer(List.of(firstProduct, secondProduct), 50);
    }

    private static Seller createFirstSeller() {
        Seller seller = new Seller();
        seller.setName("Виталий");
        seller.setLastName("Еремин");
        seller.setCash(0);

        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setPrice(10);
        firstProduct.setQuantity(2);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setPrice(8);
        secondProduct.setQuantity(100);

        List<Product> products = new ArrayList<>();
        products.add(firstProduct);
        products.add(secondProduct);
        seller.setProducts(products);

        return seller;
    }

    private static Seller createSecondSeller() {
        Seller seller = new Seller();
        seller.setName("Алексей");
        seller.setLastName("Ушаков");
        seller.setCash(0);

        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setPrice(8);
        firstProduct.setQuantity(40);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setPrice(5);
        secondProduct.setQuantity(12);

        List<Product> products = new ArrayList<>();
        products.add(firstProduct);
        products.add(secondProduct);
        seller.setProducts(products);

        return seller;
    }

}
