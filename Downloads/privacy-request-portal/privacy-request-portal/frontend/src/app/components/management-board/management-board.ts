import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrivacyRequest, ProcessingStatus } from '../../models/privacy-request.model';
import { PrivacyRequestService } from '../../services/privacy-request.service';

@Component({
  selector: 'app-management-board',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './management-board.html',
  styleUrl: './management-board.css'
})
export class ManagementBoardComponent implements OnInit {
  requests: PrivacyRequest[] = [];
  message = '';
  errorMessage = '';

  constructor(private privacyRequestService: PrivacyRequestService) {}

  ngOnInit(): void {
    this.reload();
  }

  reload(): void {
    this.privacyRequestService.getAll().subscribe({
      next: (data) => {
        this.requests = data;
      },
      error: () => {
        this.errorMessage = 'Could not load admin request list.';
      }
    });
  }

  update(requestId: number, nextStatus: ProcessingStatus): void {
    this.message = '';
    this.errorMessage = '';

    this.privacyRequestService.changeStatus(requestId, nextStatus).subscribe({
      next: () => {
        this.message = `Request ${requestId} updated to ${nextStatus}.`;
        this.reload();
      },
      error: () => {
        this.errorMessage = 'Status update failed.';
      }
    });
  }
}