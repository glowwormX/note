package com.neo.pojo;

import java.io.Serializable;

/**
 * @author 徐其伟
 * @Description:
 * @date 2018/6/14 16:16
 */
public class Order implements Serializable {
    private String id;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
