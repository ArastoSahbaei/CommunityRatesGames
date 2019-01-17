import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../../shared/service/api.service";
import {User} from "../../../shared/interface/user.interface";


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
    const user = {} as User;

    user.email = this.userDetails.value.mail;
    user.username = this.userDetails.value.name;

    if ( this.userDetails.value.action == 1 ) {
      this.api.updateUser(user).subscribe((response) => console.log(response));
    } else {
      this.api.deleteUser(user.username).subscribe((response) => console.log(response));
    }
  }

  get mail() {
    return this.userDetails.get('mail');
  }

  get name() {
    return this.userDetails.get('name');
  }

}
