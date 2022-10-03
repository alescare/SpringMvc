package com.example.springmvc.validator;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE; 
import static java.lang.annotation.ElementType.FIELD; 
import static java.lang.annotation.ElementType.METHOD; 
import static java.lang.annotation.RetentionPolicy.RUNTIME; 
import java.lang.annotation.Documented; 
import java.lang.annotation.Retention; 
import java.lang.annotation.Target; 
import javax.validation.Constraint; 
import javax.validation.Payload; 

@Target( { METHOD, FIELD, ANNOTATION_TYPE }) 
@Retention(RUNTIME) 
@Constraint(validatedBy = AnnoImmatricolazioneValidator.class)
@Documented
public @interface AnnoImmatricolazione
{
	public String message() default "{AnnoImmatricolazione.Auto.annoImmatricolazione.validation}";
	
	public Class<?>[] groups() default {}; 
    
    public abstract Class<? extends Payload>[] payload() 
    
    default {};

}
