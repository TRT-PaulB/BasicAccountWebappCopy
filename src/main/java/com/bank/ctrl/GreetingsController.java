package com.bank.ctrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bank.serv.AccountService;


@RestController
public class GreetingsController {

	private Logger logger = LoggerFactory.getLogger(GreetingsController.class);
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping(value = "/greeting/{account}/{id}/{type}")
    public ResponseEntity<?> getGreeting(@PathVariable("account") String classification, @PathVariable int id, @PathVariable String type) {
		logger.info("/greeting endpoint hit");
		return ResponseEntity.ok().body(accountService.getGreeting(id, classification, type));
	}
			
}

