package com.example.springmvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class AnnoImmatricolazioneValidator implements ConstraintValidator<AnnoImmatricolazione, String>
{
	@Override
	public boolean isValid(String annoInserito, ConstraintValidatorContext theConstraintValidatorContext)
	{
		return Integer.parseInt(annoInserito) > LocalDate.now().get(ChronoField.YEAR);
	}
}
