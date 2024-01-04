import { CommonModule } from '@angular/common';

import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CurrentUserService, Transaction } from '../current-user.service';
@Component({
  selector: 'app-transaction-history',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './transaction-history.component.html',
  styleUrl: './transaction-history.component.sass'
})
export class TransactionHistoryComponent implements OnInit {
  accountId: number;
  transactions: Transaction[];

  constructor(private currentUserService: CurrentUserService, private route: ActivatedRoute, private router: Router) {
    this.accountId = 0;
    this.transactions = [];
  }

  ngOnInit() {
    this.transHist();
  }

  transHist() {
    this.route.paramMap.subscribe(params => {
      this.accountId = Number(params.get('id'));
    });
    const username = document.cookie.split(';')[0].split('=')[1];
    this.currentUserService.getCurrentUser(username).subscribe({
      next: (response) => {
        let user = JSON.parse(JSON.stringify(response.body ? response.body : []));
        console.log(user);
        user.accounts[0].transactionsIn.forEach((transaction: Transaction) => {
          this.transactions.push(JSON.parse(JSON.stringify(transaction))) ;
          })
        /*user.accounts.find((account: { accountId: number; }) => account.accountId == this.accountId). .forEach((transaction: Transaction) => {
          this.transactions.push(JSON.parse(JSON.stringify(transaction))) ;
          })*/
        console.log(this.transactions);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
}
