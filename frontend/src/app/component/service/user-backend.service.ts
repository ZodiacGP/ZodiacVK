import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

export interface User {
  id: string;
  username: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserBackendService {

  private readonly backendUrl = 'https://localhost:8082/user'

  constructor(private http: HttpClient) { }

  getUsers(): Observable<Array<User>> {
    try {
      return this.http.get<Array<User>>(this.backendUrl);

    } catch (error) {
      console.log(error)
    }
    return new Observable<Array<User>>();
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(this.backendUrl + '/' + id);
  }
}
