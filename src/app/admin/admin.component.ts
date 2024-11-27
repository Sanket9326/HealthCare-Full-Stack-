import { HttpClient } from '@angular/common/http';
import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit {

  public constructor(private http: HttpClient) { }

  url: string = 'http://localhost:8080/details/'
  appointmentUrl: string = 'http://localhost:8080/appointment'
  recordsUrl: string = 'http://localhost:8080/record'

  allData: any;
  appointmentData: any;

  
  ngOnInit(): void {
    this.http.get<any[]>(this.url + 'get').subscribe(
      (response) => {
        this.allData = response;
      },
      (error) => {
        console.error(error);
      }
    );
    this.http.get<any[]>(this.appointmentUrl + '/all').subscribe(
      (response) => {
        this.appointmentData = response;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  addRecord(Id: string, username: string, doctorName: string, date: string, time: string, completed: string) {
    this.http.delete(this.appointmentUrl + `/remove?appointmentId=${Id}`, { responseType: 'text'}).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.error(error);
      }
    );

    const data = { username: username, doctorName: doctorName, date: date, time: time, completed: completed };

    this.http.post(this.recordsUrl + '/add', data, { responseType: 'text' }).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.error(error);
      }
    );
    this.ngOnInit();
  }

  addData(doctorName: string, speciality: string, availableTime: string) {
    this.http.put(this.url + `add?doctorName=${doctorName}&speciality=${speciality}&availableTime=${availableTime}`, {}).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateTime(Id: string, availableTime: string) {

    this.http.post(this.url + `update?doctorId=${Id}&availableTime=${availableTime}`, {}).subscribe(
      (response) => {
        this.ngOnInit();
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  removeDoctor(Id: string) {
    this.http.delete(this.url + `remove?doctorId=${Id}`).subscribe(
      (response) => {
        this.ngOnInit();
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
