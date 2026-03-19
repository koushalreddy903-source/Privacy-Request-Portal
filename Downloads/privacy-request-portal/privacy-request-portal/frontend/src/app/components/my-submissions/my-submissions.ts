import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrivacyRequest } from '../../models/privacy-request.model';
import { PrivacyRequestService } from '../../services/privacy-request.service';

@Component({
  selector: 'app-my-submissions',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './my-submissions.html',
  styleUrl: './my-submissions.css'
})
export class MySubmissionsComponent implements OnInit {
  requests: PrivacyRequest[] = [];
  errorMessage = '';

  constructor(private privacyRequestService: PrivacyRequestService) {}

  ngOnInit(): void {
    this.loadItems();
  }

  loadItems(): void {
    this.errorMessage = '';

    this.privacyRequestService.getMine().subscribe({
      next: (data) => {
        this.requests = data;
      },
      error: () => {
        this.errorMessage = 'Failed to load your requests.';
      }
    });
  }
}