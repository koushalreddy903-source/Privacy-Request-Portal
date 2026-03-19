import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivityLog } from '../models/activity-log.model';
import { PrivacyRequest, ProcessingStatus } from '../models/privacy-request.model';

@Injectable({
  providedIn: 'root'
})
export class PrivacyRequestService {
  private readonly apiRoot = 'http://localhost:8080/api/privacy-requests';

  constructor(private http: HttpClient) {}

  submit(payload: PrivacyRequest): Observable<PrivacyRequest> {
    return this.http.post<PrivacyRequest>(this.apiRoot, payload);
  }

  getMine(): Observable<PrivacyRequest[]> {
    return this.http.get<PrivacyRequest[]>(`${this.apiRoot}/mine`);
  }

  getAll(): Observable<PrivacyRequest[]> {
    return this.http.get<PrivacyRequest[]>(`${this.apiRoot}/all`);
  }

  changeStatus(requestId: number, processingStatus: ProcessingStatus): Observable<PrivacyRequest> {
    return this.http.patch<PrivacyRequest>(`${this.apiRoot}/${requestId}/status`, { processingStatus });
  }

  getAuditTrail(): Observable<ActivityLog[]> {
    return this.http.get<ActivityLog[]>(`${this.apiRoot}/audit`);
  }
  create(payload: any) {
  return this.http.post('http://localhost:8080/api/privacy-requests', payload);
}
}