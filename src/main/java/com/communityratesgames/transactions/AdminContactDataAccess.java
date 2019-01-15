package com.communityratesgames.transactions;

import com.communityratesgames.domain.AdminContact;
import com.communityratesgames.model.AdminContactModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AdminContactDataAccess {
    public abstract void newMessage(AdminContactModel model);
    public abstract AdminContact adminGetMessage(Long id);
    public abstract List<AdminContact> adminGetAllMessages();

}
