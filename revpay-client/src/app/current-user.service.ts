import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurrentUserService {
  httpClient: HttpClient;
  baseUrl: string;
  httpOptions = {
      observe: 'response',
      headers: new HttpHeaders({
          'Content-Type': 'application/json'
  })}
  
    constructor(httpClient: HttpClient) {
      this.httpClient = httpClient;
      this.baseUrl = 'http://localhost:8080';
  }

    getCurrentUser(username: string): Observable<HttpResponse<Object>> {
      return this.httpClient.get(this.baseUrl + '/user/username/' + username, { // Add a semicolon here
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      });
    }

    getAccounts(id: number): Observable<HttpResponse<Object>> {
      return this.httpClient.get(this.baseUrl + '/account/' + id, { // Add a semicolon here
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      });
    }

    createAccount(account: Account, id: number): Observable<HttpResponse<Object>> {
      return this.httpClient.post(this.baseUrl + '/account/' + id, JSON.stringify(account), {
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      });
    }
  }

export interface User {
  username: string;
  email: string;
  phoneNumber: string;
  id: number;
}

export interface Account {
  balance: number;
  accountType: boolean;
  accountName: string;
}

export interface CurrentAccount{
  balance: number;
  accountType: boolean;
  accountId: number;
  accountName: string;
}

export interface Card {
  cardNumber: string;
  expirationDate: string;
  securityCode: string;
  id?: number;
  accountId: number;
}

  
