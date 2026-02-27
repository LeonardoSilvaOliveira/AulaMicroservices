package br.com.fiap.ms_produto.exception;

public class ResourceNotFoundException extends RuntimeException {

  //RuntimeException não precisa de try-cath
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
