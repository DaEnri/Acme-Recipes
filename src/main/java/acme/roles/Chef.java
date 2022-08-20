package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.roles.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chef extends UserRole{

	protected static final long serialVersionUID = 1L;
	
	@NotBlank
	@Length(max=100) //Strictly shorter than 101
	protected String organisation;
	
	@NotBlank
	@Length(max=255)//Strictly shorter than 256
	protected String assertion;
	
	@URL
	protected String info;
}
