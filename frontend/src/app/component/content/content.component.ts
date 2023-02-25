import {Component} from '@angular/core';
import {KeycloakService} from 'keycloak-angular';
import {User, UserBackendService} from "../service/user-backend.service";

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {
  users: User[] = []

  constructor(private keycloakService: KeycloakService,
              private backend: UserBackendService) {

  }

  logout() {
    this.keycloakService.logout('https://localhost:4200');
  }

  getUsers() {
    this.backend.getUsers().subscribe(
      response => {
        this.users = response
      },

      error => {
        console.log(error.error)
      })
  }

  onMovieIdChange(event: any) {
    this.getUserById(event.value);
  }

  private getUserById(id: number) {
    this.backend.getUserById(id).subscribe(
      response => {
        this.users = [response]
      },

      error => {
        console.log(error.error)
      })
  }
}
