import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {Team} from '../models/team.interface'
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';

@Injectable()
export class TeamService {

  constructor(private http:Http ) { }

  draftStarted(){
    let headers = new Headers({ 'content-type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get('/api/checkIfDraftHasStarted',options)
      .map(response => {
        return response.json();
      });
  }

  getTeams(){
    let headers = new Headers({ 'content-type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get('/api/getTeams',options)
      .map(response => {
        return response.json();
      });
  }

  getLocalTeam(){
    let headers = new Headers({ 'content-type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get('/api/getTeams',options)
      .map(response => {
        return response.json();
      });
  }

  setLocalTeam(theTeam: Team){
    let headers = new Headers({ 'content-type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post('/api/setLocalTeam',theTeam ,options)
      .map(response => {
        return response.json();
      });
  }

}
