package gt.com.model.dao;

import gt.com.model.entity.PatientRoomEntity;
import gt.com.model.entity.PrescriptionEntity;

import java.sql.SQLException;
import java.util.List;

public interface IPrescriptionsDao {

    public List<PrescriptionEntity> getPrescriptions() throws SQLException;
    public int savePrescription(PrescriptionEntity prescriptionEntity) throws SQLException;
    public List<PrescriptionEntity> searchPrescriptionFiltred(long searchParameter) throws  SQLException;
}
