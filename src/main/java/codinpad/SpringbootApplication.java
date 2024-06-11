package codinpad;

import codinpad.entities.Certificate;
import codinpad.entities.Device;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"codinpad","codinpad.config", "codinpad.controllers",
		"codinpad.dto", "codinpad.entities", "codinpad.enums", "codinpad.exceptions", "codinpad.repositories", "codinpad.services"})
@EntityScan(basePackageClasses={Certificate.class, Device.class})
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
