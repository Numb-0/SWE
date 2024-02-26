package LC.PrenotationApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

// All resources in static get automatic-loaded .js .html .css
@SpringBootApplication
public class PrenotationApp {
	public static void main(String[] args) {
		SpringApplication.run(PrenotationApp.class, args);
	}
}
