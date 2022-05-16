package gt.com.model.dao;

import gt.com.model.entity.MedicineEntity;

import java.util.*;

/**
 * 
 */
public interface IMedicineDao {

    /**
     * @param key 
     * @return
     */
    public List<MedicineEntity> getMedicines(String key);

}