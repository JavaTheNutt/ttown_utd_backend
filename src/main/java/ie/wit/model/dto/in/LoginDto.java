
public class LoginDto
{
	@NotNull
	@NotEmpty
	private String emailAddress;
	
	@NotNull
	@NotEmpty
	private String password;
	
	public LoginDto(String emailAddress, String password){
		this.emailAddress = emailAddress;
		this.password = password;
	}
}
