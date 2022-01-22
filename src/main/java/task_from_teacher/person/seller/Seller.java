package task_from_teacher.person.seller;

import task_from_teacher.person.Person;
import task_from_teacher.person.customer.Customer;
import task_from_teacher.product.Product;

import java.util.List;
import java.util.Optional;

public class Seller extends Person {
    private String name;
    private String lastName;
    private List<Product> products;

//    public boolean sellProducts(Customer customer, Product expectedProduct) {
//        for(Product product: products) {
//            // Проверяем по имени товара что у продавца есть продукт
//            if(product.getName().equals(expectedProduct.getName())) {
//                // Проверяем что количество товара >= чем мы хотим купить
//                if(product.getQuantity() >= expectedProduct.getQuantity()) {
//                    // Проверяем что кэш покупателя позволяет купить товар
//                    long requiredCash = (long) product.getPrice() * expectedProduct.getQuantity();
//                    if(customer.getCash() >= requiredCash) {
//                        // Уменьшаем количество продукта у продавца
//                        product.setQuantity(product.getQuantity() - expectedProduct.getQuantity());
//
//                        //Создаем новый объект для покупателя, чтобы ссылка не дублировалась
//                        Product customerProduct = new Product();
//                        customerProduct.setQuantity(expectedProduct.getQuantity());
//                        customerProduct.setName(expectedProduct.getName());
//
//                        //Добавляем количество продуктов у покупателя
//                        customer.addPurchase(customerProduct);
//                        //Увеличиваем кэш продавца
//                        setCash(getCash() + requiredCash);
//                        //Уменьшаем кэш покупателя
//                        customer.setCash(customer.getCash() - requiredCash);
//                        //Сообщаем потребителю метода, что покупка совершена
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }

    //просто заменил на стрим цикл и два условия
    public boolean sellProducts(Customer customer, Product expectedProduct) {
        Optional<Product> product = products.stream()
                .filter(x -> x.getName().equals(expectedProduct.getName()))
                .filter(x -> x.getQuantity() >= expectedProduct.getQuantity()).findFirst();

        if (product.isEmpty()) {
            return false;
        }

        long requiredCash = (long) product.get().getPrice() * expectedProduct.getQuantity();

        if (customer.getCash() >= requiredCash) {
            // Уменьшаем количество продукта у продавца
            product.get().setQuantity(product.get().getQuantity() - expectedProduct.getQuantity());

            //Создаем новый объект для покупателя, чтобы ссылка не дублировалась
            Product customerProduct = new Product();
            customerProduct.setQuantity(expectedProduct.getQuantity());
            customerProduct.setName(expectedProduct.getName());

            //Добавляем количество продуктов у покупателя
            customer.addPurchase(customerProduct);
            //Увеличиваем кэш продавца
            setCash(getCash() + requiredCash);
            //Уменьшаем кэш покупателя
            customer.setCash(customer.getCash() - requiredCash);
            //Сообщаем потребителю метода, что покупка совершена
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
