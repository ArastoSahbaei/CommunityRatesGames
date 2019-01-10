import {Injectable,Injector} from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {AuthService} from './shared/service/auth.service'

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log(req);
    var authReq;
    if (AuthService.getToken() == null) {
      return next.handle(req);
    } else {
      // I can't get the token any other way, so just do a hack to get it working.
      return next.handle(req.clone({ headers: req.headers.set("Authorization", AuthService.getToken())}));
    }
  }
}
