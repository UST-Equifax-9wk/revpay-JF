import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink, RouterOutlet } from '@angular/router';
import { CurrencyFormatDirective } from '../currency-format.directive';
import { CurrentAccount, CurrentUserService } from '../current-user.service';

@Component({
  selector: 'app-send',
  standalone: true,
  hostDirectives: [CurrencyFormatDirective],
  imports: [CommonModule, FormsModule, RouterLink, RouterOutlet],
  templateUrl: './send.component.html',
  styleUrl: './send.component.sass'
})
export class SendComponent {
  balance: number;
  accountId: number;
  currentUserService: CurrentUserService;
  sendingAccount: CurrentAccount;
  receivingAccount: CurrentAccount;
  recipient: string;

  constructor(private route: ActivatedRoute, private router: Router, currentUserService: CurrentUserService) {
    this.balance = 0;
    this.accountId = 0;
    this.recipient = '';
    this.currentUserService = currentUserService;
    this.sendingAccount = {
      accountName: '',
      accountType: false,
      balance: 0,
      accountId: 0,
    }
    this.receivingAccount = {
      accountName: '',
      accountType: false,
      balance: 0,
      accountId: 0,
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
        this.sendingAccount = user.accounts.find((account: { accountId: number; }) => account.accountId == this.accountId)
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

  widthdraw(): void {
    this.currentUserService.withdraw(this.accountId, {
      accountName: this.sendingAccount.accountName, accountType: this.sendingAccount.accountType, balance: Number(this.sendingAccount.balance) - Number(this.balance)
    }).subscribe({
      next: (response) => {
        console.log(response);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  deposit(): void {
    this.currentUserService.deposit(this.receivingAccount.accountId, {
      accountName: this.receivingAccount.accountName, accountType: this.receivingAccount.accountType, balance: Number(this.receivingAccount.balance) + Number(this.balance)
    }).subscribe({
      next: (response) => {
        console.log(response);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  insideSenderandReceiver(): void {
    this.widthdraw();
    this.deposit();
    console.log(this.receivingAccount);
      this.currentUserService.send({amount: this.balance, senderAccountId: this.sendingAccount.accountId, receiverAccountId: this.receivingAccount.accountId} ).subscribe({
      next: (response) => {
        console.log(response);
        this.router.navigate(['../'], {relativeTo: this.route, queryParamsHandling: 'preserve'});
      },
      error: (error) => {
        console.log(error);
      }
    });
  }



  send(): void {
    console.log(this.sendingAccount);
    this.currentUserService.findRecipient(this.recipient).subscribe({
      next: (response) => {
        let user = JSON.parse(JSON.stringify(response.body ? response.body : []));
        console.log(user.accounts[0].accountId);
        this.receivingAccount = user.accounts[0];
        this.insideSenderandReceiver();
      },
      error: (error) => {
        console.log(error);
      }
    });

  }
}
