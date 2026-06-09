import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Auth } from '../../../core/services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  showPassword = false;
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: Auth,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          this.authService.saveToken(response.token);
          // redirigir según rol
          const role = this.authService.getRole();
          if (role === 'ADMIN') this.router.navigate(['/dashboard/admin']);
          else if (role === 'TEACHER') this.router.navigate(['/dashboard/teacher']);
          else if (role === 'STUDENT') this.router.navigate(['/dashboard/student']);
        },
        error: (err) => {
          console.error('Error de login', err);
        }
      });
    }
  }
}