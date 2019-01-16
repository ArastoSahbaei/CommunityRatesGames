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
import {Voting} from "../interface/voting.interface";




@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient,
              private url: UrlService) {
  }

  getGames() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getGames(), {headers: Headers.HeaderJSON()});
  }

  searchGame(game : string) : Observable<any> {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getGames() + this.url.getSearch(),
      { headers: Headers.HeaderJSON(),
        params: { q : game}
      });
  }

  searchGameByTitle() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getSearchGameByTitle(), {headers: Headers.HeaderJSON()});
  }

  getRating() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getRating(), {headers: Headers.HeaderJSON()});
  }

  getUserDetails(user: string): Observable<any> {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getUser() + this.url.getCertainUser(),
      { headers: Headers.HeaderJSON(),
        params: { name: user }
      });
  }

  checkCredentials(body: User) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getUser() + this.url.getLogin(), body, {headers: Headers.HeaderJSON()});
  }

  registerUser(body: Register) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getUser() + this.url.getRegister(), body, {headers: Headers.HeaderJSON()});
  }

  updateUser(body: User) {
    return this.httpClient.put(this.url.getBaseUrl() + this.url.getUser() + this.url.getUpdate(), body, {headers: Headers.HeaderJSON()});
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
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getCompany(), company, { headers: Headers.HeaderJSON() });
  }

  postGame(body: AddGame) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getUnverifiedGame(), body, {headers: Headers.HeaderJSON()}
    );
  }

  postRating(body: Voting) {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getRating(), body, {headers: Headers.HeaderJSON()}
    );
  }

  getTop100(){
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getGames() + this.url.getTop100(),{headers: Headers.HeaderJSON()});
  }

  getOneGameByTitle(title: string) {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getGames() + this.url.getOneGamebyTitle(),{headers: Headers.HeaderJSON(),
      params: { title : title}});
  }

  getAllUsers() {
    return this.httpClient.get(this.url.getBaseUrl() + this.url.getUser(), {headers: Headers.HeaderJSON()});
  }

  addNewContactMessage(message: Contact) {
    return this.httpClient.post(this.url.getMongoUrl(), message);
  }

  voteGame(voting: string):Observable<any> {
    return this.httpClient.post(this.url.getBaseUrl() + this.url.getRating(),  {headers: Headers.HeaderJSON(),
     params: {voting: voting}});
  }

  reportBug(message: Report) {
    return this.httpClient.post(this.url.getMongoUrl(), message);
  }

  // For admin view that is connected to Logging EE server
  searchUser(user: string):Observable<any> {
    return this.httpClient.get(this.url.getBaseUrlLogs() + this.url.getLogs() + this.url.getSearch(),
      { headers: Headers.HeaderJSON(),
        params: { name: user }
      });
  }

  getStatisticOnAUser(user: object): Observable<any> {
    return this.httpClient.post(this.url.getBaseUrlLogs() + this.url.getLogs() + this.url.getStatistic(),
            user, {headers: Headers.HeaderJSON()
    });
  }

}








