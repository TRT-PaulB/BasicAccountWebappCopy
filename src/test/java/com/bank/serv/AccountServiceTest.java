package com.bank.serv;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.bank.ex.InvalidAccountDetailsException;
import com.bank.ex.NotImplementedException;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

	@Autowired
	private AccountService service;
	
	
	@Test
	public void testSuccessfulGetGreeting() {
		assertThat("Hi, userId 4").isEqualTo(service.getGreeting(4, AccountService.PERSONAL, AccountService.SMALL));
		assertThat("Hi, userId 400").isEqualTo(service.getGreeting(400, AccountService.PERSONAL, AccountService.BIG));
		assertThat("Welcome, business user!").isEqualTo(service.getGreeting(401, AccountService.BUSINESS, AccountService.BIG));
		assertThat("Welcome, business user!").isEqualTo(service.getGreeting(-4009, AccountService.BUSINESS, AccountService.BIG));

		Assertions.assertThrows(NotImplementedException.class, () -> {
			service.getGreeting(4099, AccountService.BUSINESS, AccountService.SMALL);
		});
	}
	
	
	@Test
	public void testUnsuccessfulGetGreeting() {

		Assertions.assertThrows(InvalidAccountDetailsException.class, () -> {
			service.getGreeting(4099, AccountService.BUSINESS, "medium");
		});

		Assertions.assertThrows(InvalidAccountDetailsException.class, () -> {
			service.getGreeting(-180, AccountService.PERSONAL, AccountService.SMALL);
		});
		
		Assertions.assertThrows(InvalidAccountDetailsException.class, () -> {
			service.getGreeting(180, AccountService.PERSONAL, "smallish");
		});
		
		Assertions.assertThrows(InvalidAccountDetailsException.class, () -> {
			service.getGreeting(180, "BUSINESS", "smallish");
		});
	}

	
	@Test
	public void testBigBusinessGreeting() {
		String actual = service.getGreetingForBusinessAccount(AccountService.BIG);
		assertThat("Welcome, business user!").isEqualTo(actual);
	}

	
	@Test
	public void testSmallBusinessGreeting() {
		Assertions.assertThrows(NotImplementedException.class, () -> {
			service.getGreetingForBusinessAccount(AccountService.SMALL);
		});
	}
	
	
	@Test
	public void testBusinessGreetingForInvalidType() {
		Assertions.assertThrows(InvalidAccountDetailsException.class, () -> {
			service.getGreetingForBusinessAccount("medium");
		});
	}
	
	
	@Test
	public void testValidIdAndClassificationAndType() {
		service.validate(1, AccountService.PERSONAL, AccountService.SMALL);
		service.validate(90000, AccountService.BUSINESS, AccountService.BIG);
	}
	
	@Test
	public void testInvalidIdAndClassificationAndType() {
		Assertions.assertThrows(InvalidAccountDetailsException.class, () -> {
			service.validate(0, AccountService.PERSONAL, AccountService.SMALL);
		});
		
		Assertions.assertThrows(InvalidAccountDetailsException.class, () -> {
			service.validate(-400, AccountService.PERSONAL, AccountService.BIG);
		});
		
		Assertions.assertThrows(InvalidAccountDetailsException.class, () -> {
			service.validate(4, AccountService.BUSINESS, "medium");
		});
		
		service.validate(-47, AccountService.BUSINESS, AccountService.SMALL);
	}
	
	
}
