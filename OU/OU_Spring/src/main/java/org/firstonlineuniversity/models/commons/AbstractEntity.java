package org.firstonlineuniversity.models.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.format.datetime.DateFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@JsonAutoDetect
public class AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 11, nullable = false)
	private Long id;

	@Column(nullable = true)
	private Date CD;

	@Column(name = "CB", length = 11, nullable = true)
	private Long CB;

	@Column(nullable = true)
	private Date UD;

	@Column(name = "UB", length = 11, nullable = true)
	private Long UB;

	@Column(name = "ENABLED", nullable = true)
	private Boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCD() {
		return CD;
	}

	public void setCD(Date cD) {
		CD = cD;
	}

	public Long getCB() {
		return CB;
	}

	public void setCB(Long cB) {
		CB = cB;
	}

	public Date getUD() {
		return UD;
	}

	public void setUD(Date uD) {
		UD = uD;
	}

	public Long getUB() {
		return UB;
	}

	public void setUB(Long uB) {
		UB = uB;
	}

	public Boolean isEnabled() {
		return enabled == null ? false : enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
