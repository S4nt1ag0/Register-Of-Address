package com.example.registerofaddress.entities;

import java.util.Objects;

public class User {
private Long CPF;
private String name;
private Endereco endereco;
private int numero;

    public User(){};

    public User(Long CPF, String name, Endereco endereco) {
        this.CPF = CPF;
        this.name = name;
        this.endereco = endereco;
    }

    public Long getCPF() {
        return CPF;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return CPF.equals(user.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CPF);
    }

    @Override
    public String toString() {
        return "User{" +
                "CPF=" + CPF +
                ", name='" + name + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
