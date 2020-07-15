package org.sid.cinema.entities;

import java.util.Collection;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numero;
	private double longitude, latitude, altitude;
	@ManyToOne
	private Salle salle;
	@OneToMany(mappedBy = "place")
	private Collection<Ticket> tickets;
}
