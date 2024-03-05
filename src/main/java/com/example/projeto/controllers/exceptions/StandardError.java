package com.example.projeto.controllers.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class StandardError implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer status;
    private String msg;    
}
