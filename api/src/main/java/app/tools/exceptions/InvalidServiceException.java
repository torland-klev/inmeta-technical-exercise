package app.tools.exceptions;

import java.lang.Exception;

public class InvalidServiceException extends Exception {
  public InvalidServiceException(String message){
    super(message);
  }
}
