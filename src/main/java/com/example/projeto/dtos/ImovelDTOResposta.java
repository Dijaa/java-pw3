package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.OfertaModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.UserService;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ImovelDTOResposta implements Serializable{
    private static final long serialVersionUID =1L;

    @Autowired
    private UserService userService;

    private Integer id;

    private String descricao;
 
    private Integer quartos; 

    private Integer vagas;

    private Integer usuario_id;

    private String imagem;

    private List<OfertaModel> ofertas;


    public ImovelDTOResposta(){}

    public ImovelDTOResposta(ImovelModel model){
      this.id = model.getId();
      this.descricao = model.getDescricao();
      this.quartos = model.getQuartos();
      this.vagas = model.getVagas();
      this.imagem = model.getImagem();
      this.usuario_id = model.getUserModel().getId();
      this.ofertas = model.getOfertas();
    }

    public ImovelDTOResposta(Integer id, String descricao, Integer quartos, Integer vagas, String imagem, UserModel model) {
      this.id = id;
      this.descricao = descricao;
      this.quartos = quartos;
      this.vagas = vagas;
      this.imagem = imagem;
      this.usuario_id = model.getId();
    }

    public static ImovelDTOResposta transformaEmDTO(ImovelModel model) {
        return new ImovelDTOResposta(model.getId(), model.getDescricao(), model.getQuartos(), model.getVagas(), model.getImagem(), model.getUserModel());
    }


}