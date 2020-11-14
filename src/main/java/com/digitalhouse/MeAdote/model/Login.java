package com.digitalhouse.MeAdote.model;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "login")
public class Login implements BaseModel  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToOne(mappedBy = "login")
	@JsonIgnore
	private Usuario usuario;
	
	@EqualsAndHashCode.Include
	@Column(length = 45, nullable = false, unique = true)
	private String email;
	
	@Column(length = 255, nullable = false)
	@JsonIgnore
	private String senha;	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "login_roles", joinColumns = @JoinColumn(name = "login_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private Set<Role> roles;
	
	@JsonIgnore
	public List<String> getSimpleRoles() {
		return this.roles.stream().map(role -> role.getId() + "_" + role.getName()).collect(Collectors.toList());
	}

}
