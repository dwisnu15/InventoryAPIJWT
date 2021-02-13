package com.enigma.api.inventory.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.MODULE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)//dijalankan waktu runtime
@Constraint(validatedBy = AlphabeticValidator.class)//divalidasi oleh class
@Documented
public @interface Alphabetic {

    String message() default "Alphabetic";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
/*
@interface setelah "public" agar bisa dipakai dimanapun dengan pakai notasi namaclassnya
bisa dipakai di field suatu class dengan elementtype.field
elementtype.type = untuk class
message, groups, payload = default hibernate

 */