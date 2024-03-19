package com.example.projeto.dtos;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.projeto.service.UserService;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class ImovelDTO implements Serializable{
    private static final long serialVersionUID =1L;

    @Autowired
    private UserService userService;

    private Integer id;

    private String descricao;
 
    private Integer quartos; 

    private Integer vagas;

    private Integer usuario_id;


    public ImovelDTO(){}



}