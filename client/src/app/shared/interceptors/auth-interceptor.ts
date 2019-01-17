import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { StorageService } from "../service/storage.service";
import { Observable } from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
  constructor(private storage: StorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token: string = this.storage.getItem('token');
    if (token == null) {
      return next.handle(req);
    } else {
      return next.handle(req.clone({ headers: req.headers.set("Authorization", token)}));
    }
  }
}
