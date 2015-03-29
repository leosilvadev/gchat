package br.leosilvadev.gchat.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.leosilvadev.gchat.model.dto.ChatUser;

@Component
public class ChatUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ChatUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ChatUser user = (ChatUser) target;
		if ( !user.getPassword().equals(user.getPasswordConfirmation()) ) errors.rejectValue("passwordConfirmation", "Passwords must be equal");
	}

}
