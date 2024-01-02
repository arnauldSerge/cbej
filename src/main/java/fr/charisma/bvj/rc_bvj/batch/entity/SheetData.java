package fr.charisma.bvj.rc_bvj.batch.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class SheetData {
	@Id
	private Long id;
	private String column1;
	private String column3;
	private String column2;

}
