/**
 * 
 */
package com.lifetrack.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


/**
 * @author Charles Feng
 *
 */
@MappedSuperclass
public class BaseEntity extends IdEntity {

	private static final long serialVersionUID = 6949239610674580664L;
	private Long creator;
	private Date createTime;

	/**
	 * @return the creator
	 */
	@Column(name = "creator")
	public Long getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/**
	 * @return the createTime
	 */
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
