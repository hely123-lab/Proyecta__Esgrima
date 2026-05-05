import { Component, OnInit } from '@angular/core';
import { UsuariService } from '../../services/usuari.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html'
})
export class PerfilComponent implements OnInit {
  usuari: any = null;
  form: any = {};
  missatge = '';
  error = '';
  editant = false;
  loading = false;

  constructor(private usuariService: UsuariService) {}

  ngOnInit(): void {
    this.usuariService.getMe().subscribe({
      next: (u) => {
        this.usuari = u;
        this.form = { ...u };
      }
    });
  }

  desar(): void {
    this.error = '';
    this.missatge = '';
    this.loading = true;
    this.usuariService.updatePerfil(this.usuari.id, this.form).subscribe({
      next: (u) => {
        this.usuari = u;
        this.missatge = 'Perfil actualitzat correctament.';
        this.editant = false;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.error?.message || 'Error en actualitzar.';
        this.loading = false;
      }
    });
  }

  cancelar(): void {
    this.form = { ...this.usuari };
    this.editant = false;
    this.error = '';
  }

  nivellLabel(n: string): string {
    const m: any = { BASIC: 'Bàsic', INTERMEDIATE: 'Intermedi', ADVANCED: 'Avançat' };
    return m[n] || n;
  }
}
