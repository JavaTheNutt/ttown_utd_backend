package ie.wit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ThomastownUtdBackend2Application
{

	public static void main(String[] args)
	{
		SpringApplication.run(ThomastownUtdBackend2Application.class, args);
	}
}
