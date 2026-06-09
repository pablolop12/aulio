import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

// Interfaces que definen para LoginRequest, LoginResponse y JwtPayload, igual la estructura del backend para el token JWT.
export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  role: string;
}

export interface JwtPayload {
  sub: string; //email
  role: string; //role del usuario
  exp: number; //expiración del token
}

@Injectable({
  providedIn: 'root'
})

export class Auth {

  private apiUrl = 'http://localhost:8080/auth'; //URL de la API de autenticación (Mi backend Java) como variable para usarla varias veces

  constructor(private http: HttpClient) {}

  // Método de Login que hace una petición POST al backend con el email y password, y devuelve un Observable que espera
  // a la respuesta del backend (token y role)
  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, request);
  }

  // Método para guardar el token JWT en el localStorage del navegador.
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // Método que lee el token que hemos guardado en el localStorage.
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Método que usa el método getToken anterior para decodificar el token y extrtaer el role.
  getRole(): string | null {
    const token = this.getToken();
    if (!token) return null;
    const decoded = jwtDecode<JwtPayload>(token);
    return decoded.role;
  }

  // Método que verifica si el usuario está logueado comprobando si ya hay un token válido en el localStorage.
  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }

  // Método de Logout que elimina el token del localStorage para cerrar la sesión del usuario.
  logout(): void {
    localStorage.removeItem('token');
  }
}