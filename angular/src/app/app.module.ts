import { AlertModule } from 'ngx-bootstrap';
import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import {AppComponent} from './app.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import {SignupComponent} from './signup/signup.component';
import {NavigationComponent} from './components/navigation/navigation.component';

import { AuthService } from './shared/auth/auth.service';
import { AuthGuard } from './shared/auth/auth.guard';


export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: DashboardComponent,
    canActivate: [AuthGuard]
  },
  { path: 'signup', component: SignupComponent, data: { title: 'Sign Up', hideNavigation : true} },
  { path: 'login', component: LoginComponent, data: { title: 'Login', hideNavigation : true} }
  
];


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    NavigationComponent,
    LoginComponent,
    SignupComponent
  ],
  imports: [
    // Angular related
    BrowserModule,
    FormsModule,
    HttpModule,

    // Routing
    RouterModule.forRoot(routes),

    // Third-party Libraries
    AlertModule.forRoot()
    
  ],
  providers: [
    AuthService,
    AuthGuard
  ],  
  bootstrap: [AppComponent]
})
export class AppModule { }