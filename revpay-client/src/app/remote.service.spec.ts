import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'

})

export class RemoteService {
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

    registerNewUser(user: User): Observable<HttpResponse<Object>> {
        return this.httpClient.post(this.baseUrl + '/register', JSON.stringify(user), {
            observe: 'response',
            withCredentials: true,
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        })}

    login(user: User): Observable<HttpResponse<Object>> {
        return this.httpClient.post(this.baseUrl + '/login', JSON.stringify(user), {
            observe: 'response',
            withCredentials: true,
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        })}

    }

    export interface User {
        username: string;
        password: string;
        email?: string;
        phone?: string;
    }