import {HttpHeaders} from "@angular/common/http";

export class Headers {

  public static HeaderJSON(token : string) : HttpHeaders {
    let headers = new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'observe': 'response',
    });
    if (token != null) {
      headers = headers.append('Authentication', token);
    }

    return headers;
  }
}
