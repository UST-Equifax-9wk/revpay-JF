import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CurrentAccount, CurrentUserService } from '../current-user.service';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.sass'
})
export class HomepageComponent implements OnInit {
  email: string;
  phoneNumber: string;
  id: number;
  username: string;
  accounts: CurrentAccount[];



  constructor(private currentUserService: CurrentUserService) {
    this.email = "";
    this.phoneNumber = "";
    this.id = 0;
    this.username = "";
    this.accounts = [];
  }

  ngOnInit() {
    const username = document.cookie.split(';')[0].split('=')[1];
    this.currentUserService.getCurrentUser(username).subscribe({
      next: (response) => {
        let user = JSON.parse(JSON.stringify(response.body ? response.body : []));
        console.log(user);
        this.email = user.email;
        this.phoneNumber = user.phoneNumber;
        this.id = user.userId;
        this.username = user.username;
        console.log(this.id);
        user.accounts.forEach((account: CurrentAccount) => {
          this.accounts.push(JSON.parse(JSON.stringify(account)))
        })
        console.log(this.accounts);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }


  }

