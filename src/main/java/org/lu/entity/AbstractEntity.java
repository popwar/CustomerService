package org.lu.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -6965848061290894893L;

	@Version
	@Column(name = "OPT_LOCK")
	private Long version;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
