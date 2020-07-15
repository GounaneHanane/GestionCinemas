package org.sid.cinema.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String titre;
	private String realisateur;
	private String description;
	private Date dateSortie;
	private double duree;
	private String photo;
	@OneToMany(mappedBy = "film")
	@JsonProperty(access=Access.WRITE_ONLY)
	private  Collection<Projection> projections;
	@ManyToOne
	private Categorie categorie;

}
