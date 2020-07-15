package org.sid.cinema.entities;

import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Salle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int nombrePlace;
	@ManyToOne
	@JsonProperty(access=Access.WRITE_ONLY)
	private Cinema cinema;
	@OneToMany(mappedBy = "salle")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Place> places;
	@OneToMany(mappedBy = "salle")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Projection> projections;
}
