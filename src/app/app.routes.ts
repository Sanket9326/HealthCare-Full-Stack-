import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ForgotPassComponent } from './forgot-pass/forgot-pass.component';

export const routes: Routes = [
    {
        path :'',
        component : HomeComponent,
        pathMatch : 'full'
    },
    {
        path: 'login',
        component : LoginComponent
    },
    {
        path :'signup',
        component : SignUpComponent
    },
    {
        path : 'forgotPass',
        component : ForgotPassComponent
    },
    {
        path : 'user',
        component : UserComponent
    },
    {
        path : 'admin',
        component : AdminComponent
    },
    {
        path : '**',
        component : HomeComponent
    }
];
