package com.digitalhouse.MeAdote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetFiltro {
	
	private boolean gato;
	private boolean cachorro;
	
	private boolean peloCurto;
	private boolean peloLongo;
	
	private boolean brincalhao;
	private boolean calmo;	
	
	private boolean macho;
	private boolean femea;
	
	private boolean pequeno;
	private boolean medio;
	private boolean grande;
}
