import { Component, OnInit } from '@angular/core';
import { ApiService } from "../../../shared/service/api.service";
import { FormControl } from "@angular/forms";
import { SearchuserService } from "../../../shared/service/searchuser.service";

@Component({
  selector: 'app-userstatistic',
  templateUrl: './userstatistic.component.html',
  styleUrls: ['./userstatistic.component.css', '../../user/edituser/edit-user.component.css']
})
export class UserStatisticComponent implements OnInit {

  private loginsPerMonth : number[] = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  private loginsPerWeekday : number[] = [0, 0, 0, 0, 0, 0, 0];
  name: string = "None selected";
  private userSelected: string = "";
  private month: number;
  private day: number;

  public totalLogins:any = {
    scaleShowVerticalLines: false,
    legend: { display: false },
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    }
  };

  public totalLabels:string[] = ['Antal Inloggningar'];
  public totalType:string = 'bar';
  public totalLegend:boolean = true;
  public totalData: any[] = [
    {data: [1]}
  ];
  private totalAmountOfLogins: number;

  public search: FormControl = new FormControl();
  public usersFound = <object>[];

  public monthlyLogins:any = {
    scaleShowVerticalLines: false,
    legend: { display: false },
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    }
  };

  public monthlyLabels:string[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
  public monthlyType:string = 'bar';
  public monthlyLegend:boolean = true;
  public monthlyData: any[] = [
    {data: this.loginsPerMonth}
  ];

  public weeklyLogins:any = {
    scaleShowVerticalLines: false,
    legend: { display: false },
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    }
  };

  public weeklyLabels:string[] = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
  public weeklyType:string = 'bar';
  public weeklyLegend:boolean = true;
  public weeklyData: any[] = [
    {data: this.loginsPerWeekday}
  ];

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
    let clone = JSON.parse(JSON.stringify(this.totalData));

    clone[0].data = newData;
    clone[0].label = this.userSelected;

    this.totalData = clone;
  }

  yearlyOverview() {
    this.monthlyData =  JSON.parse(JSON.stringify(this.loginsPerMonth));
    this.weeklyData = JSON.parse(JSON.stringify(this.loginsPerWeekday));
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
      this.totalAmountOfLogins = response;
      this.logins(this.totalAmountOfLogins)
    });

    this.api.getAllStatisticOnAUser(user).subscribe((response) => {
      response.forEach(res => {
        let date = new Date(res.recieved);
        this.loginsPerMonth[this.month = date.getMonth()] += 1;
        this.loginsPerWeekday[this.day = date.getDay()] += 1;
        this.yearlyOverview();
        this.yearlyOverview();
      })
    });

  }
}
