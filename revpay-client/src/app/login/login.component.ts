import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RemoteService } from '../remote.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.sass'
})
export class LoginComponent {
    username: string;
    password: string;
    remoteService: RemoteService;

    constructor(remoteService: RemoteService, private router: Router) {
        this.remoteService = remoteService;
        this.username = '';
        this.password = '';
        let cookie = document.cookie.split(';');
        let cookiething = cookie[0].split('=');
        console.log("cookie: " + cookiething[1]);
        if(cookiething[1] != null){
          window.location.replace("homepage")
        }
    }

    submitLogin(){
      this.remoteService.login({username: this.username, password: this.password})
      .subscribe({
        next: (response) => {
          console.log(response);
          this.router.navigate(['/homepage']);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
        }
      })

      }
    }
