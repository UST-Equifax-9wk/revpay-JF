import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.sass'
})
export class NavbarComponent {
  constructor(private router: Router) { 

  }

logOut() {
  const date = new Date();
  const name = 'username';
  const value = document.cookie.split(';')[0].split('=')[1];
  date.setTime(date.getTime());
  const expires = `expires=${date.toUTCString()}`;
  document.cookie = `${name}=${value};${expires};path=/`;
  this.router.navigate(['/login']);
    }

}
