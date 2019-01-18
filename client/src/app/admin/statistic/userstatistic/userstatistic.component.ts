import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../../shared/service/api.service";
import {FormControl} from "@angular/forms";
import {SearchuserService} from "../../../shared/service/searchuser.service";
import {DialogComponent} from "../../dialog/dialog.component";

@Component({
  selector: 'app-userstatistic',
  templateUrl: './userstatistic.component.html',
  styleUrls: ['./userstatistic.component.css', '../../user/edituser/edit-user.component.css']
})
export class UserStatisticComponent implements OnInit {

  name: string = "None selected";
  private userSelected: string = "";

  public barChartOptions:any = {
    scaleShowVerticalLines: false,
    legend: { position: 'right' },
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    }
  };
  public barChartLabels:string[] = ['Antal Inloggningar'];
  public barChartType:string = 'bar';
  public barChartLegend:boolean = true;
  public barChartData: any[] = [
    {data: [1], name}
  ];
  private totalAmountOfLogins: number;

  public search: FormControl = new FormControl();
  public usersFound = <object>[];

  constructor(private api: ApiService,
              private searchUser: SearchuserService) {

    this.showUser();
  }

  ngOnInit() {
  }

  showUser() {
    this.search.valueChanges.subscribe(
      user => {
        if ( user != '' ) {
          this.searchUser.searchUser(user).subscribe(
            data => {
              this.usersFound = data as any[];
            })
        }
      });
    this.search.setValue('');
  }

  logins(login: number) {
    let newData = [
      login
    ];
    let clone = JSON.parse(JSON.stringify(this.barChartData));

    clone[0].data = newData;
    clone[0].label = this.userSelected;

    this.barChartData = clone;
  }

  public chartHovered(e:any):void {
    console.log(e);
  }

  public chartColors: any[] = [
    {
      backgroundColor:["#FFFFFF"],
    }
  ];

  selectedUser(event: Event) {
    this.userSelected = event.toString();

    const user ={user: event};
    this.api.getStatisticOnAUser(user).subscribe((response) => {
      console.log(response);
      this.totalAmountOfLogins = response;
      this.logins(this.totalAmountOfLogins)
    });
  }
}
