package com.digitalhouse.MeAdote.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuarios")
public class Usuario implements BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="id_login", referencedColumnName = "id", nullable=false)
	private Login login;
	
	@Column(length = 45, nullable = false)
	private String nome;
	
	@Column(length = 255, nullable = true)
	private String descricao;
	
	@Column(length = 15, nullable = true)
	private String telefone;
	
	@Column(length = 15, nullable = true)
	private String celular;
	
	@Column(length = 15, nullable = true)
	private String cep;
	
	@Column(length = 45, nullable = true)
	private String cidade;
	
	@Column(length = 45, nullable = true)
	private String bairro;
	
	@Column(length = 45, nullable = true)
	private String logradouro;
	
	@Column(length = 45, nullable = true)
	private String num_residencia;
	
	@Column(length = 45, nullable = true)
	private String link_imagem;
	
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Pet> pets;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Match> matches;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Like> likes;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Adocao> adocoes;
	
}
