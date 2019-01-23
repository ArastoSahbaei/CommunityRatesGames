import { Component, OnInit } from '@angular/core';
import {StorageService} from '../../shared/service/storage.service';
import {UrlService} from '../../shared/service/url.service';
import {ApiService} from '../../shared/service/api.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  private uname: string;
  private regdate: Date;
  private avatar: string;

  constructor(private storage: StorageService, private api: ApiService, private url: UrlService) {
    this.uname = storage.getItem('name');
    this.regdate = new Date(null);
    this.avatar = this.url.getBaseUrl() + this.url.getUser() + this.url.getAvatar() + "/" + this.uname;
    this.regdate.setSeconds(Number(storage.getItem('userCreated')));
  }

  changeAvatar(avatar) {
    //console.log(avatar);
    let reader = new FileReader();
    reader.readAsArrayBuffer(avatar.target.files[0]);
    reader.onload = event => {
      console.log(event);
      let target = <FileReader>event.target;
      this.api.uploadAvatar(target.result).subscribe(
        response => {
          console.log(response);
          this.avatar = null;
          this.avatar = this.url.getBaseUrl() + this.url.getUser() + this.url.getAvatar() + "/" + this.uname;
        }, error => {
          console.log(error);
        }
      );
      //this.api.uploadImage(new FileReader().readAsArrayBuffer(avatar.target.files[0]));
    };
  }

  deleteAvatar() {
    this.api.deleteAvatar().subscribe(
      response => {
        console.log(response);
        this.avatar = null;
        this.avatar = this.url.getBaseUrl() + this.url.getUser() + this.url.getAvatar() + "/" + this.uname;
      }, error => {
        console.log(error);
      }
    );
  }

  ngOnInit() {
  }

}
