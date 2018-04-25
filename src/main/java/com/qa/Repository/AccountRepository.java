package com.qa.Repository;

import com.qa.domain.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;
import com.qa.util.JSONUtil;

@Transactional
public class AccountRepository {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public Account find(Long id){
        return em.find(Account.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Account create(Account account){
        em.persist(account);
        return account;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Account update(Account account){
        em.merge(account);
        return (account);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id){
        em.remove(em.getReference(Account.class, id));
    }

    public List<Account> findall(){
        TypedQuery<Account> query = em.createQuery("SELECT a from Account a order by a.firstName", Account.class);
        return query.getResultList();
    }

    public void setEm(EntityManager em){
        this.em = em;
    }




}
