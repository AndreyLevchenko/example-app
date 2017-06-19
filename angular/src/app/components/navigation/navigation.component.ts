import {Component, OnInit} from '@angular/core';
import { AuthService } from '../../shared/auth/auth.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
    constructor(private authService: AuthService, private router: Router){
        
    }
    ngOnInit(): void {
    }
    doLogout(): void {
        this.authService.logout().subscribe(_ => {
            this.router.navigate(['/login']);
        });
    }
}