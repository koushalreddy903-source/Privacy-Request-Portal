export type PrivacyRequestType = 'ACCESS' | 'DELETE' | 'CORRECT';
export type ProcessingStatus = 'PENDING' | 'APPROVED' | 'REJECTED' | 'COMPLETED';

export interface PrivacyRequest {
  id?: number;
  customerName: string;
  emailAddress: string;
  requestCategory: PrivacyRequestType;
  notes: string;
  processingStatus?: ProcessingStatus;
  ownerUsername?: string;
  createdOn?: string;
  modifiedOn?: string;
}