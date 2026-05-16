package com.fooddelivery.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fooddelivery.daoimpl.MenuDAOImpl;

public class Cart {

    private Map<String, CartItem> items = new HashMap<>();

    // 🛒 Add item
    public void addItem(String menuId) {

        if(items.containsKey(menuId)) {
            CartItem item = items.get(menuId);

            if(item.getQuantity() < 10) { // 🔒 limit
                item.setQuantity(item.getQuantity() + 1);
            }

        } else {
            items.put(menuId, new CartItem(menuId, 1));
        }
    }

    // 📦 Get all items
    public Collection<CartItem> getItems() {
        return items.values();
    }

    // ❌ Remove item
    public void removeItem(String menuId) {
        items.remove(menuId);
    }

    // 🔄 Update quantity
    public void updateQuantity(String menuId, String action) {

        if(!items.containsKey(menuId)) return;

        CartItem item = items.get(menuId);

        if("increase".equals(action)) {
            if(item.getQuantity() < 10) {
                item.setQuantity(item.getQuantity() + 1);
            }
        }

        else if("decrease".equals(action)) {
            int qty = item.getQuantity() - 1;

            if(qty <= 0) {
                items.remove(menuId); // 💥 auto remove
            } else {
                item.setQuantity(qty);
            }
        }
    }

    // 🛒 Total items (for navbar badge)
    public int getTotalItems() {
        int total = 0;
        for(CartItem item : items.values()) {
            total += item.getQuantity();
        }
        return total;
    }

    // 🧹 Clear cart
    public void clearCart() {
        items.clear();
    }

    // 🔍 Get single item
    public CartItem getItem(String menuId) {
        return items.get(menuId);
    }
    
    public double getTotalAmount(MenuDAOImpl dao) {

        double total = 0;

        for(CartItem item : items.values()) {
            Menu m = dao.getMenuById(item.getMenuId());
            total += m.getPrice() * item.getQuantity();
        }

        return total;
    }
}