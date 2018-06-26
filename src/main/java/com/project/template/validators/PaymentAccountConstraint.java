package com.project.template.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Created by E082175 on 6/11/2018.
 */
@Documented
@Constraint(validatedBy = PaymentAccountValidator.class)
@Target( { ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface PaymentAccountConstraint {
    String message() default "Account number is not an acceptable type.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}