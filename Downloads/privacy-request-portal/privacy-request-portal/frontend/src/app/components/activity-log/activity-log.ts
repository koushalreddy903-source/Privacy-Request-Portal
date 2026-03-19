import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivityLog } from '../../models/activity-log.model';
import { PrivacyRequestService } from '../../services/privacy-request.service';

@Component({
  selector: 'app-activity-log',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './activity-log.html',
  styleUrl: './activity-log.css'
})
export class ActivityLogComponent implements OnInit {
  entries: ActivityLog[] = [];
  errorMessage = '';

  constructor(private privacyRequestService: PrivacyRequestService) {}

  ngOnInit(): void {
    this.loadEntries();
  }

  loadEntries(): void {
    this.errorMessage = '';

    this.privacyRequestService.getAuditTrail().subscribe({
      next: (data) => {
        this.entries = data;
      },
      error: () => {
        this.errorMessage = 'Unable to retrieve audit activity.';
      }
    });
  }
}