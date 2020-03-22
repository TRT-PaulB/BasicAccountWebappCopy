package com.bank.ctrl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.bank.ex.InvalidAccountDetailsException;
import com.bank.ex.NotImplementedException;
import com.bank.serv.AccountService;


@RunWith(SpringRunner.class)
@WebMvcTest(GreetingsControllerTest.class)
@ComponentScan("com.bank")
public class GreetingsControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@MockBean
    private AccountService accountService;
	
	
	@Test
    public void testPersonalAccountWithPositiveIdGreeting() throws Exception {
		when(accountService.getGreeting(123, "personal", "small")).thenReturn("Hi, userId 123");
		
		MvcResult result = this.mockMvc.perform(get("/greeting/personal/123/small"))
            .andDo(print())
            .andExpect(status().isOk()).andReturn();
		String output = result.getResponse().getContentAsString();   

		assertThat("Hi, userId 123").isEqualTo(output);
	}

	
	@Test
    public void testBadAccountSort() throws Exception {
		when(accountService.getGreeting(123, "impersonal", "small")).thenThrow(
			new InvalidAccountDetailsException("Invalid account sort"));
		
		this.mockMvc.perform(get("/greeting/impersonal/123/small"))
	        .andDo(print())
	        .andExpect(status().isBadRequest());
	}
	
	
	@Test
    public void testSmallBusinessAccountGreeting() throws Exception {
		when(accountService.getGreeting(1, "business", "small")).thenThrow(
			new NotImplementedException("Path not yet implemented for small/business"));
		
		this.mockMvc.perform(get("/greeting/business/1/small"))
            .andDo(print())
            .andExpect(status().isNotImplemented());
	}
	
	
	@Test
    public void testBigBusinessAccountGreeting() throws Exception {
		when(accountService.getGreeting(123, "business", "big")).thenReturn("Welcome, business user!");
		
		MvcResult result = this.mockMvc.perform(get("/greeting/business/123/big"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
		String output = result.getResponse().getContentAsString();   

		assertThat("Welcome, business user!").isEqualTo(output);
	}

	
}
