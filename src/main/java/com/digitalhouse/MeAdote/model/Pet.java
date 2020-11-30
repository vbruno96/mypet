package com.digitalhouse.MeAdote.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "pets")
public class Pet implements BaseModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_usuario", nullable=false)
	@JsonIgnore
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_especie")
	private Especie especie;
	
	@Column(length = 45, nullable = true)
	private String link_imagem_1;
	
	@Column(length = 45, nullable = true)
	private String link_imagem_2;
	
	@Column(length = 45, nullable = true)
	private String link_imagem_3;
	
	@Column(length = 45, nullable = false)
	private String nome;
	
	@Column(nullable = true)
	private Date data_nascimento;
	
	//M: Macho
	//F: Femea
	@Column(length = 1, nullable = false)
	private String sexo;
	
	//P: Pequeno
	//M: Medio
	//G: Grande
	@Column(length = 1, nullable = true)
	private String porte;
	
	//C: Pelo Curto
	//L: Pelo Longo
	@Column(length = 1, nullable = true, name="tipo_pelo")
	private String tipoPelo;
	
	//B: Brincalhao
	//C: Calmo
	@Column(length = 1, nullable = true)
	private String personalidade;
	
	@Column(nullable = true)
	private Float peso;
	
	@Column(length = 255, nullable = true)
	private String descricao;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_pet_desaparecido", referencedColumnName = "id")
	@JsonIgnore
	private PetDesaparecido petDesaparecido;
	
	@OneToMany
	private List<Match> matches;
	
	@OneToMany
	private List<Like> likes;
	
	@OneToOne
	@JoinColumn(name = "id_adocao", referencedColumnName = "id")
	private Adocao adocao;

}
