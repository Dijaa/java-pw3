package com.example.projeto.enums;

import lombok.Getter;

@Getter
public enum TipoOferta {
    ALUGUEL(1, "Aluguel"),
    VENDA(2, "Venda");

    private int codigo;
    private String descricao;

    
    private TipoOferta(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }


    public static TipoOferta toEnum(Integer codigo){
        if(codigo == null){
            return null;
         }

        for (TipoOferta x: TipoOferta.values()){
            if(codigo.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de oferta inválida");
    }


}
