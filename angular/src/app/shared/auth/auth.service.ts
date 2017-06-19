import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Headers}  from '@angular/http';

import {Observable} from 'rxjs/Observable'
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/of';

export class IUser {
    username: string;
    firstName: string;
    lastName: string;
}

@Injectable()
export class AuthService {
    public static STORAGE_PROPERTY_KEY:string = "currentUser";

    public currentUser: IUser = null;
    public authenticating: boolean = false;

    constructor(private http: Http) {}

    login(username: string, password: string): Observable<any> {
        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');        
        
        // login
        return this.http.post('/api/login', "username=" + username + "&password=" + password, {headers : headers})
            .map(res => {
                let json = res.json();
                sessionStorage.setItem(AuthService.STORAGE_PROPERTY_KEY, JSON.stringify(json));
                return json;
            });
    }

    logout(): Observable<any> {
        return this.http.post('/api/logout', {}).do(_ => {
            sessionStorage.removeItem(AuthService.STORAGE_PROPERTY_KEY);
        });
    }

    isAuthenticated(): boolean {
        return !!sessionStorage.getItem(AuthService.STORAGE_PROPERTY_KEY);
    }
    getCurrentUser() {
        return JSON.parse(sessionStorage.getItem(AuthService.STORAGE_PROPERTY_KEY));
    }
    checkAuth(): Observable<boolean> {
        return this.http.get('/api/users/current')
            .map(res => {
                return res.status === 200;
            }).catch(err => {
                if(err.status === 401) {
                    sessionStorage.removeItem(AuthService.STORAGE_PROPERTY_KEY);
                }
                return Observable.of(false);
            });
        
    }
}

