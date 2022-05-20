package com.hmd.typing.modules.score;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.hmd.typing.modules.document.entity.API;

import lombok.Data;

@Entity
@Data
public class Score {
	
	@Id
	private Long id;
	
	@ManyToOne
	private API api;
	private Integer speed;
	private Double	accurancy;
	private LocalDateTime regDate;
}
