import { Component, OnInit } from '@angular/core';
import {StorageService} from '../../shared/service/storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  private uname: string;
  private regdate: Date;

  constructor(private storage: StorageService) {
    this.uname = storage.getItem('name');
    this.regdate = new Date(null);
    this.regdate.setSeconds(Number(storage.getItem('userCreated')));
  }

  ngOnInit() {
  }

}
