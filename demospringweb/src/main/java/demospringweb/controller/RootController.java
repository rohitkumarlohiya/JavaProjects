package demospringweb.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	
	/*@Autowired
	private BookRepository bookRepository;*/
	
	List<String> strs = Arrays.asList("ad","a","asd");
	
	

	@RequestMapping("/")
	public List<String> home() {
		return strs;
		//return "Hello World...!!!";
	}
}
