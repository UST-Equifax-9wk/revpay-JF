import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { NewUserDto, RemoteService } from '../remote.service';

@Component({
  selector: 'app-registration-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './registration-form.component.html',
  styleUrl: './registration-form.component.sass'
})
export class RegistrationFormComponent {

  remoteService: RemoteService;

  constructor(remoteService: RemoteService, private router: Router) {
    this.username = "";
    this.password = "";
    this.email = "";
    this.phoneNumber = "";
    this.remoteService = remoteService;
  }

  username: string;
  password: string;
  email: string;
  phoneNumber: string;

  submitRegistration() {
    let newUser: NewUserDto = {
      user: {
        username: this.username,
        email: this.email,
        phoneNumber: this.phoneNumber
      },
      auth: {
        username: this.username,
        password: this.password
      }
    }
    this.remoteService.registerNewUser(newUser)
    .subscribe({
      next: (response) => {
        console.log(response);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      }
    })
    this.router.navigate(['/login']);
  }
    handleError(error: HttpErrorResponse) {
  }
}
