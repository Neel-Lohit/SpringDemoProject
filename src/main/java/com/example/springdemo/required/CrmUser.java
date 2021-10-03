package com.example.springdemo.required;

import com.example.springdemo.validation.FieldMatch;
import com.example.springdemo.validation.ValidEmail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
@Getter
@Setter
@NoArgsConstructor
public class CrmUser {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;

	@Pattern(regexp="(^$|[0-9]{10})", message = "should be between 0-9 and 10 digits")
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String phoneNo;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String gender;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String qualification;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String organisation;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String city;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String state;



}
