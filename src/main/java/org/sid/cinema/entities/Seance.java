package org.sid.cinema.entities;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Seance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	@Temporal(TemporalType.TIME)
	private Date heureDepart;
}
