import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { Observable } from 'rxjs/Observable'
import 'rxjs/add/observable/of'; // can be removed later this is just for Demo purposes now

export class IUser {
  firstName: string;
  lastName: string;
}

@Injectable()
export class AuthService {

  public currentUser: IUser = null;
  public authenticating: boolean = false;

  constructor(private http: Http) { }
  
  login(username: string, password: string): Observable<any> {

    // Demo purpose only
    this.currentUser = {
      firstName: username,
      lastName: 'DEMO_DEMO'
    };

    console.log(this.currentUser);

    return Observable.of(this.currentUser);

    // login
    /*
    return this.http.post('/api/login', { login: username, password: password })
       .map(res => {
         this.currentUser.firstName = (<any>res).data.account.givenName;
         return res.json();
       });
    */
  }

  getCurrentUser(user = {}): Observable<any> {
    return Observable.of(user);
  }

  authenticate() {
    if (this.authenticating) {
      return;
    } else {
      this.authenticating = true;
    }


    // Fake User above used for now

    // this.http.get('/api/users/current').map(res => res.json()).subscribe(
    //   res => {
    //     this.currentUser = res.data;
    //     this.authenticating = false;
    //     // $location.path("/");
    //   },
    //   err => {
    //     this.authenticating = false;
    //     this.currentUser = null;
    //     // $location.path("/login");
    //   });
  }

  logout(): void {
    this.http.post('/api/logout', {}).subscribe(_ => {
      this.currentUser = null;
      // $location.path("/login");
    });
  }
  isAuthenticated() : boolean {
    return !!this.currentUser;;
  }
}

