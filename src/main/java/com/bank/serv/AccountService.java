package com.bank.serv;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bank.ex.InvalidAccountDetailsException;
import com.bank.ex.NotImplementedException;


@Service
public class AccountService {
	
private Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	public static final String SMALL = "small";
	public static final String BIG = "big";
	
	public static final String BUSINESS = "business";
	public static final String PERSONAL = "personal";
	
	protected static final List<String> VALID_TYPES = Arrays.asList(SMALL, BIG);
	protected static final List<String> VALID_CLASSIFICATIONS = Arrays.asList(PERSONAL, BUSINESS);
		
	
	public String getGreeting(int id, String classification, String type) {
		classification = classification.toLowerCase().trim();
		
		if (classification.equals(BUSINESS)) {
			return getGreetingForBusinessAccount(type);
		} else {
			return "Hi, userId " + id;
		} 
	}
	
	
	protected String getGreetingForBusinessAccount(String type) {
		type = type.toLowerCase().trim();
		if (type.equals(SMALL)) {
			logger.info("business user with account type 'small'");
			throw new NotImplementedException("small/business");
		} else if (type.equals(BIG)) {
			return "Welcome, business user!";
		} else {
			throw new InvalidAccountDetailsException("Invalid account type");
		}
	}
	
	
	protected void validate(int id, String classification, String type) {
		if (id <= 0) {
			throw new InvalidAccountDetailsException("invalid details");
		}
		
	}
		

}
