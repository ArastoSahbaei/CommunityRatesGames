import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ApiService} from "../shared/service/api.service";
import {ChartsModule, ChartService } from 'ng2charts'

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: string,
              private formBuilder: FormBuilder,
              private api: ApiService) { }

  ngOnInit() {
    console.log(this.data);
    this.api.getStatisticOnAUser(this.data).subscribe((response) => {
      console.log(response);
    })
  }

}
