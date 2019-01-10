import {HttpHeaders} from "@angular/common/http";

export class Headers {

  public static HeaderJSON() : HttpHeaders {
    let headers = new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Observe': 'response'
    });

    return headers;
  }
}
