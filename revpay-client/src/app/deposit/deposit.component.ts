import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink, RouterOutlet } from '@angular/router';
import { CurrencyFormatDirective } from '../currency-format.directive';
import { Account, CurrentUserService } from '../current-user.service';

@Component({
  selector: 'app-deposit',
  standalone: true,
  hostDirectives: [CurrencyFormatDirective],
  imports: [RouterLink, FormsModule, CommonModule, RouterOutlet],
  templateUrl: './deposit.component.html',
  styleUrl: './deposit.component.sass'
})
export class DepositComponent implements OnInit{

  balance: number;
  accountId: number;
  currentUserService: CurrentUserService;
  account: Account;

  constructor(private route: ActivatedRoute, private router: Router, currentUserService: CurrentUserService) {
    this.balance = 0;
    this.accountId = 0;
    this.currentUserService = currentUserService;
    this.account = {
      accountName: '',
      accountType: false,
      balance: 0,
    }
    
  }

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      this.accountId = Number(params.get('id'));
      
    });
    const username = document.cookie.split(';')[0].split('=')[1];
    this.currentUserService.getCurrentUser(username).subscribe({
      next: (response) => {
        let user = JSON.parse(JSON.stringify(response.body ? response.body : []));
        let hasAccount = user.accounts.find((account: { accountId: number; }) => account.accountId == this.accountId)
        this.account = user.accounts.find((account: { accountId: number; }) => account.accountId == this.accountId)
        this.account.balance += this.balance;
        if (hasAccount) {
          console.log('Current user has an account with the specified ID');
        } else {
          console.log('Current user does not have an account with the specified ID');
        }
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
  deposit() {
    this.currentUserService.deposit(this.accountId, {
      accountName: this.account.accountName, accountType: this.account.accountType, balance: this.account.balance
    }).subscribe({
      next: (response) => {
        console.log(response);
        this.router.navigate(['../']);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

}
