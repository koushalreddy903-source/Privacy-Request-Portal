import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrivacyRequestService } from '../../services/privacy-request.service';

@Component({
  selector: 'app-request-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './request-form.html',
  styleUrl: './request-form.css'
})
export class RequestFormComponent {

  requestForm!: FormGroup; // renamed for variation
  successMsg = '';
  errorMsg = '';

  constructor(
    private formBuilder: FormBuilder,
    private requestService: PrivacyRequestService
  ) {
    // initialize form AFTER DI
    this.requestForm = this.formBuilder.group({
      customerName: ['', Validators.required],
      emailAddress: ['', [Validators.required, Validators.email]],
      requestCategory: ['ACCESS', Validators.required],
      notes: ['', Validators.required]
    });
  }

  submitRequest(): void {
    this.successMsg = '';
    this.errorMsg = '';

    if (this.requestForm.invalid) {
      this.errorMsg = 'Please complete all required fields.';
      return;
    }

    this.requestService.create(this.requestForm.value).subscribe({
      next: () => {
        this.successMsg = 'Request submitted successfully.';
        this.requestForm.reset({
          requestCategory: 'ACCESS'
        });
      },
      error: () => {
        this.errorMsg = 'Submission failed. Please try again.';
      }
    });
  }
}