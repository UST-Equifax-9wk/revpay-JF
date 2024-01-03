import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CurrentUserService, User } from '../current-user.service';

@Component({
  selector: 'app-account-creation-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './account-creation-form.component.html',
  styleUrl: './account-creation-form.component.sass'
})
export class AccountCreationFormComponent implements OnInit{

  accountName: string;
  accountType: boolean;
  balance: number;
  currentUser: User;
  


  currentUserService: CurrentUserService;

  constructor(currentUserService: CurrentUserService, private router: Router) {
    this.currentUserService = currentUserService;
    this.accountName = '';
    this.accountType = false;
    this.balance = 0;
    this.currentUser = {
      username: '',
      email: '',
      phoneNumber: '',
      id: 0
    }
  }

  ngOnInit(): void {
    const username = document.cookie.split(';')[0].split('=')[1];
    this.currentUserService.getCurrentUser(username).subscribe({
      next: (response) => {
        let user = JSON.parse(JSON.stringify(response.body ? response.body : []));
        this.currentUser.id = user.userId;
        console.log(this.currentUser);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  submitAccountCreation() {
    this.currentUserService.createAccount({
      accountName: this.accountName, accountType: this.accountType, balance: this.balance
    }, this.currentUser.id)
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
