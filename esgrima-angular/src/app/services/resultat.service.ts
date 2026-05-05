import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ResultatService {
  private base = `${environment.apiUrl}/api/resultats`;

  constructor(private http: HttpClient) {}

  getMeusResultats(): Observable<any[]> {
    return this.http.get<any[]>(`${this.base}/meus`);
  }

  getMevesEstadistiques(): Observable<any> {
    return this.http.get<any>(`${this.base}/estadistiques/me`);
  }
}
