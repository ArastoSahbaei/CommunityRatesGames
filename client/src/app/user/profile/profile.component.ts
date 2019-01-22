import { Component, OnInit } from '@angular/core';
import {StorageService} from '../../shared/service/storage.service';
import {UrlService} from '../../shared/service/url.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  private uname: string;
  private regdate: Date;
  private avatar: string;

  constructor(private storage: StorageService, private url: UrlService) {
    this.uname = storage.getItem('name');
    this.regdate = new Date(null);
    this.avatar = url.getBaseUrl() + url.getUser() + url.getAvatar() + "/" + this.uname;
    this.regdate.setSeconds(Number(storage.getItem('userCreated')));
  }

  ngOnInit() {
  }

}
