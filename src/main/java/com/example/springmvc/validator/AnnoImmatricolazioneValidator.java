package com.example.springmvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class AnnoImmatricolazioneValidator implements ConstraintValidator<AnnoImmatricolazione, Integer>
{
	@Override
	public boolean isValid(Integer annoInserito, ConstraintValidatorContext theConstraintValidatorContext)
	{
		return (annoInserito != null) && (annoInserito <= LocalDate.now().get(ChronoField.YEAR));
	}
}
