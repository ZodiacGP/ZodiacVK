import {Component, OnInit} from '@angular/core';
import {KeycloakService} from 'keycloak-angular';
import {User, UserBackendService} from "../service/user-backend.service";

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  users: User[] = []

  constructor(private keycloakService: KeycloakService,
              private backend: UserBackendService) {

  }

  ngOnInit(): void {
    this.getUsers();
  }

  logout() {
    this.keycloakService.logout('https://localhost:4200');
  }

  getUsers() {
    this.backend.getUsers().subscribe({
      next: response => {
        this.users = response
      },
      error: error => {
        console.log(error.error)
      }
    });
  }
}
