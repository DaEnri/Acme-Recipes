package acme.system.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotBlank
	private String acceptedCurrencies;
	
	@NotBlank
	private String defaultSystemCurrency;
	
	@NotBlank
	private String spamTerms;
	
	@NotNull
	@Range(min = 0, max = 1)
	private Double spamThreshold;
	
}
