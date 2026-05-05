import { Component, OnInit } from '@angular/core';
import { UsuariService } from '../../services/usuari.service';
import { ResultatService } from '../../services/resultat.service';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html'
})
export class RankingComponent implements OnInit {
  usuaris: any[] = [];
  loading = true;
  error = '';

  constructor(private usuariService: UsuariService) {}

  ngOnInit(): void {
    this.usuariService.getRanking().subscribe({
      next: (u) => {
        this.usuaris = u;
        this.loading = false;
      },
      error: () => {
        this.error = 'No s\'ha pogut carregar el rànquing.';
        this.loading = false;
      }
    });
  }

  nivellLabel(n: string): string {
    const m: any = { BASIC: 'Bàsic', INTERMEDIATE: 'Intermedi', ADVANCED: 'Avançat' };
    return m[n] || n;
  }

  medallaClass(i: number): string {
    if (i === 0) return 'text-warning fw-bold';
    if (i === 1) return 'text-secondary fw-bold';
    if (i === 2) return 'text-danger-emphasis fw-bold';
    return '';
  }
}
