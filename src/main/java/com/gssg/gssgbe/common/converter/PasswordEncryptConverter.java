package com.gssg.gssgbe.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
@Converter
public class PasswordEncryptConverter implements AttributeConverter<String, String> {

  private final PasswordEncoder passwordEncoder;

  @Override
  public String convertToDatabaseColumn(String entityAttribute) {
    return passwordEncoder.encode(entityAttribute);
  }

  @Override
  public String convertToEntityAttribute(String databaseColumn) {
    return databaseColumn;
  }
}
