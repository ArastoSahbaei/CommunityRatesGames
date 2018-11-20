import {HttpHeaders} from "@angular/common/http";

export class Headers {

  public static HeaderJSON() : HttpHeaders {
    let headers = new HttpHeaders();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    headers.append('observe' , 'response');

    return headers;
  }
}
