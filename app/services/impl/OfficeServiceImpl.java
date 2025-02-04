package services.impl;

import com.smartcoin.db.services.ModelServiceImpl;
import models.Office;
import services.OfficeService;

public class OfficeServiceImpl extends ModelServiceImpl<Long, Office> implements OfficeService {

    @Override
    public Office createOfficeByAdmin(String officeName, Integer columns, Integer rows) {
        Office office = new Office();
        office.setName(officeName);
        office.setColumnsLength(columns);
        office.setRowsLength(rows);
        office.save();
        return office;
    }

    @Override
    public boolean isExistOfficeByName(String officeName) {
        return getFinder().where().eq("name", officeName).findUnique() != null;
    }

}
