import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  template: `
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" *ngIf="isLoggedIn()">
      <div class="container">
        <a class="navbar-brand" routerLink="/reserves">
          <i class="bi bi-trophy-fill me-2"></i>Club Esgrima
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navMenu">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <a class="nav-link" routerLink="/reserves" routerLinkActive="active">
                <i class="bi bi-calendar-plus me-1"></i>Reserves
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" routerLink="/historic" routerLinkActive="active">
                <i class="bi bi-clock-history me-1"></i>Historial
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" routerLink="/resultats" routerLinkActive="active">
                <i class="bi bi-journals me-1"></i>Resultats
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" routerLink="/estadistiques" routerLinkActive="active">
                <i class="bi bi-bar-chart-fill me-1"></i>Estadístiques
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" routerLink="/ranking" routerLinkActive="active">
                <i class="bi bi-list-ol me-1"></i>Rànquing
              </a>
            </li>
          </ul>
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" routerLink="/perfil" routerLinkActive="active">
                <i class="bi bi-person-circle me-1"></i>{{ user?.nom }}
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" style="cursor:pointer" (click)="logout()">
                <i class="bi bi-box-arrow-right me-1"></i>Sortir
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  `
})
export class NavbarComponent {
  user: any;

  constructor(private authService: AuthService) {
    this.user = this.authService.getCurrentUser();
  }

  isLoggedIn(): boolean {
    this.user = this.authService.getCurrentUser();
    return this.authService.isLoggedIn();
  }

  logout(): void {
    this.authService.logout();
  }
}
