package com.example.projeto.dtos;

import com.example.projeto.enums.TipoOferta;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.OfertaModel;

import lombok.Getter;

@Getter
public class OfertaDTOResposta {

    private Integer id;
    private double valor;
    private TipoOferta tipoOferta;
    private String nomeUser;
    private ImovelDTOResposta imovel;
    public OfertaDTOResposta(Integer id, double d, TipoOferta tipoOferta2, String nomeUser, ImovelModel imovelModel) {
        this.id = id;
        this.valor = d;
        this.tipoOferta = tipoOferta2;
        this.nomeUser = nomeUser;
        this.imovel = ImovelDTOResposta.transformaEmDTO(imovelModel);
    }

    public static OfertaDTOResposta transformaEmDTO(OfertaModel model) {
        return new OfertaDTOResposta(model.getId(), model.getValor(), model.getTipoOferta(),
                model.getUserModel().getNome(), model.getImovelModel());
    }

}
