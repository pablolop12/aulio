import { Routes } from '@angular/router';
import { Login } from './pages/auth/login/login';
import { Admin } from './pages/dashboard/admin/admin';
import { Teacher } from './pages/dashboard/teacher/teacher';
import { Student } from './pages/dashboard/student/student';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'dashboard/admin', component: Admin },
  { path: 'dashboard/teacher', component: Teacher },
  { path: 'dashboard/student', component: Student },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];