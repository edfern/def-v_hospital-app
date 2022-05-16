import { Component, Host, h, State, Method } from '@stencil/core';
import Swal from 'sweetalert2';
import { API } from '../../global/resources';

@Component({
  tag: 'app-meeting',
  styleUrl: 'app-meeting.css',
  shadow: true,
})
export class AppMeeting {
  private URL:string = API;
  @State() loadingGetMeetings:boolean = true;
  @State() loadingGetMeetingsGuest:boolean = true;
  @State() stateDataMeetings:boolean;
  @State() stateDataPatient:boolean;
  @State() stateDataGuest:boolean;
  @State() dataTableMeeting:any;
  @State() dataTableMeetingGuest:any;
  @State() messageHTML:HTMLElement;
  @State() searchElement:HTMLElement;
  @State() tableMeeting:HTMLElement;
  @State() formMeetings:HTMLElement;
  @State() tablePatient:HTMLElement;
  @State() tableGuest:HTMLElement;
  @State() formEditMeeting:HTMLElement;
  @State() type:string;
  @State() dataTablePatient:any;
  @State() optionNew:boolean;
  @State() optionEdit:boolean;




  /**
   * Se llama cada vez que el componente está conectado al DOM. 
   * Cuando el componente se conecta por primera vez, este método se llama antes componentWillLoad.
   */
  connectedCallback(){
    this.getMeetengs()
  }





  /**
   * Metodo ASYNC obtiene las citas mas recientes Type: Patients
   * de la base de datos
   */
  @Method()
  async getMeetengs(){
    let data = await fetch(this.URL+'/appointment?'+new URLSearchParams({
      type:"appointments"
    })).then(resp =>{
      return resp.json();
    }).then(data =>{
      return data;
    }).catch(error =>{
      this.loadingGetMeetings = false;
      console.log(error)
    })
    if(data.status){
      this.getMeetengsGuest()
      this.loadingGetMeetings = false;
      this.dataTableMeeting = data.list
      this.stateDataMeetings = true;
    }else{
      this.loadingGetMeetings = false;
      this.stateDataMeetings = false;
      this.messageHTML = <div>{data.message}</div>
    }
  }






  /**
   * Obtiene todas las citas mas recientes Type: Guest
   */
  @Method()
  async getMeetengsGuest(){
    let data = await fetch(this.URL+'/guestAppointment?'+new URLSearchParams({
      type:'guestAppointments'
    })).then(resp =>{
      return resp.json();
    }).then(data =>{
      return data;
    }).catch(error =>{
      this.loadingGetMeetingsGuest = false;
      console.log(error)
    })
    if(data.status){
      this.loadingGetMeetingsGuest = false;
      this.dataTableMeetingGuest = data.object
      this.stateDataGuest = true;
    }else{
      this.loadingGetMeetingsGuest = false;
      this.stateDataGuest = false;
      this.messageHTML = <div>{data.message}</div>
    }
  }





  /**
   * Este metodo obtiene la informacion de la busqueda 
   * realizada por el componente <app-search>
   * por medio de la funcion dataSearch()
   * @param status cambia de estado el componente searchPatient
   */

  openSearch(status){
    if(status){
      this.openSearchPatient(false)
      this.searchElement = <app-search textViewInput="search by name or surname" textInfoInput="Warning! If you want to search for an appointment, you can only search by the patient's first or last name." type="meetings" returnData={(e)=>this.dataSearch(e)} closeComponent={(e)=>this.openSearch(e)}></app-search>
    }else{
      window.scrollTo(0,0)
      this.searchElement = null;
      this.tableMeeting = null;
      this.tablePatient = null;
      this.stateDataPatient = false;
    }
  }







  /**
   * Este metodo cambia el estado al componente searchElement
   * el componente <app-search>
   * @param status cambia el estado del componente searchElement()
   * si es true muestra el compoenente en el DOM
   * si es false lo remueve del DOM.
   */

  openSearchPatient(status:boolean){
    if(status){
      this.openSearch(false)
      this.searchElement = <app-search textViewInput="Juan Perez" textInfoInput="Search by first name, last name, DPI or patient ID." type="patients" returnData={(e)=>this.getDataSearchPatient(e)} closeComponent={(e)=>this.openSearchPatient(e)}></app-search>
    }else{
      this.searchElement = null;
      this.tableMeeting = null;
      this.formMeetings = null;
      this.tablePatient = null;
      this.stateDataPatient = false;
    }
  }






  /**
   * Este metodo cambia el estado al componente searchElement
   * @param status controla el estado del componente searchElement
   * si es true muestra el compoenente en el DOM
   * si es false lo remueve del DOM.
   */

  openSearchGuest(status:boolean){
    if(status){
      this.searchElement = <app-search textViewInput="Juan Perez" textInfoInput="I searched for the patient's name please." closeComponent={(e)=>this.openSearchGuest(e)} ></app-search>
    }else{
      this.searchElement = null;
    }
  }








  /**
   * Este metodo muestra una alerta al usuario 
   * si @param result isConfimed 
   * realiza el metodo openSearchPatient(true) y cambia el estado del 
   * componente tableMeeting y del State type
   * y si @param result isDenied realiza el metodo openSearchPatient(false)
   */
  openNewMeeting(){
    this.optionNew = false;
    Swal.fire({
      icon:'question',
      title: 'Select one',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: `Patient`,
      denyButtonText: `Guest`,
      denyButtonColor: '#3099d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.tableMeeting = null;
        this.openSearchPatient(true);
        this.type = "patient";
      } else if (result.isDenied) {
        this.tableMeeting = null;
        this.optionNew = true;
        this.type = "guest";
        this.openSearchPatient(false);
      }else if( result.dismiss){
        if(this.formMeetings){
          this.optionNew = true
        }
      }
    }).then(()=>{
      if(this.optionNew){
        this.formMeetings =  <app-meeting-new type={this.type} close={(e)=>this.closeFormNewMeeting(e)}></app-meeting-new>
      }
    })  
  }




  /**
   * Muestra una alerta al usuario y le pregunta que tipo quiere elegir
   * si @param result isConfirmed llama a la funcion openSearchPatient y eso despliega
   * el DOM el compoenente <app-search>
   * si @param result isDenied llama al metodo openSearchGuest
   */

  openFormEdit(){
    Swal.fire({
      icon:'question',
      title: 'Select one',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: `Patient`,
      denyButtonText: `Guest`,
      denyButtonColor: '#3099d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.tableMeeting = null;
        this.openSearchPatient(true);
        this.openSearchGuest(false);
        this.type = "patient";
      } else if (result.isDenied) {
        this.tableMeeting = null;
        this.optionNew = true;
        this.type = "guest";
        this.openSearchPatient(false);
        this.openSearchGuest(true);
        this.tablePatient = null;
      }else if( result.dismiss){
        if(this.formMeetings){
          this.optionNew = true
        }
      }
    })
    
  }







  /**
   * Metodo que cambia el estado del HTMLElement a null
   * @param b stado para cambiar el estado del componente
   */
  closeFormNewMeeting(b){
    if(!b){
      this.optionEdit = false;
      this.formMeetings = null;
      this.getMeetengsGuest()
    }
  }








/**
 * Metodo que despliega la informacion en el DOM por medio una tabla
 * @param data informacion obtenida del componente <app-search>
 */

  getDataSearchPatient(data){
    if(data){
      this.dataTablePatient = data;
      this.tablePatient =
      <div class="row">    
        <div class="col-12 mb-4 align-items-stretch">
          <div class="card h-100 border-0 rounded-0">
            <div class="card-title mb-1 p-3 d-flex">
              <h5>Result of your search</h5>
            </div>
            <div class="card-body">
              <div class="table-resposive-mb" id="table-customer-margin">
                <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                      <tr>
                        <th>Name</th>
                        <th>Sur Name</th>
                        <th>Address</th>
                        <th>Birthday</th>
                        <th>DPI</th>
                        <th>Nit</th>
                        <th>Phone</th>
                        <th>State</th>
                      </tr>
                    </thead>
                    <tbody class="no-border-x">
                    {this.dataTablePatient.map( patient =>(
                        <tr onClick={()=>this.viewFormNewPatient(patient,true)}>
                          <td>{patient.fullName}</td>
                          <td>{patient.surName}</td>
                          <td>{patient.address}</td>
                          <td>{patient.birthday}</td>
                          <td>{patient.dpi}</td>
                          <td>{patient.nit}</td>
                          <td>{patient.phone}</td>
                          {patient.statePatient?<td class="text-success">Joined</td>:<td class="text-danger">Discharged</td>}
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    }
  }







  /**
   * Muestra el resultado de la busqueda por medio del componente <app-search>
   * @param data informacion de la busqueda
   */
  dataSearch(data){
    if(data){
      this.tableMeeting = 
      <div class="row">    
        <div class="col-12 mb-4 align-items-stretch">
          <div class="card h-100 border-0 rounded-0">
            <div class="card-title mb-1 p-3 d-flex">
              <h5>Result of your search</h5>
            </div>
            <div class="card-body">
              <div class="table-resposive-mb" id="table-customer-margin">
                <div class="table-responsive">
                <table class="table table-hover text-center">
                <thead>
                  <tr>
                    <td>ID Patient</td>
                    <td>ID Clinic</td>
                    <td>Date</td>
                    <td>Time</td>
                    <th></th>
                  </tr>
                </thead>
                <tbody class="no-border-x">
                  {data.map( meeting =>(
                    <tr>
                      <td class="text-success">{meeting.idPatient}</td>
                      <td class="text-success">{meeting.idClinic}</td>
                      <td class="text-success">{meeting.dateAppointment}</td>
                      <td class="text-success">{meeting.timeAppointment}</td>
                      <td><button class="btn btn-outline-warning" onClick={()=>this.viewFormEditPatient(meeting,true)}><i class="far fa-edit"></i></button></td>
                    </tr>
                  ))}
                </tbody>
              </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    }
  }






  /**
   * Metodo que muestra en el DOM el formulario para editar la informacion de la cita
   * @param data informacion necesaria para realizar un update o un delete
   * @param status Despliega o remueve el componente formEditMeeting
   */
  viewFormEditPatient(data,status:boolean){
    if(status){
      this.optionEdit = true;
      this.formEditMeeting = <app-meeting-new type="patient" close={(e,b)=>this.viewFormEditPatient(e,b)} editMeeting={true} data={data}></app-meeting-new>
    }else{
      this.openSearchPatient(false);
      this.formEditMeeting = null;
      this.getMeetengs();
    }
  }






  /**
   * Metodo que muestra en el DOM formulario para nueva cita de un paciente
   * @param data informacion necesaria para generar una nueva cita a un paciente
   * @param status Despliega o remueve el componente formMeetings
   */

  viewFormNewPatient(data,status:boolean){
    if(status){
      this.optionNew = true;
      this.formMeetings = <app-meeting-new type="patient" idPatient={data.idPatient} close={(e,b)=>this.viewFormNewPatient(e,b)}></app-meeting-new>
    }else{
      this.optionNew = false
      this.getMeetengs();
      this.openSearchPatient(false);
    }
  }





  
  /**
   * Metodo que muestra en el DOM el formulario para editar la informacion de la cita de un visitante
   * @param data informacion necesaria para hacer un update o un delete
   * @param status Despliega o remueve el componente formEditMeeting
   */

  viewFormEditGuest(data,status:boolean){
    if(status){
      this.optionEdit = true;
      this.formEditMeeting = <app-meeting-new type="guest" close={(e,b)=>this.viewFormEditGuest(e,b)} editMeeting={true} data={data} ></app-meeting-new>
    }else{
      this.formEditMeeting = null;
      this.optionEdit = false;
      this.getMeetengsGuest()
    }
  }
  
  render() {


    let tableMeetings = null;
    let tableGuest = null;
    if(this.stateDataMeetings){
      tableMeetings = 
      <div class="table-responsive-md">
      <div class="table-responsive">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>Patient</th>
              <th>Clinic</th>
              <th>Date</th>
              <th>Hour</th>
            </tr>
          </thead>
          <tbody class="no-border-x">
          {this.dataTableMeeting.map( meeting =>(
              <tr>
                <td  class="text-info">{meeting.namePatient}</td>
                <td>{meeting.nameClinic}</td>
                <td class="text-info">{meeting.dateAppointment}</td>
                <td>{meeting.timeAppointment}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
    }
    if(this.stateDataGuest){
      tableGuest = 
      <div class="table-responsive-md">
      <div class="table-responsive">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>Name</th>
              <th>Address</th>
              <th>Date</th>
              <th>Hour</th>
              <th></th>
            </tr>
          </thead>
          <tbody class="no-border-x">
          {this.dataTableMeetingGuest.map( meeting =>(
              <tr>
                <td class="text-info">{meeting.fullName+" "+meeting.surName}</td>
                <td>{meeting.address}</td>
                <td class="text-info">{meeting.date}</td>
                <td>{meeting.hour}</td>
                <td><button class="btn btn-outline-info" onClick={()=>this.viewFormEditGuest(meeting,true)}><i class="far fa-edit"></i></button></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
    }

    let loading =<div id="loading">
    <div class="spinner-border" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div> 
    return (
      <Host>
        <h2>Meeting's</h2>
      <hr/>
      <h4 class="mb-4 text-muted">Dashboard </h4>
      <div class="row" id="operations">
        <div class="col-xl-2 mb-4">
          <div class="card border-0 rounded-0 card1 fadeInDown" onClick={()=>this.openSearch(true)}>
            <div class="card-body">
              <div class="card-icon text-light">
                <i class="fab fa-searchengin"></i>
                <h4 class="card-text text-right">Search</h4>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-2 mb-4">
          <div class="card border-0 rounded-0 card2 fadeInDown" onClick={()=>this.openNewMeeting()}>
            <div class="card-body">
              <div class="card-icon text-light">
               <i class="fas fa-file"></i>
                <h4 class="card-text text-right">New</h4>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-2 mb-4">
          <div class="card border-0 rounded-0 card3 fadeInDown">
            <div class="card-body">
              <div class="card-icon text-light">
                <i class="far fa-edit"></i>
                <h4 class="card-text text-right">Edit</h4>
              </div>
            </div>
          </div>
        </div>
      </div>
      {this.searchElement}
      <div>
      {this.tablePatient}
      {this.tableMeeting}
      {this.optionNew&&this.formMeetings}
      {this.optionEdit&&this.formEditMeeting}
      </div>
      <div class="row">    
        <div class="col-12 mb-4 align-items-stretch">
          <div class="card h-100 border-0 rounded-0">
            <div class="card-title mb-1 p-3 d-flex">
              <h5>Most recent quotes</h5>
              <a class="btn ml-auto p-0 text-lightning"> <i class="fas fa-ellipsis-h"></i> </a>
            </div>
            <div class="card-body">
            {this.loadingGetMeetings?loading:tableMeetings}
            </div>
          </div>
        </div>
      </div>
      <div class="row">    
        <div class="col-12 mb-4 align-items-stretch">
          <div class="card h-100 border-0 rounded-0">
            <div class="card-title mb-1 p-3 d-flex">
              <h5>Most recent citations from guest.</h5>
              <a class="btn ml-auto p-0 text-lightning"> <i class="fas fa-ellipsis-h"></i> </a>
            </div>
            <div class="card-body">
            {tableGuest}
            </div>
          </div>
        </div>
      </div>
      </Host>
    );
  }

}
