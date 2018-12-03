import {ErrorHandler, Injectable, Injector, NgZone} from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class GlobalErrorHandlingService implements ErrorHandler{

  private router: Router;
  constructor(private injector : Injector,
              private ngZone: NgZone) {}
  /*
  error.status gets the code.
  error.message gets the errormessage
   */

  handleError(error: any) {
    if (error.status == 404 ) {
      this.router = this.injector.get(Router);
      this.ngZone.run(() => {this.router.navigate(['/error'], {fragment: '406'})});
    }
    else if (error.status == 406 ) {
      this.router = this.injector.get(Router);
      this.ngZone.run(() => {this.router.navigate(['/error'], {fragment: '406'})});
    }
    else if (error.status == 410 ) {
      this.router = this.injector.get(Router);
      this.ngZone.run(() => {this.router.navigate(['/error'], {fragment: '410'})});
    }
  }
}
