import { Component, OnInit } from '@angular/core';
import { ResultatService } from '../../services/resultat.service';

@Component({
  selector: 'app-resultats',
  templateUrl: './resultats.component.html'
})
export class ResultatsComponent implements OnInit {
  resultats: any[] = [];
  loading = true;
  error = '';

  constructor(private resultatService: ResultatService) {}

  ngOnInit(): void {
    this.resultatService.getMeusResultats().subscribe({
      next: (r) => {
        this.resultats = r;
        this.loading = false;
      },
      error: () => {
        this.error = 'No s\'han pogut carregar els resultats.';
        this.loading = false;
      }
    });
  }

  esGuanyador(r: any, myId: string): boolean {
    return r.guanyadorId === myId;
  }
}
