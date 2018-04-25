package com.qa.service;

import com.qa.Repository.AccountMapRepository;
import com.qa.Repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceTest {

	private AccountService service;
	private Account joeBloggs;
	private Account janeBloggs;
	private JSONUtil util;

	@Before
	public void init() {
		service = new AccountMapRepository();
		joeBloggs = new Account("Joe", "Bloggs", "1234");
		janeBloggs = new Account("Jane", "Bloggs", "1234");
		util = new JSONUtil();

	}

	@Test
	public void addAndRemoveAccountTest() {
		service.addAccountFromMap(joeBloggs);
		Assert.assertEquals(service.getAccountMap().size(), 1);
		service.addAccountFromMap(janeBloggs);
		Assert.assertEquals(service.getAccountMap().size(), 2);
		service.removeAccountFromMap(0);
		Assert.assertEquals(service.getAccountMap().size(), 1);
		service.removeAccountFromMap(1);
		Assert.assertEquals(service.getAccountMap().size(), 0);
		service.removeAccountFromMap(5);
		Assert.assertEquals(service.getAccountMap().size(), 0);
	}

	@Test
	public void accountConversionToJSONTest() {
		String emptyMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals("{}", emptyMap);
		String accountAsJSON = "{\"0\":{\"firstName\":\"Joe\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"},\"1\":{\"firstName\":\"Jane\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"}}";
		Assert.assertEquals("{}", emptyMap);
		service.addAccountFromMap(joeBloggs);
		service.addAccountFromMap(janeBloggs);
		String populatedAccountMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals(accountAsJSON, populatedAccountMap);
	}

	@Test
	public void getCountForFirstNamesInAccount() {
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 0);
		service.addAccountFromMap(joeBloggs);
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 1);
		Account joeGordon = new Account("Joe", "Gordon", "1234");
		service.addAccountFromMap(joeGordon);
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 2);
	}


    @InjectMocks
    private AccountRepository repo;

	@Mock
    private EntityManager em;

	@Mock
    private Query query;

	@Test
    public void findallTest(){
		repo.setEm(em);
	    Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        List<Account> accounts = new ArrayList<Account>();
        Account acc = new Account("Ruben", "Caldeira", "1234");
        Mockito.when(query.getResultList()).thenReturn(accounts);
        assertEquals("MOCK_DATA_ARRAY", repo.findall());

    }



}
