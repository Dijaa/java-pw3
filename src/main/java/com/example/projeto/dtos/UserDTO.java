package com.example.projeto.dtos;

import java.io.Serializable;

import com.example.projeto.models.UserModel;

import lombok.Getter;


@Getter
public class UserDTO implements Serializable{
    private static final long serialVersionUID =1L;

    private Integer id;

    private String nome;

    private String senha;

    public UserDTO(){}

    public UserModel transformaParaObjeto(){
        return new UserModel(nome, senha);
    }

}