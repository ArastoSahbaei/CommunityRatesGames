import {HttpHeaders} from "@angular/common/http";

export class Headers {

  public static HeaderJSON(token: string) : HttpHeaders {
    let headers = new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Observe': 'response'
    });
    if (token != null) {
      headers = headers.append('Authorization', token);
    }

    return headers;
  }
}
