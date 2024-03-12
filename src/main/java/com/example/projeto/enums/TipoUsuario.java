package com.example.projeto.enums;

import lombok.Getter;

@Getter
public enum TipoUsuario {

    ADMINISTRADOR(1, "Admin"),
    CORRETOR(2, "Corretor");

    private int codigo;
    private String descricao;

    private TipoUsuario(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }


    public static TipoUsuario toEnum(Integer codigo){
        if(codigo == null){
            return null;
        }

        for (TipoUsuario x: TipoUsuario.values()){
            if(codigo.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de usuario inválida");
    }

}
