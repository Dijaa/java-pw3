package com.example.projeto.models;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="imoveis")
public class ImovelModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String descricao;
 
    private Integer quartos; 

    private Integer vagas;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UserModel userModel;

    @OneToMany(mappedBy="imovelModel")
    private List<OfertaModel> ofertas;


    public ImovelModel(String descricao, Integer quartos, Integer vagas, UserModel userModel){
        super();
        this.descricao = descricao;
        this.quartos = quartos;
        this.userModel = userModel;
    }   

    public ImovelModel(Integer id, String descricao, Integer quartos, Integer vagas, UserModel userModel){
        super();
        this.id = id;
        this.descricao = descricao;
        this.quartos = quartos;
        this.userModel = userModel;
    }   
    
    
    public ImovelModel(String descricao, Integer quartos, Integer vagas, UserModel userModel, List<OfertaModel> ofertas){
        super();
        this.descricao = descricao;
        this.quartos = quartos;
        this.userModel = userModel;
    }   

}
