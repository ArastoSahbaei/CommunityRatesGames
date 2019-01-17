package com.communityratesgames.transactions;

import com.communityratesgames.domain.AdminContact;
import com.communityratesgames.model.AdminContactModel;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Default
public class AdminContactService implements AdminContactDataAccess{
    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public void newMessage(AdminContactModel model) {
        em.persist(new AdminContact(model));
    }

    @Override
    public AdminContact adminGetMessage(Long id) {
        return em.find(AdminContact.class, id);
    }

    @Override
    public List<AdminContact> adminGetAllMessages() {
        return em.createQuery("SELECT m FROM AdminContact m",AdminContact.class)
                .getResultList();
    }

    @Override
    public List<AdminContactModel> userMessages(String email) {
        return convertListEntityToModel(
            em.createQuery("SELECT m FROM AdminContact m WHERE m.email = :email",AdminContact.class)
                    .setParameter("email", email)
                    .getResultList()
        );
    }

    @Override
    public void updateEntry(AdminContact contact) {
        AdminContact oldContact = em.find(AdminContact.class, contact.getId());
        oldContact.setAdmin(contact.getAdmin());
        oldContact.setRead(contact.isRead());
        oldContact.setResponseMessage(contact.getResponseMessage());
        oldContact.setFlaggedForAdmin(contact.getFlaggedForAdmin());
        oldContact.setUrgent(contact.isUrgent());
        em.merge(oldContact);
    }


    private List<AdminContactModel> convertListEntityToModel (List<AdminContact> entityList) {
        return entityList.stream().map(AdminContactModel::new).collect(Collectors.toList());
    }
}
