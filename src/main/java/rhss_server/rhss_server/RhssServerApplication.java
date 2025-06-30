package rhss_server.rhss_server;


import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAsync
@RestController
public class RhssServerApplication {

	public static void main(String[] args) {
		System.out.println("Server Initialize");
		SpringApplication.run(RhssServerApplication.class, args);
	}

	@GetMapping()
	public String pingServer () {
		LocalDate current = LocalDate.now();
		return "Server pinged at "+current;
	}

	

}
