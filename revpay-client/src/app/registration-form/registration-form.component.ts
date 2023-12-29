import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RemoteService } from '../remote.service.spec';

@Component({
  selector: 'app-registration-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './registration-form.component.html',
  styleUrl: './registration-form.component.sass'
})
export class RegistrationFormComponent {

  remoteService: RemoteService;

  constructor(remoteService: RemoteService) {
    this.username = "";
    this.password = "";
    this.email = "";
    this.phone = "";
    this.remoteService = remoteService;
  }

  username: string;
  password: string;
  email: string;
  phone: string;

  submitRegistration() {
    this.remoteService.registerNewUser({username: this.username, password: this.password, email: this.email, phone: this.phone})
    .subscribe({
      next: (response) => {
        console.log(response);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      }
    })
  }
    handleError(error: HttpErrorResponse) {
  }
}
