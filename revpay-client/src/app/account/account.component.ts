import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, NavigationEnd, Router, Event as RouterEvent, RouterLink, RouterOutlet } from '@angular/router';
import { filter } from 'rxjs/operators';
import { Card, CurrentUserService } from '../current-user.service';

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule, RouterOutlet],
  templateUrl: './account.component.html',
  styleUrl: './account.component.sass'
})
export class AccountComponent{
  accountId: number;
  cards: Card[];
  hoveredIndex = -1;
  primaryCardIndex = -1;
  balance: number;



  constructor(private currentUserService: CurrentUserService, private route: ActivatedRoute, private router: Router) {
    this.accountId = 0;
    this.cards = [];
    this.balance = 0;
    
    this.router.events.pipe(
      filter((event: RouterEvent): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.route.paramMap.subscribe(params => {
        this.accountId = Number(params.get('id'));
        if (event.url === '/account/' + this.accountId) {
          this.accountSetup();
        }
      });
    });
  }


  makePrimary(index: number) {
    this.primaryCardIndex = index;

  }

  accountSetup() {
    const username = document.cookie.split(';')[0].split('=')[1];
    this.currentUserService.getCurrentUser(username).subscribe({
      next: (response) => {
        let user = JSON.parse(JSON.stringify(response.body ? response.body : []));
        console.log(user);
        this.balance = user.accounts.find((account: { accountId: number; }) => account.accountId == this.accountId).balance
        user.accounts.find((account: { accountId: number; }) => account.accountId == this.accountId).cards.forEach((card: Card) => {
          const cardExists = this.cards.some(existingCard => existingCard.cardNum === card.cardNum);
          if (!cardExists){
            this.cards.push(JSON.parse(JSON.stringify(card))) ;
            }
          })
        console.log(this.cards);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }


}
