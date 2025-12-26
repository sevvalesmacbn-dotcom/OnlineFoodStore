package com.onlinefoodstore.dao.impl;
import com.onlinefoodstore.dao.IUserDAO;
import com.onlinefoodstore.model.User;
import java.sql.*;
public class UserDAOImpl implements IUserDAO {
    private Connection connection;
    public UserDAOImpl(Connection connection){this.connection=connection;}
    @Override
    public User getByUsername(String username) {
        String sql="SELECT * FROM users WHERE username = ?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                User user= new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getInt("int"));
                user.setRole(rs.getString("role"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getById(int id) {
        String sql="SELECT * FROM users WHERE id = ?";
        try (PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                User user= new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getInt("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(User user) {
        String sql="INSERT INTO users (name, usernam, password, role) VALUES (?, ?, ?, ?)";
        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setString(1,user.getName());
            ps.setString(2,user.getUsername());
            ps.setInt(3,user.getPassword());
            ps.setString(4,user.getRole());
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        String sql="UPDATE users SET name = ?, username = ?, password = ?, role = ? WHERE id = ?";
        try (PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setString(1,user.getName());
            ps.setString(2,user.getUsername());
            ps.setInt(3,user.getPassword());
            ps.setString(4,user.getRole());
            ps.setInt(5,user.getId());
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql="DELETE FROM users WHERE id = ?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
