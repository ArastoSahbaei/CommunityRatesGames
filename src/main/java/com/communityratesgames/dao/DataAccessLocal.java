package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.CompanyModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DataAccessLocal {

    public CompanyEntity registerNewCompany(CompanyModel companyModel);
    public List<GameEntity> showAllGames();
    public List<PlatformEntity> showAllPlatforms();
    public List<UserEntity> showAllUsers();
    public List<RatingEntity> showAllRatings();
    public List<CompanyEntity> showAllCompanies();
}
