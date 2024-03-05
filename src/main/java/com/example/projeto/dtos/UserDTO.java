package com.example.projeto.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.example.projeto.models.UserModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class UserDTO implements Serializable{
    private static final long serialVersionUID =1L;

    private Integer id;

    @NotEmpty(message = "Nome não pode ser em branco")
    @Length(min = 10, max = 255, message = "Nome deve ter entre 10 e 255 caracteres")
    private String nome;

    @NotEmpty(message = "Nome não pode ser em branco")
    @NotBlank(message = "Nome não pode ser em branco")
    @Length(min = 4, max = 10, message = "Senha deve ter entre ....")
    private String senha;

    public UserDTO(){}

    public UserModel transformaParaObjeto(){
        return new UserModel(nome, senha);
    }

}