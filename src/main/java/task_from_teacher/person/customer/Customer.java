package com.geekbrains.person.customer;

import com.geekbrains.market.Market;
import com.geekbrains.person.Person;
import com.geekbrains.person.seller.Seller;
import com.geekbrains.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Product> expectedPurchaseList;
    private List<Product> purchaseList;

    public Customer(List<Product> expectedPurchaseList, int cash) {
        this.purchaseList = new ArrayList<>();
        this.expectedPurchaseList = expectedPurchaseList;
        this.setCash(cash);
    }

    public void addPurchase(Product product) {
        if (purchaseList == null) {
            purchaseList = new ArrayList<>();
        }

        purchaseList.add(product);
    }

    public void findProductOnMarket(Market market) {
        for (Product product : getExpectedPurchaseList()) {
            for (Seller seller : market.getSellers()) {
                boolean isBought = seller.sellProducts(this, product);
                if (isBought) {
                    break;
                }
            }
        }
    }

    public void info() {
        StringBuilder result = new StringBuilder("Я купил ");
        if (purchaseList.size() == 0) {
            result.append("ничего");
        } else {
            for(Product product: purchaseList) {
                result.append(product.getName());
                result.append(" в количестве ");
                result.append(product.getQuantity());
                result.append(" ");
            }
        }

        result.append(". У меня осталось: ");
        result.append(getCash());
        result.append(" рублей");

        System.out.println(result);
    }

    public List<Product> getExpectedPurchaseList() {
        return expectedPurchaseList;
    }

    public List<Product> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Product> purchaseList) {
        this.purchaseList = purchaseList;
    }

}
