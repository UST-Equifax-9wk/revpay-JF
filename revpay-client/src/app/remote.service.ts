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

    registerNewUser(newUser: NewUserDto): Observable<HttpResponse<Object>> {
        return this.httpClient.post(this.baseUrl + '/register', JSON.stringify(newUser), {
            observe: 'response',
            withCredentials: true,
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        })}

    login(authDto: AuthDto): Observable<HttpResponse<Object>> {
        return this.httpClient.post(this.baseUrl + '/login', JSON.stringify(authDto), {
            observe: 'response',
            withCredentials: true,
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        })}



    
    }



    export interface NewUserDto{
        user: UserDto;
        auth: AuthDto;
    }

    export interface AuthDto{
        username: string;
        password: string;
    }
    export interface UserDto {
        username: string;
        email?: string;
        phoneNumber?: string;
    }
