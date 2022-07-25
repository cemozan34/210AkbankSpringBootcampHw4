package com.cemozan.bankingsystem.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cemozan.bankingsystem.Interfaces.IBankingSystem;
import com.cemozan.bankingsystem.Models.AccountCreateRequest;
import com.cemozan.bankingsystem.Models.AccountCreateResponse;
import com.cemozan.bankingsystem.Models.AccountDetails;
import com.cemozan.bankingsystem.Models.IncreaseBalance;
import com.cemozan.bankingsystem.Models.Log;
import com.cemozan.bankingsystem.Models.MoneyTransferRequest;
import com.cemozan.bankingsystem.Models.MoneyTransferResponse;




@RestController
public class BankingSystemController {
	
	@Autowired
	private IBankingSystem service;
	
	@PostMapping(path = "/accounts", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AccountCreateResponse> createAccount(@RequestBody AccountCreateRequest request){

		return this.service.createAccount(request);
		
    }
	
	@GetMapping(path = "/accounts/{id}")
	public ResponseEntity<AccountDetails> getAccountDetails(@PathVariable String id){
		
		return this.service.getAccountDetails(id);
	}
	
	@PatchMapping(path="accounts/{accountNumber}", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AccountDetails> increaseBalance(@PathVariable String accountNumber, @RequestBody IncreaseBalance request){
		return this.service.increaseBalance(accountNumber, request);
	}

	@PutMapping(path="accounts/{fromAccountNumber}", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MoneyTransferResponse> moneyTransfer(@PathVariable String fromAccountNumber, @RequestBody MoneyTransferRequest request) throws IOException{
		return this.service.moneyTransfer(fromAccountNumber, request);
		
	}
	
	@CrossOrigin("http://localhost:6162")
	@GetMapping(path="accounts/logs/{accountNumber}")
	public ResponseEntity<ArrayList<Log>> getLogs(@PathVariable String accountNumber){
		return this.service.getLogs(accountNumber);
	}
	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable String id) {
		return this.service.delete(id);
	}

}
