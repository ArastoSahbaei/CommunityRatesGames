import {AddGame} from "../interface/add-game.interface";
import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Headers} from "./headers";
import {UrlService} from "./url.service";
import {Register} from "../interface/register.interface";
import {User} from "../interface/user.interface";
import {Observable} from "rxjs";
import {Company} from "../interface/company.interface";
import {Contact} from "../interface/contact.interface";
import {Report} from "../interface/report.interface";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient,
              private url: UrlService) {
  }

  getGames() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getGames(), {headers: Headers.HeaderJSON(null)});
  }

  searchGame(game : string) : Observable<any> {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getGames() + this.url.getSearch(),
      { headers: Headers.HeaderJSON(null),
        params: { q : game}
      });
  }

  searchGameByTitle() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getSearchGameByTitle(), {headers: Headers.HeaderJSON(null)});
  }

  getRating() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getRating(), {headers: Headers.HeaderJSON(null)});
  }

  getUserDetails(user: string): Observable<any> {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getUser() + this.url.getCertainUser(),
      { headers: Headers.HeaderJSON(null),
        params: { name: user }
      });
  }

  checkCredentials(body: User) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getUser() + this.url.getLogin(), body, {headers: Headers.HeaderJSON(null)});
  }

  logout(token: number) {
    return this.httpClient.delete(this.url.getBaseUrl() + this.url.getUser() + this.url.getLogout(), {headers: Headers.HeaderJSON(token.toString())});
  }

  registerUser(body: Register) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getUser() + this.url.getRegister(), body, {headers: Headers.HeaderJSON(null)});
  }

  updateUser(body: User) {
    return this.httpClient.put(this.url.getBaseUrl() + this.url.getUser() + this.url.getUpdate(), body, {headers: Headers.HeaderJSON(null)});
  }

  deleteUser(username: string) {
    return this.httpClient.request('delete', this.url.getBaseUrl() + this.url.getUser() + this.url.getDelete(),
      {
        body: {
          username
        }
      });
  }

  createCompany(company: Company) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getCompany(), company, { headers: Headers.HeaderJSON(null) });
  }

  postGame(body: AddGame) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getGames(), body, {headers: Headers.HeaderJSON(null)}
    );
  }

  postRating(body: AddGame) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getRating(), body, {headers: Headers.HeaderJSON(null)}
    );
  }

  getTop100(){
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getGames() + this.url.getTop100(),{headers: Headers.HeaderJSON(null)});
  }

  getOneGameByTitle(title: string) {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getGames() + this.url.getOneGamebyTitle(),{headers: Headers.HeaderJSON(null),
      params: { title : title}});
  }

  getAllUsers() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getUser(), {headers: Headers.HeaderJSON(null)});
  }

  addNewContactMessage(message: Contact) {
    return this.httpClient.post(this.url.getMongoUrl(), message);
  }

  reportBug(message: Report) {
    return this.httpClient.post(this.url.getMongoUrl(), message);
  }

  // For admin view that is connected to Logging EE server
  searchUser(user: string):Observable<any> {
    return this.httpClient.get(this.url.getBaseUrlLogs() + this.url.getLogs() + this.url.getSearch(),
      { headers: Headers.HeaderJSON(null),
        params: { name: user }
      });
  }

  getStatisticOnAUser(user: object): Observable<any> {
    return this.httpClient.post(this.url.getBaseUrlLogs() + this.url.getLogs() + this.url.getStatistic(),
            user, {headers: Headers.HeaderJSON(null)
    });
  }
}
