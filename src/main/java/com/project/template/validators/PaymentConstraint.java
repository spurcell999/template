package com.project.template.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by E082175 on 6/11/2018.
 */
@Documented
@Constraint(validatedBy = PaymentValidator.class)
@Target( { ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PaymentConstraint {
    String message() default "Values can not be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
