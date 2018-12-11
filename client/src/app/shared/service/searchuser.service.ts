import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {debounceTime, map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SearchuserService {

  private allUsers;

  constructor(private api: ApiService) { }

  searchUser(user : any) {
    this.allUsers = this.api.searchUser(user).pipe(
      debounceTime(500),
      map(
        ( response: any ) => {
          return ( response.length != 0 ? response as any[] : [{"user": "No users found"} as any] );
        }
      )
    );

    return this.allUsers;
  }
}
