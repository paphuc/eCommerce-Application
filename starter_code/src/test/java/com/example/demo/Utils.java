package com.example.demo;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static void injectObjects(Object target, String fieldName, Object toInject) {
        boolean wasPrivate = false;

        try {
            Field f = target.getClass().getDeclaredField(fieldName);
            if (!f.isAccessible()) {
                f.setAccessible(true);
                wasPrivate = true;
            }
            f.set(target, toInject);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            if (wasPrivate) {
                try {
                    Field f = target.getClass().getDeclaredField(fieldName);
                    f.setAccessible(false);
                } catch (NoSuchFieldException ignored) {
                }
            }
        }
    }


    public static Item createNewItem1() {
        return new Item(1L, "item 1", BigDecimal.valueOf(10), "test item");
    }

    public static Item createNewItem2() {
        return new Item(2L, "item 2", BigDecimal.valueOf(10), "test item 2");
    }

    public static List<Item> createListItems() {
        Item item1 = createNewItem1();
        Item item2 = createNewItem2();
        return Arrays.asList(item1, item2);
    }

    public static List<UserOrder> createListUserOrders() {
        UserOrder userOrder = new UserOrder();
        User user = createUser();
        userOrder.setUser(user);
        userOrder.setItems(user.getCart().getItems());
        userOrder.setTotal(user.getCart().getTotal());
        userOrder.setId(1L);
        return Arrays.asList(userOrder);
    }

    public static User createUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        Cart cart= new Cart();
        cart.setUser(user);
        cart.addItem(createNewItem1());
        user.setCart(cart);
        return user;
    }
}