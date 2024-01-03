import { Routes } from '@angular/router';

import { AccountCreationFormComponent } from './account-creation-form/account-creation-form.component';
import { AccountComponent } from './account/account.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';


export const routes: Routes = [
    { path: '', component: LandingComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegistrationFormComponent },
    { path: 'homepage', component: HomepageComponent},
    { path: 'create-account', component: AccountCreationFormComponent},
    { path: 'account/:id', component: AccountComponent}
];
