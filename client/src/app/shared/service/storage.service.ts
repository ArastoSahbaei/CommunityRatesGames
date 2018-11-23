import {Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private storage = new Subject<boolean>();

  watchStorage(): Observable<any> {
    return this.storage.asObservable();
  }

  setItem(key: string, data: any) {
    sessionStorage.setItem(key, data);
    this.storage.next(true);
  }

  getItem(key: string) {
   return sessionStorage.getItem(key);
  }

  removeItem(key: string) {
    sessionStorage.removeItem(key);
    this.storage.next(true);
  }
}
