package com.ditrraacademy.travelagency.core.voyage;

import com.ditrraacademy.travelagency.core.destination.Destination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor

public class Voyage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String description;
	private Integer nbPlaces;
	private Double prix;
	private Date date;

	@ManyToOne
	private Destination destination;

}
