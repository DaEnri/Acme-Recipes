package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import acme.roles.Epicure;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FineDish extends AbstractEntity {
	
	// Serialisation identifier -----------------------------------------------
	
	private static final long serialVersionUID = 1L;
			
	// Attributes -------------------------------------------------------------
	
	protected Status status;
	
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$", message = "wrong pattern, example -> ABZ-019-A")
	@Column(unique = true)
	@NotBlank
	protected String code;
	
	@NotBlank
	@Length(max = 255)
	protected String request;
	
	@Valid
	@NotNull
	protected Money budget;
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishDate;
	
	@NotNull
	protected boolean published;
	
	@URL
	protected String moreInfo;
	
	//Derived attributes ------------------------------------------------------
	
	//Relationships -----------------------------------------------------------
		
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Chef chef;
		
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Epicure epicure;
}
