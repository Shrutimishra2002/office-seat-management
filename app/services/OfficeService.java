package services;

import com.google.inject.ImplementedBy;
import com.smartcoin.db.services.ModelService;
import models.Office;
import services.impl.OfficeServiceImpl;

@ImplementedBy(OfficeServiceImpl.class)
public interface OfficeService extends ModelService<Long, Office> {
//    Office createOffice(Office office);

    Office createOfficeByAdmin(String officeName, Integer columns, Integer rows);
    public boolean isExistOfficeByName(String officeName);
}
