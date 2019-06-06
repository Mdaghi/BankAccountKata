package com.BankAccountKata.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.BankAccountKata.Business.BankAccountKataBusiness;
import com.BankAccountKata.Entities.Account;
import com.BankAccountKata.Entities.Operation;



@Path("/account")
public class AccountRessources {

	/***
	 * In this exemple, I use a static list of accounts that will be initialized
	 * in constructor with a static account objects.
	 */
	
	public BankAccountKataBusiness business = new BankAccountKataBusiness();
	
	

	// Create an object of Type saveMoneyRequest
	// that contain an object type of Account and the amount that will be added
	// better than passing paramters .
	
	// http://localhost:18080/BankAccountKata/rest/account/save/1/2500
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/save/{id}/{amount}")
	public Response saveMoney(@PathParam("id") int idAccount, @PathParam("amount") float amount) throws Exception {
		// we can use lambda expression here
		for (Account account : business.getAccounts()) {
			if (account.id == idAccount) {

				account.amount += amount;
				Operation operation = new Operation();
				operation.setAmount(amount);
				operation.setDescription("Operation type : deposit, amount added :" + amount);
				operation.setDate(new Date());
				account.operations.add(operation);
				return Response.status(Status.CREATED).entity("saving money with success ... ").build();
			} else
				// we can use the log file
				return Response.status(Status.NOT_FOUND).entity("Account not found ... ").build();

		}
		return Response.status(Status.NOT_FOUND).entity("Account not found ").build();
	}

	// http://localhost:18080/BankAccountKata/rest/account/retrieve/1/2500

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/retrieve/{id}/{amount}")
	public Response retrieveMoney(@PathParam("id") int idAccount, @PathParam("amount") float amount) throws Exception {
		// we can use lambda expression here
		for (Account account : business.getAccounts()) {
			System.out.println(account.id);
			if (account.id == idAccount) {

				if (account.amount >= amount) {
					account.amount -= amount;
					Operation operation = new Operation();
					operation.setAmount(amount);
					operation.setDescription("Operation type : withdrawal, amount retrieved :" + amount);
					operation.setDate(new Date());
					account.operations.add(operation);
					return Response.status(Status.CREATED).entity("retrieving money with success ... ").build();
				} else
					return Response.status(Status.NOT_FOUND).entity("Insufficient amount ... ").build();
			} else
				// we can use the logger
				return Response.status(Status.NOT_FOUND).entity("Account not found ... ").build();

		}
		return Response.status(Status.NOT_FOUND).entity("Account not found ... ").build();

	}

	// http://localhost:18080/BankAccountKata/rest/account/history/1
	
	@GET
	@Path("/history/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response accountHistory(@PathParam("id") int idAccount) {
		for (Account account : business.getAccounts()) {
			if (account.id == idAccount)
				return Response.status(Status.OK).entity(account.operations).build();
		}
		return Response.status(Status.NOT_FOUND).entity("there is no operations for this instance").build();

	}

}
