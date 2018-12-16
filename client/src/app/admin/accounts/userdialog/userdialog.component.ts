import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../../shared/service/api.service";
import {User} from "../../../shared/interface/user.interface";
import {finalize} from "rxjs/operators";

@Component({
  selector: 'app-userdialog',
  templateUrl: './userdialog.component.html',
  styleUrls: ['./userdialog.component.css']
})
export class UserdialogComponent implements OnInit {

  public userDetails: FormGroup;
  public isReady: boolean;
  private user: User;

  constructor(public dialogRef: MatDialogRef<UserdialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: object,
              private fb: FormBuilder,
              private api: ApiService) {

    this.api.getUserDetails(this.data["user"]).subscribe((response) => {
      console.log(response);
      this.user.username = response["userName"];
      this.user.email = response["email"];
      console.log(this.user);
    },
      finalize(() => this.isReady = true)
    );
  }

  ngOnInit() {

    this.userDetails = this.fb.group({
      'name': [console.log(this.user.username), Validators.required],
      'mail': ['Hej Hopp', [Validators.required, Validators.email]],
      'action': ['', Validators.required]
    });
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
