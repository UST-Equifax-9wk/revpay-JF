import { Routes } from '@angular/router';

import { AccountCreationFormComponent } from './account-creation-form/account-creation-form.component';
import { AccountComponent } from './account/account.component';
import { CardFormComponent } from './card-form/card-form.component';
import { DepositComponent } from './deposit/deposit.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { SendComponent } from './send/send.component';
import { TransactionHistoryComponent } from './transaction-history/transaction-history.component';

export const routes: Routes = [
    { path: '', component: LandingComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegistrationFormComponent },
    { path: 'homepage', component: HomepageComponent},
    { path: 'create-account', component: AccountCreationFormComponent},
    { path: 'account/:id', component: AccountComponent,
        children: [
            {
            path: 'create-card',
            component: CardFormComponent
            },
            {
            path: 'deposit',
            component: DepositComponent
            },
            {
            path: 'send',
            component: SendComponent
            },
            {
            path: 'transaction-history',
            component: TransactionHistoryComponent
            }
        ]
        },
    
];
