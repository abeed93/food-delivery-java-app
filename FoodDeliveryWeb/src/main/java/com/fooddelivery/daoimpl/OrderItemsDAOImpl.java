package com.fooddelivery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fooddelivery.dao.OrderItemsDAO;
import com.fooddelivery.model.OrderItems;
import com.fooddelivery.util.DBConnection;

public class OrderItemsDAOImpl implements OrderItemsDAO {

    private static final String INSERT =
    "INSERT INTO order_item VALUES (?,?,?,?,?)";

    private static final String GET_BY_ORDER =
    "SELECT * FROM order_item WHERE order_id=?";

    private static final String UPDATE =
    "UPDATE order_item SET quantity=?, price=? WHERE order_item_id=?";

    private static final String DELETE =
    "DELETE FROM order_item WHERE order_item_id=?";

    @Override
    public void addOrderItem(OrderItems orderItem) {

        try(Connection connection = DBConnection.getDBConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT)) {

            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, orderItem.getOrderId());
            ps.setString(3, orderItem.getMenuId());
            ps.setInt(4, orderItem.getQuantity());
            ps.setDouble(5, orderItem.getTotalPrice());

            ps.executeUpdate();

            System.out.println("Order item added");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItems> getItemsByOrder(String orderId) {

        List<OrderItems> items = new ArrayList<>();

        try(Connection connection = DBConnection.getDBConnection();
            PreparedStatement ps = connection.prepareStatement(GET_BY_ORDER)) {

            ps.setString(1, orderId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                OrderItems item = new OrderItems(
                    rs.getString("order_item_id"),
                    rs.getString("order_id"),
                    rs.getString("menu_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                );

                items.add(item);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public void updateOrderItem(OrderItems orderItem) {

        try(Connection connection = DBConnection.getDBConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE)) {

            ps.setInt(1, orderItem.getQuantity());
            ps.setDouble(2, orderItem.getTotalPrice());
            ps.setString(3, orderItem.getOrderItemsId());

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(String orderItemId) {

        try(Connection connection = DBConnection.getDBConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE)) {

            ps.setString(1, orderItemId);

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}