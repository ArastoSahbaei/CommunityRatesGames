import {Injectable, OnDestroy} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {session} from "webdriver-js-extender/built/spec/mock-server/commands";
import {share} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private storage = new Subject<any>();

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
