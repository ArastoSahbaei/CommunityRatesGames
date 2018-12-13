import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { ApiService } from "../shared/service/api.service";

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  public barChartOptions:any = {
    scaleShowVerticalLines: false,
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
    {data: [1], label: "A"}
  ];
  private totalAmountOfLogins: number;

  constructor(public dialogRef: MatDialogRef<DialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: object,
              private formBuilder: FormBuilder,
              private api: ApiService) {

    this.api.getStatisticOnAUser(this.data).subscribe((response) => {
      this.totalAmountOfLogins = response;
      this.logins(this.totalAmountOfLogins)
    });
  }

  logins(login: number) {
    let newData = [
      login
    ];
    let clone = JSON.parse(JSON.stringify(this.barChartData));

    clone[0].data = newData;
    clone[0].label = this.data["user"];

    this.barChartData = clone;

  }

  ngOnInit() {}

  public chartHovered(e:any):void {
    console.log(e);
  }

  public chartColors: any[] = [
    {
      backgroundColor:["#FFFFFF"],
    }
  ];
}
