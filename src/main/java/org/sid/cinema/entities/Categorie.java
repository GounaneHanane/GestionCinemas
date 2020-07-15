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

public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	@Column(length=75)
	private String name;
	@OneToMany(mappedBy = "categorie")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Film> films;
}
