import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CurrentUserService } from '../current-user.service';


@Component({
  selector: 'app-card-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './card-form.component.html',
  styleUrl: './card-form.component.sass'
})
export class CardFormComponent implements OnInit {
  isDateInvalid: boolean = false;
  cardHolder: string;
  cardNum: string;
  cardExp: string;
  cardCvv: string;
  accountId: number;
  @Input() accountSetup!: Function;

  


  currentUserService: CurrentUserService;

  constructor(currentUserService: CurrentUserService, private router: Router, private route: ActivatedRoute) {

    this.currentUserService = currentUserService;
    this.accountId = 0;
    this.cardHolder = '';
    this.cardNum = '';
    this.cardExp = '';
    this.cardCvv = '';

  }

  ngOnInit(): void {
    if (this.route.parent) {
      this.route.parent.paramMap.subscribe(params => {
        this.accountId = Number(params.get('id'));
      });
    }
    const username = document.cookie.split(';')[0].split('=')[1];
    this.currentUserService.getCurrentUser(username).subscribe({
      next: (response) => {
        let user = JSON.parse(JSON.stringify(response.body ? response.body : []));
        const hasAccount = user.accounts.find((account: { accountId: number; }) => account.accountId == this.accountId)
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

  validateExpiryDate(): void {
    if (!this.cardExp) {
        this.isDateInvalid = false;
        return;
    }

    const inputDate = new Date(this.cardExp);
    const today = new Date();
    today.setHours(0, 0, 0, 0); // Reset time part to compare only dates

    this.isDateInvalid = inputDate <= today;
}

    submitCardCreation() {
      console.log(this.cardHolder, this.cardNum, this.cardExp, this.cardCvv);
      this.currentUserService.createCard({cardCvv: this.cardCvv,
        cardHolder: this.cardHolder,
        cardNum: this.cardNum,
        cardExp: this.cardExp,
      }, this.accountId)
        .subscribe({
          next: (response) => {
            this.router.navigate(['../'], { relativeTo: this.route });
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        });

  }
}
