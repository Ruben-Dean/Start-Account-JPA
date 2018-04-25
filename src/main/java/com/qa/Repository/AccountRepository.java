package com.qa.Repository;

import com.qa.domain.Account;



import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import com.qa.util.JSONUtil;

@Transactional(Transactional.TxType.SUPPORTS)
public class AccountRepository {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Inject
    private JSONUtil util;

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
        Query query = em.createQuery("SELECT a from Account a order by a.firstName", Account.class);
        Collection<Account> accounts = (Collection<Account>) query.getResultList();
        return util.getJSONForObject(accounts);
    }

    public void setEm(EntityManager em){
        this.em = em;
    }


    public void setUtil(JSONUtil util) {
        this.util = util;
    }



}
