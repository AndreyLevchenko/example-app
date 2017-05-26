import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
    title: string = 'Dashboard Page';
    body: string = 'This is the about dashboard body';
    message: string;

    constructor() {}

    ngOnInit() {
    }

    updateMessage(m: string): void {
        this.message = m;
    }
}
