import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ApiService} from "../shared/service/api.service";

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  public barChartOptions:any = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels:string[] = ['Antal Inloggningar'];
  public barChartType:string = 'bar';
  public barChartLegend:boolean = true;
  public barChartData: number[] = [];
  private userData: number[] = [];
  public isDataAvailable:boolean = false;

  constructor(public dialogRef: MatDialogRef<DialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: string,
              private formBuilder: FormBuilder,
              private api: ApiService) {

    this.api.getStatisticOnAUser(this.data).subscribe((response) => {
      this.userData = response;
      this.barChartData = this.userData;
      console.log(this.barChartData);
      this.isDataAvailable = true;
    })
  }

  ngOnInit() {}

  public chartHovered(e:any):void {
    console.log(e);
  }

  public randomize():void {
    // Only Change 3 values
    let data = [
      Math.round(Math.random() * 100),
      59,
      80,
      (Math.random() * 100),
      56,
      (Math.random() * 100),
      40];
    let clone = JSON.parse(JSON.stringify(this.barChartData));
    clone[0].data = data;
    this.barChartData = clone;

  }
}
