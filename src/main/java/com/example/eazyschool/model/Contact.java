package com.example.eazyschool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="contact_msg")
public class Contact extends BaseEntity{
	
	@Id
    //@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    //@GenericGenerator(name = "native",strategy = "native")
	
    @Column(name = "contact_id")
	private int ContactId;
	
	
	@NotBlank(message="This field is required, please enter a valid name")
	@Size(min=2, message="Please enter a valid name")
	private String name;
	
	@NotEmpty(message="This field is required, please enter a valid Contact number")
	@Pattern(regexp="(^[0-9]{10})", message= "please enter a valid number")
	private String mobileNum;
	
	@Email(message="please enter a valid email")
	private String email;
	
	@NotBlank(message="Subject field is required")
	@Size(min=5, message="Please enter minimum 5 characters at Subject field" )
	private String subject;
	
	@NotBlank(message="Messsage field is required")
	@Size(min=10, message="Please enter minimum 10 characters at Message field")
	private String message;
	
	private String status;
	
	
	
}
