package com.UserSystem.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.UserSystem.token.Token;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
	private static final long serialVersionUID = 7321374061017039662L;

	@Id
	@GeneratedValue
	private UUID id;

	private String fullName;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email must be a valid email address")
	@Column(unique = true, nullable = false) // Ensure email is unique and not null
	private String email;
	private String password;
	@Embedded
	private Address address;
	@Min(value = 18, message = "Age must be greater than or equal to 18")
	private Integer age;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt = new Date();

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<Token> tokens;

	private Date passwordChangeTime;


	public User(String fullName,
			@NotNull(message = "Email cannot be null") @Email(message = "Email must be a valid email address") String email,
			String password, Address address,
			@Min(value = 18, message = "Age must be greater than or equal to 18") Integer age, Gender gender,
			Role role) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.age = age;
		this.gender = gender;
		this.role = role;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	// method to check if password is expired or not we can use it if we want
	public boolean isPasswordExpired() {

		if (this.passwordChangeTime == null)
			return false;

		long currentTime = System.currentTimeMillis();
		long lastPasswordChangedTime = this.passwordChangeTime.getTime();

		return currentTime > lastPasswordChangedTime + PASSWORD_EXPIRATION_TIME;

	}







	private static final long PASSWORD_EXPIRATION_TIME = 30L * 24L * 60L * 60L * 1000L;

}
