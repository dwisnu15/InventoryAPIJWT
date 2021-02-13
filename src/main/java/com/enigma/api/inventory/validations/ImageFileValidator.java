package com.enigma.api.inventory.validations;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;

public class ImageFileValidator implements ConstraintValidator<ImageFile, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        Tika tika = new Tika();
        try(InputStream input = multipartFile.getInputStream()) {
            String mediaType = tika.detect(input);
            if(mediaType.matches("^image.*")){
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
