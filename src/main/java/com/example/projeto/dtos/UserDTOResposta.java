package com.example.projeto.dtos;

import com.example.projeto.models.UserModel;

import lombok.Getter;

//@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserDTOResposta{
    private Integer id;
    private String nome;
    private boolean admin;


    public UserDTOResposta(UserModel model){
        this.id = model.getId();
        this.nome = model.getNome();
        this.admin = model.isAdmin();
    }

    public UserDTOResposta(Integer id, String nome, boolean admin) {
        this.id = id;
        this.nome = nome;
        this.admin = admin;
    }

    public static UserDTOResposta transformaEmDTO(UserModel model) {
        return new UserDTOResposta(model.getId(), model.getNome(), model.isAdmin());
    }
    
}