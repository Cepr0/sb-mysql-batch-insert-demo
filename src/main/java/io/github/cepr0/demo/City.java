package io.github.cepr0.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cities")
@DynamicInsert
@DynamicUpdate
public class City extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(generator = "city_id_generator")
	@SequenceGenerator(name = "city_id_generator", allocationSize = 1)
	private Integer id;

	@Column(length = 32, nullable = false)
	private String name;

	@OneToMany(mappedBy = "city")
	private Set<Citizen> citizens;

	@Tolerate
	public City(final String name) {
		this.name = name;
	}
}
