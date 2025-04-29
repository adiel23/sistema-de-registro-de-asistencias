package modelos;

import java.util.Date;

public class Usuario {
    private int id;
    private String name;
    private String username;
    private String password;
    private String rol;
    private Date birthDate;

    public Usuario() {
    }
    
    public Usuario(int id, String name, String username, String password, String rol, Date birthDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.birthDate = birthDate;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}

