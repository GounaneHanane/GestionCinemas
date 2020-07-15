package org.sid.cinema.entities;

import java.util.Collection;

import javax.persistence.*;

import lombok.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ville {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double longitude,latitude,altitude;
	@OneToMany(mappedBy = "ville")
	private Collection<Cinema> cinemas;
}
