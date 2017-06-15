import {Component, OnInit} from '@angular/core';
import { AuthService } from '../shared/auth/auth.service';
import { Router } from '@angular/router';


@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    username: string;
    password: string;
    errorMessage: string;

    constructor(private authService: AuthService, private router: Router) {}

    ngOnInit() {
    }
    doLogin() {
        this.errorMessage = "";
        
        this.authService.login(this.username, this.password).subscribe(_ => {
          this.router.navigate(['/']);
        }, err => {
            this.errorMessage = "Some weird error while login, probably password was wrong";
        });
            
    }

}
