package com.project.transferapi.domain.exceptions;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {
   public ConflictException(String message) {
      super(message);
   }
}
