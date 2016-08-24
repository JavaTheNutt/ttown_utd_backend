package ie.wit.model.entity;

import javax.persistence.*;

/**
 * Represents a role for authentication
 *
 * @author Joe Wemyss
 *
 */
@Entity
@Table(name = "Role")
public class Role
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", unique = true)
	@Basic(optional = false)
	private String name;

	// todo: getters and setters


	protected Role() {}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
