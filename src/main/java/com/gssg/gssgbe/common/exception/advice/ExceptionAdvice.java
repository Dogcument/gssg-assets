package com.gssg.gssgbe.common.exception.advice;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ExceptionAdvice {

  protected UUID createLogId(Exception ex) {
    UUID uuid = UUID.randomUUID();
    log.error("### {}, {}", uuid, ex.getClass().getSimpleName(), ex);
    return uuid;
  }
}
