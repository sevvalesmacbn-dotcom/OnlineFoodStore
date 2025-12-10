package com.onlinefoodstore.model;

public class User {
    private int id;
    private String name;
    private String username;
    private int password;
    private String role;

    public User(){}

    public User(String name,String username,int password,String role){
        this.name=name;
        this.username=username;
        this.password=password;
        this.role=role;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}

    public int getPassword(){return password;}
    public void setPassword(int password){this.password=password;}

    public String getRole(){return role;}
    public void setRole(String role){this.role=role;}

}
