package com.example.projeto.dtos;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.UserService;

import lombok.Getter;


@Getter
public class ImovelDTOResposta implements Serializable{
    private static final long serialVersionUID =1L;

    @Autowired
    private UserService userService;

    private Integer id;

    private String descricao;
 
    private Integer quartos; 

    private Integer vagas;

    private Integer usuario_id;


    public ImovelDTOResposta(){}

    public ImovelDTOResposta(Integer id, String descricao, Integer quartos, Integer vagas, UserModel model) {
      this.id = id;
      this.descricao = descricao;
      this.quartos = quartos;
      this.vagas = vagas;
      this.usuario_id = model.getId();
    }

    public static ImovelDTOResposta transformaEmDTO(ImovelModel model) {
        return new ImovelDTOResposta(model.getId(), model.getDescricao(), model.getQuartos(), model.getVagas(), model.getUserModel());
    }


}