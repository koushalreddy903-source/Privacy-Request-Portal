import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionService } from '../../services/session.service';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './sign-in.html',
  styleUrl: './sign-in.css'
})
export class SignInComponent {

  form!: FormGroup; // ✅ declare first
  loginError = '';

  constructor(
    private fb: FormBuilder,
    private session: SessionService,
    private router: Router
  ) {
    // ✅ initialize AFTER fb is available
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  submit(): void {
    this.loginError = '';

    const username = this.form.value.username?.trim() ?? '';
    const password = this.form.value.password?.trim() ?? '';

    const validUser = username === 'user' && password === 'user123';
    const validAdmin = username === 'admin' && password === 'admin123';

    if (!validUser && !validAdmin) {
      this.loginError = 'Invalid username or password.';
      return;
    }

    this.session.beginSession(username, password);

    this.router.navigate([username === 'admin' ? '/management' : '/my-submissions']);
  }
}