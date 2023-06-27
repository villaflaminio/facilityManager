package it.flaminiovilla.facilitymanager.model.modelbase;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorProvider implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		try{
			if (SecurityContextHolder.getContext().getAuthentication().getClass().equals(org.springframework.security.authentication.AnonymousAuthenticationToken.class)) {
				return Optional.of("Anonymous");
			} else {
				UsernamePasswordAuthenticationToken authToken = ((UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication());
				String loggedUser = authToken.getName();
				return Optional.of(loggedUser);
			}
		}catch (NullPointerException e){
			return Optional.of("Anonymous");
		}

	}

}