import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CredentialsServiceService } from '../services/credentials-service.service';
import { NgIf, NgFor } from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-user',
  standalone: true,
  imports: [NgIf, RouterModule, NgFor],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit {

  flag: boolean = false;

  constructor(private http: HttpClient, private service: CredentialsServiceService) { }

  credentials: any = this.service.getCredentials();

  url: string = "http://localhost:8080/appointment"

  allData: any;

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/details/get').subscribe(
      (response) => {
        this.allData = response;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getAppointmentData(doctor: string, availableTime: string, appointmentDate: string, appointmentTime: string) {
    const obj = {
      username: this.credentials.username,
      appointmentDate: appointmentDate,
      appointmentTime: appointmentTime,
      doctorName: doctor,
      availableTime: availableTime
    };
    this.http.put(this.url + "/add", obj).subscribe(
      (response) => {
        this.flag = true;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  records: any;
  histroyController: boolean = false;

  getRecords() {
    if (this.histroyController) {
      this.histroyController = false;
    } else {
      this.http.get<any[]>(`${this.url}/history?username=${this.credentials.username}`).subscribe(
        (response) => {
          this.records = response;
          this.histroyController = true;
        },
        (error) => {
          console.log('Error:', error);
        }
      );
    }
  }

  appointmentCancelController: boolean = false;
  appointments: any;

  getCurrentAppointments() {
    if (this.appointmentCancelController) {

      this.appointmentCancelController = false;
      this.appointments = null;

    } else {

      this.http.get<any[]>(this.url + `/current?username=${this.credentials.username}`).subscribe(

        (response) => {
          this.appointments = response;
          this.appointmentCancelController = true;
        },
        (error) => {
          console.log('Error:', error);
        }

      );

    }
  }
  cancelAppointment(Id: number, name: string, date: string, time: string) {
    this.http.delete(this.url + `/cancel?appointmentId=${Id}&doctorName=${name}&date=${date}&time=${time}&username=${this.credentials.username}`).subscribe(
      (response) => {
        this.http.get<any[]>(this.url + `/current?username=${this.credentials.username}`).subscribe(
          (response) => {
            this.appointments = response;
            this.appointmentCancelController = true;
          },
          (error) => {
            console.log('Error:', error);
          }
        );
      },
      (error) => {
        console.log('Error:', error);
      }
    );
  }

}

