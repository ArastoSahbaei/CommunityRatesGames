import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../../shared/service/api.service";


@Component({
  selector: 'app-userdialog',
  templateUrl: './userdialog.component.html',
  styleUrls: ['./userdialog.component.css']
})
export class UserdialogComponent implements OnInit {

  public userDetails: FormGroup;

  constructor(public dialogRef: MatDialogRef<UserdialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: object,
              private fb: FormBuilder,
              private api: ApiService) {

    this.userDetails = new FormGroup({
      'name': new FormControl('', [Validators.required]),
      'mail': new FormControl('', [Validators.required, Validators.email]),
      'action': new FormControl('', Validators.required)
    });

  }

  ngOnInit() {
    this.api.getUserDetails(this.data["user"]).subscribe(
      response =>
        this.userDetails.patchValue({
          name: response["userName"],
          mail: response["email"]
        }),
      )
  }
  onSubmit(){
//    this.api.getUserDetails()
    console.log(this.userDetails.value);
  }

  get mail() {
    return this.userDetails.get('mail');
  }

  get name() {
    return this.userDetails.get('name');
  }

}
