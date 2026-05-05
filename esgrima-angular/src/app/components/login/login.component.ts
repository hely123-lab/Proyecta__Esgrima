import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  email = '';
  password = '';
  error = '';
  loading = false;

  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    this.error = '';
    this.loading = true;
    this.authService.login(this.email, this.password).subscribe({
      next: () => this.router.navigate(['/reserves']),
      error: () => {
        this.error = 'Email o contrasenya incorrectes.';
        this.loading = false;
      }
    });
  }
}
