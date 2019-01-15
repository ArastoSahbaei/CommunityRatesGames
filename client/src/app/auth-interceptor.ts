import {Injectable,Injector} from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {AuthService} from './shared/service/auth.service'
import {StorageService} from "./shared/service/storage.service";
import {Storage} from "./shared/interface/storage.interface";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
  constructor(private storage: StorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    var token: string = this.storage.getItem('token');
    if (token == null) {
      return next.handle(req);
    } else {
      return next.handle(req.clone({ headers: req.headers.set("Authorization", token)}));
    }
  }
}
