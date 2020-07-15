package org.sid.cinema.entities;

import java.util.Collection;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cinema {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double longitude, latitude, altitude;
	private int nombreSalles;
	@OneToMany(mappedBy = "cinema")
	private Collection<Salle> salles;
	@ManyToOne
	private Ville ville;

}
