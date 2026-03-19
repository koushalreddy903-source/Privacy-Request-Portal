export interface ActivityLog {
  id: number;
  eventName: string;
  actor: string;
  linkedRequestId: number;
  eventTime: string;
  detailMessage: string;
}