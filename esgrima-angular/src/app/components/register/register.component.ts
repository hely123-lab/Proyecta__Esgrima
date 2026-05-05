import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  form = {
    nom: '',
    cognoms: '',
    email: '',
    password: '',
    dataNaixement: '',
    sexe: '',
    nivell: ''
  };
  error = '';
  loading = false;

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    this.error = '';
    this.loading = true;
    this.authService.register(this.form).subscribe({
      next: () => this.router.navigate(['/reserves']),
      error: (err) => {
        this.error = err.error?.message || 'Error en el registre.';
        this.loading = false;
      }
    });
  }
}
