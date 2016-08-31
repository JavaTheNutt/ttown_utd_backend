package ie.wit.model.enums;

/**
 * This enum represents the roles that a user may have
 */
public enum Role
{
	ADMIN(1), WRITE(2), READ(3);
	private final Integer value;

	Role(int value)
	{
		this.value = value;
	}

	public static String getStringValueFromInt(Integer i)
	{
		for (Role role : Role.values()) {
			if (role.getValue() == i) {
				return role.toString();
			}
		}
		throw new IllegalArgumentException("The parameter does not match any role");
	}

	Integer getValue()
	{
		return this.value;
	}
}
