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

public class Projection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private Date dateProjection;
	private double prix;
	@ManyToOne
	private Film film;
	@ManyToOne
	@JsonProperty(access=Access.WRITE_ONLY)
	private Salle salle;
	@OneToMany(mappedBy = "projection")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Ticket> tickets;
	@ManyToOne
	private Seance seance;
}
