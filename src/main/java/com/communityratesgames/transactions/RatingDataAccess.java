package com.communityratesgames.transactions;

import com.communityratesgames.domain.RatingEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RatingDataAccess {

    public abstract List<RatingEntity> showAllRatings();
}
