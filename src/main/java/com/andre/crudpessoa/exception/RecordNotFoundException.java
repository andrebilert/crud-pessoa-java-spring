package com.andre.crudpessoa.exception;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public RecordNotFoundException(Long id){
        super("Pessoa não encontrada com o id: " + id);
    }
}   
