package fr.charisma.bvj.rc_bvj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RcBvjApplication {

	public static void main(String[] args) {
		SpringApplication.run(RcBvjApplication.class, args);
	}

}
