package com.ecc.hibernate_xml.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.FetchType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import javax.persistence.Cacheable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(
	name="contacts",
	uniqueConstraints=
		@UniqueConstraint(columnNames={"contact_type", "data"})
)
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Inheritance
@DiscriminatorColumn(name="contact_type", length=20)
public abstract class Contact {
	private Integer id;
	private String data;
	private Person person;

	@Id @GeneratedValue(generator="ContactIdGenerator")
	@SequenceGenerator(name="ContactIdGenerator", sequenceName="contacts_id_seq")
	@Column(nullable=false)
	public Integer getId() {
		return id;
	}

	@Column(nullable=false, length=50)
	public String getData() {
		return data;
	}

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@OrderBy
	public Person getPerson() {
		return person;
	}
	
	@Transient
	public String getContactType() {
		return this.getClass().getSimpleName();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public boolean equals(Object object) {
		if (object != null && object instanceof Contact) {
			Contact otherContact = (Contact) object; 
			return id.equals(otherContact.id) && data.equals(otherContact.data)
				&& getContactType().equals(otherContact.getContactType());
		}
		return false;
	}
}