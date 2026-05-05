import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class UsuariService {
  private base = `${environment.apiUrl}/api/usuaris`;

  constructor(private http: HttpClient) {}

  getMe(): Observable<any> {
    return this.http.get<any>(`${this.base}/me`);
  }

  updatePerfil(id: string, data: any): Observable<any> {
    return this.http.put<any>(`${this.base}/${id}`, data);
  }

  getRanking(): Observable<any[]> {
    return this.http.get<any[]>(`${this.base}`);
  }
}
