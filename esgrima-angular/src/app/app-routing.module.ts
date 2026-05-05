import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { ReservesComponent } from './components/reserves/reserves.component';
import { HistoricComponent } from './components/historic/historic.component';
import { ResultatsComponent } from './components/resultats/resultats.component';
import { EstadistiquesComponent } from './components/estadistiques/estadistiques.component';
import { RankingComponent } from './components/ranking/ranking.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'perfil', component: PerfilComponent, canActivate: [AuthGuard] },
  { path: 'reserves', component: ReservesComponent, canActivate: [AuthGuard] },
  { path: 'historic', component: HistoricComponent, canActivate: [AuthGuard] },
  { path: 'resultats', component: ResultatsComponent, canActivate: [AuthGuard] },
  { path: 'estadistiques', component: EstadistiquesComponent, canActivate: [AuthGuard] },
  { path: 'ranking', component: RankingComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '/login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
