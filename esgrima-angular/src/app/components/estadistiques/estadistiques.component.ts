import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ResultatService } from '../../services/resultat.service';

declare var Chart: any;

@Component({
  selector: 'app-estadistiques',
  templateUrl: './estadistiques.component.html'
})
export class EstadistiquesComponent implements OnInit, AfterViewInit {
  stats: any = null;
  loading = true;
  error = '';
  private chartsReady = false;
  private statsReady = false;

  constructor(private resultatService: ResultatService) {}

  ngOnInit(): void {
    this.resultatService.getMevesEstadistiques().subscribe({
      next: (s) => {
        this.stats = s;
        this.loading = false;
        this.statsReady = true;
        if (this.chartsReady) this.renderCharts();
      },
      error: () => {
        this.error = 'No s\'han pogut carregar les estadístiques.';
        this.loading = false;
      }
    });
  }

  ngAfterViewInit(): void {
    const script = document.createElement('script');
    script.src = 'https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js';
    script.onload = () => {
      this.chartsReady = true;
      if (this.statsReady) this.renderCharts();
    };
    document.head.appendChild(script);
  }

  renderCharts(): void {
    if (!this.stats) return;

    // Gràfic de pastís: victòries / derrotes
    const ctxDoughnut = document.getElementById('chartVD') as HTMLCanvasElement;
    if (ctxDoughnut) {
      new Chart(ctxDoughnut, {
        type: 'doughnut',
        data: {
          labels: ['Victòries', 'Derrotes'],
          datasets: [{
            data: [this.stats.victories, this.stats.derrotes],
            backgroundColor: ['#198754', '#dc3545']
          }]
        },
        options: { responsive: true, plugins: { legend: { position: 'bottom' } } }
      });
    }

    // Gràfic de barres: combats totals simulat per mesos (demo)
    const ctxBar = document.getElementById('chartMesos') as HTMLCanvasElement;
    if (ctxBar) {
      new Chart(ctxBar, {
        type: 'bar',
        data: {
          labels: ['Gen', 'Feb', 'Mar', 'Abr', 'Mai', 'Jun'],
          datasets: [{
            label: 'Combats',
            data: [1, 3, 2, 4, 2, this.stats.totalCombats],
            backgroundColor: '#0d6efd'
          }]
        },
        options: { responsive: true, plugins: { legend: { display: false } } }
      });
    }

    // Gràfic de línies: evolució del rendiment (demo)
    const ctxLine = document.getElementById('chartEvolucio') as HTMLCanvasElement;
    if (ctxLine) {
      new Chart(ctxLine, {
        type: 'line',
        data: {
          labels: ['Gen', 'Feb', 'Mar', 'Abr', 'Mai', 'Jun'],
          datasets: [{
            label: '% Victòries',
            data: [40, 50, 45, 60, 55, this.stats.percentatgeVictories],
            borderColor: '#198754',
            backgroundColor: 'rgba(25,135,84,0.1)',
            fill: true,
            tension: 0.3
          }]
        },
        options: { responsive: true, scales: { y: { min: 0, max: 100 } } }
      });
    }
  }
}
