package io.github.cepr0.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "citizens")
@DynamicInsert
@DynamicUpdate
public class Citizen extends BaseEntity<Long> {

	@Id
	@GeneratedValue(generator = "citizen_id_generator")
	@SequenceGenerator(name = "citizen_id_generator", allocationSize = 100)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private City city;

	@Column(length = 32, nullable = false)
	private String name;

	@Tolerate
	public Citizen(City city, String name) {
		this.city = city;
		this.name = name;
	}
}
