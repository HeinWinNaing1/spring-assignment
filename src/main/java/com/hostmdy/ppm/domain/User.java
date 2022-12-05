package com.hostmdy.ppm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email(message = "username must be email")
	@NotBlank(message = "username is required")
	@Column(unique = true)
	private String userName;
	
	@NotBlank(message = "fullName is required")
	private String fullName;
	
	@NotBlank(message = "password is required")
	private String password;
	
	@Transient
	private String comfirmPassword;

	private LocalDate createdAt;
	private LocalDate updateAt;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,
			cascade = CascadeType.REFRESH,orphanRemoval = true)
	private List<Project> projects = new ArrayList<>();
	
	
	@PrePersist
	void OnCreate() {
		this.createdAt = LocalDate.now();
	}
	
	@PreUpdate
	void onUpdate() {
		this.updateAt = LocalDate.now();
	}

	public User(@Email(message = "username must be email") @NotBlank(message = "username is required") String userName,
			@NotBlank(message = "fullName is required") String fullName,
			@NotBlank(message = "password is required") String password) {
		super();
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
	}

}
