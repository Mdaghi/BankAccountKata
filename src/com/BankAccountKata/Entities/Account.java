package com.BankAccountKata.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {

	public int id;
	// we suppose that account contain only 1 attribut and not affected to a user
	public float amount;
	
	// list of operations to a specific account 
	public List<Operation> operations = new ArrayList<>();
	
}
