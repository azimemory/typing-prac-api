package com.hmd.typing.modules.document.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
@NoArgsConstructor
@AllArgsConstructor
public class API {
	
	@Id
	@GeneratedValue
	protected Long id;
	protected String name;
	
	@Column(name = "description",columnDefinition = "varchar2(1000 char)")
	protected String desc;
	

}
