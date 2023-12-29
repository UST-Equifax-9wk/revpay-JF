import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RemoteService } from '../remote.service.spec';

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

    constructor(remoteService: RemoteService) {
        this.remoteService = remoteService;
        this.username = '';
        this.password = '';
    }

    submitLogin(){
      this.remoteService.login({username: this.username, password: this.password})
      .subscribe({
        next: (response) => {
          console.log(response);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
        }
      });
      }
    }
