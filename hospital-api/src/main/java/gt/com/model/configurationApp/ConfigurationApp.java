package gt.com.model.configurationApp;

public final class ConfigurationApp {


    public static final String QUERY_PATIENTS_DELETE = "delete from patients where idPatient = ?";
    public static final String QUERY_PATIENTS_UPDATE ="UPDATE patients set dpi = ?,nit = ?,birthday = ?,gender = ?,fullName = ?,surName = ?,address = ?,phone = ?,emergencyContact = ?  WHERE idPatient = ? ;";
    public static final String QUERY_PATIENT_STATUS_UPDATE = "update patients set status = ? where idPatient = ?";
    public static final String QUERY_PATIENT_LAST_ID = "select MAX(idPatient) as id from patients;";
    public static final String QUERY_PATIENT_UPDATE_STATUSWAIT = "UPDATE patients set statusWait = ? where idPatient = ?;";
    public static final String QUERY_PATIENT_UPDATE_STATUSALL = "UPDATE patients set status = ?, statusWait = ? where idPatient = ?;";


    public static final String SELECT_FROM_PATIENT_STATUSWAIT = "SELECT * FROM patients where statusWait = true and status = 'true'";
    public static final String SELECT_NAME_SURNAME_PATIENTS = "select idPatient,dpi,nit,birthday,gender,fullName,surName,address,phone,emergencyContact,status,statusWait from patients where (status = '%s' and ((fullName = '%s' and surName = '%s') || (fullName = '%s' and surName = '%s') || (fullName = '%s') || (surName = '%s') || (fullName = '%s') || (surName = '%s')|| (fullName = '%s') || (surName = '%s') || (fullName = '%s') || (surName = '%s')));";
    //variables para Appointmenst
    public static final String SELECT_QUERY_APPOINTMENTS ="select ap.idAppointment, concat(p.fullName,\" \", p.surName) as namePatient , c.clinicName,ap.date,ap.hour from appointments ap inner join patients as p on " +
            "ap.IdPatient = p.idPatient inner join clinic as c on " +
            "c.idClinic = ap.idClinic order by date desc limit 20;";
    public static final String INSERT_QUERY_APPOINTMENTS = "insert into appointments (idPatient,date,hour,idClinic) values(%d,'%s','%s',%d);";
    public static final String UPDATE_QUERY_APPOINTMENTS = "update appointments set idPatient = ?, date = ?, hour = ?, idClinic = ? where idAppointment = ?";
    public static final String DELETE_QUERY_APPOINTMENTS = "delete from appointments where idAppointment = ?";
    public static final String SELECT_QUERY_APPOINTMENTS_BY_PATTIENT ="select idAppointment,idPatient,date,hour,idClinic from appointments where idPatient = (select idPatient from patients where dpi = ? || idPatient = ? LIMIT 1)  order by date limit 20;";

    //consultas para PatientRoom
    public static final String INSERT_QUERY_PATIENT_ROOM = "insert into patient_room (idPatient, idRoom) values(%d,%d)";
    public static final String UPDATE_QUERY_PATIENT_ROOM = "update patient_room set status = ? where idPatient = ?;";
    public static final String LIST_ROOMS_PATIENTS_STATUS = "select rm.idRoom,rm.numberRoom,rm.capacityRoom,rm.statusRoom from patient_room pr inner join rooms as rm on " +
            "rm.idRoom = pr.idRoom where pr.idPatient = '%d' and pr.status = %b;";

    //conusltar para habitaciones (ROOMS)
    public static final String SELECT_ROOMS_STATUS = "select * from rooms where statusRoom = %b;";
    public static final String UPDATE_QUERY_ROOMS_STATUS = "UPDATE rooms set statusRoom = ? where idRoom = ?";
    public static final String SELECT_REGISTER_BY_PATIENT ="select ro.idRoom,ro.numberRoom,ro.capacityRoom,ro.statusRoom from patient_room pr inner join rooms as ro on ro.idRoom = pr.idRoom where pr.idPatient = (select idPatient from patients where dpi = ? || idPatient = ?);";
    public static final String SELECT_ROOMS_ALL = "select * from rooms limit 10";

    //consultas para prescriptions
    public static final String SELECT_PRESCRIPTIONS = "SELECT * FROM prescriptions order by datePrescription limit 10;";
    public static final String SELECT_PRESCRIPTIONS_FILTRED = "select * from prescriptions where idPatient = (select idPatient from patients where dpi = ?) || idPatient = ?;";
    public static final String INSERT_PRESCRIPTIONS = "insert into prescriptions (prescription, idPatient, datePrescription, idDoctor) values (?,?,?,?);";

    //Consultas para GuestAppointments
    public static final String SELECT_ALL_GUEST_APPOINTMENTS ="select idGuestAppointment, fullName,surName,address,date,hour,idClinic from guestAppointments order by date limit 10;";
    public static final String SELECT_GUEST_APPOINTMENT_BY_NAME ="select idGuestAppointment, fullName, surName, address, date, hour from guestAppointments  where (( fullName like ? and surName like ?) || (fullName like ?) || (surName like ?) || (fullName like ?)|| (ap.surName like ?));";
    public static final String DELETE_GUEST_APPOINTMENT = "delete from guestAppointments where idGuestAppointment = ?";
    public static final String UPDATE_GUEST_APPOINTMENT = "update guestAppointments set fullName = ?, surName = ?, address = ?, date = ?, hour = ? where idGuestAppointment = ?;";
    public static final String INSERT_GUEST_APPOINTMENT = " insert into guestAppointments (fullName,surName,address,date,hour) values ('%s','%s','%s','%s','%s');";

}