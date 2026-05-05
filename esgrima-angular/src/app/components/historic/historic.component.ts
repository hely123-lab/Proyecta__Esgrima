import { Component, OnInit } from '@angular/core';
import { ReservaService } from '../../services/reserva.service';

@Component({
  selector: 'app-historic',
  templateUrl: './historic.component.html'
})
export class HistoricComponent implements OnInit {
  reserves: any[] = [];
  loading = true;
  error = '';
  missatgeCancelar = '';

  constructor(private reservaService: ReservaService) {}

  ngOnInit(): void {
    this.carregarReserves();
  }

  carregarReserves(): void {
    this.loading = true;
    this.reservaService.getMevesReserves().subscribe({
      next: (r) => {
        this.reserves = r;
        this.loading = false;
      },
      error: () => {
        this.error = 'No s\'han pogut carregar les reserves.';
        this.loading = false;
      }
    });
  }

  cancellar(id: string): void {
    if (!confirm('Segur que vols cancel·lar aquesta reserva?')) return;
    this.reservaService.cancellarReserva(id).subscribe({
      next: () => {
        this.missatgeCancelar = 'Reserva cancel·lada.';
        this.carregarReserves();
      },
      error: (err) => {
        this.error = err.error?.message || 'No s\'ha pogut cancel·lar.';
      }
    });
  }

  esCancelable(estat: string): boolean {
    return estat === 'PENDING' || estat === 'CONFIRMED';
  }

  badgeClass(estat: string): string {
    const m: any = {
      PENDING: 'bg-warning text-dark',
      CONFIRMED: 'bg-primary',
      COMPLETED: 'bg-success',
      CANCELLED: 'bg-danger'
    };
    return m[estat] || 'bg-secondary';
  }
}
