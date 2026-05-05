import { Component, OnInit } from '@angular/core';
import { ReservaService } from '../../services/reserva.service';

@Component({
  selector: 'app-reserves',
  templateUrl: './reserves.component.html'
})
export class ReservesComponent implements OnInit {
  pistes: any[] = [];
  usuaris: any[] = [];
  form = {
    pistaId: '',
    dataHoraInici: '',
    dataHoraFi: '',
    tipusArma: '',
    esgrimista2Id: '',
    buscarRivalAutomatic: false
  };
  missatge = '';
  error = '';
  loading = false;

  constructor(private reservaService: ReservaService) {}

  ngOnInit(): void {
    this.reservaService.getPistesDisponibles().subscribe({
      next: (p) => this.pistes = p,
      error: () => {}
    });
    this.reservaService.getUsuaris().subscribe({
      next: (u) => this.usuaris = u,
      error: () => {}
    });
  }

  crearReserva(): void {
    this.error = '';
    this.missatge = '';
    this.loading = true;

    const payload: any = {
      pistaId: this.form.pistaId,
      dataHoraInici: this.form.dataHoraInici,
      dataHoraFi: this.form.dataHoraFi,
      tipusArma: this.form.tipusArma || null,
      buscarRivalAutomatic: this.form.buscarRivalAutomatic
    };
    if (this.form.esgrimista2Id) {
      payload.esgrimista2Id = this.form.esgrimista2Id;
    }

    this.reservaService.crearReserva(payload).subscribe({
      next: () => {
        this.missatge = 'Reserva creada correctament!';
        this.resetForm();
        this.loading = false;
      },
      error: (err) => {
        this.error = err.error?.message || 'Error en crear la reserva.';
        this.loading = false;
      }
    });
  }

  resetForm(): void {
    this.form = {
      pistaId: '',
      dataHoraInici: '',
      dataHoraFi: '',
      tipusArma: '',
      esgrimista2Id: '',
      buscarRivalAutomatic: false
    };
  }
}
