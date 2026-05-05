import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ReservaService {
  private base = `${environment.apiUrl}/api/reserves`;
  private pisteBase = `${environment.apiUrl}/api/pistes`;

  constructor(private http: HttpClient) {}

  getMevesReserves(): Observable<any[]> {
    return this.http.get<any[]>(`${this.base}/meves`);
  }

  crearReserva(data: any): Observable<any> {
    return this.http.post<any>(this.base, data);
  }

  cancellarReserva(id: string): Observable<any> {
    return this.http.put<any>(`${this.base}/${id}/cancellar`, {});
  }

  getDisponibilitat(pistaId: string, inici: string, fi: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.base}/disponibilitat?pistaId=${pistaId}&inici=${inici}&fi=${fi}`);
  }

  getPistesDisponibles(): Observable<any[]> {
    return this.http.get<any[]>(`${this.pisteBase}/disponibles`);
  }

  getUsuaris(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/api/usuaris`);
  }
}
