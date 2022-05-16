package gt.com.model.configurationApp;

public class MessagesErrorApp {
    // USERS  ERRORS
    public static final String SQL_NOT_USER = "No se ha encontrado el usuario";

    // DATA BASE ERORR
    public static final String SQL_TIME_OUT = "Hubo un problema en el repositorio";
    public static final  String SQL_ERROR_QUERY = "Se ha producido un error al momento de ejecutar la consulta";


    // SESSION ERRORS
    public static final String SERVICE_JSON_FAIL = "Hubo un problema al momento de iniciar sesion, por favor vuelva a intentarlo";

    //GENERIC MESSAGESS ERRORS
    public static final String SUCCESS ="success";
    public static final String ERROR ="error";

    // ROOMS ERRORS
    public static final String ARRAY_ROOMS_EMPTY ="No se ha encontrado ninguna habitacion disponible";
    public static final String ARRAY_EMPTY ="No se ha encontrado ningun";

    //Login Error messages
    public static final String SIG_IN_ERROR = "No ha iniciado session";
    public static final String LOG_OUT_ERROR = "Error al cerrar la session, intente de nuevo";

    // PARAMETERS ERRORS
    public static final String PARAMETER_NOTFOUND = "No se ha encontrado el parametro";
    public static final String PARAMETER_EMPTY = "El parametro viene vacio.";
    public static final String OPTION_INCORRET = "type incorrecto";


    //login succes messages
    public static final String LOG_OUT_SUCCESS = "Sesion Cerrada";
    //Patient Erros Messages
    public static final  String SQL_NOT_PATIETN = "No se econtraron pacientes por favor intente de nuevo";
    public static final  String SQL_UPDATE_ERROR_PATIENT = "No se pudo actualizar el usuario por favor intente de nuevo";
    public static final  String SQL_NOT_PATIETNWAIT = "No hay ningun paciente esperando por una habitacion";


    //appointments erros messages
    public static final String ERROR_APPOINTMENT_CONTROLLER ="error, peticion fallida";
    public static final  String SQL_UPDATE_ERROR_APPOINTMENT = "No se pudo actualizar la cita por favor intente de nuevo";

    //ARRAY EMPTY
    public static final String PATIENT_ROOM_ERROR ="Error, paciente no tiene ningua habitacion";





}

