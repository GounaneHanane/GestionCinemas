package org.sid.cinema.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String nomClient;
	private double prix;
	@Column(unique=false,nullable=true)
	private Integer codePaimenet;
	private boolean reservee;
	@ManyToOne
	private Place place;
	@ManyToOne
	@JsonProperty(access=Access.WRITE_ONLY)
	private Projection projection;
}
