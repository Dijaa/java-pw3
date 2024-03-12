package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.OfertaModel;
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

    private List<OfertaModel> ofertas;


    public ImovelDTOResposta(){}

    public ImovelDTOResposta(ImovelModel model){
      this.id = model.getId();
      this.descricao = model.getDescricao();
      this.quartos = model.getQuartos();
      this.vagas = model.getVagas();
      this.usuario_id = model.getUserModel().getId();
      this.ofertas = model.getOfertas();
    }

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