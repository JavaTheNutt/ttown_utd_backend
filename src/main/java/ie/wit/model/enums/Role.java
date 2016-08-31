package ie.wit.model.enums;

/**
 * This enum represents the roles that a user may have. Currently, the roles are ADMIN, WRITE and READ.
 * These roles are arranged in a hierarchical tree structure with WRITE having all of the permissions of READ and ADMIN having all of the permissions of WRITE.
 * Each user can only have one role.
 * @see ie.wit.model.entity.UserEntity
 * 
 * @author Joe Wemyss
 */
public enum Role
{
	/**
	 * The three user roles.
	 */
	ADMIN(1), WRITE(2), READ(3);
	
	/**
	 * The integer value of each role
	 */
	private final Integer value;

	/**
	 * Constructor to be called internally.
	 * 
	 * @param  The integer value of the Role
	 */
	Role(int value)
	{
		this.value = value;
	}

	/**
	 * Get the string value of a specific role.
	 * 
	 * @param i  the integer value of the role
	 * @return  the String value of the role
	 */
	public static String getStringValueFromInt(Integer i)
	{
		for (Role role : Role.values()) {
			if (role.getValue() == i) {
				return role.toString();
			}
		}
		throw new IllegalArgumentException("The parameter does not match any role");
	}

	/**
	 * Accessor for the value of the role.
	 * 
	 * @return the integer value of the role
	 */
	Integer getValue()
	{
		return this.value;
	}
}
